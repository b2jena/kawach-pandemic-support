package com.stackroute.service;

import com.stackroute.model.MessageModel;

import java.util.List;

public interface MessageService {
    MessageModel saveMessage(MessageModel messageModel);

    List<MessageModel> getAllMessages(String senderName, String receiverName);

    void deleteAll(String senderName);
}
