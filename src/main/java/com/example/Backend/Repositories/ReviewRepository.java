package com.example.Backend.Repositories;

import com.example.Backend.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "SELECT reviews.* FROM bookings INNER JOIN reviews ON reviews.booking_id = bookings.id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    List<Review> findByProviderId(@Param("id")Long providerId);

    @Query(value = "SELECT COUNT(reviews.booking_id) FROM bookings INNER JOIN reviews ON reviews.booking_id = bookings.id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Long countByProviderId(@Param("id")Long providerId);

    @Query(value = "SELECT reviews.* FROM reviews " +
            "INNER JOIN bookings ON bookings.id=reviews.booking_id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "WHERE job_focus.id=:id",nativeQuery = true)
    List<Review> findByJobFocusId(@Param("id") Long jobFocusId);

    @Query(value = "SELECT COUNT(reviews.booking_id) FROM reviews " +
            "INNER JOIN bookings ON bookings.id=reviews.booking_id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "WHERE job_focus.id=:id",nativeQuery = true)
    Long countByJobFocusId(@Param("id") Long jobFocusId);

    @Query(value = "SELECT SUM(reviews.rate) FROM reviews " +
            "INNER JOIN bookings ON bookings.id=reviews.booking_id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "WHERE job_focus.id=:id",nativeQuery = true)
    Long sumByJobFocusId(@Param("id") Long jobFocusId);


}
