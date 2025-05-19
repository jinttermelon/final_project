package org.multi.final_project.chat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatRoomVO> findAllRooms();
    void insertRoom(ChatRoomVO room);
    void insertMessage(ChatMessageVO message);
    List<ChatMessageVO> getMessagesByRoomId(String room_id);
    ChatRoomVO selectRoomById(String room_id); // 단일 방 정보
    List<ChatMessageVO> selectMessagesByRoomId(String room_id); // 해당 방 메시지

}

