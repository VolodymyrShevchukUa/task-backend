package com.example.bots_crew_task.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class LectorDto {
    Grade grade;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+ [a-zA-Z]+$", message = "Name should contain two words without symbols")
    String name;
    Long departmentId;

    public LectorDto(Lector lector){
        grade = lector.getGrade();
        name = lector.getName();
        departmentId = Optional.ofNullable(lector.getDepartment()).map(Department::getId).orElse(null);
    }
}
