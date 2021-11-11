package com.example.MCardSpring.MainModel;

import javax.persistence.*;

/**
 * Kullanıcı içinde kullanılan Role entity si
 */
@Entity
public class Role {
    /**
     * User rolünün unique id si
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * User rolünün ERole den belirlenmesi
     */
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
