package com.stackroute.controller;

import com.stackroute.model.MessageModel;
import com.stackroute.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("api/v1/")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("chat-messages/{senderName}/{receiverName}")
    public ResponseEntity<MessageModel> messageModel(@RequestBody MessageModel messageModel) {
        try {
            MessageModel messageModels = messageService.saveMessage(messageModel);
            return new ResponseEntity<>(messageModels, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("chat-messages/{senderName}/{receiverName}")
    public ResponseEntity<List<MessageModel>> getMessages(@PathVariable("senderName") String senderName,
                                                          @PathVariable("receiverName") String receiverName) {
        return new ResponseEntity<List<MessageModel>>((List<MessageModel>) messageService.
                getAllMessages(senderName, receiverName), HttpStatus.OK);
    }

    @DeleteMapping("chat-messages/{senderName}/{receiverName}")
    public ResponseEntity<String> deleteMessages(@PathVariable("senderName") String senderName,
                                                 @PathVariable("receiverName") String receiverName) {
        messageService.deleteAll(senderName);
        return new ResponseEntity<String>("Deleted chat", HttpStatus.OK);
    }

}
