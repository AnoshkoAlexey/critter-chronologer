package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    Long id;

    @Nationalized
    String name;

    @ElementCollection
    @Enumerated
    Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated
    Set<DayOfWeek> daysAvailable;
}