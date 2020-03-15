package com.habeebcycle.microservices.api.core.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {
    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     *
     * @param productId the product id to get its reviews
     * @return the list of reviews for the product
     */
    @GetMapping(
            value    = "/review",
            produces = "application/json")
    List<Review> getReviews(@RequestParam(value = "productId") int productId);
}
