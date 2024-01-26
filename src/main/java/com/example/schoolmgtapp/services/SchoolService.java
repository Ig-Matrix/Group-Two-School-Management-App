package com.example.schoolmgtapp.services;

import com.example.schoolmgtapp.entity.School;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SchoolService {

    public void create(String name, String address,  int no_of_staff);

    public School getSchool(Long id);

    public void updateSchool(School school);

    public void deleteSchool(long id);

    public void deleteBulkSchools(List<Long> ids);


    public Page<School> getSchoolsByPage(int pageNumber , int pageSize);

    public List<School> getSchoolsByName(School school);


    public List<School> getAllSchools();




}