package com.caionastu.userservice.api.infrastructure.user.repository;

import com.caionastu.userservice.api.domain.user.repository.IUserRepository;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.google.common.base.Strings;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserRepository implements IUserRepository {

    private final IUserMongoRepository repository;

    private final ReactiveMongoTemplate mongoTemplate;

    public UserRepository(IUserMongoRepository repository, ReactiveMongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
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

    public Flux<User> findAll() {
        return repository.findAll();
    }
}
