package com.example.Backend.DTOS.AdminDashboards.PlanReports;

import java.util.List;

public class PlanReport {
    private PlanPriView priView;
    private List<PlanReportList> plans;

    public PlanReport(PlanPriView priView, List<PlanReportList> plans) {
        this.priView = priView;
        this.plans = plans;
    }

    public PlanPriView getPriView() {
        return priView;
    }

    public void setPriView(PlanPriView priView) {
        this.priView = priView;
    }

    public List<PlanReportList> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanReportList> plans) {
        this.plans = plans;
    }
}
