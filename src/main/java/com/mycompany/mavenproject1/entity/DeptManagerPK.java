/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dieppv
 */
@Embeddable
public class DeptManagerPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "dept_no")
    private String deptNo;
    @Basic(optional = false)
    @Column(name = "emp_no")
    private int empNo;

    public DeptManagerPK() {
    }

    public DeptManagerPK(String deptNo, int empNo) {
        this.deptNo = deptNo;
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptNo != null ? deptNo.hashCode() : 0);
        hash += (int) empNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptManagerPK)) {
            return false;
        }
        DeptManagerPK other = (DeptManagerPK) object;
        if ((this.deptNo == null && other.deptNo != null) || (this.deptNo != null && !this.deptNo.equals(other.deptNo))) {
            return false;
        }
        if (this.empNo != other.empNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.DeptManagerPK[ deptNo=" + deptNo + ", empNo=" + empNo + " ]";
    }
    
}
