/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.models;

import com.namph.utils.Utils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_export")
public class TblExport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "so_hd")
    private int soHd;
    @Column(name = "house_id")
    private Integer houseId;
    @Column(name = "total")
    private Double total;
//    @Column(name = "name")
//    private String name;
    @JoinColumn(name = "name", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private TblDaily name;

    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soHd")
    private List<TblExportDetail> tblExportDetailList;
    @Transient
    private String strSoHD;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStrSoHD() {
        strSoHD = Utils.formatSoHD(soHd);
        return strSoHD;
    }

    public void setStrSoHD(String strSoHD) {
        this.strSoHD = strSoHD;
    }

    public TblDaily getName() {
        return name;
    }

    public void setName(TblDaily name) {
        this.name = name;
    }

    public TblExport() {
    }

    public TblExport(Integer id) {
        this.id = id;
    }

    public TblExport(Integer id, int soHd) {
        this.id = id;
        this.soHd = soHd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSoHd() {
        return soHd;
    }

    public void setSoHd(int soHd) {
        this.soHd = soHd;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @XmlTransient
    public List<TblExportDetail> getTblExportDetailList() {
        return tblExportDetailList;
    }

    public void setTblExportDetailList(List<TblExportDetail> tblExportDetailList) {
        this.tblExportDetailList = tblExportDetailList;
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
        if (!(object instanceof TblExport)) {
            return false;
        }
        TblExport other = (TblExport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.TblExport[ id=" + id + " ]";
    }

}
