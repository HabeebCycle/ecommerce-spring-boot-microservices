package com.habeebcycle.microservices.discovery.servicediscoveryserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"spring.cloud.config.enabled=false"})
class ServiceDiscoveryServerApplicationTests {

    @Test
    void contextLoads() {
    }

    // TODO; Replace with WebTestClient
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${app.service-discovery-server.username:u}")
    private String username;

    @Value("${app.service-discovery-server.password:p}")
    private String password;

    @Autowired
    public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate.withBasicAuth(username, password);
    }

    @Test
    public void catalogLoads() {

        String expectedResponseBody = "{\"applications\":{\"versions__delta\":\"1\",\"apps__hashcode\":\"\",\"application\":[]}}";
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/eureka/apps", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(expectedResponseBody, entity.getBody());
    }

    @Test
    public void healthy() {
        String expectedResponseBody = "{\"status\":\"UP\"}";
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/actuator/health", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(expectedResponseBody, entity.getBody());
    }

}
