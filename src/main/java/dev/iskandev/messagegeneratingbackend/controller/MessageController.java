package dev.iskandev.messagegeneratingbackend.controller;

import dev.iskandev.messagegeneratingbackend.exception.MessageNotFoundException;
import dev.iskandev.messagegeneratingbackend.model.Message;
import dev.iskandev.messagegeneratingbackend.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public void createMessage() {
        logger.info("POST-request for creating a message has been received");
        var m= service.createMessage();
        logger.info("\nMessage:\n\tvalue = " + m.getValue() + "\n\tsha2 = " + m.getSha2());
    }

    @GetMapping
    public Message getLastMessage() throws MessageNotFoundException {
        logger.info("GET-request getting the last message has been received");
        return service.getLastMessage();
    }
}