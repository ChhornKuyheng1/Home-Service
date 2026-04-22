package com.example.Backend.DTOS.Users.MUsers;

import java.time.LocalDate;

public class UserProfileDto {

    private String name;
    private String tel;
    private String image;
    private LocalDate dob;
    private Long totalBooking;
    private Long complete;
    private Long cancel;

    public UserProfileDto( String name, String tel, String image, Long totalBooking, Long complete, Long cancel,LocalDate d) {

        this.name = name;
        this.tel = tel;
        this.image = image;
        this.totalBooking = totalBooking;
        this.complete = complete;
        this.cancel = cancel;
        this.dob = d;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Long totalBooking) {
        this.totalBooking = totalBooking;
    }

    public Long getComplete() {
        return complete;
    }

    public void setComplete(Long complete) {
        this.complete = complete;
    }

    public Long getCancel() {
        return cancel;
    }

    public void setCancel(Long cancel) {
        this.cancel = cancel;
    }
}
