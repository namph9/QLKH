/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.TonKhoDao;
import com.namph.utils.dto.TonKhoDto;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Huy_Nam
 */
public class TonKhoDaoImpl extends BasicDAO<TonKhoDto> implements TonKhoDao {

    @Override
    public List<TonKhoDto> getLstTonKho(int month, int year) {
        String sqlTonKho = "SELECT nhap.maSP msSp, p.name tenSp, (slnhap - IFNULL(slxuat,0)) AS COUNT FROM "
                + " (SELECT SUM(im.count) AS slnhap, im.maSP"
                + " FROM tbl_import_detail im  WHERE MONTH(insert_date) = ? AND YEAR(insert_date) = ? "
                + " GROUP BY im.maSP) AS nhap "
                + " LEFT JOIN "
                + "  (SELECT SUM(ex.count) AS slxuat, ex.ma_sp"
                + " FROM tbl_export_detail ex  WHERE MONTH(insert_date) = ? AND YEAR(insert_date) = ? "
                + " GROUP BY ex.ma_sp) AS xuat"
                + " ON nhap.maSP = xuat.ma_sp LEFT JOIN `tbl_products` p ON p.`code`= nhap.maSP ";
        Session session = getSession();
        List<TonKhoDto> lst = new ArrayList<TonKhoDto>();
        try {
            Query query = session.createSQLQuery(sqlTonKho).
                    addScalar("msSp", Hibernate.STRING).
                    addScalar("tenSp", Hibernate.STRING).
                    addScalar("count", Hibernate.LONG).
                    setResultTransformer(Transformers.aliasToBean(TonKhoDto.class));
            query.setParameter(0, month);
            query.setParameter(1, year);
            query.setParameter(2, month);
            query.setParameter(3, year);
            lst = query.list();
        } catch (Exception e) {
        }
        return lst;
    }

    @Override
    public List<TonKhoDto> getLstCongno(String code) {
        String sqlCongNo = "SELECT tonghop.name CODE, dl.`name` NAME, dl.`address` address, \n" +
"                IFNULL(SUM(tonghop.total),0) congno FROM \n" +
"				(SELECT ex.`name`, ex.`total`, ex.`insert_date` \n" +
"                FROM `tbl_export` ex \n" +
"                WHERE MONTH(ex.`insert_date`) = MONTH(CURRENT_DATE)\n" +
"                AND YEAR(ex.`insert_date`)= YEAR(CURRENT_DATE)\n" +
"                UNION ALL\n" +
"                SELECT `tbl_thu`.`agen_code`, `tbl_thu`.`total`, `tbl_thu`.`insert_date` \n" +
"                FROM `tbl_thu`\n" +
"                WHERE MONTH(tbl_thu.`insert_date`) = MONTH(CURRENT_DATE)\n" +
"                AND YEAR(tbl_thu.`insert_date`)= YEAR(CURRENT_DATE)) AS tonghop\n" +
"				JOIN tbl_daily dl ON dl.`code` = tonghop.name  ";

        Session session = getSession();
        List<TonKhoDto> lst = new ArrayList<TonKhoDto>();
        try {
            if (null != code && !code.trim().equals("")) {
                sqlCongNo += " where tonghop.name = ? ";
            }

            sqlCongNo += "  GROUP BY tonghop.name ORDER BY congno DESC ";

            Query query = session.createSQLQuery(sqlCongNo).
                    addScalar("code", Hibernate.STRING).
                    addScalar("name", Hibernate.STRING).
                    addScalar("address", Hibernate.STRING).
                    addScalar("congno", Hibernate.DOUBLE).
                    setResultTransformer(Transformers.aliasToBean(TonKhoDto.class));
            if (null != code && !code.trim().equals("")) {
                query.setParameter(0, code);
            }
            lst = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public double getSumDebit() {
        String sqlSumDebit = " SELECT SUM( congno ) AS congno\n"
                + "FROM (\n"
                + "\n"
                + "SELECT IFNULL( SUM( `tbl_export`.`total` ) , 0 ) congno\n"
                + "FROM `tbl_export`\n"
                + "WHERE MONTH( tbl_export.`insert_date` ) = MONTH( CURRENT_DATE )\n"
                + "AND YEAR( tbl_export.`insert_date` ) = YEAR( CURRENT_DATE )\n"
                + "UNION ALL\n"
                + "SELECT IFNULL( SUM( tbl_thu.`total` ) , 0 ) congno\n"
                + "FROM `tbl_thu`\n"
                + "WHERE MONTH( tbl_thu.`insert_date` ) = MONTH( CURRENT_DATE )\n"
                + "AND YEAR( tbl_thu.`insert_date` ) = YEAR( CURRENT_DATE )\n"
                + ") AS tonghop ";
        Session session = getSession();
        List<TonKhoDto> lst = new ArrayList<TonKhoDto>();
        Query query = session.createSQLQuery(sqlSumDebit).
                addScalar("congno", Hibernate.DOUBLE).
                setResultTransformer(Transformers.aliasToBean(TonKhoDto.class));
        lst = query.list();
        return lst.get(0).getCongno();
    }

}
