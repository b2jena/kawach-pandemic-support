package com.stackroute.service;

import com.stackroute.model.MessageModel;
import com.stackroute.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    MessageRepository messageRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
//        System.out.println(1);
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageModel saveMessage(MessageModel messageModel) {
//        messageModel.setRid(UUID.randomUUID());
//        System.out.println(2);
        return messageRepository.save(messageModel);
    }

    @Override
    public List<MessageModel> getAllMessages(String senderName, String reciverName) {
//        Query query = new Query(Criteria.where("senderName").is(senderName).and("reciverName").is(reciverName)
//                .orOperator(Criteria.where("senderName").is(reciverName).and("reciverName").is(senderName)));
//        Query query1 = new Query(Criteria.where("reciverName").is(reciverName));
        return (List<MessageModel>) messageRepository.getAllMessages(senderName, reciverName);

//        return request;
    }

    @Override
    public List<MessageModel> deleteAll(String senderName) {
//        Query query = new Query(Criteria.where("senderName").is(name));
//        DeleteResult request = mongoTemplate.remove(query, MessageModel.class);
//        mongoTemplate.deleteAll({"senderName": name});

        return  messageRepository.deleteAll(senderName);
    }
}
