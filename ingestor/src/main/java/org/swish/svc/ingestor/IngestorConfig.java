package org.swish.svc.ingestor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngestorConfig {

    @Bean
    public RabbitMqListener queueListener() {
        return new RabbitMqListener();
    }

}
