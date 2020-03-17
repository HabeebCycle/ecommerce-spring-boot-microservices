package com.habeebcycle.microservices.api.core.product;

import com.habeebcycle.microservices.api.core.ProductUtil;

public class Product {
    private int productId;
    private String name;
    private int weight;
    private double price;
    private String serviceAddress;

    public Product() {
        productId = 0;
        name = null;
        weight = 0;
        price = 0.00;
        serviceAddress = null;
    }

    public Product(int productId, String name, int weight, double price, String serviceAddress) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.serviceAddress = serviceAddress;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getPrice() {
        return ProductUtil.roundToDecimalPlaces(price, 2);
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }
}
