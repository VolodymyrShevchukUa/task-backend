package com.example.bots_crew_task.service;

import com.example.bots_crew_task.model.Grade;
import com.example.bots_crew_task.model.Lector;
import com.example.bots_crew_task.model.LectorDto;
import com.example.bots_crew_task.repository.LectorRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LectorService {

    LectorRepository lectorRepository;
    DepartmentService departmentService;

    public List<Lector> findLectorBySearch(String search) {
        return lectorRepository.findAllByNameIgnoreCaseContaining(search);
    }

    public void createLector(LectorDto lectorDto) {
        lectorRepository.save(toLector(lectorDto));
    }

    private Lector toLector(LectorDto lectorDto) {
        Lector lector = new Lector();
        lector.setGrade(lectorDto.getGrade());
        lector.setName(lectorDto.getName());
        lector.setDepartment(departmentService.getDepartmentById(lectorDto.getDepartmentId()));
        return lector;
    }

    @Transactional
    public void promoteLector(long id) {
        Lector lector = lectorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "NotFound"));
        Grade grade = lector.getGrade();
        switch (grade) {
            case ASSISTANT -> lector.setGrade(Grade.ASSOCIATE_PROFESSOR);
            case ASSOCIATE_PROFESSOR -> lector.setGrade(Grade.PROFESSOR);
            case PROFESSOR ->
                    throw new ResponseStatusException(HttpStatusCode.valueOf(400), "lector is already professor");
        }
    }

    @Transactional
    public void renameLector(long id, String newName) {
        Lector lector = lectorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "NotFound"));
        lector.setName(newName);
    }
}
