package com.habeebcycle.microservices.api.core.product;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface ProductService {
    /**
     * Sample usage: curl $HOST:$PORT/product/1
     *
     * @param productId the id of product to look for
     * @return the product, if found, else null
     */
    //Using HTTP GET Method protocol
    @GetMapping(
            value    = "/product/{productId}",
            produces = "application/json")
    Mono<Product> getProduct(@PathVariable int productId);

    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/product \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"name":"product 123","weight":123,"price":123.0}'
     *
     * @param body the valid Product to be created
     * @return the product created if valid
     */
    //Using Event-driven messaging system
    Product createProduct(@RequestBody Product body);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/product/1
     *
     * @param productId the id of the product to be deleted
     */
    //Using event-driven messaging system
    void deleteProduct(@PathVariable int productId);
}
