/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dieppv
 */
@Embeddable
public class SalariesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "emp_no")
    private int empNo;
    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    public SalariesPK() {
    }

    public SalariesPK(int empNo, Date fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empNo;
        hash += (fromDate != null ? fromDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalariesPK)) {
            return false;
        }
        SalariesPK other = (SalariesPK) object;
        if (this.empNo != other.empNo) {
            return false;
        }
        if ((this.fromDate == null && other.fromDate != null) || (this.fromDate != null && !this.fromDate.equals(other.fromDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.SalariesPK[ empNo=" + empNo + ", fromDate=" + fromDate + " ]";
    }
    
}
