/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.models;

import bsh.util.Util;
import com.namph.utils.Utils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huy_Nam
 */
@Entity
@Table(name = "tbl_import")
public class Import implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "soHD")
    private int soHD;
    @Column(name = "house_id")
    private Integer houseId;
    @Column(name = "name")
    private String name;
    @Column(name = "total")
    private Double total;
    @Column(name = "is_tonkho")
    private boolean tonKho;
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soHD", fetch = FetchType.LAZY)
    private List<TblImportDetail> tblImportDetailList;
    @Transient
    private String strSoHD;

    public boolean isTonKho() {
        return tonKho;
    }

    public void setTonKho(boolean tonKho) {
        this.tonKho = tonKho;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStrSoHD() {
        strSoHD = Utils.formatSoHD(soHD);
        return strSoHD;
    }

    public void setStrSoHD(String strSoHD) {
        this.strSoHD = strSoHD;
    }

    public Import() {
    }

    public Import(Integer id) {
        this.id = id;
    }

    public Import(Integer id, int soHD) {
        this.id = id;
        this.soHD = soHD;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @XmlTransient
    public List<TblImportDetail> getTblImportDetailList() {
        return tblImportDetailList;
    }

    public void setTblImportDetailList(List<TblImportDetail> tblImportDetailList) {
        this.tblImportDetailList = tblImportDetailList;
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
        if (!(object instanceof Import)) {
            return false;
        }
        Import other = (Import) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.Import[ id=" + id + " ]";
    }

}
