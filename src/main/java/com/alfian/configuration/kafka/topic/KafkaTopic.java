package com.alfian.configuration.kafka.topic;

import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KafkaTopic {
}
