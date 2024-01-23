package com.example.bots_crew_task.repository;

import com.example.bots_crew_task.model.Department;
import com.example.bots_crew_task.model.DepartmentStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query(nativeQuery = true, value =
                    "SELECT  l.grade Grade, count(*) Count FROM lector l " +
                    "WHERE l.department_id = :id " +
                    "GROUP BY l.grade")
    List<DepartmentStatistic> getStatistic(long id);

    @Query(nativeQuery = true, value =
                    "SELECT d.*, l.id AS lectorId, l.name AS lectorName " +
                    "FROM department d " +
                    "LEFT JOIN lector l ON l.department_id = d.id " +
                    "WHERE l.name ILIKE %:search%")
    List<Department> getDepartmentBySearch(@Param("search") String search);

    @Query("SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.lectors")
    List<Department> findAllDepartmentsWithLectors();


}





