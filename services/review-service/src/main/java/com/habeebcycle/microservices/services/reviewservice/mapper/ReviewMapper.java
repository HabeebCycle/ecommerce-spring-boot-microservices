package com.habeebcycle.microservices.services.reviewservice.mapper;

import com.habeebcycle.microservices.api.core.review.Review;
import com.habeebcycle.microservices.services.reviewservice.entity.ReviewEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    public Review entityToApi(ReviewEntity entity){
        Review review = new Review();
        review.setProductId(entity.getProductId());
        review.setReviewId(entity.getReviewId());
        review.setAuthor(entity.getAuthor());
        review.setSubject(entity.getSubject());
        review.setContent(entity.getContent());
        return review;
    }

    public ReviewEntity apiToEntity(Review api){
        return new ReviewEntity(
                api.getProductId(), api.getReviewId(), api.getAuthor(),
                api.getSubject(), api.getContent()
        );
    }

    public List<Review> entityListToApiList(List<ReviewEntity> entityList){
        List<Review> apiList = new ArrayList<>();
        entityList.forEach(e -> apiList.add(entityToApi(e)));
        return apiList;
    }

    public List<ReviewEntity> apiListToEntityList(List<Review> apiList){
        List<ReviewEntity> entityList = new ArrayList<>();
        apiList.forEach(api -> entityList.add(apiToEntity(api)));
        return entityList;
    }
}
