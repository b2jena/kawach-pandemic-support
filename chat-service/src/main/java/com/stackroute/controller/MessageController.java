package com.stackroute.controller;

import com.stackroute.model.MessageModel;
import com.stackroute.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/v1/")
//@CrossOrigin(value = "http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("chat")
    public ResponseEntity<MessageModel> saveMessage  (@RequestBody MessageModel messageModel){
        try{
            MessageModel messageModels = messageService.saveMessage(messageModel);
            return new ResponseEntity<MessageModel>(messageModels, HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("method")
    public String method()
    {
        return "Works";
    }
}
