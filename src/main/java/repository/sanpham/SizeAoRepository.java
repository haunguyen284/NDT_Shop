/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.sanpham.TrangThaiQuanAo;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.SizeAo;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nguyenth28
 */
public class SizeAoRepository {

    public List<SizeAo> findAll(TrangThaiQuanAo trangThai) {
        List<SizeAo> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SizeAo x "
                    + "WHERE (:trangThaiQuanAo IS NULL "
                    + "OR trangThaiQuanAo LIKE '%' + :trangThaiQuanAo + '%')"
                    + "ORDER BY ten";
            TypedQuery<SizeAo> query = session.createQuery(hql, SizeAo.class);
            query.setParameter("trangThaiQuanAo", trangThai != null ? String.valueOf(trangThai.ordinal()) : trangThai);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public SizeAo save(SizeAo model) {
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

    public SizeAo findByMa(String ma) {
        SizeAo model = null;
        List<SizeAo> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SizeAo x WHERE x.ma = :ma";
            TypedQuery<SizeAo> query = session.createQuery(hql, SizeAo.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }
}
