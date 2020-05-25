package com.alfian.configuration.kafka.topic;

import lombok.Data;

import java.util.List;

@Data
public class RegistryRequest {

  private String serviceName;

  private List<String> publishedTopics;

  private List<String> subscribedTopics;

}
