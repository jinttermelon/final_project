package org.multi.final_project.chat;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatMessageVO {
    private int num;
    private String room_id;
    private String sender;//nickname
    private String message;
    private Timestamp wdate;
}
