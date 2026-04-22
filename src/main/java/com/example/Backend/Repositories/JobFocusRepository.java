package com.example.Backend.Repositories;

import com.example.Backend.Models.JobFocus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobFocusRepository extends JpaRepository<JobFocus,Long> {

    long countByJob_Id(Long id);

    Long countByJob_IdAndStatus(Long jobId,String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE job_focus INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "SET job_focus.top=:top " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    void setAllTopToFalseByProviderId(@Param("id") Long id,@Param("top") boolean top);

    @Modifying
    @Transactional
    @Query(value = "UPDATE job_focus SET status=:status " +
            "WHERE job_id=:id",nativeQuery = true)
    void setStatusByJobStatus(@Param("id")Long id,@Param("status") String status);

    @Query(value = "SELECT job_focus.* FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_Id=:id",nativeQuery = true)
    List<JobFocus> findByProviderId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE job_focus INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "SET status=:status",nativeQuery = true)
    void setStatusByProviderIdAndStatus(@Param("id")Long id,@Param("status") String status);

    @Query(value = "SELECT MAX(job_focus.duration) FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Long findByMaxDuration(@Param("id") Long id);
}
