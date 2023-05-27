/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.dongiao;

import comon.constant.PaginationConstant;
import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.dongiao.DonGiao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN KH
 */
public class DonGiaoRepository {

    public List<DonGiao> getAll(int currentPage, String maDG, TrangThaiDonGiao trangThaiDonGiao, YeuCauDonHang yeuCauDonHang) {
        String hql = "SELECT x FROM DonGiao x "
                + "WHERE (:maDG IS NULL OR :maDG LIKE '' OR x.maDG LIKE '%' + :maDG +'%') "
                + "AND (:trangThaiDonGiao IS NULL OR :trangThaiDonGiao LIKE '' OR x.trangThaiDonGiao LIKE '%' + :trangThaiDonGiao + '%') "
                + "AND (:yeuCauDonHang IS NULL OR :yeuCauDonHang LIKE '' OR x.yeuCauDonHang LIKE '%' + :yeuCauDonHang + '%') "
                + "AND x.thongSo != null";
        List<DonGiao> listDonGiao = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<DonGiao> query = s.createQuery(hql, DonGiao.class);
            query.setParameter("maDG", maDG);
            query.setParameter("yeuCauDonHang", yeuCauDonHang != null? String.valueOf(yeuCauDonHang.ordinal()):yeuCauDonHang);
            query.setParameter("trangThaiDonGiao", trangThaiDonGiao  != null? String.valueOf(trangThaiDonGiao.ordinal()):trangThaiDonGiao);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listDonGiao = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDonGiao;
    }

    public boolean saveOrUpdate(DonGiao x) {
        Transaction tx = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.saveOrUpdate(x);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

    public Optional<DonGiao> finByID(String id) {
        String hql = "FROM DonGiao x  WHERE x.id = :id";
        List<DonGiao> list = new ArrayList<>();
        DonGiao x = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<DonGiao> q = s.createQuery(hql, DonGiao.class);
            q.setParameter("id", id);
            list = q.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()) {
            x = list.get(0);
        }
        return Optional.ofNullable(x);
    }
    
    public DonGiao findById(String id) {
        DonGiao model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM DonGiao n Where n.id=:id";
            TypedQuery<DonGiao> query = session.createQuery(hql, DonGiao.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public long count() {
        long count = 0;
        String hql = "SELECT COUNT(x.id)FROM DonGiao x";
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery(hql);
            count = (long) q.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public String findId(String maDG) {
        String id;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT id FROM DonGiao n Where n.maDG=:maDG";
            TypedQuery<String> query = session.createQuery(hql, String.class);
            query.setParameter("maDG", maDG);
            id = query.getSingleResult();
        } catch (NoResultException e) {
            id = null;
        }
        return id;
    }

    public DonGiao save(DonGiao model) {
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
    
    public DonGiao findByHoaDon(String idHD) {
        DonGiao model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM DonGiao n Where n.hoaDon.id=:HD";
            TypedQuery<DonGiao> query = session.createQuery(hql, DonGiao.class);
            query.setParameter("HD", idHD);
            model = query.getSingleResult();
        }
        return model;
    }

}
