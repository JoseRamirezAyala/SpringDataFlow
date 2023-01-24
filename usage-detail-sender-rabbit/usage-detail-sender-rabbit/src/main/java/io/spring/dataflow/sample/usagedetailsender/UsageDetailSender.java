package io.spring.dataflow.sample.usagedetailsender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.function.Supplier;

@Configuration
public class UsageDetailSender {

    private String[] users = {"user1", "user2", "user3", "user4", "user5"};

    @Bean
    public Supplier<UsageDetail> sendEvents() {
        return () -> {
            UsageDetail usageDetail = new UsageDetail();
            usageDetail.setUserId(this.users[new Random().nextInt(5)]);
            usageDetail.setDuration(new Random().nextInt(300));
            usageDetail.setData(new Random().nextInt(700));
            return usageDetail;
        };
    }
}