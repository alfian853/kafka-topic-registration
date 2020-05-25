package com.alfian.configuration.kafka.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class KafkaAnnotationScanner {

  private String applicationName;
  private TopicManagerFeign topicManagerFeign;
  private List<String> publishedTopics = new LinkedList<>();
  private List<String> subscribedTopics = new LinkedList<>();

  private KafkaAnnotationScanner(String applicationName, TopicManagerFeign topicManagerFeign) {
    this.applicationName = applicationName;
    this.topicManagerFeign = topicManagerFeign;
  }

  private static KafkaAnnotationScanner instance;

  public static KafkaAnnotationScanner getInstance(String applicationName, TopicManagerFeign topicManagerFeign){
    if(instance == null){
      synchronized (KafkaAnnotationScanner.class) {
        if(instance == null){
          instance = new KafkaAnnotationScanner(applicationName, topicManagerFeign);
        }
      }
    }

    return instance;
  }

  public static KafkaAnnotationScanner getInstance(){
    return instance;
  }

  public void doWith(Object object) throws IllegalArgumentException {
    Class<?> clazz = object.getClass();

    try {
      for(Field field: clazz.getFields()){
        if(field.isAnnotationPresent(KafkaTopic.class)) {
          publishedTopics.add(field.get(object).toString());
        }
      }
      for(Method method: clazz.getDeclaredMethods()){
        if (method.isAnnotationPresent(KafkaListener.class)) {
          subscribedTopics.addAll(Arrays.asList(method.getDeclaredAnnotation(KafkaListener.class).topics()));
        }
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

  }

  public void handleRegistry(){
    RegistryRequest request = new RegistryRequest();
    request.setServiceName(applicationName);
    request.setSubscribedTopics(subscribedTopics);
    request.setPublishedTopics(publishedTopics);

    RegistryResponse response = topicManagerFeign.register(request);
    if(response == null){
      log.error("Failed when register topic to topic-manager service");
    }
    else {
      log.info("Success when register topic to topic-manager service, messages : {}", response.getMessages());
    }
  }

}
