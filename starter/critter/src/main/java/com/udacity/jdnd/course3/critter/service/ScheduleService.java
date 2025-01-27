package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    public List<Schedule> getScheduleByPetId(long id) {
        return scheduleRepository.findByPetsId(id);
    }

    public List<Schedule> getScheduleByEmployeeId(long employeeId) {
        return scheduleRepository.findByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleByCustomerId(long customerId) {
        return scheduleRepository.findByPetsCustomerId(customerId);
    }
}
