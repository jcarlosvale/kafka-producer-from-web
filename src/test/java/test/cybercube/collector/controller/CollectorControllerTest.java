package test.cybercube.collector.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import test.cybercube.collector.dto.PeopleDTO;

import static test.cybercube.collector.configuration.CollectorConstants.ITEM_END_POINT_V1;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext
@AutoConfigureWebTestClient(timeout = "PT1M")
//@Testcontainers
class CollectorControllerTest extends AbstractIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    KafkaProperties properties;

    @BeforeEach
    void setUp() {
        //kafka.start();
    }

    @AfterEach
    void tearDown() {
//        kafka.stop();
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
