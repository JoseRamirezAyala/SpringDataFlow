package io.spring.dataflow.sample.usagecostprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UsageCostProcessorRabbitApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUsageCostProcessor() {
		try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
				TestChannelBinderConfiguration.getCompleteConfiguration(
						UsageCostProcessorRabbitApplication.class)).web(WebApplicationType.NONE)
				.run()) {

			InputDestination source = context.getBean(InputDestination.class);

			UsageDetail usageDetail = new UsageDetail();
			usageDetail.setUserId("user1");
			usageDetail.setDuration(30L);
			usageDetail.setData(100L);

			MessageConverter converter = context.getBean(CompositeMessageConverter.class);
			Map<String, Object> headers = new HashMap<>();
			headers.put("contentType", "application/json");
			MessageHeaders messageHeaders = new MessageHeaders(headers);
			Message<?> message = converter.toMessage(usageDetail, messageHeaders);

			source.send(message);

			OutputDestination target = context.getBean(OutputDestination.class);
			Message<byte[]> sourceMessage = target.receive(10000, "usage-cost");

			UsageCostDetail usageCostDetail = (UsageCostDetail) converter
					.fromMessage(sourceMessage, UsageCostDetail.class);

			assertThat(usageCostDetail.getCallCost()).isEqualTo(3.0);
			assertThat(usageCostDetail.getDataCost()).isEqualTo(5.0);
		}
	}

}
