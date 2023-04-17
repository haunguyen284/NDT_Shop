/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.khachhang;

import comon.constant.khachhang.TrangThaiKhachHang;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.NoResultException;
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
            query.setFirstResult(position*pageSize);
            query.setMaxResults(pageSize);
            listModel = query.getResultList();
        }
        return listModel;
    }
    
    public List<KhachHang> findByTrangThai(TrangThaiKhachHang trangThaiKhachHang,int position, int pageSize) {
        List<KhachHang> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM KhachHang n Where n.trangThaiKhachHang =:trangThaiKhachHang";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("trangThaiKhachHang", trangThaiKhachHang);
            query.setFirstResult(position*pageSize);
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
            query.setParameter("ten", "%" + ten + "%");
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
    
    public KhachHang findBySDT(String sdt) {
        KhachHang model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM KhachHang n Where n.sdt=:sdt";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("sdt", sdt);
            model = query.getSingleResult();
        } catch (Exception e) {
                e.printStackTrace();
                model = null;
            }
        return model;
    }
    
    public KhachHang findByIdTheThanhVien(String idTTV) {
        KhachHang model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "SELECT n FROM KhachHang n Where n.the_thanh_vien_id=:idTTV";
            String hql = "SELECT n FROM KhachHang n WHERE n.theThanhVien.id = :idTTV";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("idTTV", idTTV);
            model = query.getSingleResult();
        }
        return model;
    }

    public String findId(String maKH) {
        String id;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT id FROM KhachHang n Where n.maKH=:maKH";
            TypedQuery<String> query = session.createQuery(hql, String.class);
            query.setParameter("maKH", maKH);
            id = query.getSingleResult();
        } catch (NoResultException e) {
            id = null;
        }
        return id;
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

    public long totalCount() {
        long total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(n.id) FROM KhachHang n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
}
