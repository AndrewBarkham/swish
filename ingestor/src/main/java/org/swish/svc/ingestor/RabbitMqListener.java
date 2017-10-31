package org.swish.svc.ingestor;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.client.RestTemplate;

@RabbitListener(queues = "${rabbit.queue.name}")
public class RabbitMqListener {

    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
        RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation("http://localhost:8080/insert/Loz/" + in, null);
//		String quote = restTemplate.getForObject("http://localhost:8080/view", String.class);
//		System.out.println(quote);
    }
}
