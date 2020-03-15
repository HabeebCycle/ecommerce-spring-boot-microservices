package com.habeebcycle.microservices.api.core.product;

import com.habeebcycle.microservices.api.core.ProductUtil;

public class Product {
    private final int productId;
    private final String name;
    private final int weight;
    private final double price;
    private final String serviceAddress;

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
}
