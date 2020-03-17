package com.habeebcycle.microservices.api.composite.product;

import com.habeebcycle.microservices.api.core.ProductUtil;

import java.util.List;

public class ProductAggregate {
    private int productId;
    private String name;
    private double price;
    private int weight;
    private List<RecommendationSummary> recommendations;
    private List<ReviewSummary> reviews;
    private ServiceAddresses serviceAddresses;

    public ProductAggregate() {
    }

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

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setRecommendations(List<RecommendationSummary> recommendations) {
        this.recommendations = recommendations;
    }

    public void setReviews(List<ReviewSummary> reviews) {
        this.reviews = reviews;
    }

    public void setServiceAddresses(ServiceAddresses serviceAddresses) {
        this.serviceAddresses = serviceAddresses;
    }
}
