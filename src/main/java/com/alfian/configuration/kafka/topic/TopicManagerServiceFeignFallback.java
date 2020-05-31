package com.alfian.configuration.kafka.topic;

public class TopicManagerServiceFeignFallback implements TopicManagerFeign {
  @Override
  public RegistryResponse register(RegistryRequest registryRequest) {
    return null;
  }
}
