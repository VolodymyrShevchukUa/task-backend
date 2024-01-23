package com.example.bots_crew_task.controller;

import com.example.bots_crew_task.model.Department;
import com.example.bots_crew_task.model.DepartmentStatistic;
import com.example.bots_crew_task.service.DepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {

    DepartmentService departmentService;


    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public String createDepartment(@RequestBody Department department) {
        departmentService.createNewDepartment(department);
        return "ok";
    }

    @GetMapping("/{departmentId}/statistic")
    public List<DepartmentStatistic> getDepartmentStatistic(@PathVariable long departmentId) {
        return departmentService.getStatistic(departmentId);
    }

    @GetMapping("/search")
    public List<Department> getDepartmentWithCertainLectors(@RequestParam String search) {
        return departmentService.getDepartmentWithLectorsBySearch(search);
    }

}
