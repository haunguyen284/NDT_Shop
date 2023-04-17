/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.PaginationConstant;
import comon.utilities.HibernateUtil;
import dto.sanpham.SearchAoDTO;
import dto.sanpham.SearchQuanDTO;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.Ao;
import model.sanpham.Quan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author nguyenth28
 */
public class QuanRepository {

    public List<Quan> findAll(int position, SearchQuanDTO searchDTO) {
        List<Quan> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Quan x WHERE "
                    + "(:eo IS NULL OR :eo LIKE '' OR :eo BETWEEN x.sizeQuan.eoBeNhat AND x.sizeQuan.eoLonNhat) "
                    + "AND (:canNang IS NULL OR :canNang LIKE '' OR :canNang BETWEEN x.sizeQuan.canNangBeNhat AND x.sizeQuan.canNangLonNhat) "
                    + "AND (:mong IS NULL OR :mong LIKE '' OR :mong BETWEEN x.sizeQuan.mongBeNhat AND x.sizeQuan.mongLonNhat) "
                    + "AND (:mauSac IS NULL OR :mauSac LIKE '' OR x.sanPham.mauSac.ten LIKE '%' + :mauSac + '%') "
                    + "AND (:chatLieu IS NULL OR :chatLieu LIKE '' OR x.sanPham.chatLieu.ten LIKE '%' + :chatLieu + '%') "
                    + "AND (:thuongHieu IS NULL OR :thuongHieu LIKE '' OR x.sanPham.thuongHieu.ten LIKE '%' + :thuongHieu + '%') "
                    + "AND (:ma IS NULL OR :ma LIKE '' OR x.sanPham.maSP LIKE '%' + :ma + '%') "
                    + "ORDER BY x.sanPham.createdAt DESC";
            TypedQuery<Quan> query = session.createQuery(hql, Quan.class);
            query.setParameter("canNang", searchDTO.getCanNang());
            query.setParameter("eo", searchDTO.getEo());
            query.setParameter("mong", searchDTO.getMong());
            query.setParameter("mauSac", searchDTO.getMauSac());
            query.setParameter("chatLieu", searchDTO.getChatLieu());
            query.setParameter("thuongHieu", searchDTO.getThuongHieu());
            query.setParameter("ma", searchDTO.getMa());

            query.setFirstResult(position);
            query.setMaxResults(PaginationConstant.DEFAULT_SIZE);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public List<Quan> findAll() {
        List<Quan> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Quan x";
            TypedQuery<Quan> query = session.createQuery(hql, Quan.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public Quan findById(String id) {
        Quan model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Quan x WHERE x.id = :id";
            TypedQuery<Quan> query = session.createQuery(hql, Quan.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public Quan findByMa(String ma) {
        Quan model = null;
        List<Quan> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Quan x JOIN SanPham y ON x.sanPham=y.id WHERE y.maSP = :ma";
            TypedQuery<Quan> query = session.createQuery(hql, Quan.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }

    public Quan save(Quan model) {
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
                String hql = "DELETE Quan x WHERE x.id = :id";
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
            String statement = "SELECT COUNT(x.id) FROM Quan x";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
}
