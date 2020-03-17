package com.habeebcycle.microservices.services.recommendationservice.repository;

import com.habeebcycle.microservices.services.recommendationservice.entity.RecommendationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends MongoRepository<RecommendationEntity, String> {
    List<RecommendationEntity> findByProductId(int productId);
}
