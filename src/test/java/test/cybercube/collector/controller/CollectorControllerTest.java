package test.cybercube.collector.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import test.cybercube.collector.dto.PeopleDTO;

import static test.cybercube.collector.configuration.CollectorConstants.ITEM_END_POINT_V1;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class CollectorControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void collectTest() {
        PeopleDTO peopleDTO =
                PeopleDTO
                        .builder()
                        .firstName("someFirstName")
                        .lastName("someLastName")
                        .build();
        webTestClient
                .post()
                .uri(ITEM_END_POINT_V1)
                .body(Mono.just(peopleDTO), PeopleDTO.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}
