package com.alfian.configuration.kafka.topic;

import feign.Headers;
import feign.RequestLine;

public interface TopicManagerHystrixFeign extends TopicManagerFeign {

  @Override
  @RequestLine("POST /register")
  @Headers("Content-Type: application/json")
  RegistryResponse register(RegistryRequest registryRequest);

}
