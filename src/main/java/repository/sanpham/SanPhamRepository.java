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
import model.sanpham.SanPham;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN KH
 */
public class SanPhamRepository {

    private final SessionFactory factory;

    public List<SanPham> getAll(int currentPage) {
        String hql = "SELECT g FROM SanPham g";
        List<SanPham> listSanPham = new ArrayList<>();
        try ( Session s = factory.openSession()) {
            Query<SanPham> query = s.createQuery(hql, SanPham.class);
            query.setFirstResult((currentPage - 1) * PaginationConstant.DEFAULT_SIZE);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listSanPham = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSanPham;
    }

    public SanPhamRepository() {
        this.factory = HibernateUtil.getSessionFactory();
    }

    public Optional<SanPham> finByID(String id) {
        String hql = "FROM SanPham g  WHERE g.id = :id";
        List<SanPham> list = new ArrayList<>();
        SanPham x = null;
        try ( Session s = factory.openSession()) {
            Query<SanPham> q = s.createQuery(hql, SanPham.class);
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
        String hql = "SELECT COUNT(g.id)FROM SanPham g";
        try ( Session s = factory.openSession()) {
            Query q = s.createQuery(hql);
            count = (long) q.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return count;
    }
}
