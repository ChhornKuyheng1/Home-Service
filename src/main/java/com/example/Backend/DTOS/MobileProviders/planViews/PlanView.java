package com.example.Backend.DTOS.MobileProviders.planViews;

import java.util.List;

public class PlanView {
    private Plan plan;
    private PlanBenefits planBenefits;
    private List<BillingHistory> billingHistory;

    public PlanView(Plan plan, PlanBenefits planBenefits, List<BillingHistory> billingHistory) {
        this.plan = plan;
        this.planBenefits = planBenefits;
        this.billingHistory = billingHistory;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public PlanBenefits getPlanBenefits() {
        return planBenefits;
    }

    public void setPlanBenefits(PlanBenefits planBenefits) {
        this.planBenefits = planBenefits;
    }

    public List<BillingHistory> getBillingHistory() {
        return billingHistory;
    }

    public void setBillingHistory(List<BillingHistory> billingHistory) {
        this.billingHistory = billingHistory;
    }
}
