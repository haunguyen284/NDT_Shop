/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.nhanvien;

import comon.constant.PaginationConstant;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.nhanvien.TaiKhoan;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dell
 */
public class TaiKhoanRepositoy {

    public List<TaiKhoan> findAll(int position) {
        List<TaiKhoan> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setFirstResult(position);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<TaiKhoan> findAll() {
        List<TaiKhoan> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public TaiKhoan findById(String id) {
        TaiKhoan model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x WHERE x.id = :id";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public TaiKhoan findByIdNhanVien(String idNV) {
        TaiKhoan model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x WHERE x.nhanVien.id = :idNV";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setParameter("idNV", idNV);
            model = query.getSingleResult();
        } catch (NoResultException e) {
            model = null;
        }
        return model;
    }

    public TaiKhoan findByMa(String ma) {
        TaiKhoan model = null;
        List<TaiKhoan> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x WHERE x.ma = :ma";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }

    public TaiKhoan save(TaiKhoan model) {
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
        int result = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE TaiKhoan x WHERE x.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                result = query.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result > 0;
    }

    public long totalCount() {
        long total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String statement = "SELECT COUNT(x.id) FROM ChatLieu x";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }

    public TaiKhoan login(String user, String pass) {
        TaiKhoan model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x WHERE x.tenTaiKhoan = :user and x.matKhau = :pass";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setParameter("user", user);
            query.setParameter("pass", pass);
            model = query.getSingleResult();
        }
        return model;
    }

    public TaiKhoan findByTenTaiKhoan(String user) {
        TaiKhoan model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM TaiKhoan x WHERE x.tenTaiKhoan = :user";
            TypedQuery<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setParameter("user", user);
            model = query.getSingleResult();
        }
        return model;
    }

    public String findMatKhau(String id) {
        String matKhau;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT matKhau FROM TaiKhoan n Where n.id=:id";
            TypedQuery<String> query = session.createQuery(hql, String.class);
            query.setParameter("id", id);
            matKhau = query.getSingleResult();
        } catch (NoResultException e) {
            matKhau = null;
        }
        return matKhau;
    }

}
