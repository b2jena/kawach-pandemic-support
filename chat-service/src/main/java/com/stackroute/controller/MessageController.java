package com.stackroute.controller;

import com.stackroute.model.MessageModel;
import com.stackroute.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

        @PostMapping("chat-messages")
    public ResponseEntity<MessageModel> saveMessage  (@RequestBody MessageModel messageModel){
        try{
            MessageModel messageModels = messageService.saveMessage(messageModel);
            return new ResponseEntity<>(messageModels, HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
}
