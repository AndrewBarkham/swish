package org.swish.svc.ingestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IngestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngestorApplication.class, args);
	}

//	public static void main(String args[]) {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.postForLocation("http://localhost:8080/insert/Loz/Boz", null);
//		String quote = restTemplate.getForObject("http://localhost:8080/view", String.class);
//		System.out.println(quote);
//	}
}
