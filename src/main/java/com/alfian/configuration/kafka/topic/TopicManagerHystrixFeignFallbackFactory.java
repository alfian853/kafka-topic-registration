package com.alfian.configuration.kafka.topic;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicManagerHystrixFeignFallbackFactory implements FallbackFactory<TopicManagerHystrixFeign> {

  @Override
  public TopicManagerHystrixFeign create(Throwable cause) {
    log.error("Hystrix Error: {}", cause.getMessage());
    return new TopicManagerHystrixFeignFallback();
  }
}
