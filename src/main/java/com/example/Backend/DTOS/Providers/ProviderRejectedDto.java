package com.example.Backend.DTOS.Providers;

public class ProviderRejectedDto {

    private Long id;
    private String name;
    private String email;
    private String Tel;
    private String requestDate;
    private  String rejectDate;
    private  String reason;
    private  String status;
    private String image;
    public ProviderRejectedDto(Long id, String name, String email, String tel, String requestDate, String rejectDate, String reason,String status,String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        Tel = tel;
        this.requestDate = requestDate;
        this.rejectDate = rejectDate;
        this.reason = reason;
        this.status = status;
        this.image =image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
