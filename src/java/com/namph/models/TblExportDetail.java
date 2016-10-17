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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Huy_Nam
 */
@Entity
@Table(name = "tbl_export_detail")
public class TblExportDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "count")
    private double count;
    @Column(name = "isKM")
    private boolean isKm;
    @Column(name = "price")
    private Double price;
    @Column(name = "insert_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date insertDate;
    @JoinColumn(name = "so_hd", referencedColumnName = "so_hd")
    @ManyToOne(optional = false)
    private TblExport soHd;
    @JoinColumn(name = "ma_sp", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private TblProducts maSp;

    public boolean isIsKm() {
        return isKm;
    }

    public void setIsKm(boolean isKm) {
        this.isKm = isKm;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TblExportDetail() {
    }

    public TblExportDetail(Integer id) {
        this.id = id;
    }

    public TblExportDetail(Integer id, double count) {
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

    public TblExport getSoHd() {
        return soHd;
    }

    public void setSoHd(TblExport soHd) {
        this.soHd = soHd;
    }

    public TblProducts getMaSp() {
        return maSp;
    }

    public void setMaSp(TblProducts maSp) {
        this.maSp = maSp;
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
        if (!(object instanceof TblExportDetail)) {
            return false;
        }
        TblExportDetail other = (TblExportDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.TblExportDetail[ id=" + id + " ]";
    }

}
