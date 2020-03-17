package com.habeebcycle.microservices.services.productservice;

import com.habeebcycle.microservices.api.core.product.Product;
import com.habeebcycle.microservices.services.productservice.entity.ProductEntity;
import com.habeebcycle.microservices.services.productservice.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapperTests {

    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void mapperTests() {

        assertNotNull(mapper);

        Product api = new Product(1, "n", 1, 1.0, "tests");

        ProductEntity entity = mapper.apiToEntity(api);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getName(), entity.getName());
        assertEquals(api.getWeight(), entity.getWeight());
        assertEquals(api.getPrice(), entity.getPrice());

        Product api2 = mapper.entityToApi(entity);

        assertEquals(api.getProductId(), api2.getProductId());
        assertEquals(api.getProductId(), api2.getProductId());
        assertEquals(api.getName(),      api2.getName());
        assertEquals(api.getWeight(),    api2.getWeight());
        assertEquals(api.getPrice(), api2.getPrice());
        assertNull(api2.getServiceAddress());
    }
}
