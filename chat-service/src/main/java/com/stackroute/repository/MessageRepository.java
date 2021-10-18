package com.stackroute.repository;

import com.stackroute.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*This is a repository class which manages the messages using mongo repository*/
@Repository
public interface MessageRepository extends MongoRepository<Message, Long> {

    /*This Method will fetch all the message from the mongoDB database */
    @Query("{$or: [ {'strSenderName': ?0}, { 'strReceiverName': ?0} ]}")
    List<Message> getAllMessages(String strSenderName, String strReceiverName);

}
