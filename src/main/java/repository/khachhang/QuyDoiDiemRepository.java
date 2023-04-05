/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.khachhang.QuyDoiDiem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class QuyDoiDiemRepository {

    public List<QuyDoiDiem> findAll(int position, int pageSize) {
        List<QuyDoiDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM QuyDoiDiem n ORDER BY n.giaTri";
            TypedQuery<QuyDoiDiem> query = session.createQuery(hql, QuyDoiDiem.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    
    public List<QuyDoiDiem> findAll() {
        List<QuyDoiDiem> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM QuyDoiDiem n ORDER BY n.giaTri";
            TypedQuery<QuyDoiDiem> query = session.createQuery(hql, QuyDoiDiem.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public QuyDoiDiem findById(String id) {
        QuyDoiDiem model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM QuyDoiDiem n Where n.id=:id";
            TypedQuery<QuyDoiDiem> query = session.createQuery(hql, QuyDoiDiem.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public QuyDoiDiem save(QuyDoiDiem model) {
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
                String hql = "DELETE QuyDoiDiem n WHERE n.id = :id";
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
            String hql = "SELECT COUNT(n.id) FROM QuyDoiDiem n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
    
    public QuyDoiDiem findByTen(String ten) {
        QuyDoiDiem model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM QuyDoiDiem n Where n.ten=:ten";
            TypedQuery<QuyDoiDiem> query = session.createQuery(hql, QuyDoiDiem.class);
            query.setParameter("ten", ten);
            model = query.getSingleResult();
        }
        return model;
    }
}
