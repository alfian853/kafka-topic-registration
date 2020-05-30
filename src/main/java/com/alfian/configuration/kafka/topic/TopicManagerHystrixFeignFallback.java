package com.alfian.configuration.kafka.topic;

public class TopicManagerHystrixFeignFallback implements TopicManagerHystrixFeign {
  @Override
  public RegistryResponse register(RegistryRequest registryRequest) {
    return null;
  }
}
