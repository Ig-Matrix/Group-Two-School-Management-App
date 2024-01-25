package com.example.schoolmgtapp.services;

import com.example.schoolmgtapp.entity.School;
import com.example.schoolmgtapp.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository sRepo;




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


}
