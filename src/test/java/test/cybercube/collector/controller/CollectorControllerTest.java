package test.cybercube.collector.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.KafkaContainer;
import reactor.core.publisher.Mono;
import test.cybercube.collector.dto.PeopleDTO;

import static test.cybercube.collector.configuration.CollectorConstants.ITEM_END_POINT_V1;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient(timeout = "PT1M")
class CollectorControllerTest {

    @Autowired
    WebTestClient webTestClient;

    static KafkaContainer kafkaContainer = new KafkaContainer();

    @BeforeAll
    public static void setUp() {
        kafkaContainer.start();
        System.setProperty("spring.kafka.properties.bootstrap.servers", kafkaContainer.getBootstrapServers());
        System.setProperty("spring.kafka.consumer.properties.auto.offset.reset", "earliest");
    }

    @Test
    public void collectOkTest() {
        PeopleDTO peopleDTO =
                PeopleDTO
                        .builder()
                        .firstName("someFirstName")
                        .lastName("someLastName")
                        .age(30)
                        .build();
        webTestClient
                .post()
                .uri(ITEM_END_POINT_V1)
                //.contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(peopleDTO), PeopleDTO.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void missingFirstNameTest() {
        PeopleDTO peopleDTO =
                PeopleDTO
                        .builder()
                        .lastName("someLastName")
                        .age(30)
                        .build();
        webTestClient
                .post()
                .uri(ITEM_END_POINT_V1)
                .body(Mono.just(peopleDTO), PeopleDTO.class)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    public void invalidAgeTest() {
        PeopleDTO peopleDTO =
                PeopleDTO
                        .builder()
                        .firstName("someFirstName")
                        .lastName("someLastName")
                        .age(10)
                        .build();
        webTestClient
                .post()
                .uri(ITEM_END_POINT_V1)
                .body(Mono.just(peopleDTO), PeopleDTO.class)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}
