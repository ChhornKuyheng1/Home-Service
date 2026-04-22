package com.example.Backend.DTOS.Providers;

public class ProviderAnalytics {
    private Long totalJob;
    private Long completeJob;
    private double completeRate;
    private double avgRate;

    public ProviderAnalytics(Long totalJob, Long completeJob, double completeRate, double avgRate) {
        this.totalJob = totalJob;
        this.completeJob = completeJob;
        this.completeRate = completeRate;
        this.avgRate = avgRate;
    }

    public Long getTotalJob() {
        return totalJob;
    }

    public void setTotalJob(Long totalJob) {
        this.totalJob = totalJob;
    }

    public Long getCompleteJob() {
        return completeJob;
    }

    public void setCompleteJob(Long completeJob) {
        this.completeJob = completeJob;
    }

    public double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(double completeRate) {
        this.completeRate = completeRate;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }
}
