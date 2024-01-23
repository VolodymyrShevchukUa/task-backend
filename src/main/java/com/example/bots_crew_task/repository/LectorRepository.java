package com.example.bots_crew_task.repository;

import com.example.bots_crew_task.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector,Long> {

    List<Lector> findAllByNameIgnoreCaseContaining(String search);
}
