package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPetId(Long id);
    List<Schedule> findByEmployeeId(Long id);
    List<Schedule> findByPetCustomerId(Long id);
}