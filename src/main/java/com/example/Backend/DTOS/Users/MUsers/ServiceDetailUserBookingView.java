package com.example.Backend.DTOS.Users.MUsers;

public class ServiceDetailUserBookingView {
    private String jobName;
    private String jobImage;
    private String serviceName;
    private long duration;
    private String price;
    private String description;

    public ServiceDetailUserBookingView(String jobName, String jobImage, String serviceName, long duration, String price, String description) {
        this.jobName = jobName;
        this.jobImage = jobImage;
        this.serviceName = serviceName;
        this.duration = duration;
        this.price = price;
        this.description = description;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobImage() {
        return jobImage;
    }

    public void setJobImage(String jobImage) {
        this.jobImage = jobImage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
