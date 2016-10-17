package com.namph.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "tbl_products")
public class TblProducts implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maSp")
    private List<TblExportDetail> tblExportDetailList;

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
    @Column(name = "price")
    private Double price;
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Basic(optional = false)
    @Column(name = "unit")
    private String unit;
    @Column(name = "_lock")
    private boolean lock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maSP")
    private List<TblImportDetail> tblImportDetailList;
    @JoinColumn(name = "type", referencedColumnName = "code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblProductType type;
    @Transient
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public TblProductType getType() {
        return type;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public void setType(TblProductType type) {
        this.type = type;
    }

    public TblProducts() {
    }

    public TblProducts(String code) {
        this.code = code;
    }

    public TblProducts(Integer id) {
        this.id = id;
    }

    public TblProducts(Integer id, String code, String name, String unit) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        if (!(object instanceof TblProducts)) {
            return false;
        }
        TblProducts other = (TblProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.namph.models.TblProducts[ id=" + id + " ]";
    }

    @XmlTransient
    public List<TblExportDetail> getTblExportDetailList() {
        return tblExportDetailList;
    }

    public void setTblExportDetailList(List<TblExportDetail> tblExportDetailList) {
        this.tblExportDetailList = tblExportDetailList;
    }

}
