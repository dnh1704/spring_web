/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.service;

/**
 *
 * @author dieppv
 */

import com.mycompany.mavenproject1.entity.Employees;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.mavenproject1.repository.EmployeesRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public List<Employees> filter_salary(Integer salary){
        return employeesRepository.filter_salary(salary);
    }
    
    public List<Employees> filter_hiredate(String hire_date) throws ParseException{
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(hire_date);  
        return employeesRepository.filter_hiredate(date1);
    }
     
    public List<Employees> filter_deptNo(String deptNo){
        return employeesRepository.filter_deptNo(deptNo);
    }
      
    public List<Employees> filter_title(String title){
        return employeesRepository.filter_title(title);
    }
    
    public void insert_employees(String dateOfBir,String fullName,String gender,String hire_date,String title,String from_date,String to_date,Integer salary) throws ParseException{
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBir);
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(hire_date);
        Date date3=new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
        Date date4=new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
        employeesRepository.insert_employee(date1, fullName, gender, date2, title, date3, date4, salary);
    }
       
}
