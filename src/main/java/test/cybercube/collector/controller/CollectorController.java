package test.cybercube.collector.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import test.cybercube.collector.dto.PeopleDTO;
import test.cybercube.collector.service.CollectorService;

import static test.cybercube.collector.configuration.CollectorConstants.ITEM_END_POINT_V1;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CollectorController {

    private final CollectorService collectorService;

    @PostMapping(value = ITEM_END_POINT_V1, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> collect(@RequestBody PeopleDTO peopleDTO) {
        log.info("Received data: {}", peopleDTO);
        collectorService.processMessage(peopleDTO);
        return Mono.empty();
    }
}
