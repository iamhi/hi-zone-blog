package com.github.iamhi.hizone.blog.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BlogRepository extends ReactiveMongoRepository<BlogEntity, String> {

    Flux<BlogEntity> findByUuidNotNull(Pageable pageable);
}
