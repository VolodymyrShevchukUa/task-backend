package com.example.bots_crew_task.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectorDto {
    Grade grade;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+ [a-zA-Z]+$", message = "Name should contain two words without symbols")
    String name;
    Integer departmentId;
}
