package com.udacity.jdnd.course3.critter.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Pet> pets;
}