package com.example.Backend.Repositories;

import com.example.Backend.DTOS.Categorys.Mobiles.ServiceDto;
import com.example.Backend.Models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill,Long> {

    @Query(value = "SELECT * FROM skills " +
            "WHERE skills.service_id=:id " +
            "AND skills.id NOT IN (SELECT job_focus.skill_id FROM job_focus " +
            "WHERE job_focus.job_id=:job_id )",nativeQuery = true)
    List<Skill> findByService_Id(@Param("id") Long id,@Param("job_id") Long jobId);

    Optional<Skill> findByName(String name);

    @Query(value = "SELECT COUNT(job_focus.id) FROM job_focus " +
            "WHERE job_focus.skill_id=:id",nativeQuery = true)
    Long countTotalJobFocus(@Param("id") Long id);

    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "WHERE job_focus.skill_id=:id",nativeQuery = true)
    Long countTotalBooking(@Param("id") Long id);

    @Query(value = "SELECT * FROM skills " +
            "WHERE name=:name " +
            "AND id!=:id",nativeQuery = true)
    Optional<Skill> findByNameAndId(@Param("name") String name,@Param("id") Long id);

    @Query(value = "SELECT skills.id AS id , skills.name AS name , skills.image AS image " +
            "FROM job_focus INNER JOIN skills ON skills.id=job_focus.skill_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "INNER JOIN providers ON providers.user_id=jobs.provider_id " +
            "WHERE (6371 * ACOS(COS(RADIANS(:userLat)) * COS(RADIANS(providers.lat)) * COS(RADIANS(providers.lon) - RADIANS(:userLon))+SIN(RADIANS(:userLat)) * SIN(RADIANS(providers.lat)))) <= providers.service_radius " +
            "GROUP BY skills.id , skills.name , skills.image "
            ,nativeQuery = true)
    List<ServiceDto> findSkillNearCustomer(@Param("userLat") Double userLat, @Param("userLon") Double userLon);


    List<Skill> findByService_Id(Long id);
}
