/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.hoadon;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.hoadon.HoaDonChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository {

    public List<HoaDonChiTiet> findAll(int position, int pageSize) {
        List<HoaDonChiTiet> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM HoaDonChiTiet n";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql, HoaDonChiTiet.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<HoaDonChiTiet> findAll() {
        List<HoaDonChiTiet> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM HoaDonChiTiet n";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql, HoaDonChiTiet.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public HoaDonChiTiet findById(String id) {
        HoaDonChiTiet model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM HoaDonChiTiet n Where n.id=:id";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql, HoaDonChiTiet.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }
    
    public HoaDonChiTiet save(HoaDonChiTiet model) {
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

    public boolean delete(String id) {
        int affectedRows = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE HoaDonChiTiet n WHERE n.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                affectedRows = query.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return affectedRows > 0;
    }

    public long totalCount() {
        long total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(n.id) FROM HoaDonChiTiet n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
}
