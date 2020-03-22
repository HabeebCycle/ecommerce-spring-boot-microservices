package com.habeebcycle.microservices.services.productservice.repository;

import com.habeebcycle.microservices.services.productservice.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

    Mono<ProductEntity> findByProductId(int productId);
}
