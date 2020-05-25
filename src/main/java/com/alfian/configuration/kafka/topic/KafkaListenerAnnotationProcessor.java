package com.alfian.configuration.kafka.topic;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class KafkaListenerAnnotationProcessor implements BeanPostProcessor {

  private KafkaAnnotationScanner kafkaAnnotationScanner;

  public KafkaListenerAnnotationProcessor(String applicationName, TopicManagerFeign topicManagerFeign) {
    this.kafkaAnnotationScanner = KafkaAnnotationScanner.getInstance(applicationName, topicManagerFeign);
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
    throws BeansException {
    kafkaAnnotationScanner.doWith(bean);
    return bean;
  }

}
