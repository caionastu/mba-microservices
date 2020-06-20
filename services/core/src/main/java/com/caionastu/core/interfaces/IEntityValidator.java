package com.caionastu.core.interfaces;

import com.caionastu.core.error.ErrorBlock;
import reactor.core.publisher.Mono;

public interface IEntityValidator<Entity> {

    Mono<ErrorBlock> validate(Entity entity);
}
