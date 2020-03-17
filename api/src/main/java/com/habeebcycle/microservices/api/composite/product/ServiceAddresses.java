package com.habeebcycle.microservices.api.composite.product;

public class ServiceAddresses {
    private String compositeAddress;
    private String productAddress;
    private String reviewAddress;
    private String recommendationAddress;



    public ServiceAddresses() {
        compositeAddress = null;
        productAddress = null;
        reviewAddress = null;
        recommendationAddress = null;
    }

    public ServiceAddresses(String compositeAddress, String productAddress, String reviewAddress, String recommendationAddress) {
        this.compositeAddress = compositeAddress;
        this.productAddress = productAddress;
        this.reviewAddress = reviewAddress;
        this.recommendationAddress = recommendationAddress;
    }

    public String getCompositeAddress() {
        return compositeAddress;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public String getReviewAddress() {
        return reviewAddress;
    }

    public String getRecommendationAddress() {
        return recommendationAddress;
    }

    public void setCompositeAddress(String compositeAddress) {
        this.compositeAddress = compositeAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public void setReviewAddress(String reviewAddress) {
        this.reviewAddress = reviewAddress;
    }

    public void setRecommendationAddress(String recommendationAddress) {
        this.recommendationAddress = recommendationAddress;
    }
}
