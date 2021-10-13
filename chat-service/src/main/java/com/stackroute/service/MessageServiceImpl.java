package com.stackroute.service;

import com.stackroute.model.MessageModel;
import com.stackroute.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageModel saveMessage(MessageModel messageModel) {
        return messageRepository.save(messageModel);
    }

    @Override
    public List<MessageModel> getAllMessages(String senderName, String reciverName) {
        return (List<MessageModel>) messageRepository.getAllMessages(senderName, reciverName);
    }

    @Override
    public void deleteAll(String senderName) {
        messageRepository.deleteAll();
    }
}
