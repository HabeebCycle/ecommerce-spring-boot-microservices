package com.habeebcycle.microservices.api.core.review;

import org.springframework.web.bind.annotation.*;

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

    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/review \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"reviewId":456,"author":"me","subject":"yada, yada, yada","content":"yada, yada, yada"}'
     *
     * @param body the valid Review to be created
     * @return the Review created
     */
    @PostMapping(
            value    = "/review",
            consumes = "application/json",
            produces = "application/json")
    Review createReview(@RequestBody Review body);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/review?productId=1
     *
     * @param productId the product id of whose reviews will be deleted
     */
    @DeleteMapping(value = "/review")
    void deleteReviews(@RequestParam(value = "productId")  int productId);

}
