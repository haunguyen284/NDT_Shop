/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.sanpham.TrangThaiThuongHieu;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.ThuongHieu;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nguyenth28
 */
public class ThuongHieuRepository {

    public List<ThuongHieu> findAll(TrangThaiThuongHieu trangThai) {
        List<ThuongHieu> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM ThuongHieu x "
                    + "WHERE (:trangThaiThuongHieu IS NULL "
                    + "OR trangThaiThuongHieu LIKE '%' + :trangThaiThuongHieu + '%')"
                    + "ORDER BY ten";
            TypedQuery<ThuongHieu> query = session.createQuery(hql, ThuongHieu.class);
            query.setParameter("trangThaiThuongHieu", trangThai != null ? String.valueOf(trangThai.ordinal()) : trangThai);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public ThuongHieu save(ThuongHieu model) {
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

    public ThuongHieu findByMa(String ma) {
        ThuongHieu model = null;
        List<ThuongHieu> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM ThuongHieu x WHERE x.ma = :ma";
            TypedQuery<ThuongHieu> query = session.createQuery(hql, ThuongHieu.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }
}
