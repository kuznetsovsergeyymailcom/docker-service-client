package kss.springframework.simplewebservice.listener;

import kss.springframework.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RabbitMqListener {

    public void receiveMessage(User user) {

        System.out.println("Received a new notification...");

    }
}