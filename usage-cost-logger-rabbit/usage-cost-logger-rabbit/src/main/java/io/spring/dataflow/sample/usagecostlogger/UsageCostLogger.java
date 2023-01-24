package io.spring.dataflow.sample.usagecostlogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UsageCostLogger {

    private static final Logger logger = LoggerFactory.getLogger(UsageCostLoggerRabbitApplication.class);

    @Bean
    public Consumer<UsageCostDetail> process() {
        return usageCostDetail -> {
            logger.info(usageCostDetail.toString());
        };
    }
}
