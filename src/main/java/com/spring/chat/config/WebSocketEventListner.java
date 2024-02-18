package com.spring.chat.config;

import com.spring.chat.chat.ChatMessage;
import com.spring.chat.chat.MessageType;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListner
{
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketDisconnetHandler( SessionDisconnectEvent event)
    {
        StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(event.getMessage());
        String username= (String) stompHeaderAccessor.getSessionAttributes().get("username");
        if(username!=null)
        {
            log.info("User Disconnected:{}",username);
            var chatMessage= ChatMessage.builder().messageType(MessageType.LEAVE).sender(username).build();
            messageTemplate.convertAndSend("/topic/public",chatMessage);
        }
    }
}