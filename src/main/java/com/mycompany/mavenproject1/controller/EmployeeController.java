/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.controller;

/**
 *
 * @author dieppv
 */
import com.mycompany.mavenproject1.entity.Employees;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.mycompany.mavenproject1.service.EmployeesService;
import com.mycompany.mavenproject1.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping(value = "/rest")
public class EmployeeController {
    @Autowired
    private EmployeesService employeesService;
    
//    @RequestMapping("/")
//    String get(){
//    //mapped to hostname:port/home/
//        return "Hello from get";
//    }
    
    @GetMapping ( value = "/salary")
    public List<Employees> filter_salary(@RequestParam("salary") Integer salary){
        return employeesService.filter_salary(salary);
    }
    
    @GetMapping ( value = "/deptNo")
    public List<Employees> filter_deptNo(@RequestParam("deptNo") String deptNo){
        return employeesService.filter_deptNo(deptNo);
    }
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @GetMapping ( value = "/hireDate")
    public List<Employees> filter_hireDate(@RequestParam("hireDate") String hireDate) throws ParseException{
        return employeesService.filter_hiredate(hireDate);
    }
    
    @GetMapping ( value = "/title")
    public List<Employees> filter_title(@RequestParam("title") String title){
        return employeesService.filter_title(title);
    }
    
    
    @PostMapping (value = "/insertEmployee")
    public void insert_employee(@RequestParam("dateOfBir") String dateOfBir,
                                @RequestParam("fullName") String fullName,
                                @RequestParam("gender") String gender,
                                @RequestParam("hire_date") String hire_date,
                                @RequestParam("title") String title,
                                @RequestParam("from_date") String from_date,
                                @RequestParam("to_date") String to_date,
                                @RequestParam("salary") Integer salary) throws ParseException{
        employeesService.insert_employees(dateOfBir, fullName, gender, hire_date, title, from_date, to_date, salary);
    }
            
    
}