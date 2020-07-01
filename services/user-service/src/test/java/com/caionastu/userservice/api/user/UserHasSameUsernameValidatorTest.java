package com.caionastu.userservice.api.user;

import com.caionastu.core.error.ErrorBlock;
import com.caionastu.userservice.api.domain.user.validator.UserHasSameUsernameValidator;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.caionastu.userservice.api.infrastructure.user.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class UserHasSameUsernameValidatorTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserHasSameUsernameValidator validator;

    @Test
    public void usernameAlreadyInUse() {
        User user = User.builder()
                .id(ObjectId.get().toString())
                .username("usernameInUse")
                .build();

        Mockito.when(repository.existByUsernameWithDifferentId(user.getUsername(), user.getId())).thenReturn(Mono.just(true));

        StepVerifier.create(validator.validate(user))
                .expectNextMatches(ErrorBlock::hasErrors)
                .expectComplete()
                .verify();
    }

    @Test
    public void usernameNotInUse() {
        User user = User.builder()
                .id(ObjectId.get().toString())
                .username("NotInUse")
                .build();

        Mockito.when(repository.existByUsernameWithDifferentId(user.getUsername(), user.getId())).thenReturn(Mono.just(false));

        StepVerifier.create(validator.validate(user))
                .expectNextMatches(errorBlock -> !errorBlock.hasErrors())
                .expectComplete()
                .verify();
    }
}
