package com.example.bots_crew_task.service;

import com.example.bots_crew_task.model.Department;
import com.example.bots_crew_task.model.DepartmentStatistic;
import com.example.bots_crew_task.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DepartmentService {

    DepartmentRepository departmentRepository;


    public void createNewDepartment(Department department) {
        try {
            departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatusCode
                    .valueOf(405), "department" + department.getName() + "already exist");
        }
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAllDepartmentsWithLectors();
    }

    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public List<DepartmentStatistic> getStatistic(long id) {
        return departmentRepository.getStatistic(id);
    }

    public List<Department> getDepartmentWithLectorsBySearch(String search) {
        List<Department> departmentBySearch = departmentRepository.getDepartmentBySearch(search);
        return departmentBySearch
                .stream()
                .distinct()
                .peek(department -> department.setLectors(department.getLectors()
                        .stream()
                        .filter(lector -> lector.getName().contains(search))
                        .collect(Collectors.toList()))
                )
                .filter(department -> !department.getLectors().isEmpty())
                .collect(Collectors.toList());
    }
}
