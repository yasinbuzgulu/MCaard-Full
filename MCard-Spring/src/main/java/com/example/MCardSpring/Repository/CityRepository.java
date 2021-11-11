package com.example.MCardSpring.Repository;

import com.example.MCardSpring.MainModel.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Şehir bilgilerinin tutulduğu repository
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
