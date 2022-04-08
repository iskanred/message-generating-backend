package dev.iskandev.messagegeneratingbackend.service;

import dev.iskandev.messagegeneratingbackend.exception.MessageNotFoundException;
import dev.iskandev.messagegeneratingbackend.model.Message;

public interface MessageService {

    /**
     * Default length of message.
     */
    int DEFAULT_MESSAGE_LENGTH = 32;

    /**
     * Creates message of length {@link #DEFAULT_MESSAGE_LENGTH},
     *  saves it to database.
     *
     * @return saved message
     */
    Message createMessage();

    /**
     * Returns last created message from repository.
     *
     * @return last created message from repository
     * @throws MessageNotFoundException if there are no messages in repository yet
     */
    Message getLastMessage() throws MessageNotFoundException;
}
