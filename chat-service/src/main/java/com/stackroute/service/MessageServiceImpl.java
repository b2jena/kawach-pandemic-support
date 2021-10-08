package com.stackroute.service;

import com.stackroute.model.MessageModel;
import com.stackroute.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MessageServiceImpl implements MessageService{

    MessageRepository messageRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageModel saveMessage(MessageModel messageModel) {
        return messageRepository.save(messageModel);
    }
}
