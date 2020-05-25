# kafka-topic-configuration

This project help to register kafka topics to 
[Topic-Manager](https://github.com/alfian853/topic-manager) server automatically


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

