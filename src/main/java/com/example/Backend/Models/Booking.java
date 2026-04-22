package com.example.Backend.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false)
    private JobFocus jobFocus;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;
    @Column(nullable = false)
    private LocalDate workDate;
    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false,columnDefinition = "TIME(0)")
    private LocalTime workTime;
    private LocalDateTime completeDate;
    @Column(nullable = false,columnDefinition = "DATETIME(0)")
    private LocalDateTime bookingDate;
    @Column(nullable = false)
    private String status;
    private double finalPrice;
    private LocalDateTime confirmDate;
    private LocalDateTime inProgressDate;
    public Booking() {
    }

    @PrePersist
    @PreUpdate
    public void truncateSeconds(){
        if(bookingDate!=null){ bookingDate = bookingDate.truncatedTo(ChronoUnit.MINUTES);}
    }

    public Booking(User user,JobFocus jobFocus, LocalDate workDate, LocalTime workTime, LocalDateTime bookingDate, String status, Address address) {
        this.user = user;
        this.jobFocus = jobFocus;
        this.workDate = workDate;
        this.workTime = workTime;
        this.bookingDate = bookingDate;
        this.status = status;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobFocus getJobFocus() {
        return jobFocus;
    }

    public void setJobFocus(JobFocus jobFocus) {
        this.jobFocus = jobFocus;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(LocalTime workTime) {
        this.workTime = workTime;
    }

    public LocalDateTime getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDateTime completeDate) {
        this.completeDate = completeDate;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDateTime confirmDate) {
        this.confirmDate = confirmDate;
    }

    public LocalDateTime getInProgressDate() {
        return inProgressDate;
    }

    public void setInProgressDate(LocalDateTime inProgressDate) {
        this.inProgressDate = inProgressDate;
    }
}
