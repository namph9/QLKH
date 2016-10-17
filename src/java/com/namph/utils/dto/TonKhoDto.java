/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.utils.dto;

/**
 *
 * @author Huy_Nam
 */
public class TonKhoDto {

    private String msSp;
    private String tenSp;
    private long count;
    private String address;
    private Double congno;
    private String code;
    private String name;

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

    public Double getCongno() {
        return congno;
    }

    public void setCongno(Double congno) {
        this.congno = congno;
    }

    public String getMsSp() {
        return msSp;
    }

    public void setMsSp(String msSp) {
        this.msSp = msSp;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

}
