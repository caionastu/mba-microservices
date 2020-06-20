package com.caionastu.userservice.api.domain.user.validator;

import com.caionastu.core.error.ErrorBlock;
import com.caionastu.userservice.api.application.error.ErrorKeys;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.caionastu.userservice.api.infrastructure.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserHasSameUsernameValidator implements IUserPersistValidator {

    private final UserRepository repository;

    public UserHasSameUsernameValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ErrorBlock> validate(User user) {
        return repository.existByUsernameWithDifferentId(user.getUsername(), user.getId())
                .flatMap(exist -> {
                    ErrorBlock errorBlock = ErrorBlock.builder().build();
                    if (exist) {
                        String errorKey = ErrorKeys.User.USERNAME_ALREADY_EXISTS;

                        log.error(errorKey);

                        errorBlock.addErrorMessage(errorKey);
                    }

                    return Mono.just(errorBlock);
                });
    }
}
