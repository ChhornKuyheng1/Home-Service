package com.example.Backend.Repositories;

import com.example.Backend.DTOS.TopName;
import com.example.Backend.Models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    Subscription findByProvider_Id(long id);

    long count();

    long countByStatus(String status);

    @Query(value = "SELECT plans.name AS name , COUNT(subscriptions.plan_id) AS total " +
            "FROM subscriptions " +
            "INNER JOIN plans ON plans.id=subscriptions.plan_id " +
            "GROUP BY plans.name " +
            "ORDER BY total DESC " +
            "LIMIT 1 ",nativeQuery = true)
    TopName findTopPlan();
}
