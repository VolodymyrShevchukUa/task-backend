package com.example.bots_crew_task.controller;

import com.example.bots_crew_task.model.Lector;
import com.example.bots_crew_task.model.LectorDto;
import com.example.bots_crew_task.service.LectorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lectors")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LectorController {

    LectorService lectorService;

    @GetMapping("/search")
    public List<Lector> getLector(@RequestParam String search) {
        return lectorService.findLectorBySearch(search);
    }

    @PostMapping
    public String addLector(@Valid @RequestBody LectorDto lector) {
        lectorService.createLector(lector);
        return "ok";
    }

    @PutMapping("/{lectorId}")
    public String changeName(@NotEmpty(message = "Name cannot be empty")
                             @Pattern(regexp = "[a-zA-Z]+\\s[a-zA-Z]+", message = "Invalid name format")
                             @RequestParam String name, @PathVariable long lectorId) {
        lectorService.renameLector(lectorId, name);
        return "ok";
    }

    @PatchMapping("/{lectorId}/promote")
    public String promote(@PathVariable long lectorId) {
        lectorService.promoteLector(lectorId);
        return "ok";
    }
}
