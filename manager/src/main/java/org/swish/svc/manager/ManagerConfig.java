package org.swish.svc.manager;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManagerConfig {

    /**
     * AmqpAdmin provided for free by Spring-Boot
     */
    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * Queue name from properties file
     */
    @Value("${rabbit.queue.name}")
    private String queueName;

    /**
     * Create a bean for the queue and declare it on RabbitMQ
     * @return The Queue bean
     */
    @Bean
    public Queue queue(){
        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

}
