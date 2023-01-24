package io.spring.dataflow.sample.usagecostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class UsageCostProcessor {

    private double ratePerSecond = 0.1;

    private double ratePerMB = 0.05;

    @Bean
    public Function<UsageDetail, UsageCostDetail> processUsageCost() {
        return usageDetail -> {
            UsageCostDetail usageCostDetail = new UsageCostDetail();
            usageCostDetail.setUserId(usageDetail.getUserId());
            usageCostDetail.setCallCost(usageDetail.getDuration() * this.ratePerSecond);
            usageCostDetail.setDataCost(usageDetail.getData() * this.ratePerMB);
            return usageCostDetail;
        };
    }
}
