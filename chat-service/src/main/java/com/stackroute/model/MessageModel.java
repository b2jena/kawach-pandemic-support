package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Message")
public class MessageModel {
//    @Transient
//    public static final String SEQUENCE_NAME="sequence";
    //public static city;
//    @Id
//    private UUID Rid;
//    private String sender;
//    private String reciver;
    private String senderName;
    private String reciverName;
    private String messageBody;
    String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
}
