package com.caionastu.core.interfaces;

import reactor.core.publisher.Mono;

public interface ICrudRepository<Entity> {

    Mono<Entity> create(Entity entity);

    Mono<Entity> update(Entity entity);

    Mono<Entity> findById(String id);

    Mono<Void> delete(String id);

}
