package kss.springframework.simplewebservice;

import kss.springframework.simplewebservice.listener.RabbitMqListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleWebServiceApplication {

    public final static String SFG_MESSAGE_QUEUE = "sfg-message-queue";
    public final static String SFG_MESSAGE_LISTENER_QUEUE = "sfg-message-listener-queue";
    public final static String SFG_MESSAGE_WEB_QUEUE = "sfg-message-web-queue";

    @Bean
    Queue queue() {
        return new Queue(SFG_MESSAGE_WEB_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_WEB_QUEUE);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SFG_MESSAGE_WEB_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    //
    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMqListener listener) {
        return new MessageListenerAdapter(listener, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleWebServiceApplication.class, args);
    }

}
