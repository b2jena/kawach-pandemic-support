package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
* This is a Model class, this contains Sender Name, Receiver Name, Message Body, Time of  Sending the Message,
* the Message Data will be stored in this format.
* */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "collMessage")
public class Message {
    private String strSenderName;
    private String strReceiverName;
    private String strMessageBody;
    String strFormattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
}
