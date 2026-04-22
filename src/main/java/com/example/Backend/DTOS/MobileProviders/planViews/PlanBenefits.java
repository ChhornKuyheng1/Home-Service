package com.example.Backend.DTOS.MobileProviders.planViews;

public class PlanBenefits {
    private int maxService;
    private int maxSkill;

    public PlanBenefits(int maxService, int maxSkill) {
        this.maxService = maxService;
        this.maxSkill = maxSkill;
    }

    public int getMaxService() {
        return maxService;
    }

    public void setMaxService(int maxService) {
        this.maxService = maxService;
    }

    public int getMaxSkill() {
        return maxSkill;
    }

    public void setMaxSkill(int maxSkill) {
        this.maxSkill = maxSkill;
    }
}
