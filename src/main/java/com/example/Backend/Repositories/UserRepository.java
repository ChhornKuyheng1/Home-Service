package com.example.Backend.Repositories;

import com.example.Backend.DTOS.AdminDashboards.UserReports.CustomerGrowthPerMonth;
import com.example.Backend.DTOS.Bookings.BookingPerMonth;
import com.example.Backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByTel(String tel);

    long count();

    @Query(value = """
              SELECT 
              m.month,
              COUNT(t.id) AS total
              FROM (
                    SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                    UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8
                    UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
                    ) m
                     LEFT JOIN users t
                     ON MONTH(t.create_date) = m.month
                     AND YEAR(t.create_date) = :year
                     GROUP BY m.month
                     ORDER BY m.month
                     """, nativeQuery = true)
    List<CustomerGrowthPerMonth> getUserPerMonth(int year);

}
