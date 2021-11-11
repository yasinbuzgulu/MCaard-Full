package com.example.MCardSpring.Repository;

import com.example.MCardSpring.MainModel.CityOpportunity;
import com.example.MCardSpring.MainModel.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Şehir-olanak bilgilerinin tutulduğu repository
 */
@Repository
public
interface CityOpportunityRepository extends JpaRepository<CityOpportunity, Long> {
    List<CityOpportunity> findAllByCity_CityName(String cityName);
}

