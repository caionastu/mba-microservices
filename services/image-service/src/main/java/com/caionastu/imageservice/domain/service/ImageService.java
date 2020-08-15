package com.caionastu.imageservice.domain.service;

import com.caionastu.core.error.ErrorBlock;
import com.caionastu.core.excpetions.BusinessException;
import com.caionastu.core.excpetions.RecordNotFoundException;
import com.caionastu.imageservice.application.ErrorKeys;
import com.caionastu.imageservice.application.image.dto.ImageRequestDTO;
import com.caionastu.imageservice.domain.vo.Image;
import com.caionastu.imageservice.infrastructure.image.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ImageService {

    private final ImageRepository repository;


    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Flux<Image> findByFilter(ImageRequestDTO requestDTO) {
        return repository.findByFilter(requestDTO);
    }

    public Mono<Image> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Image> create(Image image) {
        return repository.findById(image.getId())
                .switchIfEmpty(repository.create(image))
                .flatMap(imageFound -> {
                    ErrorBlock errorBlock = ErrorBlock.builder()
                            .header(ErrorKeys.Common.FAIL_TO_CREATE_ENTITY)
                            .code(HttpStatus.BAD_REQUEST)
                            .build();

                    errorBlock.addErrorMessage(ErrorKeys.Image.IMAGE_ALREADY_EXIST);
                    return Mono.error(new BusinessException(errorBlock));
                });
    }

    public Mono<Image> update(Image image) {
        return repository.findById(image.getId())
                .switchIfEmpty(Mono.error(new RecordNotFoundException(ErrorKeys.Common.ENTITY_NOT_FOUND)))
                .flatMap(oldImage -> {
                    oldImage = Image.builder()
                            .id(oldImage.getId())
                            .userId(oldImage.getUserId())
                            .image(image.getImage())
                            .imageType(oldImage.getImageType())
                            .build();

                    return repository.update(oldImage);
                });

    }

    public Mono<Void> delete(String id) {
        return repository.delete(id);
    }
}
