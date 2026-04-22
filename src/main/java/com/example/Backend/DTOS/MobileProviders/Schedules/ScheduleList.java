package com.example.Backend.DTOS.MobileProviders.Schedules;

import java.time.LocalTime;

public class ScheduleList {
    private Long id;
    private String skill;
    private String username;
    private LocalTime startTime;
    private long duration;
    private String status;
    private String image;

    public ScheduleList(Long id, String skill, String username, LocalTime startTime, long duration, String status, String image) {
        this.id = id;
        this.skill = skill;
        this.username = username;
        this.startTime = startTime;
        this.duration = duration;
        this.status = status;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
