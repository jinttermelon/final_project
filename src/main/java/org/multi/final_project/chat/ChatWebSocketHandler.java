package org.multi.final_project.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, Set<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = getRoomId(session);
        chatRooms.computeIfAbsent(roomId, k -> new HashSet<>()).add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String roomId = getRoomId(session);
        log.info("received text message: " + message.getPayload());
        ChatMessageVO vo = new ObjectMapper().readValue(message.getPayload(), ChatMessageVO.class);

        // DB 저장
        chatMapper.insertMessage(vo);
        for (WebSocketSession s : chatRooms.getOrDefault(roomId, Set.of())) {
            s.sendMessage(message); // broadcast
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        chatRooms.values().forEach(sessions -> sessions.remove(session));
    }

    private String getRoomId(WebSocketSession session) {
        // 예: /ws/chat?room_id=123
        String room_id = session.getUri().getQuery().split("=")[1];
        log.info("room_id: " + room_id);
        return room_id;
    }
}

