package com.alfian.configuration.kafka.topic;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty("plugin.kafka-topic-manager.service-name")
public class TopicManagerFeignFallbackFactory implements FallbackFactory<TopicManagerFeign> {

  @Override
  public TopicManagerFeign create(Throwable cause) {
    log.error("Hystrix Error: {}", cause.getMessage());
    return new TopicManagerFeignFallback();
  }
}
