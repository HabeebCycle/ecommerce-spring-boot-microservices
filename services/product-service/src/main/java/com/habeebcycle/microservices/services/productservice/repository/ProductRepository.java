package com.habeebcycle.microservices.services.productservice.repository;

import com.habeebcycle.microservices.services.productservice.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    Optional<ProductEntity> findByProductId(int productId);
}
