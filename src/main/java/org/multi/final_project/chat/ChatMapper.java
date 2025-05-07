package org.multi.final_project.chat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatRoomVO> findAllRooms();
    void insertRoom(ChatRoomVO room);
    void insertMessage(ChatMessageVO message);
    List<ChatMessageVO> getMessagesByRoomId(String roomId);
    //ChatRoom findById(String id);

}

