package com.caionastu.userservice.api.infrastructure.user.repository;

import com.caionastu.userservice.api.domain.user.vo.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IUserMongoRepository extends ReactiveMongoRepository<User, String> {

    @Query("{ 'address.location': ?0}")
    Flux<User> findByLocationNear(Point point, Distance distance);
}
