/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;

import comon.constant.PaginationConstant;
import comon.utilities.HibernateUtil;
import dto.sanpham.SearchAoDTO;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.Ao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author nguyenth28
 */
public class AoRepository {

    public List<Ao> findAll(int position, SearchAoDTO searchDTO) {
        List<Ao> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Ao x WHERE "
                    + "(:dai IS NULL OR :dai LIKE '' OR :dai BETWEEN x.sizeAo.rongVaiBeNhat AND x.sizeAo.rongVaiLonNhat) "
                    + "AND (:tayAo IS NULL OR :tayAo LIKE '' OR :tayAo BETWEEN x.sizeAo.tayAoBeNhat AND x.sizeAo.tayAoLonNhat) "
                    + "AND (:vai IS NULL OR :vai LIKE '' OR :vai BETWEEN x.sizeAo.rongVaiBeNhat AND x.sizeAo.rongVaiLonNhat) "
                    + "AND (:mauSac IS NULL OR :mauSac LIKE '' OR x.sanPham.mauSac.ten LIKE '%' + :mauSac + '%') "
                    + "AND (:chatLieu IS NULL OR :chatLieu LIKE '' OR x.sanPham.chatLieu.ten LIKE '%' + :chatLieu + '%') "
                    + "AND (:thuongHieu IS NULL OR :thuongHieu LIKE '' OR x.sanPham.thuongHieu.ten LIKE '%' + :thuongHieu + '%') "
                    + "AND (:ma IS NULL OR :ma LIKE '' OR x.sanPham.maSP LIKE '%' + :ma + '%')";
            TypedQuery<Ao> query = session.createQuery(hql, Ao.class);
            query.setParameter("dai", searchDTO.getDai());
            query.setParameter("tayAo", searchDTO.getTayAo());
            query.setParameter("vai", searchDTO.getVai());
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

    public List<Ao> findAll() {
        List<Ao> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Ao x";
            TypedQuery<Ao> query = session.createQuery(hql, Ao.class);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public Ao findById(String id) {
        Ao model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Ao x WHERE x.id = :id";
            TypedQuery<Ao> query = session.createQuery(hql, Ao.class);
            query.setParameter("id", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public Ao findByMa(String ma) {
        Ao model = null;
        List<Ao> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM Ao x JOIN SanPham y ON x.sanPham=y.id WHERE y.maSP = :ma";
            TypedQuery<Ao> query = session.createQuery(hql, Ao.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }

    public Ao save(Ao model) {
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
                String hql = "DELETE Ao x WHERE x.id = :id";
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
            String statement = "SELECT COUNT(x.id) FROM Ao x";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            total = query.getSingleResult();
        }
        return total;
    }
}
