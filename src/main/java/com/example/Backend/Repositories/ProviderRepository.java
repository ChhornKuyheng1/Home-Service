package com.example.Backend.Repositories;

import com.example.Backend.DTOS.AdminDashboards.ProviderReports.ProviderGrowthPerMonth;
import com.example.Backend.DTOS.JobFocus.Mobiles.ServiceNear;
import com.example.Backend.DTOS.JobFocus.Mobiles.ServiceNearView;
import com.example.Backend.DTOS.MobileProviders.Homes.Completed;
import com.example.Backend.DTOS.Providers.ProviderApproveDto;
import com.example.Backend.DTOS.Providers.ProviderNear;
import com.example.Backend.DTOS.Providers.ProviderNearView;
import com.example.Backend.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider,Long> {

    long count();

    Optional<Provider> findByEmail(String email);

    @Query(value = "SELECT email FROM providers WHERE email=:email AND user_id!=:user_id ",nativeQuery = true)
    Optional<String> findByEmailAndUserId(@Param("email") String email , @Param("user_id") Long userId);

    @Query(value = "SELECT COUNT(user_id) FROM providers " +
            "WHERE job_status=:status",nativeQuery = true)
    Long countByjob_status(@Param("status") String status);

    @Query(value = "SELECT * FROM providers " +
            "WHERE job_status=:status",nativeQuery = true)
    List<Provider> findByjob_status(@Param("status") String status);

    //Revenue Analytics To Provider

    // 1 day
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings "+
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id "+
            "INNER JOIN jobs ON jobs.id=job_focus.job_id "+
            "WHERE jobs.provider_id=:id "+
            "AND bookings.status='Completed' " +
            "AND DATE(bookings.complete_date)=:date",nativeQuery = true)
    Double totalRevenue1Day(@Param("id") Long id, @Param("date") LocalDate date);

    // All day
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings "+
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id "+
            "INNER JOIN jobs ON jobs.id=job_focus.job_id "+
            "WHERE jobs.provider_id=:id "+
            "AND bookings.status='Completed'",nativeQuery = true)
    Double totalRevenueAllDay(@Param("id") Long id);

    // 7 day
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id " +
            "AND DATE(bookings.complete_date)<=:date1 " +
            "AND DATE(bookings.complete_date)>=:date2 " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Double  totalRevenue7Day(@Param("id") Long id,@Param("date1") LocalDate date1,@Param("date2")LocalDate date2,@Param("month") int month);

    // 1 Month
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Double totalRevenue1Month(@Param("id") Long id,@Param("month") int month);

    // Sum All
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id ",nativeQuery = true)
    Double  totalRevenueAll(@Param("id") Long id);


    // Count 1 Day
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings "+
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id "+
            "INNER JOIN jobs ON jobs.id=job_focus.job_id "+
            "WHERE jobs.provider_id=:id "+
            "AND bookings.status='Completed' " +
            "AND DATE(bookings.complete_date)=:date",nativeQuery = true)
    Long count1Day(@Param("id")Long id,@Param("date")LocalDate date);

    // Count 7 Day
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id " +
            "AND DATE(bookings.complete_date)<=:date1 " +
            "AND DATE(bookings.complete_date)>=:date2 " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Long count7Day(@Param("id") Long id,@Param("date1") LocalDate date1,@Param("date2")LocalDate date2,@Param("month") int month);

    //Count 1 Month
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Long count1Month(@Param("id") Long id,@Param("month") int month);

    // Max Price 1 Day
    @Query(value = "SELECT MAX(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id "+
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND DATE(bookings.complete_date)=:date",nativeQuery = true)
    Double  maxPrice1Day(@Param("id") Long id, @Param("date") LocalDate date);

    //Max Price All
    @Query(value = "SELECT MAX(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id "+
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed'",nativeQuery = true)
    Double  maxPriceAll(@Param("id") Long id);

    // Max Price 7 Day
    @Query(value = "SELECT MAX(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id " +
            "AND DATE(bookings.complete_date)<=:date1 " +
            "AND DATE(bookings.complete_date)>=:date2 " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Double  maxPrice7Day(@Param("id") Long id,@Param("date1") LocalDate date1,@Param("date2")LocalDate date2,@Param("month") int month);

    // Max 1 Month
    @Query(value = "SELECT MAX(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Double  maxPrice1Month(@Param("id") Long id,@Param("month") int month);

    // Top Service 1 Day
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND jobs.service_id=:service " +
            "AND DATE(bookings.complete_date)=:date",nativeQuery = true)
    Long countBookingByService1Day(@Param("id")Long id,@Param("service")Long serviceId,@Param("date")LocalDate date);

    // Top Service All
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND jobs.service_id=:service ",nativeQuery = true)
    Long countBookingByServiceAllDay(@Param("id")Long id,@Param("service")Long serviceId);

    // Top Service 7 Day
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND jobs.service_id=:service " +
            "AND DATE(bookings.complete_date)<=:date1 " +
            "AND DATE(bookings.complete_date)>=:date2 " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Long countBookingByService7Day(@Param("id")Long id,@Param("service")Long serviceId,@Param("date1")LocalDate date1,@Param("date2")LocalDate date2,@Param("month") int month);

    // Top Service 1 Month
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.service_id=:service " +
            "AND jobs.provider_id=:id " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Long countBookingByService1Month(@Param("id") Long id,@Param("service")Long serviceId,@Param("month") int month);

    // Total Price By Service By 1 Day
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND jobs.service_id=:service " +
            "AND DATE(bookings.complete_date)=:date",nativeQuery = true)
    Double  totalPriceBookingByService1Day(@Param("id")Long id,@Param("service")Long serviceId,@Param("date")LocalDate date);

    // Total Price By Service By All Day
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND jobs.service_id=:service",nativeQuery = true)
    Double  totalPriceBookingByServiceAllDay(@Param("id")Long id,@Param("service")Long serviceId);


    // Total Price By Service By 7 Day
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "AND jobs.service_id=:service " +
            "AND DATE(bookings.complete_date)<=:date1 " +
            "AND DATE(bookings.complete_date)>=:date2 " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Double  totalPriceBookingByService7Day(@Param("id")Long id,@Param("service")Long serviceId,@Param("date1")LocalDate date1,@Param("date2")LocalDate date2,@Param("month") int month);

    // Total Price By Service By 1 Month
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.service_id=:service " +
            "AND jobs.provider_id=:id " +
            "AND MONTH(bookings.complete_date)=:month",nativeQuery = true)
    Long totalPriceBookingByService1Month(@Param("id") Long id,@Param("service")Long serviceId,@Param("month") int month);

    // All Booking
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Long countAllBookingByProviderId(@Param("id")Long id);

    // Insights

    // All Completed Job
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id ",nativeQuery = true)
    Long countAllCompletedBooking(@Param("id")Long id);

    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Cancelled' " +
            "AND jobs.provider_id=:id ",nativeQuery = true)
    Long countAllCancelledBooking(@Param("id")Long providerId);

    // All Completed Price
    @Query(value = "SELECT SUM(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE bookings.status='Completed' " +
            "AND jobs.provider_id=:id ",nativeQuery = true)
    Double totalPriceCompleted(@Param("id")Long id);

    // All Skill Active
    @Query(value = "SELECT COUNT(job_focus.id) FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND job_focus.status='Active' ",nativeQuery = true)
    Long totalJobFocusActive(@Param("id")Long id);

    @Query(value = "SELECT COUNT(job_focus.id) FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Long totalJobFocus(@Param("id")Long id);

    // All
    @Query(value = "SELECT COUNT(bookings.id) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND jobs.service_id=:service",nativeQuery = true)
    Long countBookingByService(@Param("id")Long id,@Param("service")Long serviceId);

    // Get Ration By ProviderId
    @Query(value = "SELECT COUNT(reviews.booking_id) FROM reviews " +
            "INNER JOIN bookings ON bookings.id=reviews.booking_id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Long countRationByProviderId(@Param("id") Long id);

    // Sum Ration By ProviderId
    @Query(value = "SELECT SUM(reviews.rate) FROM reviews " +
            "INNER JOIN bookings ON bookings.id=reviews.booking_id " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Long sumRationByProviderId(@Param("id")Long id);

    // View Subscription Plan
    @Query(value = "SELECT subscriptions.* FROM subscriptions " +
            "WHERE provider_id=:id",nativeQuery = true)
    Subscription findSubscriptionByProviderId(@Param("id")Long id);

    // View History Transaction By ProviderId
    @Query(value = "SELECT transactions.* FROM transactions " +
            "WHERE provider_id=:id " +
            "AND status='Completed'",nativeQuery = true)
    List<Transaction> findTransactionByProviderId(@Param("id") Long id);

    // Get JobFocus
    @Query(value = "SELECT skills.name FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "INNER JOIN skills ON skills.id=job_focus.skill_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    List<String> findJobFocusByProviderId(@Param("id") Long id);

    // Get Top Skill
    @Query(value = "SELECT skills.name FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "INNER JOIN skills ON skills.id=job_focus.skill_id " +
            "WHERE jobs.provider_id=:id " +
            "AND job_focus.top=:top",nativeQuery = true)
    List<String> topSkill(@Param("id") Long id,@Param("top")boolean top);

    @Query(value = "SELECT plans.name FROM subscriptions " +
            "INNER JOIN plans ON plans.id=subscriptions.plan_id " +
            "WHERE subscriptions.provider_id=:id",nativeQuery = true)
    String getPlanName(@Param("id")Long id);

    @Query(value = "SELECT job_focus.* FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "INNER JOIN skills ON skills.id=job_focus.skill_id " +
            "WHERE jobs.provider_id=:id " +
            "AND job_focus.top=:top",nativeQuery = true)
    List<JobFocus> TopJobFocus(@Param("id") Long id,@Param("top")boolean top);

    @Query(value = "SELECT COUNT(jobs.id) FROM jobs " +
            "WHERE jobs.provider_id=:id " +
            "AND jobs.status='Active'",nativeQuery = true)
    Long countActiveJob(@Param("id")Long id);

    @Query(value = "SELECT COUNT(job_focus.id) FROM job_focus " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "AND job_focus.status='Active'",nativeQuery = true)
    Long countActiveJobFocus(@Param("id") Long id);

    @Query(value = "SELECT * FROM jobs " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    List<Job> findJobByProviderId(@Param("id")Long id);

    @Query(value = "SELECT * FROM jobs " +
            "WHERE jobs.provider_id=:id " +
            "AND jobs.status='Active'",nativeQuery = true)
    List<Job> findJobByProviderIdToCustomer(@Param("id")Long id);

    @Query(value = "SELECT * FROM job_focus " +
            "WHERE job_focus.job_id=:id",nativeQuery = true)
    List<JobFocus> findJobFocusByJobId(@Param("id")Long id);

    @Query(value = "SELECT * FROM job_focus " +
            "WHERE job_focus.job_id=:id " +
            "AND job_focus.status='Active'",nativeQuery = true)
    List<JobFocus> findJobFocusByJobIdToCustomer(@Param("id")Long id);

    //Get Provider Near Customer
    @Query(value = "SELECT p.id, p.business_name, p.image " +
            "FROM (" +
            "   SELECT providers.user_id as id, " +
            "   providers.business_name, " +
            "   users.profile as image, " +
            "   providers.service_radius, " +
            "   (6371 * ACOS(" +
            "       COS(RADIANS(MAX(address.lat))) * COS(RADIANS(providers.lat)) " +
            "       * COS(RADIANS(providers.lon) - RADIANS(MAX(address.lon))) " +
            "       + SIN(RADIANS(MAX(address.lat))) * SIN(RADIANS(providers.lat))" +
            "   )) as distance " +
            "   FROM providers " +
            "   CROSS JOIN address " +
            "   INNER JOIN users ON users.id=providers.user_id " +
            "   WHERE address.user_id=:id " +
            "   AND address.status='Default' " +
            "   AND providers.status='Active' " +
            "   AND providers.user_id !=:id " +
            "   GROUP BY providers.user_id, providers.business_name, users.profile, providers.service_radius, providers.lat, providers.lon " +
            ") as p " +
            "WHERE p.distance <= p.service_radius " +
            "ORDER BY p.distance ASC",
            nativeQuery = true)
    List<ProviderNear> findProviderNearCustomer(@Param("id") Long userId);

    @Query(value = "SELECT p.id, p.name, p.image , p.distance AS km " +
            "FROM ( " +
            "   SELECT providers.user_id AS id, " +
            "   providers.business_name AS name, " +
            "   users.profile AS image, " +
            "   providers.service_radius AS serviceRadius, " +
            "   (6371 * ACOS( " +
            "       COS(RADIANS(MAX(address.lat))) * COS(RADIANS(providers.lat)) " +
            "       * COS(RADIANS(providers.lon) - RADIANS(MAX(address.lon))) " +
            "       + SIN(RADIANS(MAX(address.lat))) * SIN(RADIANS(providers.lat)) " +
            "   )) AS distance " +
            "   FROM providers " +
            "   CROSS JOIN address " +
            "   INNER JOIN jobs ON jobs.provider_id = providers.user_id " +
            "   INNER JOIN users ON users.id=providers.user_id " +
            "   WHERE address.user_id = :user_id " +
            "   AND address.status = 'Default' " +
            "   AND jobs.service_id = :service_id " +
            "   AND providers.status='Active' " +
            "   AND providers.user_id !=:user_id " +
            "   GROUP BY providers.user_id, providers.business_name, users.profile, providers.service_radius, providers.lat, providers.lon " +
            ") AS p " +
            "WHERE p.distance <= p.serviceRadius " +
            "ORDER BY p.distance ASC",
            nativeQuery = true)
    List<ProviderNearView> findProviderNearByServiceId(@Param("user_id")Long userId,@Param("service_id") Long serviceId);

    @Query(value = "SELECT p.id, p.name, p.image , p.distance AS km " +
            "FROM ( " +
            "   SELECT providers.user_id AS id, " +
            "   providers.business_name AS name, " +
            "   users.profile AS image, " +
            "   providers.service_radius AS serviceRadius, " +
            "   (6371 * ACOS( " +
            "       COS(RADIANS(MAX(address.lat))) * COS(RADIANS(providers.lat)) " +
            "       * COS(RADIANS(providers.lon) - RADIANS(MAX(address.lon))) " +
            "       + SIN(RADIANS(MAX(address.lat))) * SIN(RADIANS(providers.lat)) " +
            "   )) AS distance " +
            "   FROM providers " +
            "   CROSS JOIN address " +
            "   INNER JOIN jobs ON jobs.provider_id = providers.user_id " +
            "   INNER JOIN job_focus ON job_focus.job_id = jobs.id " +
            "   INNER JOIN skills ON skills.id = job_focus.skill_id " +
            "   INNER JOIN users ON users.id=providers.user_id " +
            "   WHERE address.user_id = :user_id " +
            "   AND address.status = 'Default' " +
            "   AND skills.id=:job_focus_id " +
            "   AND providers.status='Active' " +
            "   AND job_focus.status='Active' " +
            "   AND providers.user_id !=:user_id " +
            "   GROUP BY providers.user_id, providers.business_name, users.profile, providers.service_radius, providers.lat, providers.lon " +
            ") AS p " +
            "WHERE p.distance <= p.serviceRadius " +
            "ORDER BY p.distance ASC",
            nativeQuery = true)
    List<ProviderNearView> findProviderNearByJobFocusId(@Param("user_id")Long id,@Param("job_focus_id") Long jobId);

    @Query(value = "SELECT p.id, p.name, p.image , p.distance AS km " +
            "FROM ( " +
            "   SELECT providers.user_id AS id, " +
            "   providers.business_name AS name, " +
            "   users.profile AS image, " +
            "   providers.service_radius AS serviceRadius, " +
            "   (6371 * ACOS( " +
            "       COS(RADIANS(MAX(address.lat))) * COS(RADIANS(providers.lat)) " +
            "       * COS(RADIANS(providers.lon) - RADIANS(MAX(address.lon))) " +
            "       + SIN(RADIANS(MAX(address.lat))) * SIN(RADIANS(providers.lat)) " +
            "   )) AS distance " +
            "   FROM providers " +
            "   CROSS JOIN address " +
            "   INNER JOIN users ON users.id=providers.user_id " +
            "   WHERE address.user_id =:user_id " +
            "   AND address.status = 'Default' " +
            "   AND providers.status='Active' " +
            "   AND providers.user_id !=:user_id " +
            "   GROUP BY providers.user_id, providers.business_name, users.profile, providers.service_radius, providers.lat, providers.lon " +
            ") AS p " +
            "WHERE p.distance <= p.serviceRadius " +
            "ORDER BY p.distance ASC",
            nativeQuery = true)
    List<ProviderNearView> findProviderNear(@Param("user_id")Long userId);

    @Query(value =
            "SELECT p.id AS id, " +
                    "       p.name AS name, " +
                    "       p.image AS image, " +
                    "       p.provider AS provider, " +
                    "       p.price AS price " +
                    "FROM ( " +
                    "   SELECT " +
                    "       s.id AS id, " +
                    "       s.name AS name, " +
                    "       s.image AS image, " +
                    "       MIN(jf.price1) AS price, " +
                    "       COUNT(pr.user_id) AS provider " +
                    "   FROM job_focus jf " +
                    "   INNER JOIN jobs j ON j.id = jf.job_id " +
                    "   INNER JOIN skills s ON s.id = jf.skill_id " +
                    "   INNER JOIN providers pr ON pr.user_id = j.provider_id " +
                    "   WHERE (6371 * ACOS(COS(RADIANS(:userLat)) * COS(RADIANS(pr.lat)) * COS(RADIANS(pr.lon) - RADIANS(:userLon))+SIN(RADIANS(:userLat)) * SIN(RADIANS(pr.lat)))) <= pr.service_radius " +
                    "   AND pr.status='Active' " +
                    "   AND jf.status='Active' " +
                    "   AND pr.user_id !=:user_id " +
                    "   GROUP BY s.id, s.name, s.image" +
                    ") AS p " ,nativeQuery = true)
    List<ServiceNear> getServiceNear(@Param("userLat") Double userLat, @Param("userLon") Double userLon,@Param("user_id") Long userId);



    Optional<Provider> findByUser_Id(Long userId);


    @Query(value = "SELECT * FROM providers WHERE job_status='Approved'",nativeQuery = true)
    List<Provider> findByProviderApprove();

    @Query(value = """
              SELECT 
              m.month,
              COUNT(t.user_id) AS total
              FROM (
                    SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                    UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8
                    UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
                    ) m
                     LEFT JOIN providers t
                     ON MONTH(t.create_date) = m.month
                     AND YEAR(t.create_date) = :year
                     AND t.job_status ='Approved'
                     GROUP BY m.month
                     ORDER BY m.month
                     """, nativeQuery = true)
    List<ProviderGrowthPerMonth> getGrowthPerMonth(@Param("year") int year);

    @Query(value = "SELECT AVG(bookings.final_price) FROM bookings " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id",nativeQuery = true)
    Double findAvg(@Param("id") Long providerId);

    @Query(value = "SELECT " +
            "FROM (" +
            "SELECT 0 AS complete_job, 'Monday' AS day UNION " +
            "SELECT 1 ,'Tuesday' UNION " +
            "SELECT 2 ,'Wednesday' UNION " +
            "SELECT 3 ,'Thursday' UNION " +
            "SELECT 4 ,'Friday' UNION " +
            "SELECT 5 ,'Saturday' UNION " +
            "SELECT 6 ,'Sunday' " +
            ")d LEFT JOIN bookings ON WEEKDAY(DATE(bookings.complete_date))=d.complete_job " +
            "AND YEARWEEK(DATE(bookings.complete_date),1)=YEARWEEK(CURDATE(),1)  " +
            "INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "WHERE jobs.provider_id=:id " +
            "GROUP BY d.complete_date , d.day " +
            "ORDER BY d.complete_date ",nativeQuery = true)
    List<Object> gets(@Param("id") Long providerId);

    // New Completed
    @Query(value = "SELECT skills.name , skills.image , bookings.final_price " +
            "FROM bookings INNER JOIN job_focus ON job_focus.id=bookings.job_focus_id " +
            "INNER JOIN jobs ON jobs.id=job_focus.job_id " +
            "INNER JOIN skills ON skills.id=job_focus.skill_id " +
            "WHERE jobs.provider_id=:id " +
            "AND bookings.status='Completed' " +
            "ORDER BY bookings.complete_date DESC " +
            "LIMIT 10 ",nativeQuery = true)
    List<Completed> newCompleted(@Param("id") Long providerId);

}

