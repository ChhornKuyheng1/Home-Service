package com.example.Backend.Repositories;

import com.example.Backend.DTOS.AdminDashboards.PlanReports.PlanReportList;
import com.example.Backend.DTOS.PlanNameDto;
import com.example.Backend.Models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Long> {

    Plan findByStatus(String status);
    Optional<Plan> findByName(String name);

    @Query(value = "SELECT COUNT(subscriptions.provider_id) FROM subscriptions " +
            "WHERE subscriptions.plan_id=:id",nativeQuery = true)
    Long countTotalSubscriptionPlan(@Param("id") Long id);

    @Query(value = "SELECT * FROM plans " +
            "WHERE plans.name=:name " +
            "AND plans.id!=:id",nativeQuery = true)
    Optional<Plan> findByNameAndId(@Param("name") String name,@Param("id") Long id);

    Plan findByPrice(double price);

    List<Plan> findByDurationAndStatus(String duration,String status);

    @Query(value = "SELECT COUNT(transactions.id) FROM transactions " +
            "WHERE plan_id=:id",nativeQuery = true)
    Long countPlanInTransaction(@Param("id") Long id);

    @Query(value = "SELECT id AS id, name AS name FROM plans",nativeQuery = true)
    List<PlanNameDto> findAllPlan();

    @Query(value = "SELECT p.id, p.name, p.price, p.total, p.active, p.expired , SUM(CASE WHEN transactions.status='Completed' THEN transactions.total ELSE 0 END) AS revenue " +
            "FROM(" +
            "SELECT plans.id AS id, " +
            "plans.name AS name, " +
            "plans.price AS price, " +
            "COUNT(subscriptions.provider_id) AS total, " +
            "CAST(SUM(CASE WHEN subscriptions.status = 'Active' THEN 1 ELSE 0 END) AS SIGNED) AS active , " +
            "CAST(SUM(CASE WHEN subscriptions.status= 'Expired' THEN 1 ELSE 0 END) AS SIGNED) AS expired " +
            "FROM plans " +
            "LEFT JOIN subscriptions ON subscriptions.plan_id=plans.id " +
            "GROUP BY plans.id , plans.name , plans.price" +
            " ) AS p " +
            "LEFT JOIN transactions ON transactions.plan_id = p.id " +
            "GROUP BY p.id , p.name , p.price , p.total , p.active , p.expired ",nativeQuery = true)
    List<PlanReportList> findToReport();


}
