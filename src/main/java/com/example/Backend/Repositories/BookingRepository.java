package com.example.Backend.Repositories;

import com.example.Backend.DTOS.BookingDate;
import com.example.Backend.DTOS.BookingJob;
import com.example.Backend.DTOS.Bookings.BookingPerMonth;
import com.example.Backend.DTOS.WorkDateAndWorkTime;
import com.example.Backend.DTOS.WorkTime;
import com.example.Backend.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    long count();

    @Query(value = "SELECT bookings.work_date , bookings.work_time , job_focus.duration  " +
            "FROM bookings INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status!='Completed' " +
            "AND bookings.status!='Cancelled' " +
            "ORDER BY bookings.work_date DESC" ,nativeQuery = true)
    List<WorkDateAndWorkTime> findBookingByWorkDate(@Param("id") Long providerId);

    @Query(value = "SELECT * FROM bookings " +
            "WHERE DATE(booking_date)=:date " +
            "AND status='Requested' ",nativeQuery = true)
    List<Booking> findByBookingDateRequest(@Param("date") LocalDate date);

    @Query(value =
            "SELECT t.work_date AS workDate, " +
                    "t.work_time AS workTime, " +
                    "t.duration AS duration " +
                    "FROM ( " +
                    "   SELECT b.work_date, b.work_time, jf.duration, " +
                    "   ROW_NUMBER() OVER ( " +
                    "       PARTITION BY b.work_date " +
                    "       ORDER BY jf.duration DESC " +
                    "   ) as rn " +
                    "   FROM bookings b " +
                    "   INNER JOIN job_focus jf ON jf.id = b.job_focus_id " +
                    "   INNER JOIN jobs j ON j.id = jf.job_id " +
                    "   WHERE j.provider_id = :id " +
                    "   AND b.status NOT IN ('Completed', 'Cancelled') " +
                    ") t " +
                    "WHERE t.rn = 1 " +
                    "ORDER BY t.work_date DESC",
            nativeQuery = true)
    List<BookingJob> findWorkDateAndAndDateAndDuration(@Param("id")Long providerId);

    @Query(value = "SELECT bookings.work_date AS workDate, CAST(SUM(job_focus.duration) AS SIGNED) AS time " +
            "FROM bookings INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status !='Completed' " +
            "AND bookings.status !='Cancelled' " +
            "GROUP BY bookings.work_date " +
            "ORDER BY bookings.work_date DESC",nativeQuery = true)
    List<BookingDate> sumBookingTimeByWorkDate(@Param("id")Long id);

    @Query(value = "SELECT bookings.* FROM bookings INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status !='Completed' " +
            "AND bookings.status !='Cancelled' " +
            "AND bookings.work_date=:date",nativeQuery = true)
    List<Booking> findByProviderIdAndWorkDate(@Param("id")Long id,@Param("date")LocalDate date);

    @Query(value = "SELECT bookings.* FROM bookings INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status !='Completed' " +
            "AND bookings.status !='Cancelled' ",nativeQuery = true)
    List<Booking> findByProviderIdAndWorkDate(@Param("id")Long id);

    //Providers
    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "ORDER BY bookings.booking_date DESC ",nativeQuery = true)
    List<Booking> findByProviderId(@Param("id") Long id);

    @Query(value = "SELECT * FROM bookings " +
            "WHERE work_date=:date",nativeQuery = true)
    List<Booking> findByWord_Date(@Param("date") LocalDate date);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND DATE(bookings.booking_date)=:date " +
            "ORDER BY bookings.booking_date DESC ",nativeQuery = true)
    List<Booking> findByProviderIdAndBookingDate(@Param("id") Long id,@Param("date") LocalDate date);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status=:status " +
            "ORDER BY bookings.work_date ASC , bookings.work_time ASC ",nativeQuery = true)
    List<Booking> findByProviderIdAndStatus(@Param("id") Long id,@Param("status") String status);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "ORDER BY bookings.complete_date DESC ",nativeQuery = true)
    List<Booking> findByProviderIdAndComplete(@Param("id") Long id);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Cancelled' " +
            "ORDER BY bookings.booking_date DESC ",nativeQuery = true)
    List<Booking> findByProviderIdAndCancelled(@Param("id") Long providerId);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Requested' " +
            "ORDER BY bookings.booking_date DESC ",nativeQuery = true)
    List<Booking> findByProviderIdAndRequest(@Param("id") Long id);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status=:status " +
            "AND DATE(bookings.booking_date)=:date " +
            "ORDER BY bookings.booking_date DESC ",nativeQuery = true)
    List<Booking> findByProviderIdAndStatusAndBookingDate(@Param("id")Long id,@Param("status")String status,@Param("date") LocalDate date);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status=:status " +
            "AND bookings.work_date=:date " +
            "ORDER BY bookings.work_date ASC , bookings.work_time ASC ",nativeQuery = true)
    List<Booking> findByProviderIdAndStatusAndWorkDate(@Param("id")Long id,@Param("status")String status,@Param("date") LocalDate date);

    @Query(value = "SELECT bookings.* FROM bookings "+
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id "+
            "INNER JOIN jobs ON jobs.id=job_focus.job_id "+
            "INNER JOIN services ON services.id=jobs.service_id "+
            "WHERE jobs.provider_id=:id "+
            "AND services.id=:service " +
            "ORDER BY bookings.work_date ASC ",nativeQuery = true)
    List<Booking> findInProgressByProviderIdAndServiceId(@Param("id")Long id, @Param("service") Long serviceId);

    @Query(value = """
              SELECT 
              m.month,
              COUNT(t.id) AS total
              FROM (
                    SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                    UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8
                    UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
                    ) m
                     LEFT JOIN bookings t
                     ON MONTH(t.booking_date) = m.month
                     AND YEAR(t.booking_date) = :year
                     GROUP BY m.month
                     ORDER BY m.month
                     """, nativeQuery = true)
    List<BookingPerMonth> getBookingPerMonth(int year);

    Long countByStatus(String status);

    @Query(value = "SELECT * FROM bookings " +
            "ORDER BY DATE(booking_date) DESC " +
            "LIMIT 4",nativeQuery = true)
    List<Booking> findAll4Date();

    @Query(value = """
        SELECT date,
               SUM(TIMESTAMPDIFF(HOUR, prev_time, time_point)) AS total
        FROM (
            SELECT date,
                   work_time,
                   LAG(work_time) OVER (PARTITION BY date ORDER BY work_time) AS prev_time
            FROM bookings INNER JOIN job_focus ON job_focus.id = bookings.job_focus_id INNER JOIN jobs ON jobs.id = job_focus.job_id
        ) t
        WHERE prev_time IS NOT NULL AND jobs.provider_id = :id
        GROUP BY date
    """, nativeQuery = true)
    List<WorkTime> sumHoursByDate(@Param("id") Long providerId);

    @Query(value = "SELECT bookings.* FROM bookings WHERE bookings.id=:id",nativeQuery = true)
    Booking findBy_Id(@Param("id")Long id);

    //Users
    @Query(value = "SELECT bookings.* FROM bookings " +
            "WHERE bookings.user_id=:id " +
            "AND bookings.status=:status " +
            "ORDER BY :date ",nativeQuery = true)
    List<Booking> findByUser_IdAndStatus(@Param("id") Long userId,@Param("status") String status,@Param("date") String date);

    Long countByUser_IdAndStatus(Long userId,String status);

    Long countByUser_Id(Long userId);

    @Query(value = "SELECT bookings.* FROM bookings " +
            "WHERE bookings.user_id=:id " +
            "ORDER BY bookings.booking_date DESC ",nativeQuery = true)
    List<Booking> findByUser_Id(@Param("id") Long userId);

    



}
