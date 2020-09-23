package test.cybercube.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import test.cybercube.collector.configuration.CollectorProperties;

@SpringBootApplication
@EnableConfigurationProperties(CollectorProperties.class)
public class CollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
    }

}
