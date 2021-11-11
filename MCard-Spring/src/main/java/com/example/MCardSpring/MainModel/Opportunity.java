package com.example.MCardSpring.MainModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Olanak nesnesinin özelliklerini belirten entity
 */
@Entity
public class Opportunity {

    /**
     * Opportunity ile CityOpportunity arası entity relation var ve bu ilişkinin opportunity deki değişiklerinden -
     * CityOpportunity ninde etkilenmesi için olan kısmı
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "opportunity")
    @JsonIgnore
    private final List<CityOpportunity> cityOpportunities = new ArrayList<>();
    /**
     * Olanak kaydının tutulduğu unique ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Olanağın ismi
     */
    private String name;

    public Opportunity( String name) {
        this.name = name;
    }

    public Opportunity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CityOpportunity> getCityOpportunities() {
        return cityOpportunities;
    }


    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
