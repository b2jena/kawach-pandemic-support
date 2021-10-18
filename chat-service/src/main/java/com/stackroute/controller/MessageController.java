package com.stackroute.controller;

import com.stackroute.exception.NullValueException;
import com.stackroute.model.Message;
import com.stackroute.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*This is a controller class containing Api of saving, fetching and deleting the messages from mongoDB database
* This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation*/

@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/")
public class MessageController {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(MessageController.class.getName());


    private MessageService messageService;

    /*Message Service is injected in this controller class by @Autowired annotation*/
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /*This Post Mapping method is responsible for saving the message in the mongoDB repository*/
    @PostMapping("chat-messages/{strSenderName}/{strReceiverName}")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        try {
            Message messageModels = messageService.saveMessage(message);
            return new ResponseEntity<>(messageModels, HttpStatus.CREATED);
        } catch (Exception exception) {
            logger.error("Failed to save message");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*This Get Mapping method is responsible for fetching all the message from the mongoDB repository*/
    @GetMapping("chat-messages/{strSenderName}/{strReceiverName}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("strSenderName") String strSenderName,
                                                     @PathVariable("strReceiverName") String strReceiverName) {
        try{
            return new ResponseEntity<List<Message>>((List<Message>) messageService.
                    getAllMessages(strSenderName, strReceiverName), HttpStatus.OK);
        }catch(NullValueException nullValueException){
            logger.error("Failed to get all messages");
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }


    /*This Delete Mapping method is responsible for deleting the message from the mongoDB repository*/
    @DeleteMapping("chat-messages/{strSenderName}/{strReceiverName}")
    public ResponseEntity<String> deleteMessages(@PathVariable("strSenderName") String strSenderName,
                                                             @PathVariable("strReceiverName") String strReceiverName) {
        try{
            messageService.deleteAll(strSenderName);
            return new ResponseEntity<String>("Deleted chat", HttpStatus.OK);
        }catch(NullValueException nullValueException){
            logger.error("Failed to delete all messages");
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }

}
