package com.github.iamhi.hizone.blog.data;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends ReactiveMongoRepository<BlogEntity, String> {
}
