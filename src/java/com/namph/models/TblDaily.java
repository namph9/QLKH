/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Sep 18, 2016
 *
 * @author Pham_Huy_Nam
 */
@Entity
@Table(name = "tbl_daily")
public class TblDaily implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "name")
    private Collection<TblExport> tblExportCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    public TblDaily() {
    }

    public TblDaily(Integer id) {
        this.id = id;
    }

    public TblDaily(String code) {
        this.code = code;
    }

    public TblDaily(Integer id, String code, String name, boolean status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        if (!(object instanceof TblDaily)) {
            return false;
        }
        TblDaily other = (TblDaily) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.TblDaily[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TblExport> getTblExportCollection() {
        return tblExportCollection;
    }

    public void setTblExportCollection(Collection<TblExport> tblExportCollection) {
        this.tblExportCollection = tblExportCollection;
    }

}
