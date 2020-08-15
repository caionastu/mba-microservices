package com.caionastu.imageservice.domain.repository;

import com.caionastu.core.interfaces.ICrudRepository;
import com.caionastu.imageservice.application.image.dto.ImageRequestDTO;
import com.caionastu.imageservice.domain.vo.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IImageRepository extends ICrudRepository<Image> {
    Flux<Image> findByFilter(ImageRequestDTO requestDTO);
}
