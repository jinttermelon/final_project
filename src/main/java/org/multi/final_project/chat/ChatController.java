package org.multi.final_project.chat;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.friend.FriendMapper;
import org.multi.final_project.user.UserMapper;
import org.multi.final_project.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMapper chatMapper;
    private UserMapper userMapper;
    private FriendMapper friendMapper;

    public ChatController(ChatMapper chatMapper, UserMapper userMapper, FriendMapper friendMapper) {
        this.chatMapper = chatMapper;
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
    }

    @GetMapping("/rooms")
    public String findAllRooms(Model model) {
        List<ChatRoomVO> rooms = chatMapper.findAllRooms();
        log.info("rooms.size() : {}",rooms.size());
        model.addAttribute("rooms", rooms);
        return "chat/rooms";
    }

    @PostMapping("/room")
    public String createRoom(ChatRoomVO room) {
        log.info("Creating roomName : {}", room);
        room.setRoom_id(UUID.randomUUID().toString());
        log.info("Creating roomName : {}", room);

        chatMapper.insertRoom(room);
        return "redirect:/chat/rooms";
    }

    @GetMapping("/room/{room_id}")
    public String enterRoom(@PathVariable String room_id, Model model) {
        // 더미 닉네임
        String dummyNickname = "더미유저";
        model.addAttribute("nickname", dummyNickname);

        // ✅ 더미 room 객체 생성
        ChatRoomVO dummyRoom = new ChatRoomVO();
        dummyRoom.setRoom_id(room_id);
        dummyRoom.setRoom_name("더미 채팅방");

        model.addAttribute("room", dummyRoom); // 이게 없으면 room.room_name에서 에러 발생

        model.addAttribute("room_id", room_id);
        model.addAttribute("messages_history", chatMapper.getMessagesByRoomId(room_id));

        return "chat/room";
    }

    @GetMapping("/chat")
    public String chatMain(Model model) {
        String userId = "name02"; // 테스트용 아이디
        model.addAttribute("chatRooms", chatMapper.findAllRooms());
        model.addAttribute("friends", friendMapper.selectAcceptedFriends(userId)); // 예시
        log.info("{}",friendMapper.selectAcceptedFriends(userId));
        model.addAttribute("messages", chatMapper.getMessagesByRoomId("room1")); // 더미 방 ID
        return "chat/chatMain";
    }


}



