package com.habeebcycle.microservices.services.recommendationservice.repository;

import com.habeebcycle.microservices.services.recommendationservice.entity.RecommendationEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RecommendationRepository extends ReactiveMongoRepository<RecommendationEntity, String> {
    Flux<RecommendationEntity> findByProductId(int productId);
}
