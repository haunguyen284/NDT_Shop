/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.PaginationConstant;
import comon.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import model.sanpham.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN KH
 */
public class SanPhamRepository {

    public List<SanPham> getAll(int currentPage) {
        String hql = "SELECT g FROM SanPham g";
        List<SanPham> listSanPham = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<SanPham> query = s.createQuery(hql, SanPham.class);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listSanPham = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSanPham;
    }

    public List<SanPham> findAll(int position) {
        List<SanPham> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SanPham x";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            query.setFirstResult(position);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<SanPham> findAll() {
        List<SanPham> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SanPham x";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public SanPham findById(String id) {
        SanPham model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SanPham x WHERE x.id = :id";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }
//    Ruy

    public Optional<SanPham> finByID(String id) {
        String hql = "FROM SanPham g  WHERE g.id = :id";
        List<SanPham> list = new ArrayList<>();
        SanPham x = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<SanPham> q = s.createQuery(hql, SanPham.class);
            q.setParameter("id", id);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()) {
            x = list.get(0);
        }
        return Optional.ofNullable(x);
    }

    public long count() {
        long count = 0;
        String hql = "SELECT COUNT(g.id)FROM SanPham g";
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery(hql);
            count = (long) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
//    Ruy

    public SanPham findByMa(String ma) {
        SanPham model = null;
        List<SanPham> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SanPham x WHERE x.maSP = :ma";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }

    public SanPham save(SanPham model) {
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
}
