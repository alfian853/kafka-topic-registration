package com.alfian.configuration.kafka.topic;

import com.alfian.configuration.kafka.topic.*;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


@Configuration
@ComponentScan({"com.alfian.configuration.kafka.topic"})
@EnableFeignClients({"com.alfian.configuration.kafka.topic"})
@ConditionalOnProperty(value = "plugin.kafka-topic-manager.enabled", havingValue = "true")
@EnableConfigurationProperties({KafkaTopicManagerProperties.class})
public class KafkaTopicManagerConfiguration {

  @Value("${spring.application.name}")
  private String applicationName;

  @Bean
  public KafkaListenerAnnotationProcessor kafkaListenerAnnotationProcessor(TopicManagerFeign topicManagerFeign) {
    return new KafkaListenerAnnotationProcessor(applicationName, topicManagerFeign);
  }

  @Bean
  @ConditionalOnProperty("plugin.kafka-topic-manager.url")
  public TopicManagerHystrixFeign topicManagerHystrixFeign(KafkaTopicManagerProperties kafkaTopicManagerProperties){

    return HystrixFeign.builder()
      .decoder(new JacksonDecoder())
      .encoder(new JacksonEncoder())
      .target(TopicManagerHystrixFeign.class, kafkaTopicManagerProperties.getUrl(),
        new TopicManagerHystrixFeignFallbackFactory());
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startRegistration(){
    KafkaAnnotationScanner.getInstance().handleRegistry();
  }

}
