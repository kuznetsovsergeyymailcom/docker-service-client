package kss.springframework.simplewebservice.controller;

import kss.springframework.model.User;
import kss.springframework.simplewebservice.SimpleWebServiceApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
@RequiredArgsConstructor
public class MainController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public ResponseEntity main() {

        String message = "some message";
        User new_user = User.builder().name("new user").build();

        rabbitTemplate.convertAndSend(SimpleWebServiceApplication.SFG_MESSAGE_LISTENER_QUEUE, new_user);

        return new ResponseEntity(message, HttpStatus.OK);
    }
}
