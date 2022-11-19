package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Schedule getById(long scheduleId) {
        return scheduleRepository.getOne(scheduleId);
    }

    public List<Schedule> getScheduleByPetId(long id) {
        return scheduleRepository.findByPetId(id);
    }

    public List<Schedule> getScheduleByEmployeeId(long employeeId) {
        return scheduleRepository.findByEmployeeId(employeeId);
    }

    public List<Schedule> getScheduleByCustomerId(long customerId) {
        return scheduleRepository.findByCustomerId(customerId);
    }
}