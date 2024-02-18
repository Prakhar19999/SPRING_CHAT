package com.spring.chat.config;

import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor

public class WebSocketEventListner
{
    @EventListener
    public void handleWebSocketDisconnetHandler(){
        SessionDisconnectEvent event;
    }
}