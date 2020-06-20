package com.caionastu.userservice.api.domain.user.repository;

import com.caionastu.core.interfaces.ICrudRepository;
import com.caionastu.userservice.api.domain.user.vo.User;
import reactor.core.publisher.Mono;

public interface IUserRepository extends ICrudRepository<User> {

    Mono<Boolean> existByUsernameWithDifferentId(String username, String id);

}
