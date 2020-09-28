package test.cybercube.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import test.cybercube.collector.configuration.CollectorProperties;
import test.cybercube.collector.dto.PeopleDTO;

import javax.validation.Valid;

@Service
@EnableConfigurationProperties(CollectorProperties.class)
@RequiredArgsConstructor
@Log4j2
public class CollectorService {

    private final @Valid KafkaTemplate<String, PeopleDTO> kafkaTemplate;
    private final CollectorProperties collectorProperties;

    public void processMessage(@Valid PeopleDTO peopleDTO) {
        peopleDTO.setBaseSeed(collectorProperties.getSeed());
        log.info("sending to Kafka peopleDTO='{}' topic='{}'", peopleDTO, collectorProperties.getTopic());
        kafkaTemplate.send(collectorProperties.getTopic(), peopleDTO);
    }
}
