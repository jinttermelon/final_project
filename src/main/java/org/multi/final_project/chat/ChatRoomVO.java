package org.multi.final_project.chat;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatRoomVO {
    private int num;
    private String room_id;
    private String room_name;
    private Timestamp cdate;
    private String nick;
    private String chat_info;
}
