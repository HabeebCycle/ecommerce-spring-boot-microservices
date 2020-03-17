package com.habeebcycle.microservices.services.recommendationservice.mapper;

import com.habeebcycle.microservices.api.core.recommendation.Recommendation;
import com.habeebcycle.microservices.services.recommendationservice.entity.RecommendationEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationMapperTest {

    private RecommendationMapper recommendationMapper = new RecommendationMapper();

    @Test
    void entityToApi() {
        assertNotNull(recommendationMapper);

        RecommendationEntity entity = new RecommendationEntity(1, 2, "a", 4, "C");

        Recommendation api = recommendationMapper.entityToApi(entity);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getRecommendationId(), entity.getRecommendationId());
        assertEquals(api.getAuthor(), entity.getAuthor());
        assertEquals(api.getRate(), entity.getRating());
        assertEquals(api.getContent(), entity.getContent());
    }

    @Test
    void apiToEntity() {
        assertNotNull(recommendationMapper);

        Recommendation api = new Recommendation(1, 2, "a", 4, "C", "adr");

        RecommendationEntity entity = recommendationMapper.apiToEntity(api);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getRecommendationId(), entity.getRecommendationId());
        assertEquals(api.getAuthor(), entity.getAuthor());
        assertEquals(api.getRate(), entity.getRating());
        assertEquals(api.getContent(), entity.getContent());
    }

    @Test
    void entityListToApiList() {
        assertNotNull(recommendationMapper);

        RecommendationEntity entity = new RecommendationEntity(1, 2, "a", 4, "C");
        List<RecommendationEntity> entityList = Collections.singletonList(entity);

        List<Recommendation> apiList = recommendationMapper.entityListToApiList(entityList);
        assertEquals(apiList.size(), entityList.size());

        Recommendation api = apiList.get(0);

        assertEquals(entity.getProductId(), api.getProductId());
        assertEquals(entity.getRecommendationId(), api.getRecommendationId());
        assertEquals(entity.getAuthor(), api.getAuthor());
        assertEquals(entity.getRating(), api.getRate());
        assertEquals(entity.getContent(), api.getContent());
        assertNull(api.getServiceAddress());
    }

    @Test
    void apiListToEntityList() {
        assertNotNull(recommendationMapper);

        Recommendation api = new Recommendation(1, 2, "a", 4, "C", "adr");
        List<Recommendation> apiList = Collections.singletonList(api);

        List<RecommendationEntity> entityList = recommendationMapper.apiListToEntityList(apiList);
        assertEquals(apiList.size(), entityList.size());

        RecommendationEntity entity = entityList.get(0);

        assertEquals(api.getProductId(), entity.getProductId());
        assertEquals(api.getRecommendationId(), entity.getRecommendationId());
        assertEquals(api.getAuthor(), entity.getAuthor());
        assertEquals(api.getRate(), entity.getRating());
        assertEquals(api.getContent(), entity.getContent());
    }
}