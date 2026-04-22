package com.example.Backend.Repositories;

import com.example.Backend.Models.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "SELECT * FROM notifications " +
            "WHERE user_id=:id " +
            "ORDER BY notifications.create_date DESC ",nativeQuery = true)
    List<Notification> findByUserIdAndIsRead(@Param("id") Long userId);

    @Query(value = "SELECT * FROM notifications " +
            "WHERE type=:type " +
            "AND user_id=:userId",nativeQuery = true)
    Notification findByTypes(@Param("type") String type,@Param("userId") Long id);

    @Query(value = "SELECT * FROM notifications " +
            "WHERE type=:type ",nativeQuery = true)
    List<Notification> findByType(@Param("type") String type);


    @Query(value = "SELECT * FROM notifications " +
            "WHERE user_id=:id " +
            "AND type='SUBSCRIPTION' " +
            "AND YEAR(create_date)=:year",nativeQuery = true)
    Optional<Notification> findByUserIdAndTitleAndYear(@Param("id") Long userId,@Param("year") int year);

    @Modifying
    @Transactional
    @Query(value = "UPDATE notifications SET is_read=1 " +
            "WHERE user_id=:id AND is_read=0 ",nativeQuery = true)
    void  updateReadAllByUserId(@Param("id")Long userId);
}

