package com.example.Backend.Repositories;

import com.example.Backend.Models.Job;
import com.example.Backend.Models.JobFocus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    int countByProvider_Id(Long id);

    List<Job> findByProvider_Id(Long id);

    int countByProvider_IdAndStatus(Long id,String status);

    Job findByProvider_IdAndService_Id(Long providerId,Long serviceId);

    @Query(value = "SELECT services.name FROM jobs " +
            "INNER JOIN services ON services.id=jobs.service_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    List<String> findServiceByProviderId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE jobs SET status='Inactive' " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    void setInActiveAllByProviderId(@Param("id") Long id);

    @Query(value = "SELECT * FROM job_focus " +
            "WHERE job_focus.job_id=:id", nativeQuery = true)
    List<JobFocus> findJobFocusByJobId(@Param("id") Long id);
}
