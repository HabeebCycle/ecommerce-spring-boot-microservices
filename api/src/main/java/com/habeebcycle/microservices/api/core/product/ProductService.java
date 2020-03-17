package com.habeebcycle.microservices.api.core.product;

import org.springframework.web.bind.annotation.*;

public interface ProductService {
    /**
     * Sample usage: curl $HOST:$PORT/product/1
     *
     * @param productId the id of product to look for
     * @return the product, if found, else null
     */
    @GetMapping(
            value    = "/product/{productId}",
            produces = "application/json")
    Product getProduct(@PathVariable int productId);

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
    @PostMapping(
            value    = "/product",
            consumes = "application/json",
            produces = "application/json")
    Product createProduct(@RequestBody Product body);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/product/1
     *
     * @param productId the id of the product to be deleted
     */
    @DeleteMapping(value = "/product/{productId}")
    void deleteProduct(@PathVariable int productId);
}
