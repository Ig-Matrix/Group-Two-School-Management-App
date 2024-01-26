package com.example.SchoolManagementApplication.repository;

import com.example.SchoolManagementApplication.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    public Page<School> findByNameContaining(String keyword, Pageable pageable);

}
