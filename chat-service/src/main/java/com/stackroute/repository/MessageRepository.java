package com.stackroute.repository;

import com.stackroute.model.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, Long> {

    @Query("{$or: [ {'senderName': ?0}, { 'receiverName': ?0} ]}")
    List<MessageModel> getAllMessages(String senderName, String receiverName);

}
