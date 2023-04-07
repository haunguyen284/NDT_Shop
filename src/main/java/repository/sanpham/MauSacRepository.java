/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.PaginationConstant;
import comon.constant.sanpham.TrangThaiMauSac;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nguyenth28
 */
public class MauSacRepository {

    public List<MauSac> findAll(TrangThaiMauSac trangThai) {
        List<MauSac> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM MauSac x "
                    + "WHERE (:trangThaiMauSac IS NULL "
                    + "OR trangThaiMauSac LIKE '%' + :trangThaiMauSac + '%')"
                    + "ORDER BY ten";
            TypedQuery<MauSac> query = session.createQuery(hql, MauSac.class);
            query.setParameter("trangThaiMauSac", trangThai != null ? String.valueOf(trangThai.ordinal()): trangThai);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public MauSac save(MauSac model) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                session.saveOrUpdate(model);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                model = null;
            }
        } finally {
            return model;
        }
    }

    public MauSac findByMa(String ma) {
        MauSac model = null;
        List<MauSac> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM MauSac x WHERE x.ma = :ma";
            TypedQuery<MauSac> query = session.createQuery(hql, MauSac.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }
}
