package com.caionastu.userservice.api.domain.user.service;

import com.caionastu.core.excpetions.BusinessException;
import com.caionastu.core.excpetions.RecordNotFoundException;
import com.caionastu.userservice.api.application.error.ErrorKeys;
import com.caionastu.userservice.api.application.user.dto.UserRequestDTO;
import com.caionastu.userservice.api.domain.user.validator.IUserPersistValidator;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.caionastu.userservice.api.infrastructure.user.repository.UserRepository;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository repository;
    private final Flux<IUserPersistValidator> persistValidators;

    public UserService(UserRepository repository, ListableBeanFactory listableBeanFactory) {
        this.repository = repository;
        persistValidators = Flux.fromIterable(listableBeanFactory.getBeansOfType(IUserPersistValidator.class).values());
    }

    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    public Flux<User> findByFilter(UserRequestDTO requestDTO) {
        return repository.findByFilter(requestDTO);
    }

    public Mono<User> create(User user) {
        // TODO Tratar registros que vierem com ID, caso já exista no banco

        return persistValidators.flatMap(validator -> validator.validate(user))
                .reduce((blockTotalize, currentBlock) -> {
                    blockTotalize.addErrorMessages(currentBlock.getErrorMessages());
                    return blockTotalize;
                })
                .flatMap(errorBlock -> {
                    if (errorBlock.hasErrors()) {
                        errorBlock.setCode(HttpStatus.BAD_REQUEST);
                        errorBlock.setHeader(ErrorKeys.Common.FAIL_TO_CREATE_ENTITY);
                        return Mono.error(new BusinessException(errorBlock));
                    }

                    return repository.create(user);
                });
    }

    public Mono<User> update(User user) {

        return persistValidators.flatMap(validator -> validator.validate(user))
                .reduce((blockTotalize, currentBlock) -> {
                    blockTotalize.addErrorMessages(currentBlock.getErrorMessages());
                    return blockTotalize;
                })
                .flatMap(errorBlock -> {
                    if (errorBlock.hasErrors()) {
                        errorBlock.setCode(HttpStatus.BAD_REQUEST);
                        errorBlock.setHeader(ErrorKeys.Common.FAIL_TO_UPDATE_ENTITY);
                        return Mono.error(new BusinessException(errorBlock));
                    }

                    return findById(user.getId());
                })
                .switchIfEmpty(Mono.error(new RecordNotFoundException(ErrorKeys.Common.ENTITY_NOT_FOUND)))
                .flatMap(oldUser -> {
                    oldUser = User.builder()
                            .id(oldUser.getId())
                            .name(user.getName())
                            .lastName(user.getLastName())
                            .age(user.getAge())
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .address(user.getAddress())
                            .email(user.getEmail())
                            .userType(user.getUserType())
                            .build();

                    return repository.update(oldUser);
                });
    }

    public Mono<Void> delete(String id) {
        return repository.delete(id);
    }
}
