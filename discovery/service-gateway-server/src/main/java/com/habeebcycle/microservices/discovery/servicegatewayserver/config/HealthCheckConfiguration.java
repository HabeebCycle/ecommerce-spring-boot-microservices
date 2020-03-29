package com.habeebcycle.microservices.discovery.servicegatewayserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;

@Configuration
public class HealthCheckConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(HealthCheckConfiguration.class);

    private HealthAggregator healthAggregator;

    private final WebClient.Builder webClientBuilder;

    private WebClient webClient;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;
    private final String productCompositeServiceUrl;

    @Autowired
    public HealthCheckConfiguration(
            WebClient.Builder webClientBuilder,
            HealthAggregator healthAggregator,

            @Value("${app.product-composite-service}") String productCompositeServiceHost,
            @Value("${app.product-service}") String productServiceHost,
            @Value("${app.recommendation-service}") String recommendationServiceHost,
            @Value("${app.review-service}") String reviewServiceHost
    ) {
        this.webClientBuilder = webClientBuilder;
        this.healthAggregator = healthAggregator;

        productCompositeServiceUrl = productCompositeServiceHost;
        productServiceUrl        = productServiceHost;
        recommendationServiceUrl = recommendationServiceHost;
        reviewServiceUrl         = reviewServiceHost;
    }

    @Bean
    ReactiveHealthIndicator healthcheckMicroservices() {

        ReactiveHealthIndicatorRegistry registry = new DefaultReactiveHealthIndicatorRegistry(new LinkedHashMap<>());

        registry.register("product-service",           () -> getHealth(productServiceUrl));
        registry.register("recommendation-service",    () -> getHealth(recommendationServiceUrl));
        registry.register("review-service",            () -> getHealth(reviewServiceUrl));
        registry.register("product-composite-service", () -> getHealth(productCompositeServiceUrl));

        return new CompositeReactiveHealthIndicator(healthAggregator, registry);
    }

    private Mono<Health> getHealth(String url) {
        url += "/actuator/health";
        LOG.debug("Will call the Health API on URL: {}", url);
        return getWebClient().get().uri(url).retrieve().bodyToMono(String.class)
                .map(s -> new Health.Builder().up().build())
                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()))
                .log();
    }

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }

}
