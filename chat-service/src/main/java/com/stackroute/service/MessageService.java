package com.stackroute.service;

import com.stackroute.exception.NullValueException;
import com.stackroute.model.Message;

import java.util.List;

/*This is the Message Service class where abstract methods are declared*/
public interface MessageService {

    /*This Method will save the messages in the mongoDB database */
    Message saveMessage(Message message) throws Exception;

    /*This Method will fetch all the message from the mongoDB database */
    List<Message> getAllMessages(String strSenderName, String strReceiverName) throws NullValueException;

    /*This Method will delete all the message from the mongoDB database */
    void deleteAll(String strSenderName) throws NullValueException;
}
