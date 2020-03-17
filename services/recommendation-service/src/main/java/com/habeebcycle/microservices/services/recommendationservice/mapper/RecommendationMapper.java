package com.habeebcycle.microservices.services.recommendationservice.mapper;

import com.habeebcycle.microservices.api.core.recommendation.Recommendation;
import com.habeebcycle.microservices.services.recommendationservice.entity.RecommendationEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendationMapper {

    public Recommendation entityToApi(RecommendationEntity entity){
        Recommendation recommendation = new Recommendation();
        recommendation.setProductId(entity.getProductId());
        recommendation.setRecommendationId(entity.getRecommendationId());
        recommendation.setAuthor(entity.getAuthor());
        recommendation.setRate(entity.getRating());
        recommendation.setContent(entity.getContent());

        return recommendation;
    }

    public RecommendationEntity apiToEntity(Recommendation api){
        return new RecommendationEntity(
          api.getProductId(), api.getRecommendationId(), api.getAuthor(), api.getRate(), api.getContent()
        );
    }

    public List<Recommendation> entityListToApiList(List<RecommendationEntity> entityList){
        List<Recommendation> apiList = new ArrayList<>();
        entityList.forEach(e -> apiList.add(entityToApi(e)));
        return apiList;
    }

    public List<RecommendationEntity> apiListToEntityList(List<Recommendation> apiList){
        List<RecommendationEntity> entityList = new ArrayList<>();
        apiList.forEach(api -> entityList.add(apiToEntity(api)));
        return entityList;
    }
}
