package com.example.Backend.DTOS.MobileProviders.Insights;

import java.util.List;

public class Insights {
    private Long jobDone;
    private double avg;
    private double rate;
    private Long totalRate;
    private int completion;
    private int jobActive;
    private int jobUse;
    private List<TopServiceInsights> topService;
    public Insights(Long jobDone, double avg, double rate, int completion, int jobActive, int jobUse,List<TopServiceInsights> topService,Long totalRate) {
        this.jobDone = jobDone;
        this.avg = avg;
        this.rate = rate;
        this.completion = completion;
        this.jobActive = jobActive;
        this.jobUse = jobUse;
        this.topService = topService;
        this.totalRate = totalRate;
    }

    public int getJobActive() {
        return jobActive;
    }

    public void setJobActive(int jobActive) {
        this.jobActive = jobActive;
    }

    public int getJobUse() {
        return jobUse;
    }

    public void setJobUse(int jobUse) {
        this.jobUse = jobUse;
    }

    public Long getJobDone() {
        return jobDone;
    }

    public void setJobDone(Long jobDone) {
        this.jobDone = jobDone;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    public Long getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Long totalRate) {
        this.totalRate = totalRate;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public List<TopServiceInsights> getTopService() {
        return topService;
    }

    public void setTopService(List<TopServiceInsights> topService) {
        this.topService = topService;
    }


}
