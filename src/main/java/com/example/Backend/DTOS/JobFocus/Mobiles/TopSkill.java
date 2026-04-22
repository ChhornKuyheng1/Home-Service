package com.example.Backend.DTOS.JobFocus.Mobiles;

public class TopSkill {
    private Long id;
    private String name;

    public TopSkill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
