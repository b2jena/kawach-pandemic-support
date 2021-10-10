package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Message")
public class MessageModel {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    //public static city;
    @Id
    private UUID id;
    private String sender;
    private String reciver;
    public String senderName;
    public String reciverName;
    public String messageBody;

}
