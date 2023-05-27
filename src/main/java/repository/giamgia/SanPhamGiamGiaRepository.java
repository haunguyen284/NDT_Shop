/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.giamgia;

import comon.constant.PaginationConstant;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.constant.sanpham.LoaiSanPham;
import comon.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import model.giamgia.GiamGia;
import model.giamgia.SanPhamGiamGia;
import model.sanpham.SanPham;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN KH
 */
public class SanPhamGiamGiaRepository {

    private final SessionFactory factory;

    public SanPhamGiamGiaRepository() {
        this.factory = HibernateUtil.getSessionFactory();
    }

    public List<SanPhamGiamGia> getAll(int currentPage, TrangThaiGiamGia trangThaiGiamGia, LoaiSanPham loaiSanPham, String maGG) {
        String hql = "SELECT x FROM SanPhamGiamGia x WHERE "
                + "(:trangThai IS NULL OR :trangThai LIKE '' OR x.giamGia.trangThaiGiamGia LIKE '%' + :trangThai + '%')"
                + " AND (:loaiSP IS NULL OR :loaiSP LIKE '' OR x.sanPham.loaiSp LIKE '%' + :loaiSP + '%')"
                + " AND (:maGG IS NULL OR :maGG LIKE '' OR x.giamGia.maGg LIKE '%' + :maGG + '%')";
        List<SanPhamGiamGia> listSanPhamGiamGia = new ArrayList<>();
        try ( Session s = factory.openSession()) {
            Query<SanPhamGiamGia> query = s.createQuery(hql, SanPhamGiamGia.class);
            query.setParameter("trangThai", trangThaiGiamGia != null ? String.valueOf(trangThaiGiamGia.ordinal()) : trangThaiGiamGia);
            query.setParameter("loaiSP", loaiSanPham != null ? String.valueOf(loaiSanPham.ordinal()) : loaiSanPham);
            query.setParameter("maGG", maGG);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listSanPhamGiamGia = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSanPhamGiamGia;
    }

    public boolean saveOrUpdate(List<SanPhamGiamGia> listSPGG) {
        Transaction tx = null;
        try ( Session s = factory.openSession()) {
            tx = s.beginTransaction();
            for (SanPhamGiamGia x : listSPGG) {
                s.saveOrUpdate(x);
            }
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

    public boolean delete(String id) {
        int check;
        String hql = "DELETE FROM SanPhamGiamGia g WHERE g.id = :id";
        Transaction tx = null;
        try ( Session s = factory.openSession()) {
            tx = s.beginTransaction();
            Query q = s.createQuery(hql);
            q.setParameter("id", id);
            check = q.executeUpdate();
            tx.commit();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

    public Optional<SanPhamGiamGia> finByID(String id) {
        String hql = "FROM SanPhamGiamGia g  WHERE g.id = :id";
        List<SanPhamGiamGia> list = new ArrayList<>();
        SanPhamGiamGia x = null;
        try ( Session s = factory.openSession()) {
            Query<SanPhamGiamGia> q = s.createQuery(hql, SanPhamGiamGia.class);
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

    public long count() {
        long count = 0;
        String hql = "SELECT COUNT(g.id)FROM SanPhamGiamGia g";
        try ( Session s = factory.openSession()) {
            Query q = s.createQuery(hql);
            count = (long) q.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<SanPhamGiamGia> listSanPhamTheoMaGG(int currentPage, String searchByMa) {
        List<SanPhamGiamGia> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM SanPhamGiamGia x WHERE x.giamGia.maGg = :maGG";
            TypedQuery<SanPhamGiamGia> query = session.createQuery(hql, SanPhamGiamGia.class);
            query.setParameter("maGG", searchByMa);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public boolean deleteSanPhamByIdGiamGia(String id) {
        int check;
        String hql = "DELETE FROM SanPhamGiamGia x WHERE x.id = :id";
        Transaction tx = null;
        try ( Session s = factory.openSession()) {
            tx = s.beginTransaction();
            Query q = s.createQuery(hql);
            q.setParameter("id", id);
            check = q.executeUpdate();
            tx.commit();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

}
