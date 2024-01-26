package com.example.SchoolManagementApplication.services;

import java.util.List;

import com.example.SchoolManagementApplication.entity.School;
import org.springframework.data.domain.Page;

public interface SchoolService {

    public void create(String name, String address, int no_of_staff);

    public School getSchool(Long id);

    public void updateSchool(School school);

    public void deleteSchool(long id);

    public void deleteBulkSchools(List<Long> ids);

    public Page<School> getAllSchool(int pageNumber, int pageSize);

    public Page<School> getSchoolsByName(School name, int pageNumber, int pageSize);

    public Page<School> getSchoolsByName(String term, int pageNumber, int pageSize);

    public List<School> getAllSchools();

}