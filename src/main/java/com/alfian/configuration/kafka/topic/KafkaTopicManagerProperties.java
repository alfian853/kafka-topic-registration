package com.alfian.configuration.kafka.topic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "plugin.kafka-topic-manager")
public class KafkaTopicManagerProperties {

  public static final String PREFIX = "plugin.kafka-topic-manager";
  public static final String ENABLED_PROP = "${" + PREFIX + ".enabled}";

  private boolean enabled;

  private String url;

  private String serviceName;

}
