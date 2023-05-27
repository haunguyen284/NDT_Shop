/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.giamgia;

import comon.constant.PaginationConstant;
import comon.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import model.giamgia.GiamGia;
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
public class GiamGiaRepository {

    private final SessionFactory factory;

    public GiamGiaRepository() {
        this.factory = HibernateUtil.getSessionFactory();
    }

    public List<GiamGia> getAll(int currentPage,String maGG) {
        String hql = "SELECT x FROM GiamGia x WHERE :maGG IS NULL OR :maGG LIKE '' OR x.maGg LIKE '%' + :maGG +'%'";
        List<GiamGia> listGiamGia = new ArrayList<>();
        try ( Session s = factory.openSession()) {
            Query<GiamGia> query = s.createQuery(hql, GiamGia.class);
            query.setParameter("maGG",maGG);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listGiamGia = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGiamGia;
    }

    public boolean saveOrUpdate(GiamGia x) {
        Transaction tx = null;
        try ( Session s = factory.openSession()) {
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

    public boolean delete(String id) {
        int check;
        String hql = "DELETE FROM GiamGia g WHERE g.id = :id";
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

    public Optional<GiamGia> finByID(String id) {
        String hql = "FROM GiamGia g  WHERE g.id = :id";
        List<GiamGia> list = new ArrayList<>();
        GiamGia x = null;
        try ( Session s = factory.openSession()) {
            Query<GiamGia> q = s.createQuery(hql, GiamGia.class);
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
        String hql = "SELECT COUNT(g.id)FROM GiamGia g";
        try ( Session s = factory.openSession()) {
            Query q = s.createQuery(hql);
            count = (long) q.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean updateTrangThai(Long ngayHienTai) {
        int check;
        String hql = "UPDATE GiamGia x SET x.trangThaiGiamGia =1 "
                + "WHERE x.trangThaiGiamGia = 0 "
                + "AND :ngayHienTai > x.ngayKetThuc "
                + "OR :ngayHienTai< x.ngayBatDau";
        Transaction tx = null;
        try ( Session s = factory.openSession()) {
            tx = s.beginTransaction();
            Query q = s.createQuery(hql);
            q.setParameter("ngayHienTai", ngayHienTai);
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
