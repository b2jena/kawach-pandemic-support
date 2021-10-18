package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.model.MessageModel;
import com.stackroute.service.MessageServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    private MockMvc mockMvc;
    @Mock
    MessageServiceImpl messageService;
    @InjectMocks
    private MessageController messageController;

    private MessageModel messageModel;
    private List<MessageModel> messageModelList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
        messageModel = new MessageModel();
        messageModel.setSenderName("Debjit");
        messageModel.setReciverName("Bikash");
        messageModel.setMessageBody("Good Morning India");
        messageModel.setFormattedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        messageModelList = new ArrayList<>();
        messageModelList.add(messageModel);
    }

    @AfterEach
    public void tearDown() {
        messageModel = null;
    }

    @Test
    public void givenMessageModelToSaveThenShouldReturnSavedMessageModel() throws Exception {
        when(messageService. saveMessage(any())).thenReturn(messageModel);
        mockMvc.perform(post("/api/v1/chat-messages/Debjit/Bikash")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(messageModel)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(messageService).saveMessage(any());
    }

    @Test
    public void givenMessageModelToFindAllMessageModelThenShouldReturnSavedMessageModelList() throws Exception {
        List<MessageModel> messageModel =  messageService. getAllMessages("Debjit", "Bikash");
        mockMvc.perform(get("/api/v1/chat-messages/Debjit/Bikash"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}