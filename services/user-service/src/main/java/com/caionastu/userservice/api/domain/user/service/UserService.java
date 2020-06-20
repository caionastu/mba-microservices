package com.caionastu.userservice.api.domain.user.service;

import com.caionastu.userservice.api.domain.user.vo.User;
import com.caionastu.userservice.api.infrastructure.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<User> create(User user) {
        return repository.create(user);
    }
}
