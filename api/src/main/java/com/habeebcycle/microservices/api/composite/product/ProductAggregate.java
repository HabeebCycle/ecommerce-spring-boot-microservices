package com.habeebcycle.microservices.api.composite.product;

import com.habeebcycle.microservices.api.core.ProductUtil;

import java.util.List;

public class ProductAggregate {
    private final int productId;
    private final String name;
    private final double price;
    private final int weight;
    private final List<RecommendationSummary> recommendations;
    private final List<ReviewSummary> reviews;
    private final ServiceAddresses serviceAddresses;

    public ProductAggregate(
            int productId,
            String name,
            int weight,
            double price,
            List<RecommendationSummary> recommendations,
            List<ReviewSummary> reviews,
            ServiceAddresses serviceAddresses) {

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.recommendations = recommendations;
        this.reviews = reviews;
        this.serviceAddresses = serviceAddresses;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return ProductUtil.roundToDecimalPlaces(price, 2);
    }

    public int getWeight() {
        return weight;
    }

    public List<RecommendationSummary> getRecommendations() {
        return recommendations;
    }

    public List<ReviewSummary> getReviews() {
        return reviews;
    }

    public ServiceAddresses getServiceAddresses() {
        return serviceAddresses;
    }
}
