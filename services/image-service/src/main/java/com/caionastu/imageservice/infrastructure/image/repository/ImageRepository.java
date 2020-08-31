package com.caionastu.imageservice.infrastructure.image.repository;

import com.caionastu.imageservice.application.image.dto.ImageRequestDTO;
import com.caionastu.imageservice.domain.repository.IImageRepository;
import com.caionastu.imageservice.domain.vo.Image;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class ImageRepository implements IImageRepository {

    private final IImageMongoRepository mongoRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public ImageRepository(IImageMongoRepository mongoRepository, ReactiveMongoTemplate mongoTemplate) {
        this.mongoRepository = mongoRepository;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Mono<Image> create(Image image) {
        return mongoRepository.save(image);
    }

    @Override
    public Mono<Image> update(Image image) {
        return mongoRepository.save(image);
    }

    @Override
    public Mono<Image> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return mongoRepository.deleteById(id);
    }

    @Override
    public Flux<Image> findByFilter(ImageRequestDTO requestDTO) {
        boolean hasImageType = Objects.nonNull(requestDTO.getImageType());

        Query query = new Query();
        query.addCriteria(where("userId").is(requestDTO.getUserId()));

        if (hasImageType) {
            query.addCriteria((where("imageType")).is(requestDTO.getImageType().getType()));
        }

        return mongoTemplate.find(query, Image.class);
    }
}
