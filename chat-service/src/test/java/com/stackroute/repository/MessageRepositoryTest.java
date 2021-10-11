package com.stackroute.repository;

import com.stackroute.model.MessageModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MessageRepositoryIntegrationTest {

    @Autowired
    private MessageRepository messageRepository;
    private MessageModel message;

    @BeforeEach
    void setUp() {
        message = new MessageModel();
        message.setSenderName("User1");
        message.setReciverName("User2");
        message.setMessageBody("Hi User1");

    }

    @AfterEach
    void tearDown () {
        messageRepository.deleteAll();
        message = null;
    }

    @Test
    public void givenBlogToSaveThenShouldReturnSavedBlog() {
        messageRepository.save(message);
        MessageModel fetchedmessage = messageRepository.findAll().get(0);
        assertEquals("User2", fetchedmessage.getReciverName());
    }

    @Test
    public void givenMessageToSaveThenShouldReturnSavedMessage() {
        MessageModel message = new MessageModel("Demo9", "Imneet", "SampleBlog", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        MessageModel message1 = messageRepository.save(message);
        List<MessageModel> fetchedmessage = messageRepository.getAllMessages(message.getSenderName(), message.getReciverName());


        assertEquals(message1.getSenderName(), fetchedmessage.get(0).getSenderName());
        assertEquals(message1.getReciverName(), fetchedmessage.get(0).getReciverName());
        assertEquals(message1.getMessageBody(), fetchedmessage.get(0).getMessageBody());

    }

    @Test
    public void givenGetAllMessagesThenShouldReturnListOfAllMessages() {
        MessageModel message = new MessageModel("Debjit", "Sandhya", "hi",  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        MessageModel message1 = new MessageModel("Sandhya", "Debjit1", "hello",  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        messageRepository.save(message);
        messageRepository.save(message1);

        List<MessageModel> MessageModelList = (List<MessageModel>) messageRepository.findAll();
        assertEquals("Sandhya", MessageModelList.get(1).getSenderName());
        assertEquals("hi", MessageModelList.get(0).getMessageBody());
    }

}