package io.spring.dataflow.sample.usagecostlogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsageCostLoggerRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsageCostLoggerRabbitApplication.class, args);
	}

}
