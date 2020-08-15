package com.caionastu.imageservice.infrastructure.image.repository;

import com.caionastu.imageservice.domain.vo.Image;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IImageMongoRepository extends ReactiveMongoRepository<Image, String> {
}
