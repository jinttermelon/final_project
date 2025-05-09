package org.multi.final_project.chat;

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
    private final UserMapper userMapper;
    private final FriendMapper friendMapper;

    public ChatController(ChatMapper chatMapper, UserMapper userMapper, FriendMapper friendMapper) {
        this.chatMapper = chatMapper;
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
    }

    //채팅방 전체 목록 출력
    @GetMapping("/rooms")
    public String findAllRooms(Model model) {
        List<ChatRoomVO> rooms = chatMapper.findAllRooms();
        model.addAttribute("rooms", rooms);
        return "chat/rooms"; // 방 목록만 있는 별도 페이지가 있다면
    }

    //채팅방 입장 (room_id 파라미터 받기)
    @GetMapping("/room")
    public String enterRoom(@RequestParam(name = "room_id", required = false) String roomId, Model model) {
        if (roomId != null) {
            ChatRoomVO selectedRoom = chatMapper.selectRoomById(roomId);
            model.addAttribute("selectedRoom", selectedRoom);

            List<ChatMessageVO> messages = chatMapper.selectMessagesByRoomId(roomId);
            model.addAttribute("messages", messages);
        }

        model.addAttribute("chatRooms", chatMapper.findAllRooms());
        model.addAttribute("friends", friendMapper.selectAcceptedFriends("name03")); // 테스트용 유저
        return "chat/chatMain";
    }

    //채팅방 생성 (POST 방식)
    @PostMapping("/room")
    public String insertRoom(ChatRoomVO room) {
        room.setRoom_id(UUID.randomUUID().toString());
        chatMapper.insertRoom(room);
        return "redirect:/chat/room?room_id=" + room.getRoom_id();
    }
}
