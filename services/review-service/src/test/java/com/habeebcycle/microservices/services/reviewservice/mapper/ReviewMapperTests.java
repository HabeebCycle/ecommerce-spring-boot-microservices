package com.habeebcycle.microservices.services.reviewservice.mapper;

import com.habeebcycle.microservices.api.core.review.Review;
import com.habeebcycle.microservices.services.reviewservice.entity.ReviewEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReviewMapperTests {
    private ReviewMapper reviewMapper = new ReviewMapper();

    @Test
    void entityToApi() {
        assertNotNull(reviewMapper);

        ReviewEntity entity = new ReviewEntity(1, 2, "a", "S", "C");

        Review api = reviewMapper.entityToApi(entity);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getReviewId(), entity.getReviewId());
        assertEquals(api.getAuthor(), entity.getAuthor());
        assertEquals(api.getSubject(), entity.getSubject());
        assertEquals(api.getContent(), entity.getContent());
    }

    @Test
    void apiToEntity() {
        assertNotNull(reviewMapper);

        Review api = new Review(1, 2, "a", "S", "C", "adr");

        ReviewEntity entity = reviewMapper.apiToEntity(api);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getReviewId(), entity.getReviewId());
        assertEquals(api.getAuthor(), entity.getAuthor());
        assertEquals(api.getSubject(), entity.getSubject());
        assertEquals(api.getContent(), entity.getContent());
    }

    @Test
    void entityListToApiList() {
        assertNotNull(reviewMapper);

        ReviewEntity entity = new ReviewEntity(1, 2, "a", "S", "C");
        List<ReviewEntity> entityList = Collections.singletonList(entity);

        List<Review> apiList = reviewMapper.entityListToApiList(entityList);
        assertEquals(apiList.size(), entityList.size());

        Review api = apiList.get(0);

        assertEquals(entity.getProductId(), api.getProductId());
        assertEquals(entity.getReviewId(), api.getReviewId());
        assertEquals(entity.getAuthor(), api.getAuthor());
        assertEquals(entity.getSubject(), api.getSubject());
        assertEquals(entity.getContent(), api.getContent());
        assertNull(api.getServiceAddress());
    }

    @Test
    void apiListToEntityList() {
        assertNotNull(reviewMapper);

        Review api = new Review(1, 2, "a", "S", "C", "adr");
        List<Review> apiList = Collections.singletonList(api);

        List<ReviewEntity> entityList = reviewMapper.apiListToEntityList(apiList);
        assertEquals(apiList.size(), entityList.size());

        ReviewEntity entity = entityList.get(0);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getReviewId(), entity.getReviewId());
        assertEquals(api.getAuthor(), entity.getAuthor());
        assertEquals(api.getSubject(), entity.getSubject());
        assertEquals(api.getContent(), entity.getContent());
    }
}
