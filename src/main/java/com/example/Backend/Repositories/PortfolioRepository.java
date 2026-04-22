package com.example.Backend.Repositories;

import com.example.Backend.Models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    List<Portfolio> findByProvider_Id(Long id);
}
