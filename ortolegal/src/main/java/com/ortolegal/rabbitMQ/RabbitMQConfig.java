package com.ortolegal.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue ortolegalQueue() {
        return new Queue("ortolegal", true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("ortolegal-exchange");
    }

    @Bean
    Binding ortolegalBinding(Queue ortolegalQueue, DirectExchange exchange) {
        return BindingBuilder.bind(ortolegalQueue).to(exchange).with("ortolegal-routing-key");
    }

}
