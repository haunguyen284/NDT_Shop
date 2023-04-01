/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.khachhang.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class KhachHangRepository {

    public List<KhachHang> findAll(int position, int pageSize) {
        List<KhachHang> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM KhachHang n";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    public List<KhachHang> findByName(String ten, int position, int pageSize) {
        List<KhachHang> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM KhachHang n Where n.ten LIKE: ten";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("ten", "%"+ ten + "%");
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    
    public List<KhachHang> findAll() {
        List<KhachHang> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM KhachHang n";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public KhachHang findById(String id) {
        KhachHang model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM KhachHang n Where n.id=:id";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public KhachHang save(KhachHang model) {
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
                String hql = "DELETE KhachHang n WHERE n.id = :id";
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
            String hql = "SELECT COUNT(n.id) FROM KhachHang n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
    public static void main(String[] args) {
        System.out.println(new KhachHangRepository().findById("9719e7a7-8800-45be-92b7-6a2cbb52d8e3"));
    }
}
