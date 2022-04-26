/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.entity.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huandn
 */
@Repository
public interface SalariesRepository extends JpaRepository<Salaries, Integer> {
    
}
