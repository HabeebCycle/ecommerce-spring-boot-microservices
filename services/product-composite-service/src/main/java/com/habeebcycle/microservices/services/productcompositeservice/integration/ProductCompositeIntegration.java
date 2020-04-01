package com.habeebcycle.microservices.services.productcompositeservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habeebcycle.microservices.api.core.product.Product;
import com.habeebcycle.microservices.api.core.product.ProductService;
import com.habeebcycle.microservices.api.core.recommendation.Recommendation;
import com.habeebcycle.microservices.api.core.recommendation.RecommendationService;
import com.habeebcycle.microservices.api.core.review.Review;
import com.habeebcycle.microservices.api.core.review.ReviewService;
import com.habeebcycle.microservices.api.event.Event;
import com.habeebcycle.microservices.services.productcompositeservice.message.MessageSources;
import com.habeebcycle.microservices.util.exceptions.InvalidInputException;
import com.habeebcycle.microservices.util.exceptions.NotFoundException;
import com.habeebcycle.microservices.util.http.HttpErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static com.habeebcycle.microservices.api.event.Event.Type.CREATE;
import static com.habeebcycle.microservices.api.event.Event.Type.DELETE;
import static reactor.core.publisher.Flux.empty;

@Component
@EnableBinding(MessageSources.class)
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper mapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;

    private MessageSources messageSources;

    private WebClient webClient;

    @Autowired
    public ProductCompositeIntegration(
            WebClient.Builder webClientBuilder,
            ObjectMapper mapper,
            MessageSources messageSources,

            @Value("${app.product-service.host}") String productServiceHost,
            @Value("${app.recommendation-service.host}") String recommendationServiceHost,
            @Value("${app.review-service.host}") String reviewServiceHost
    ){
        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.messageSources = messageSources;

        productServiceUrl        = productServiceHost;
        recommendationServiceUrl = recommendationServiceHost;
        reviewServiceUrl         = reviewServiceHost;
    }

    @Override
    public Product createProduct(Product body) {
        messageSources.outputProducts()
                .send(MessageBuilder.withPayload(new Event(CREATE, body.getProductId(), body)).build());
        return body;
    }

    @Override
    public Mono<Product> getProduct(int productId) {
        String url = productServiceUrl + "/product/" + productId;
        LOG.debug("Will call the getProduct API on URL: {}", url);

        return getWebClient().get().uri(url).retrieve()
                .bodyToMono(Product.class).log()
                .onErrorMap(WebClientResponseException.class, this::handleException);
    }

    @Override
    public void deleteProduct(int productId) {
        messageSources.outputProducts()
                .send(MessageBuilder.withPayload(new Event(DELETE, productId, null)).build());
    }

    @Override
    public Recommendation createRecommendation(Recommendation body) {

        messageSources.outputRecommendations()
                .send(MessageBuilder.withPayload(new Event(CREATE, body.getProductId(), body)).build());
        return body;
    }

    public Flux<Recommendation> getRecommendations(int productId) {

        String url = recommendationServiceUrl + "/recommendation?productId=" + productId;

        LOG.debug("Will call the getRecommendations API on URL: {}", url);

        // Return an empty result if something goes wrong to make it possible for the composite service to return partial responses
        return getWebClient().get().uri(url).retrieve()
                .bodyToFlux(Recommendation.class).log().onErrorResume(error -> empty());
    }

    @Override
    public void deleteRecommendations(int productId) {
        messageSources.outputRecommendations()
                .send(MessageBuilder.withPayload(new Event(DELETE, productId, null)).build());
    }

    @Override
    public Review createReview(Review body) {

        messageSources.outputReviews()
                .send(MessageBuilder.withPayload(new Event(CREATE, body.getProductId(), body)).build());
        return body;
    }

    @Override
    public Flux<Review> getReviews(int productId) {

        String url = reviewServiceUrl + "/review?productId=" + productId;

        LOG.debug("Will call the getReviews API on URL: {}", url);

        // Return an empty result if something goes wrong to make it possible for the composite service to return partial responses
        return getWebClient().get().uri(url).retrieve()
                .bodyToFlux(Review.class).log().onErrorResume(error -> empty());

    }

    @Override
    public void deleteReviews(int productId) {
        messageSources.outputReviews()
                .send(MessageBuilder.withPayload(new Event(DELETE, productId, null)).build());
    }

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }

    private Throwable handleException(Throwable ex) {

        if (!(ex instanceof WebClientResponseException)) {
            LOG.warn("Got a unexpected error: {}, will rethrow it", ex.toString());
            return ex;
        }

        WebClientResponseException wcre = (WebClientResponseException)ex;

        switch (wcre.getStatusCode()) {

            case NOT_FOUND:
                return new NotFoundException(getErrorMessage(wcre));

            case UNPROCESSABLE_ENTITY :
                return new InvalidInputException(getErrorMessage(wcre));

            default:
                LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
                LOG.warn("Error body: {}", wcre.getResponseBodyAsString());
                return ex;
        }
    }

    private String getErrorMessage(WebClientResponseException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ex.getMessage();
        }
    }
}
