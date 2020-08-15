package com.caionastu.imageservice.application.image.appService;

import com.caionastu.imageservice.application.image.dto.ImageAssemblerDTO;
import com.caionastu.imageservice.application.image.dto.ImageDTO;
import com.caionastu.imageservice.application.image.dto.ImageRequestDTO;
import com.caionastu.imageservice.domain.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ImageAppService {

    private final ImageService service;
    private final ImageAssemblerDTO assembler;

    public ImageAppService(ImageService service, ImageAssemblerDTO assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    public Flux<ImageDTO> findByFilter(ImageRequestDTO requestDTO) {
        return service.findByFilter(requestDTO)
                .flatMap(image -> Mono.just(assembler.toDTO(image)));
    }

    public Mono<ImageDTO> findById(String id) {
        return service.findById(id)
                .flatMap(image -> Mono.just(assembler.toDTO(image)));
    }

    public Mono<ImageDTO> create(ImageDTO imageDTO) {
        return service.create(assembler.toEntity(imageDTO))
                .flatMap(image -> Mono.just(assembler.toDTO(image)))
                .doOnError(error -> log.error(error.getMessage()));
    }

    public Mono<ImageDTO> update(String id, ImageDTO imageDTO) {
        imageDTO.setId(id);
        return service.update(assembler.toEntity(imageDTO))
                .flatMap(image -> Mono.just(assembler.toDTO(image)))
                .doOnError(error -> log.error(error.getMessage()));
    }

    public Mono<Void> delete(String id) {
        return service.delete(id);
    }
}
