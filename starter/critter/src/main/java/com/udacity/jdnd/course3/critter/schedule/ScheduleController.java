package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private final ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    public ScheduleController(ScheduleService scheduleService, EmployeeService employeeService, PetService petService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleDTOToScheduleFunction.apply(scheduleDTO);
        if (schedule.getEmployees() == null || schedule.getEmployees().isEmpty()) {

            List<Employee> employees = employeeService.findEmployeesForService(schedule.getActivities(), schedule.getDate());
            schedule.setEmployees(employees);
        }

        Schedule savedSchedule = scheduleService.save(scheduleDTOToScheduleFunction.apply(scheduleDTO));
        return scheduleToScheduleDTOFunction.apply(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAll().stream().map(scheduleToScheduleDTOFunction).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleByPetId(petId).stream().map(scheduleToScheduleDTOFunction).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleByEmployeeId(employeeId).stream().map(scheduleToScheduleDTOFunction).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleByCustomerId(customerId).stream().map(scheduleToScheduleDTOFunction).collect(Collectors.toList());
    }

    Function<Schedule, ScheduleDTO> scheduleToScheduleDTOFunction = schedule -> {
        ScheduleDTO newScheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, newScheduleDTO);
        if (schedule.getPets() != null) {
            newScheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        if (schedule.getEmployees() != null) {
            newScheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        }

        return newScheduleDTO;
    };

    Function<ScheduleDTO, Schedule> scheduleDTOToScheduleFunction = scheduleDTO -> {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if (scheduleDTO.getPetIds() != null) {
            schedule.setPets(scheduleDTO.getPetIds().stream().map(id -> petService.getById(id)).collect(Collectors.toList()));
        }
        if (scheduleDTO.getEmployeeIds() != null) {
            schedule.setEmployees(scheduleDTO.getEmployeeIds().stream().map(id -> employeeService.getByEmployeeId(id)).collect(Collectors.toList()));
        }
        return schedule;
    };

}