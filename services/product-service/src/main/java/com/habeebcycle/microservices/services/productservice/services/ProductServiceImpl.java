package com.habeebcycle.microservices.services.productservice.services;

import com.habeebcycle.microservices.api.core.product.Product;
import com.habeebcycle.microservices.api.core.product.ProductService;
import com.habeebcycle.microservices.util.exceptions.InternalServerException;
import com.habeebcycle.microservices.util.exceptions.InvalidInputException;
import com.habeebcycle.microservices.util.exceptions.NotFoundException;
import com.habeebcycle.microservices.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(int productId) {
        LOG.debug("/product return the found product for productId={}", productId);

        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 13) throw new NotFoundException("No product found for productId: " + productId);

        if (productId == 101) throw new InternalServerException("Internal Server Error 500 for productId: " + productId);

        return new Product(productId, "name-" + productId, 123,12.50, serviceUtil.getServiceAddress());
    }
}
