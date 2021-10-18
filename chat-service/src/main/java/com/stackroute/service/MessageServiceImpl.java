package com.stackroute.service;

import com.stackroute.exception.NullValueException;
import com.stackroute.model.Message;
import com.stackroute.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*This is the implementation class of Message Service where abstract methods of Message Service are implemented*/
@Service
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;

    /*Message Repository is injected in this Message Service Implementation class by @Autowired annotation*/
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /*This Method is responsible for saving the message in the mongoDB database */
    @Override
    public Message saveMessage(Message message) throws Exception {
        return messageRepository.save(message);
    }

    /*This Method is responsible for fetching all the message from the mongoDB database */
    @Override
    public List<Message> getAllMessages(String strSenderName, String strReceiverName) throws NullValueException {
        return (List<Message>) messageRepository.getAllMessages(strSenderName, strReceiverName);
    }


    /*This Method is responsible for deleting all the message from the mongoDB database */
    @Override
    public void deleteAll(String strSenderName) throws NullValueException {
        messageRepository.deleteAll();
    }
}
