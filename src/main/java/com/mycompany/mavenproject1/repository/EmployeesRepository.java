/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1.repository;

/**
 *
 * @author dieppv
 */
import com.mycompany.mavenproject1.entity.Employees;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.mavenproject1.*;
import java.util.Date;

@Repository
public interface EmployeesRepository extends  JpaRepository<Employees,Integer>{
    @Query(value = "call filter_salary(:salary);", nativeQuery = true)
    List<Employees> filter_salary(@Param("salary") Integer salary);

    @Query(value = "call filter_hiredate(:date);", nativeQuery = true)
    List<Employees> filter_hiredate(@Param("date") Date date);
    
    @Query(value = "call filter_deptNo(:deptNo);", nativeQuery = true)
    List<Employees> filter_deptNo(@Param("deptNo") String deptNo);
    
    @Query(value = "call filter_title(:title);", nativeQuery = true)
    List<Employees> filter_title(@Param("title") String title);
    
    /**
     *
     * @param dateOfBir
     * @param fullName
     * @param gender
     * @param hire_date
     * @param title
     * @param from_date
     * @param to_date
     * @param salary
     */
    @Query(value = "call insert_employees(:dateOfBir,:fullName,:gender,:hire_date,:title,:from_date,:to_date,:salary );",nativeQuery = true)
    void insert_employee(@Param("dateOfBir") Date dateOfBir,
                         @Param("fullName") String fullName,
                         @Param("gender") String gender,
                         @Param("hire_date") Date hire_date,
                         @Param("title") String title,
                         @Param("from_date") Date from_date,
                         @Param("to_date") Date to_date,
                         @Param("salary") Integer salary);
}


                   