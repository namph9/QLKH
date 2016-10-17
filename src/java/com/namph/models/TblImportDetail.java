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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Huy_Nam
 */
@Entity
@Table(name = "tbl_import_detail")
public class TblImportDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "count")
    private double count;
    @Column(name = "price")
    private Double price;
    @Column(name = "is_tonkho")
    private boolean tonKho;
    @Column(name = "insert_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date insertDate;
    @JoinColumn(name = "soHD", referencedColumnName = "soHD")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Import soHD;
    @JoinColumn(name = "maSP", referencedColumnName = "code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblProducts maSP;

    public TblImportDetail() {
    }

    public boolean isTonKho() {
        return tonKho;
    }

    public void setTonKho(boolean tonKho) {
        this.tonKho = tonKho;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public TblImportDetail(Integer id) {
        this.id = id;
    }

    public TblImportDetail(Integer id, double count) {
        this.id = id;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public Import getSoHD() {
        return soHD;
    }

    public void setSoHD(Import soHD) {
        this.soHD = soHD;
    }

    public TblProducts getMaSP() {
        return maSP;
    }

    public void setMaSP(TblProducts maSP) {
        this.maSP = maSP;
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
        if (!(object instanceof TblImportDetail)) {
            return false;
        }
        TblImportDetail other = (TblImportDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.TblImportDetail[ id=" + id + " ]";
    }

}
