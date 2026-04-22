package com.example.Backend.Utils;

import com.example.Backend.Components.WebSocketUserRegistry;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class JwtWebSocketInterceptor implements ChannelInterceptor {
    private final WebSocketUserRegistry userRegistry;

    public JwtWebSocketInterceptor(WebSocketUserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (authHeader != null) {
                String userId = JwtUtil.getUserId(authHeader);
                String role = JwtUtil.getUserRole(authHeader);
                accessor.setUser(() -> userId);
                accessor.getSessionAttributes().put("ROLE", role);
                this.userRegistry.addUser(accessor.getUser().getName(),role);
            }
        }
        if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {

            if (accessor.getUser() != null) {
                String userId = accessor.getUser().getName();
                this.userRegistry.removeUser(userId);
            }
        }

        return message;
    }
}
