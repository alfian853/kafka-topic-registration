package com.alfian.configuration.kafka.topic;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@ConditionalOnProperty("plugin.kafka-topic-manager.service-name")
@FeignClient(
  name = "${plugin.kafka-topic-manager.service-name}",
  fallbackFactory = TopicManagerServiceFeignFallbackFactory.class
)
public interface TopicManagerServiceFeign extends TopicManagerFeign{

  @Override
  @PostMapping("/register")
  RegistryResponse register(RegistryRequest registryRequest);

}
