package com.example.MCardSpring.MainModel;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Kart nesnesinin özelliklerini belirten entity
 */
@Entity
public class Card {
    /**
     * Kart kaydının tutulduğu unique ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Kart kaydında kart fiyatı
     */
    @NotNull
    private Integer price;

    /**
     * Kart kaydında kart son kullanma tarihi
     */
    private String expiryDate;

    /**
     * Kart kaydında karta başvuranın
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Applicant applicant;

    /**
     * Kart kaydında kartın tanımlandığı şehir
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CityOpportunity> cityOpportunity;

    /**
     * Kart kaydında seçilen olanakların kaç yıl için tanımlanacağı
     */
    private Integer cardOpportunityYear;

    public Card() {
    }

    public Card(int price, String expiryDate, Applicant applicant, List<CityOpportunity> cityOpportunity,
                int cardOpportunityYear) {
        this.price = price;
        this.expiryDate = expiryDate;
        this.applicant = applicant;
        this.cityOpportunity = cityOpportunity;
        this.cardOpportunityYear = cardOpportunityYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<CityOpportunity> getCityOpportunity() {
        return cityOpportunity;
    }

    public void setCityOpportunity(List<CityOpportunity> cityOpportunity) {
        this.cityOpportunity = cityOpportunity;
    }

    public Integer getCardOpportunityYear() {
        return cardOpportunityYear;
    }

    public void setCardOpportunityYear(Integer cardOpportunityYear) {
        this.cardOpportunityYear = cardOpportunityYear;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", price=" + price +
                ", expiryDate='" + expiryDate + '\'' +
                ", applicant=" + applicant +
                ", cityOpportunity=" + cityOpportunity +
                ", cardOpportunityYear=" + cardOpportunityYear +
                '}';
    }
}
