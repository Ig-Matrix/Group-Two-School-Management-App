package com.example.schoolmgtapp.controllers;


import com.example.schoolmgtapp.entity.School;
import com.example.schoolmgtapp.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(){
        String info="Welcome to SpringBoot";
        ModelAndView mv= new ModelAndView();
        mv.setViewName("school/index");
        mv.addObject("info",info);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("school/create");
        return mv;
    }

/*
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
       List<School> schools = schoolService.getAllSchool();
        ModelAndView mv= new ModelAndView();
        mv.setViewName("school/list");
        mv.addObject("schools", schools);
        return mv;
    }
*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listByPage(@RequestParam(name = "page", defaultValue = "0") int page){

        ModelAndView mv= new ModelAndView();

        int pageSize = 4;

        Page<School> byPage  = schoolService.getAllSchool(page,pageSize);
        mv.setViewName("school/listByPage");

        mv.addObject("hasContent", byPage.hasContent());

        if(byPage.hasContent()){

            mv.addObject("schools", byPage.getContent());
            mv.addObject("page_num",byPage.getNumber());
            mv.addObject("page_total",byPage.getTotalPages());
            mv.addObject("page_hasNext",byPage.hasNext());
            mv.addObject("page_hasPrev",byPage.hasPrevious());

        }
        return mv;
    }

    @RequestMapping(value = "/searchbyname", method = RequestMethod.GET)
    public ModelAndView searchStaffByName(@RequestParam(name = "term", defaultValue = "") String searchTerm, @RequestParam(name = "page", defaultValue = "0") int page){
        ModelAndView mv= new ModelAndView();

        int pageSize = 2;
        School sch= new School();
        sch.setName(searchTerm);

        Page<School> byPage  = schoolService.getSchoolsByName(sch,page,pageSize);

        mv.setViewName("school/search");

        mv.addObject("hasContent", byPage.hasContent());
        mv.addObject("term", searchTerm);

        if(byPage.hasContent()){

            mv.addObject("schools", byPage.getContent());
            mv.addObject("page_num",byPage.getNumber());
            mv.addObject("page_total",byPage.getTotalPages());
            mv.addObject("page_hasNext",byPage.hasNext());
            mv.addObject("page_hasPrev",byPage.hasPrevious());

        }
        return mv;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "address") String address,
                                 @RequestParam(value = "no_of_staff") int no_of_staff){
        schoolService.create( name,  address, no_of_staff);
   return "redirect:/school/list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam(value = "id") long id){

       School school = schoolService.getSchool(id);
        ModelAndView mv= new ModelAndView();
        mv.addObject("school", school);
        mv.setViewName("school/view");

        return mv;

    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") long id){

        School school = schoolService.getSchool(id);
        ModelAndView mv= new ModelAndView();
        mv.addObject("school", school);
        mv.setViewName("school/edit");
        return mv;

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(School school, @PathVariable(value ="id") long id){
       school.setId(id);
        schoolService.updateSchool(school);
           return "redirect:/school/list";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value ="id") long id){
        schoolService.deleteSchool(id);
         return "redirect:/school/list";
    }

    @RequestMapping(value = "/deleteselected", method = RequestMethod.GET)
    public String deleteSelectedSchools(@RequestParam("selectedIds") List<Long> selectedIds) {

        schoolService.deleteBulkSchools(selectedIds);
        return "redirect:/school/list";
    }



}
