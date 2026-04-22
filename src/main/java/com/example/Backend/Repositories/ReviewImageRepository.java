package com.example.Backend.Repositories;

import com.example.Backend.Models.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {

    @Query(value = "SELECT image FROM review_images WHERE review_id=:id",nativeQuery = true)
    List<String> findByReviewId(@Param("id") Long id);
}
