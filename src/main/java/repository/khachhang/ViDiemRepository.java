/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.khachhang.ViDiem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class ViDiemRepository {

    public List<ViDiem> findAll(int position, int pageSize) {
        List<ViDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM ViDiem n ORDER BY n.giaTri";
            TypedQuery<ViDiem> query = session.createQuery(hql, ViDiem.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    
    public List<ViDiem> findAll() {
        List<ViDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM ViDiem n ORDER BY n.giaTri";
            TypedQuery<ViDiem> query = session.createQuery(hql, ViDiem.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public ViDiem findById(String id) {
        ViDiem model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM ViDiem n Where n.id=:id";
            TypedQuery<ViDiem> query = session.createQuery(hql, ViDiem.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public ViDiem save(ViDiem model) {
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
                String hql = "DELETE ViDiem n WHERE n.id = :id";
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
            String hql = "SELECT COUNT(n.id) FROM ViDiem n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
    
    public ViDiem findByTen(String ten) {
        ViDiem model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM ViDiem n Where n.ten=:ten";
            TypedQuery<ViDiem> query = session.createQuery(hql, ViDiem.class);
            query.setParameter("ten", ten);
            model = query.getSingleResult();
        }
        return model;
    }
}
