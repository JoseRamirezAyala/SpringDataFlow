package io.spring.dataflow.sample.usagedetailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsageDetailSenderRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsageDetailSenderRabbitApplication.class, args);
	}

}
