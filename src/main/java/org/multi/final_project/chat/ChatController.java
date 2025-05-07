package org.multi.final_project.chat;

import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private UserMapper userMapper;

    public ChatController(ChatMapper chatMapper) {
        log.info("ChatController");
        this.chatMapper = chatMapper;
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
    public String enterRoom(@PathVariable String room_id, Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/user/login"; // 로그인 안 했으면 로그인 페이지로
//        }
        String dummyNickname = "더미유저";
        model.addAttribute("nickname", dummyNickname);

        model.addAttribute("room_id", room_id);
        model.addAttribute("messages_history", chatMapper.getMessagesByRoomId(room_id));

        return "chat/room";
    }
}



