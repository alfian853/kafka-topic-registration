# kafka-topic-registration

This project help to register kafka topics to 
[Topic-Manager](https://github.com/alfian853/topic-manager) server automatically

## Installation
If you are new in github maven package, please read this: 
[Configuring Apache Maven for use with GitHub Packages](https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-apache-maven-for-use-with-github-packages)
```
<dependency>
  <groupId>com.alfian</groupId>
  <artifactId>kafka-topic-registration</artifactId>
  <version>${version}</version>
</dependency>
```

## Usage

### 1. Register published topic
```
import com.alfian.configuration.kafka.topic.KafkaTopic;
import org.springframework.stereotype.Component;

@Component
public class FooTopics {

  @KafkaTopic
  public static final String fooTopic1 = "foo-1";

  @KafkaTopic
  public static  String fooTopic2 = "foo-2";

  @KafkaTopic
  public final String fooTopic3 = "foo-3";

  @KafkaTopic
  public String fooTopic4 = "foo-4";

  @KafkaTopic
  String fooTopic5 = "foo-5";
}
```
Note: use ``public`` modifier

### 2. Register Published topic

Use ``org.springframework.kafka.annotation.KafkaListener``

```
@KafkaListener(topics = "foo-1")
  public void listenFoo1(ConsumerRecord<String, String> record) {
    //do something
  }
```
