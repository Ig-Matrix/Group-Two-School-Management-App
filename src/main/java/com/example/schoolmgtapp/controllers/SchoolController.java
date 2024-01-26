package com.example.schoolmgtapp.controllers;


import com.example.schoolmgtapp.entity.School;
import com.example.schoolmgtapp.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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

        Page<School> byPage  = schoolService.getSchoolsByPage(page,pageSize);
        mv.setViewName("school/listByPage");

        mv.addObject("hasContent", byPage.hasContent());


        int totalStaffs = schoolService.getAllSchools().stream().collect(Collectors.summingInt(School::getNo_of_staff));


        if(byPage.hasContent()){
            mv.addObject("total_staffs",totalStaffs);
            mv.addObject("total_schools",byPage.getContent().size());
            mv.addObject("schools", byPage.getContent());
            mv.addObject("page_num",byPage.getNumber());
            mv.addObject("page_total",byPage.getTotalPages());
            mv.addObject("page_hasNext",byPage.hasNext());
            mv.addObject("page_hasPrev",byPage.hasPrevious());

        }
        return mv;
    }

    @RequestMapping(value = "/searchbyname", method = RequestMethod.GET)
    public ModelAndView searchStaffByName(@RequestParam(name = "term", defaultValue = "") String searchTerm){

        ModelAndView mv= new ModelAndView();

        School sch= new School(searchTerm);

        List<School> byName  = schoolService.getSchoolsByName(sch);


        mv.setViewName("school/search");

        boolean hasContent = !byName.isEmpty();

        mv.addObject("hasContent", hasContent);
        System.out.println("term  "+searchTerm);
        mv.addObject("term", searchTerm);

        System.out.println("size "+byName.size());

        if(hasContent){
            mv.addObject("schools", byName);
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


    @RequestMapping(value = "/search/delete", method = RequestMethod.GET)
    public String deletesearch(@RequestParam(name = "term") String searchTerm,@RequestParam(value ="id") long id){

        schoolService.deleteSchool(id);

        return "redirect:/school/searchbyname";
    }




}
