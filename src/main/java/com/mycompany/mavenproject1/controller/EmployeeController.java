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
import com.mycompany.mavenproject1.entity.Salaries;
import com.mycompany.mavenproject1.entity.SalariesPK;
import com.mycompany.mavenproject1.entity.Titles;
import com.mycompany.mavenproject1.entity.TitlesPK;
import com.mycompany.mavenproject1.request.EmployeeRequest;
import com.mycompany.mavenproject1.service.SalariesService;
import com.mycompany.mavenproject1.service.TitlesService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest")
public class EmployeeController {
    @Autowired
    private EmployeesService employeesService;
    
    @Autowired
    private TitlesService titlesService;
    
    @Autowired
    private SalariesService salariesService;
    
    
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
    
  
    
    @PostMapping(value ="/insertEmployee")
    public void insert_employee(@RequestBody EmployeeRequest eRequest) throws ParseException{
        Employees employee = new Employees(eRequest);
        employeesService.insert_test(employee);
        
        //insert title
        TitlesPK titlePK = new TitlesPK(eRequest.getEmpNo(),eRequest.getTitle(), eRequest.getHireDate());
        Titles titles = new Titles(titlePK);
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse("9999-01-01");
        titles.setToDate(date1);
        titlesService.insert_title(titles); 
        
        //insert salary
        SalariesPK salaryPK = new SalariesPK(eRequest.getEmpNo(), eRequest.getHireDate());
        Salaries salaries = new Salaries(salaryPK);
        salaries.setSalary(eRequest.getSalary());
        salaries.setToDate(date1);
        salariesService.insert_salary(salaries);
        
    }
    
    
//    @PostMapping (value = "/insertEmployee")
//    public List<Employees> insert_employee(@RequestBody Map<String, String> requestParams) throws ParseException{
//        
//        String dateOfBir = requestParams.get("birthDate");
//        System.out.println(dateOfBir);
//        String fullName = requestParams.get("fullName");
//        String genderString = requestParams.get("gender");
//        char gender = genderString.charAt(0);
//        String hire_date = requestParams.get("hireDate");
//        String title = requestParams.get("title");
//        String from_date = requestParams.get("fromDate");
//        String to_date = requestParams.get("toDate");
//        String salaryString = requestParams.get("salary");
//        Integer salary = Integer.parseInt(salaryString);
//        
//        return employeesService.insert_employees(dateOfBir, fullName, gender, hire_date, title, from_date, to_date, salary);
//    }
    
    
 
    
    
       
        
//    @PathVariable String dateOfBir,
//                                @PathVariable String fullName,
//                                @PathVariable String gender,
//                                @PathVariable String hire_date,
//                                @PathVariable String title,
//                                @PathVariable String from_date,
//                                @PathVariable String to_date,
//                                @PathVariable Integer salary
    
}