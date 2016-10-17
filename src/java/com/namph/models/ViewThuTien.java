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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Oct 2, 2016
 * @author Pham_Huy_Nam
 */
@Entity
@Table(name = "view_thu_tien")
@NamedQueries({
    @NamedQuery(name = "ViewThuTien.findAll", query = "SELECT v FROM ViewThuTien v")})
public class ViewThuTien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "id")
    @Id
    private long id;
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
    @Column(name = "content")
    private String content;

    public ViewThuTien() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
