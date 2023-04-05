/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.khachhang.TheThanhVien;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class TheThanhVienRepository {

    public List<TheThanhVien> findAll(int position, int pageSize) {
        List<TheThanhVien> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM TheThanhVien n";
            TypedQuery<TheThanhVien> query = session.createQuery(hql, TheThanhVien.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    
    public List<TheThanhVien> findAll() {
        List<TheThanhVien> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM TheThanhVien n";
            TypedQuery<TheThanhVien> query = session.createQuery(hql, TheThanhVien.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public TheThanhVien findById(String id) {
        TheThanhVien model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM TheThanhVien n Where n.id=:id";
            TypedQuery<TheThanhVien> query = session.createQuery(hql, TheThanhVien.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }
    
    public String findId(String maTTV) {
        String id;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT id FROM TheThanhVien n Where n.maTTV=:maTTV";
            TypedQuery<String> query = session.createQuery(hql, String.class);
            query.setParameter("maTTV", maTTV);
            id = query.getSingleResult();
        } catch (NoResultException e) {
            id = null;
        }
        return id;
    }

    public TheThanhVien save(TheThanhVien model) {
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
                String hql = "DELETE TheThanhVien n WHERE n.id = :id";
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
            String hql = "SELECT COUNT(n.id) FROM TheThanhVien n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
    
    public TheThanhVien findByNgayHetHan(Long ngayHetHan) {
        TheThanhVien model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM TheThanhVien n Where n.ngayHetHan=:ngayHetHan";
            TypedQuery<TheThanhVien> query = session.createQuery(hql, TheThanhVien.class);
            query.setParameter("ngayHetHan", ngayHetHan);
            model = query.getSingleResult();
        }
        return model;
    }
    public TheThanhVien findByMaTheTV(String maTTV) {
        TheThanhVien model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM TheThanhVien n Where n.maTTV=:maTTV";
            TypedQuery<TheThanhVien> query = session.createQuery(hql, TheThanhVien.class);
            query.setParameter("maTTV", maTTV);
            model = query.getSingleResult();
        }catch(NoResultException e){
            model = null;
        }
        return model;
    }
}
