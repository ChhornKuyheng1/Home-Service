package com.example.Backend.Repositories;

import com.example.Backend.DTOS.AdminDashboards.Earning;
import com.example.Backend.Models.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Long countByProvider_IdAndStatus(Long id,String status);

    long count();

    @Query(value = "SELECT SUM(total) FROM transactions WHERE status=:status",nativeQuery = true)
    Double sumPriceByStatus(@Param("status") String status);

    long countByStatus(String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE transactions SET status=:status " +
            "WHERE provider_id=:id " +
            "AND status=:oldStatus",nativeQuery = true)
    void update(@Param("id") Long id,@Param("oldStatus") String oldStatus,@Param("status") String status);

    @Query(value = """
              SELECT 
              m.month,
              COALESCE(SUM(t.total),0.0) AS total
              FROM (
                    SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                    UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8
                    UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
                    ) m
                     LEFT JOIN transactions t
                     ON MONTH(t.pay_date) = m.month
                     AND YEAR(t.pay_date) = :year
                     AND t.status = 'Completed'
                     GROUP BY m.month
                     ORDER BY m.month
                     """, nativeQuery = true)
    List<Earning> getEarnings(int year);
}
