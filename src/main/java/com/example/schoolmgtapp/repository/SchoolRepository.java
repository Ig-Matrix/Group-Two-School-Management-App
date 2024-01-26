package com.example.schoolmgtapp.repository;

import com.example.schoolmgtapp.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {


    @Query(value = "SELECT * From school s WHERE s.name LIKE %:searchTerm%", nativeQuery = true)
    List<School> searchByName(@Param("searchTerm") String searchTerm);

}
