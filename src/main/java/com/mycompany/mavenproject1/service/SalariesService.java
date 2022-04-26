/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.entity.Salaries;
import com.mycompany.mavenproject1.repository.SalariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huandn
 */
@Service
public class SalariesService {
    @Autowired
    private SalariesRepository salariesRepository;
    
    public void insert_salary(Salaries salaries){
        salariesRepository.save(salaries);
    }
}
