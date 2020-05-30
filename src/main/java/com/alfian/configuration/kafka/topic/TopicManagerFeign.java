package com.alfian.configuration.kafka.topic;

public interface TopicManagerFeign {

  RegistryResponse register(RegistryRequest registryRequest);

  default RegistryResponse doRegister(RegistryRequest registryRequest){
    return register(registryRequest);
  }
}
