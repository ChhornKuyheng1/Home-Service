package com.example.Backend.Repositories;

import com.example.Backend.Models.Services;
import com.example.Backend.Models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services,Long> {

    @Query(value = "SELECT COUNT(jobs.id) FROM jobs " +
            "WHERE jobs.service_id=:id",nativeQuery = true)
    Long countTotalJob(@Param("id")Long id);

    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.service_id=:id",nativeQuery = true)
    Long countTotalBooking(@Param("id")Long id);

    Optional<Services> findByName(String name);

    @Query(value = "SELECT * FROM services " +
            "WHERE name=:name " +
            "AND id!=:id",nativeQuery = true)
    Optional<Services> findByNameAndId(@Param("name")String name,@Param("id") Long id);

    @Query(value = "SELECT COUNT(skills.id) FROM skills " +
            "WHERE service_id=:id",nativeQuery = true)
    Long countServiceInSkill(@Param("id") Long id);

    // Report

    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id = bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id = job_focus.job_id " +
            "WHERE jobs.service_id = :serviceId " +
            "AND job_focus.skill_id = :skillId " +
            "AND bookings.status = :status",nativeQuery = true)
    Long countByServiceIdAndSkill(@Param("serviceId") Long serviceId,@Param("skillId")Long skillId,@Param("status") String status);

    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id = bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id = job_focus.job_id " +
            "WHERE jobs.service_id = :serviceId " +
            "AND job_focus.skill_id = :skillId ",nativeQuery = true)
    Long countAllByServiceIdAndSkill(@Param("serviceId") Long serviceId,@Param("skillId") Long skillId);

    @Query(value = "SELECT COUNT(job_focus.id) FROM job_focus " +
            "WHERE job_focus.skill_id=:id",nativeQuery = true)
    Long countProviderUseSkill(@Param("id") Long skillId);

    @Query(value = "SELECT COUNT(job_focus.id) FROM job_focus WHERE status='Active'",nativeQuery = true)
    Long countJob();

    long count();

    @Query(value = "SELECT COUNT(skills.id) FROM skills",nativeQuery = true)
    Long countSkill();

    @Query(value = "SELECT * FROM skills " +
            "WHERE skills.service_id=:id",nativeQuery = true)
    List<Skill> findSkillByServiceId(@Param("id") Long  serviceId);

}
