package com.example.Backend.Repositories;

import com.example.Backend.DTOS.UserAddressDto;
import com.example.Backend.Models.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByUser_Id(long id);

    Long countByUser_Id(Long id);

    Address findByUser_IdAndStatus(Long id, String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE address SET status='Active' " +
            "WHERE user_id=:id",nativeQuery = true)
    void setStatusToActiveAll(@Param("id") Long id);

    @Query(value = "SELECT id AS id, name AS name, status AS status, lat AS lat, lon AS lon " +
            "FROM address " +
            "WHERE address.user_id=:id",nativeQuery = true)
    List<UserAddressDto> findByUserId(@Param("id")Long id);
}
