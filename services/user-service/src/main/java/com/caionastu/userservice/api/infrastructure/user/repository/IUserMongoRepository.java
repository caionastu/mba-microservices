package com.caionastu.userservice.api.infrastructure.user.repository;

import com.caionastu.userservice.api.domain.user.vo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IUserMongoRepository extends ReactiveMongoRepository<User, String> {
}
