package com.example.Backend.Repositories;

import com.example.Backend.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query(value = "SELECT * FROM admins WHERE email=:email",nativeQuery = true)
    Admin findByEmail(@Param("email") String email);
}
