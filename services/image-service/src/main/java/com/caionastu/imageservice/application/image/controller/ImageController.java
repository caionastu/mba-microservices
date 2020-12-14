package com.caionastu.imageservice.application.image.controller;

import com.caionastu.imageservice.application.image.appService.ImageAppService;
import com.caionastu.imageservice.application.image.dto.ImageDTO;
import com.caionastu.imageservice.application.image.dto.ImageRequestDTO;
import com.caionastu.imageservice.application.image.dto.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/images")
public class ImageController {

    private final ImageAppService appService;

    public ImageController(ImageAppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public Flux<ImageDTO> findByFilter(ImageRequestDTO imageRequestDTO) {
        return appService.findByFilter(imageRequestDTO);
    }

    @GetMapping(path = "/{id}")
    public Mono<ImageDTO> findById(@PathVariable String id) {
        return appService.findById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<ImageDTO>> create(@RequestBody @Valid ImageDTO imageDTO) {
        return appService.create(imageDTO)
                .flatMap(imageAdded -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(imageAdded)));
    }

    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<ImageDTO>> update(@PathVariable String id, @RequestBody @Valid ImageDTO imageDTO) {
        return appService.update(id, imageDTO)
                .flatMap(imageUpdated -> Mono.just(ResponseEntity.ok().body(imageUpdated)));
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return appService.delete(id)
                .flatMap(aVoid -> Mono.just(ResponseEntity.ok().body(aVoid)));
    }

}
