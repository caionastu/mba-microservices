package com.caionastu.userservice.api.infrastructure.user.repository;

import com.caionastu.userservice.api.domain.user.repository.IUserRepository;
import com.caionastu.userservice.api.domain.user.vo.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository implements IUserRepository {

    private final IUserMongoRepository mongoRepository;

    public UserRepository(IUserMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public Mono<User> create(User user) {
        return mongoRepository.save(user);
    }
}
