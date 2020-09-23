package test.cybercube.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import test.cybercube.collector.configuration.CollectorProperties;
import test.cybercube.collector.dto.PeopleDTO;

@Service
@RequiredArgsConstructor
@Log4j2
public class CollectorService {

    private final KafkaTemplate<String, PeopleDTO> kafkaTemplate;
    private final CollectorProperties collectorProperties;

    public void processMessage(PeopleDTO peopleDTO) {
        peopleDTO.setBaseSeed(collectorProperties.getSeed());
        log.info("sending to Kafka peopleDTO='{}' topic='{}'", peopleDTO, collectorProperties.getTopic());
        kafkaTemplate.send(collectorProperties.getTopic(), peopleDTO);
    }
}
