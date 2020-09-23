package test.cybercube.collector.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("collector")
@Data
public class CollectorProperties {
    private Double seed  = 1D;
    private String topic = "";
}
