package com.ortolegal.rabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    private final static String QUEUE_NAME = "ortolegal";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

}
