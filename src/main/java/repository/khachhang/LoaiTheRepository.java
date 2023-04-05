/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.khachhang.LoaiThe;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class LoaiTheRepository {

    public List<LoaiThe> findAll(int position, int pageSize) {
        List<LoaiThe> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LoaiThe n ORDER BY n.giaTri";
            TypedQuery<LoaiThe> query = session.createQuery(hql, LoaiThe.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    
    public List<LoaiThe> findAll() {
        List<LoaiThe> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LoaiThe n ORDER BY n.giaTri";
            TypedQuery<LoaiThe> query = session.createQuery(hql, LoaiThe.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public LoaiThe findById(String id) {
        LoaiThe model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LoaiThe n Where n.id=:id";
            TypedQuery<LoaiThe> query = session.createQuery(hql, LoaiThe.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public LoaiThe save(LoaiThe model) {
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
                String hql = "DELETE LoaiThe n WHERE n.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                affectedRows = query.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return affectedRows > 0;
    }
    
    public long totalCount(){
        long total = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT COUNT(n.id) FROM LoaiThe n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
    
    public LoaiThe findByTen(String ten) {
        LoaiThe model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM LoaiThe n Where n.ten=:ten";
            TypedQuery<LoaiThe> query = session.createQuery(hql, LoaiThe.class);
            query.setParameter("ten", ten);
            model = query.getSingleResult();
        }
        return model;
    }
}
