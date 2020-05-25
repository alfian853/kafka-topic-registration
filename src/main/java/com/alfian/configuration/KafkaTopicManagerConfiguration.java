package com.alfian.configuration;

import com.alfian.configuration.kafka.topic.*;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;


@Configuration
@EnableKafka
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
  @ConditionalOnMissingBean
  public TopicManagerFeign topicManagerFeign(KafkaTopicManagerProperties kafkaTopicManagerProperties){

    return HystrixFeign.builder()
      .decoder(new JacksonDecoder())
      .encoder(new JacksonEncoder())
      .target(TopicManagerFeign.class, kafkaTopicManagerProperties.getUrl(), new TopicManagerFeignFallback());
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startRegistration(){
    KafkaAnnotationScanner.getInstance().handleRegistry();
  }

}
