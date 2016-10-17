/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.namph.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sep 25, 2016
 * @author Pham_Huy_Nam
 */
@Entity
@Table(name = "tbl_thu")
@NamedQueries({
    @NamedQuery(name = "TblThu.findAll", query = "SELECT t FROM TblThu t")})
public class TblThu implements Serializable {

    @Column(name = "content")
    private String content;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "agen_code")
    private String agenCode;

    public TblThu() {
    }

    public TblThu(Long id) {
        this.id = id;
    }

    public TblThu(Long id, Date insertDate, double total, String agenCode) {
        this.id = id;
        this.insertDate = insertDate;
        this.total = total;
        this.agenCode = agenCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAgenCode() {
        return agenCode;
    }

    public void setAgenCode(String agenCode) {
        this.agenCode = agenCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblThu)) {
            return false;
        }
        TblThu other = (TblThu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.TblThu[ id=" + id + " ]";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
