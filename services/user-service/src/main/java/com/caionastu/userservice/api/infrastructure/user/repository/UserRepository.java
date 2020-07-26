package com.caionastu.userservice.api.infrastructure.user.repository;

import com.caionastu.userservice.api.application.coordinate.dto.LocationRequestDTO;
import com.caionastu.userservice.api.application.user.dto.UserRequestDTO;
import com.caionastu.userservice.api.domain.address.Address;
import com.caionastu.userservice.api.domain.user.repository.IUserRepository;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.google.common.base.Strings;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserRepository implements IUserRepository {

    private final IUserMongoRepository repository;

    private final ReactiveMongoTemplate mongoTemplate;

    public UserRepository(IUserMongoRepository repository, ReactiveMongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;

        mongoTemplate.indexOps(Address.class).ensureIndex(new GeospatialIndex("location"));
    }

    @Override
    public Mono<User> create(User user) {
        return repository.save(user);
    }

    @Override
    public Mono<User> update(User user) {
        return repository.save(user);
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existByUsernameWithDifferentId(String username, String id) {
        ObjectId objectId = Strings.isNullOrEmpty(id) ? new ObjectId() : new ObjectId(id);

        Query query = new Query()
                .addCriteria(where("username").is(username))
                .addCriteria(where("_id").ne(objectId));

        return mongoTemplate.exists(query, User.class);
    }

    @Override
    public Flux<User> findByFilter(UserRequestDTO requestDTO) {
        boolean hasName = !Strings.isNullOrEmpty(requestDTO.getName());
        boolean hasUserType = !Strings.isNullOrEmpty(requestDTO.getUserType());
        boolean hasLocation = Objects.nonNull(requestDTO.getLocation());

        Query query = new Query();
        query.with(PageRequest.of(requestDTO.getPage(), requestDTO.getSize()));

        if (hasName) {
            query.addCriteria(where("name").is(requestDTO.getName()));
        }

        if (hasUserType) {
            query.addCriteria(where("userType").is(requestDTO.getUserType()));
        }

        if (hasLocation) {
            return findByLocation(requestDTO.getLocation(), query);
        }

        query.with(Sort.by(Sort.Direction.ASC, "username"));
        return mongoTemplate.find(query, User.class);
    }

    private Flux<User> findByLocation(LocationRequestDTO locationRequestDTO, Query query) {
        if (locationRequestDTO.getLat() == 0 || locationRequestDTO.getLng() == 0) {
            return Flux.empty();
        }

        Point point = new Point(locationRequestDTO.getLng(), locationRequestDTO.getLat());

        BigDecimal distanceValue = Objects.isNull(locationRequestDTO.getDistance()) ? BigDecimal.valueOf(50) : locationRequestDTO.getDistance();
        Distance distance = new Distance(distanceValue.doubleValue(), Metrics.KILOMETERS);

        NearQuery nearQuery = NearQuery.near(point);
        nearQuery.maxDistance(distance);
        nearQuery.spherical(true);

        query.with(Sort.by(Sort.Direction.ASC, "distance.value"));
        nearQuery.query(query);

        return mongoTemplate.geoNear(nearQuery, User.class)
                .flatMap(userGeoResult -> {
                    User user = userGeoResult.getContent();
                    user.getAddress().setDistance(userGeoResult.getDistance());
                    return Mono.just(user);
                });
    }


}
