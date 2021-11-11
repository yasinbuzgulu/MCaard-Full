package com.example.MCardSpring.MainModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Şehir nesnesinin özelliklerini belirten entity
 */
@Entity
public class City {

    /**
     * City ile CityOpportunity arası entity relation var ve bu ilişkinin city deki değişiklerinden -
     * CityOpportunity ninde etkilenmesi için olan kısmı
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "city")
    @JsonIgnore
    private final List<CityOpportunity> cityOpportunities = new ArrayList<>();

    public List<CityOpportunity> getCityOpportunities() {
        return cityOpportunities;
    }

    /**
     * Şehir nesnesinin unique id si
     */
    @Id
    @GeneratedValue
    private  Long id;

    /**
     * Şehrin ismi
     */
    @NotBlank
    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;

    }

    public City() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
