package com.habeebcycle.microservices.api.core.recommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
