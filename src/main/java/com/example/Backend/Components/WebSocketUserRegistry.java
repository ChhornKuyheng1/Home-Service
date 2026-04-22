package com.example.Backend.Components;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class WebSocketUserRegistry {
    // userId -> role
    private final Map<String, String> users = new ConcurrentHashMap<>();

    // user online
    public void addUser(String userId, String role) {
        users.put(userId, role);
    }

    // user offline
    public void removeUser(String userId) {
        users.remove(userId);
    }

    // check online
    public boolean isOnline(String userId) {
        return users.containsKey(userId);
    }

    // get role
    public String getRole(String userId) {
        return users.get(userId);
    }

    // get all online users
    public Map<String, String> getUsers() {
        return users;
    }
}
