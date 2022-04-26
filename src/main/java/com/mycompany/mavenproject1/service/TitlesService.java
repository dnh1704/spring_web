/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.entity.Titles;
import com.mycompany.mavenproject1.repository.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huandn
 */
@Service
public class TitlesService {
    @Autowired
    private TitlesRepository titlesRepository;
    
    public void insert_title(Titles title){
        titlesRepository.save(title);
    }
    
    
    
}
