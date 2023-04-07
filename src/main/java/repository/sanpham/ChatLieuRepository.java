/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sanpham;


import comon.constant.sanpham.TrangThaiChatLieu;
import comon.utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import model.sanpham.ChatLieu;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nguyenth28
 */
public class ChatLieuRepository {

    public List<ChatLieu> findAll(TrangThaiChatLieu trangThai) {
        List<ChatLieu> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM ChatLieu x WHERE "
                    + "(:trangThaiChatLieu IS NULL "
                    + "OR trangThaiChatLieu LIKE '%' + :trangThaiChatLieu + '%')"
                    + "ORDER BY ten";
            TypedQuery<ChatLieu> query = session.createQuery(hql, ChatLieu.class);
            query.setParameter("trangThaiChatLieu", trangThai != null ? String.valueOf(trangThai.ordinal()): trangThai);
            listModel = query.getResultList();
        }
        return listModel;
    }

    public ChatLieu save(ChatLieu model) {
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

    public ChatLieu findByMa(String ma) {
        ChatLieu model = null;
        List<ChatLieu> listModel;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT x FROM ChatLieu x WHERE x.ma = :ma";
            TypedQuery<ChatLieu> query = session.createQuery(hql, ChatLieu.class);
            query.setParameter("ma", ma);
            listModel = query.getResultList();
        }
        if (!listModel.isEmpty()) {
            model = listModel.get(0);
        }
        return model;
    }
}
