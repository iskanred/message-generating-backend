package dev.iskandev.messagegeneratingbackend.service;

import dev.iskandev.messagegeneratingbackend.exception.MessageNotFoundException;
import dev.iskandev.messagegeneratingbackend.model.Message;
import dev.iskandev.messagegeneratingbackend.repository.MessageRepository;
import dev.iskandev.messagegeneratingbackend.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    /**
     * Id of the last inserted message
     */
    private Long lastMessageId;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Message createMessage() {
        var message = new Message();
        // generate random string and put it to message
        message.updateValue(StringUtils.generateRandomAlphaNumeric(DEFAULT_MESSAGE_LENGTH));

        message = repository.saveAndFlush(message);
        lastMessageId = message.getId(); // keep its id

        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Message getLastMessage() throws MessageNotFoundException {
        if (lastMessageId == null)
            throw new MessageNotFoundException();
        return repository.getById(lastMessageId);
    }
}