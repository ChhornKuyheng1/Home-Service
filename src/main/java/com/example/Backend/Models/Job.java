package com.example.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false,name = "provider_id")
    private Provider provider;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Services service;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private LocalDate createDate;

    public Job(Provider provider, Services service, String status, LocalDate createDate) {
        this.provider = provider;
        this.service = service;
        this.status = status;
        this.createDate = createDate;
    }

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
