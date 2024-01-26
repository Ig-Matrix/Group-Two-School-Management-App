package com.example.SchoolManagementApplication.controllers;

import com.example.SchoolManagementApplication.entity.School;
import com.example.SchoolManagementApplication.services.SchoolService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        String info = "Welcome to SpringBoot";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("school/index");
        mv.addObject("info", info);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("school/create");
        mv.addObject("school", new School());
        return mv;
    }

    /*
     * @RequestMapping(value = "/list", method = RequestMethod.GET)
     * public ModelAndView list(){
     * List<School> schools = schoolService.getAllSchool();
     * ModelAndView mv= new ModelAndView();
     * mv.setViewName("school/list");
     * mv.addObject("schools", schools);
     * return mv;
     * }
     */

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listByPage(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "term", required = false) String searchTerm){

        ModelAndView mv = new ModelAndView();

        int pageSize = 4;
        Page<School> byPage;
        List<School> schools=schoolService.getAllSchools();

        if(searchTerm==null){

            byPage   = schoolService.getAllSchool(page,pageSize);
        }else{

            byPage   = schoolService.getSchoolsByName(searchTerm,page,pageSize);
        }

        mv.setViewName("school/listbypage");

        mv.addObject("hasContent", byPage.hasContent());

        int totalStaffs = schools.stream().collect(Collectors.summingInt(School::getNo_of_staff));


            mv.addObject("total_staffs",totalStaffs);
            mv.addObject("total_schools",schools.size());
            mv.addObject("schools", byPage.getContent());
            mv.addObject("page_num", byPage.getNumber());
            mv.addObject("page_total", byPage.getTotalPages());
            mv.addObject("page_hasNext", byPage.hasNext());
            mv.addObject("page_hasPrev", byPage.hasPrevious());


        return mv;
    }





    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid School school, Errors errors) {
        if (errors.hasErrors()) {
            return "school/create";
        }

        schoolService.create(school.getName(), school.getAddress(), school.getNo_of_staff());
        return "redirect:/school/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable(value = "id") long id) {

        School school = schoolService.getSchool(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("school", school);
        mv.setViewName("school/view");

        return mv;

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("school/edit");
        School school = schoolService.getSchool(id);
        mv.addObject("school", school);
        return mv;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid School school, Errors errors, @PathVariable(value = "id") long id) {
        if (errors.hasErrors()) {
            return "school/edit";
        }

        school.setId(id);
        schoolService.updateSchool(school);
        return "redirect:/school/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") long id) {
        schoolService.deleteSchool(id);
        return "redirect:/school/list";
    }

    @RequestMapping(value = "/deleteselected", method = RequestMethod.GET)
    public String deleteSelectedSchools(@RequestParam("selectedIds") List<Long> selectedIds) {

        schoolService.deleteBulkSchools(selectedIds);
        return "redirect:/school/list";
    }

}
