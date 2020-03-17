package com.habeebcycle.microservices.api.core.recommendation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RecommendationService {
    /**
     * Sample usage: curl $HOST:$PORT/recommendation?productId=1
     *
     * @param productId the id of the product to the its recommendations
     * @return the list of recommendations
     */
    @GetMapping(
            value    = "/recommendation",
            produces = "application/json")
    List<Recommendation> getRecommendations(@RequestParam(value = "productId") int productId);

    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/recommendation \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"recommendationId":456,"author":"me","rate":5,"content":"blah, blah, blah"}'
     *
     * @param body the valid recommendation to be created
     * @return the recommendation created
     */
    @PostMapping(
            value    = "/recommendation",
            consumes = "application/json",
            produces = "application/json")
    Recommendation createRecommendation(@RequestBody Recommendation body);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/recommendation?productId=1
     *
     * @param productId the id of the product whose recommendation is to be deleted
     */
    @DeleteMapping(value = "/recommendation")
    void deleteRecommendations(@RequestParam(value = "productId")  int productId);
}
