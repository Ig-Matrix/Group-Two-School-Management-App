package com.example.SchoolManagementApplication.services;

import com.example.SchoolManagementApplication.entity.School;
import com.example.SchoolManagementApplication.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository sRepo;


    @Override
    public List<School> getAllSchools() {
        return sRepo.findAll();
    }



    @Override
    public void create(String name, String address,  int no_of_staff) {
        sRepo.save(new School(name, address, no_of_staff));
    }

    @Override
    public School getSchool(Long id) {
        Optional<School> School=sRepo.findById(id);
        return School.orElse(null);
    }

    @Override
    public void updateSchool(School school) {
        sRepo.save(school);
    }

    @Override
    public void deleteSchool(long id) {
        sRepo.deleteById(id);
    }

    @Override
    public void deleteBulkSchools(List<Long> ids) {
        sRepo.deleteAllById(ids);
    }

    @Override
    public Page<School> getAllSchool(int pageNumber, int pageSize) {
        Pageable pageable=  (Pageable) PageRequest.of(pageNumber, pageSize);
        return sRepo.findAll(pageable);
    }

    @Override
    public Page<School> getSchoolsByName(School school, int pageNumber, int pageSize) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<School> example= Example.of(school,matcher);
        System.out.println("Example "+example);
        Pageable pageable=  (Pageable) PageRequest.of(pageNumber, pageSize);

        System.out.println("Example "+example.getMatcher());
        return sRepo.findAll(example,pageable);

    }

    @Override
    public Page<School> getSchoolsByName(String term, int pageNumber, int pageSize) {
             return sRepo.findByNameContaining( term, PageRequest.of(pageNumber, pageSize));

    }


}

