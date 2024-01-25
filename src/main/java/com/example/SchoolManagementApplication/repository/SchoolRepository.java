package com.example.SchoolManagementApplication.repository;

import com.example.SchoolManagementApplication.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {


}
