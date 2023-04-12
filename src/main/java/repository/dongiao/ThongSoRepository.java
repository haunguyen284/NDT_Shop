/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.dongiao;

import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.dongiao.ThongSo;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ThongSoRepository {

    public ThongSo findById(String id) {
        ThongSo model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM ThongSo n Where n.ma=:ma";
            TypedQuery<ThongSo> query = session.createQuery(hql, ThongSo.class);
            query.setParameter("ma", id);
            model = query.getSingleResult();
        }
        return model;
    }

    public ThongSo findByDonGiao(String maDG) {
        ThongSo model;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT n FROM ThongSo n Where n.donGiao.maDG=:maDG";
            TypedQuery<ThongSo> query = session.createQuery(hql, ThongSo.class);
            query.setParameter("idDG", maDG);
            model = query.getSingleResult();
        }
        return model;
    }

    public ThongSo save(ThongSo model) {
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
