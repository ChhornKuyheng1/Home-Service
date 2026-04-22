package com.example.Backend.Events;

public class Event {
    private boolean event;

    public Event(boolean event) {
        this.event = event;
    }

    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }
}
