package com.example.bots_crew_task.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DepartmentDto {

    Long id;
    String name;
    List<Lector> lectors;

    public DepartmentDto(Department department){
        id = department.getId();
        name = department.getName();
        lectors = department.getLectors();
    }

}
