package org.multi.final_project.chat;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.friend.FriendMapper;
import org.multi.final_project.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private HttpSession session;



    //채팅방 입장 (room_id 파라미터 받기)
    @GetMapping("/cummunity")
    public String cummunity(@RequestParam(name = "room_id", required = false) String room_id, Model model) {
//        if (roomId != null) {
//            ChatRoomVO selectedRoom = chatMapper.selectRoomById(roomId);
//            model.addAttribute("selectedRoom", selectedRoom);
//
//            List<ChatMessageVO> messages = chatMapper.selectMessagesByRoomId(roomId);
//            model.addAttribute("messages", messages);
//        }

        List<ChatRoomVO> rooms = chatMapper.findAllRooms();
        model.addAttribute("rooms", rooms);
        model.addAttribute("messages_history", chatMapper.getMessagesByRoomId(room_id));
        model.addAttribute("friends", friendMapper.selectAcceptedFriends(session.getAttribute("nickname").toString()));
        return "chat/chatMain";
    }

    //채팅방 생성 (POST 방식)
    @PostMapping("/room")
    public String insertRoom(ChatRoomVO room) {
        room.setRoom_id(UUID.randomUUID().toString());
        chatMapper.insertRoom(room);
        return "redirect:/chat/cummunity?room_id=" + room.getRoom_id();
    }
}
