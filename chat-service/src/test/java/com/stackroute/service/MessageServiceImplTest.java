package com.stackroute.service;

import com.stackroute.exception.NullValueException;
import com.stackroute.model.Message;
import com.stackroute.repository.MessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;
    private Message message;
    private List<Message> MessageList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        message = new Message("User1", "User2", "Hi", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        optional = Optional.of(message);
    }

    @AfterEach
    public void tearDown() {
        message = new Message();
    }

    @Test
    void givenMessageModelToSaveThenShouldReturnSavedMessageModel() throws Exception {
        when(messageRepository.save(any())).thenReturn(message);
        assertEquals(message, messageService.saveMessage(message));
        verify(messageRepository, times(1)).save(any());
    }

    @Test
    void givenGetAllMessagesThenShouldReturnListOfAllMessages() throws NullValueException {
        messageRepository.save(message);
        List<Message>MessageList = messageRepository.findAll();
        List<Message> messageList = messageService.getAllMessages(message.getStrSenderName(), message.getStrReceiverName());
        assertEquals(MessageList, messageList);
        verify(messageRepository, times(1)).save(message);
        verify(messageRepository, times(1)).findAll();
    }

}