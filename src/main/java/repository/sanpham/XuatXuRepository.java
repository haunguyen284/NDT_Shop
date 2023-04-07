/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.sanpham.TrangThaiXuatXu;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.XuatXu;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nguyenth28
 */
public class XuatXuRepository {

    public List<XuatXu> findAll(TrangThaiXuatXu trangThai) {
        List<XuatXu> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM XuatXu x "
                    + "WHERE (:trangThaiXuatXu IS NULL "
                    + "OR trangThaiXuatXu LIKE '%' + :trangThaiXuatXu + '%')"
                    + "ORDER BY ten";
            TypedQuery<XuatXu> query = session.createQuery(hql, XuatXu.class);
            query.setParameter("trangThaiXuatXu", trangThai != null ? String.valueOf(trangThai.ordinal()) : trangThai);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public XuatXu save(XuatXu model) {
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

    public XuatXu findByMa(String ma) {
        XuatXu model = null;
        List<XuatXu> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM XuatXu x WHERE x.ma = :ma";
            TypedQuery<XuatXu> query = session.createQuery(hql, XuatXu.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }
}
