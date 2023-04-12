/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.hoadon;

import comon.constant.PaginationConstant;
import comon.constant.TinhTrangHoaDon;
import comon.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.hoadon.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {

    public List<HoaDon> findByTinhTrang(TinhTrangHoaDon tinhTrangHoaDon) {
        List<HoaDon> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM HoaDon n Where n.tinhTrangHoaDon =:tinhTrang";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            query.setParameter("tinhTrang", tinhTrangHoaDon);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<HoaDon> findAll() {
        List<HoaDon> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM HoaDon n";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public HoaDon findById(String id) {
        HoaDon model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM HoaDon n Where n.id=:id";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }
    
    public String findId(String maHD) {
        String id;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT id FROM HoaDon n Where n.maHD=:maHD";
            TypedQuery<String> query = session.createQuery(hql, String.class);
            query.setParameter("maHD", maHD);
            id = query.getSingleResult();
        } catch (NoResultException e) {
            id = null;
        }
        return id;
    }

    public HoaDon save(HoaDon model) {
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
                String hql = "DELETE HoaDon n WHERE n.id = :id";
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
            String hql = "SELECT COUNT(n.id) FROM HoaDon n";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
    public List<HoaDon> getAllLichSuHoaDon(int currentPage) {
        String hql = "SELECT x FROM HoaDon x ORDER BY x.createdAt DESC";
        List<HoaDon> listSanPham = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<HoaDon> query = s.createQuery(hql, HoaDon.class);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listSanPham = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSanPham;
    }

    public List<HoaDon> searchByMaHoaDon(int currentPage, String searchByMa) {
        List<HoaDon> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM HoaDon x WHERE x.maHD LIKE '%' + :maHD +'%'";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            query.setParameter("maHD", searchByMa);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listModel = query.getResultList();
        }
        return listModel;
    }
}
