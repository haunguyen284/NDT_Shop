/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.khachhang.LichSuTieuDiem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class LichSuTieuDiemRepository {

    public List<LichSuTieuDiem> findAll(int position, int pageSize) {
        List<LichSuTieuDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LichSuTieuDiem n";
            TypedQuery<LichSuTieuDiem> query = session.createQuery(hql, LichSuTieuDiem.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<LichSuTieuDiem> findAll() {
        List<LichSuTieuDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LichSuTieuDiem n";
            TypedQuery<LichSuTieuDiem> query = session.createQuery(hql, LichSuTieuDiem.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<LichSuTieuDiem> findAllByViDiem(String idViDiem) {
        List<LichSuTieuDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LichSuTieuDiem n where n.viDiem.id =:idViDiem";
            TypedQuery<LichSuTieuDiem> query = session.createQuery(hql, LichSuTieuDiem.class);
            query.setParameter("idViDiem", idViDiem);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public LichSuTieuDiem findById(String id) {
        LichSuTieuDiem model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LichSuTieuDiem n Where n.id=:id";
            TypedQuery<LichSuTieuDiem> query = session.createQuery(hql, LichSuTieuDiem.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public LichSuTieuDiem save(LichSuTieuDiem model) {
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
                String hql = "DELETE LichSuTieuDiem n WHERE n.id = :id";
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
            String hql = "SELECT COUNT(n.id) FROM LichSuTieuDiem n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }

    public LichSuTieuDiem findByTen(String ten) {
        LichSuTieuDiem model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LichSuTieuDiem n Where n.ten=:ten";
            TypedQuery<LichSuTieuDiem> query = session.createQuery(hql, LichSuTieuDiem.class);
            query.setParameter("ten", ten);
            model = query.getSingleResult();
        }
        return model;
    }
}
