package com.caionastu.userservice.api.application.user.appService;

import com.caionastu.userservice.api.application.user.dto.UserAssemblerDTO;
import com.caionastu.userservice.api.application.user.dto.UserDTO;
import com.caionastu.userservice.api.application.user.dto.UserRequestDTO;
import com.caionastu.userservice.api.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class UserAppService {

    private final UserAssemblerDTO assembler;
    private final UserService service;

    public UserAppService(UserService service) {
        this.assembler = new UserAssemblerDTO();
        this.service = service;
    }

    public Flux<UserDTO> findAll() {
        return service.findAll()
                .flatMap(user -> Mono.just(assembler.toDTO(user)));
    }

    public Mono<UserDTO> findById(String id) {
        return service.findById(id)
                .flatMap(user -> Mono.just(assembler.toDTO(user)));
    }

    public Flux<UserDTO> findByFilter(UserRequestDTO requestDTO) {
        return service.findByFilter(requestDTO)
                .flatMap(user -> Mono.just(assembler.toDTO(user)));
    }

    public Mono<UserDTO> create(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return Mono.empty();
        }

        return service.create(assembler.toEntity(userDTO))
                .flatMap(user -> Mono.just(assembler.toDTO(user)))
                .doOnError(error -> log.error(error.getMessage()));
    }

    public Mono<UserDTO> update(String id, UserDTO userDTO) {
        userDTO.setId(id);
        return service.update(assembler.toEntity(userDTO))
                .flatMap(user -> Mono.just(assembler.toDTO(user)))
                .doOnError(error -> log.error(error.getMessage()));
    }

    public Mono<Void> delete(String id) {
        return service.delete(id);
    }

}
