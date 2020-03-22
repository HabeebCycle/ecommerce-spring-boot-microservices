package com.habeebcycle.microservices.services.productservice.services;

import com.habeebcycle.microservices.api.core.product.Product;
import com.habeebcycle.microservices.api.core.product.ProductService;
import com.habeebcycle.microservices.services.productservice.entity.ProductEntity;
import com.habeebcycle.microservices.services.productservice.mapper.ProductMapper;
import com.habeebcycle.microservices.services.productservice.repository.ProductRepository;
import com.habeebcycle.microservices.util.exceptions.InvalidInputException;
import com.habeebcycle.microservices.util.exceptions.NotFoundException;
import com.habeebcycle.microservices.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.error;

@RestController
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ServiceUtil serviceUtil;

    private final ProductRepository repository;

    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper, ServiceUtil serviceUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product createProduct(Product body) {
        if (body.getProductId() < 1) throw new InvalidInputException("Invalid productId: " + body.getProductId());

        ProductEntity entity = mapper.apiToEntity(body);
        Mono<Product> newEntity = repository.save(entity)
                .log()
                .onErrorMap(
                        DuplicateKeyException.class,
                        ex -> new InvalidInputException("Duplicate key, Product Id: " + body.getProductId()))
                .map(mapper::entityToApi);

        return newEntity.block();
    }

    @Override
    public Mono<Product> getProduct(int productId) {

        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        return repository.findByProductId(productId)
                .switchIfEmpty(error(new NotFoundException("No product found for productId: " + productId)))
                .log()
                .map(mapper::entityToApi)
                .map(e -> {e.setServiceAddress(serviceUtil.getServiceAddress()); return e;});
    }

    @Override
    public void deleteProduct(int productId) {
        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        LOG.debug("deleteProduct: tries to delete an entity with productId: {}", productId);
        repository.findByProductId(productId).log().map(repository::delete).flatMap(e -> e).block();
    }
}
