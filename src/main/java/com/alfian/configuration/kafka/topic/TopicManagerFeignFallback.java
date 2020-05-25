package com.alfian.configuration.kafka.topic;

public class TopicManagerFeignFallback implements TopicManagerFeign {
  @Override
  public RegistryResponse register(RegistryRequest registryRequest) {
    return null;
  }
}
