package com.example.MCardSpring.Repository;

import com.example.MCardSpring.MainModel.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Kart bilgilerinin tutulduğu repository
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
