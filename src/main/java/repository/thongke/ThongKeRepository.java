/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.thongke;

import comon.constant.TinhTrangHoaDon;
import comon.utilities.HibernateUtil;
import dto.thongke.DoanhThuTheoThang;
import dto.thongke.SanPhamThongKe;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author nguyenth28
 */
public class ThongKeRepository {

    public List<DoanhThuTheoThang> getDoanhThuTheoTungThang(int year, TinhTrangHoaDon tinhTrangHoaDon) {
        List<DoanhThuTheoThang> results = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.set(Calendar.YEAR, year);
            startCalendar.set(Calendar.MONTH, Calendar.JANUARY);
            startCalendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = startCalendar.getTime();

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.set(Calendar.YEAR, year);
            endCalendar.set(Calendar.MONTH, Calendar.DECEMBER);
            endCalendar.set(Calendar.DAY_OF_MONTH, 31);
            Date endDate = endCalendar.getTime();

            Long startTimestamp = new Timestamp(startDate.getTime()).getTime();
            Long endTimestamp = new Timestamp(endDate.getTime()).getTime();

            String hql = "SELECT MONTH(DATEADD(SECOND, ngayThanhToan / 1000, '19700101')) as month, SUM(tongTien) as sumTongTien "
                    + "FROM HoaDon "
                    + "WHERE ngayThanhToan "
                    + "BETWEEN :startDate AND :endDate AND tinhTrangHoaDon=:tinhTrang "
                    + "GROUP BY MONTH(DATEADD(SECOND, ngayThanhToan / 1000, '19700101'))";
            TypedQuery<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("startDate", startTimestamp);
            query.setParameter("endDate", endTimestamp);
            query.setParameter("tinhTrang", tinhTrangHoaDon);
            List<Object[]> resultList = query.getResultList();
            for (Object[] obj : resultList) {
                int month = (int) obj[0];
                double sumTongTien = (double) obj[1];
                results.add(new DoanhThuTheoThang(month, sumTongTien));
            }
        }
        return results;
    }

    public double getDoanhThuNgay() {
        double total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date startDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
            Date endDate = new Date();

            Long startTimestamp = new Timestamp(startDate.getTime()).getTime();
            Long endTimestamp = new Timestamp(endDate.getTime()).getTime();

            String hql = "SELECT SUM(tongTien) as sumTongTien "
                    + "FROM HoaDon "
                    + "WHERE ngayThanhToan "
                    + "BETWEEN :startDate AND :endDate AND tinhTrangHoaDon=:tinhTrang";
            TypedQuery<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("startDate", startTimestamp);
            query.setParameter("endDate", endTimestamp);
            query.setParameter("tinhTrang", TinhTrangHoaDon.DA_THANH_TOAN);
            Double result = query.getSingleResult();
            if (result != null) {
                total = result;
            }
            return total;
        }
    }

    public double getDoanhThuThang() {
        double total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startDate = calendar.getTime();
            Date endDate = new Date();

            Date currentDateTime = new Date();
            Long startTimestamp = new Timestamp(startDate.getTime()).getTime();
            Long endTimestamp = new Timestamp(endDate.getTime()).getTime();

            String hql = "SELECT SUM(tongTien) as sumTongTien "
                    + "FROM HoaDon "
                    + "WHERE ngayThanhToan "
                    + "BETWEEN :startDate AND :endDate AND tinhTrangHoaDon=:tinhTrang";
            TypedQuery<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("startDate", startTimestamp);
            query.setParameter("endDate", endTimestamp);
            query.setParameter("tinhTrang", TinhTrangHoaDon.DA_THANH_TOAN);
            Double result = query.getSingleResult();
            if (result != null) {
                total = result;
            }
            return total;
        }
    }

    public double getDoanhThuNam() {
        double total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startDate = calendar.getTime();
            Date endDate = new Date();

            Date currentDateTime = new Date();
            Long startTimestamp = new Timestamp(startDate.getTime()).getTime();
            Long endTimestamp = new Timestamp(endDate.getTime()).getTime();

            String hql = "SELECT SUM(tongTien) as sumTongTien "
                    + "FROM HoaDon "
                    + "WHERE ngayThanhToan "
                    + "BETWEEN :startDate AND :endDate AND tinhTrangHoaDon=:tinhTrang";
            TypedQuery<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("startDate", startTimestamp);
            query.setParameter("endDate", endTimestamp);
            query.setParameter("tinhTrang", TinhTrangHoaDon.DA_THANH_TOAN);
            Double result = query.getSingleResult();
            if (result != null) {
                total = result;
            }
            return total;
        }
    }

    public long getTongDonNgay() {
        long total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date startDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
            Date endDate = new Date();

            Long startTimestamp = new Timestamp(startDate.getTime()).getTime();
            Long endTimestamp = new Timestamp(endDate.getTime()).getTime();

            String hql = "SELECT COUNT(id) "
                    + "FROM HoaDon "
                    + "WHERE ngayThanhToan "
                    + "BETWEEN :startDate AND :endDate AND tinhTrangHoaDon=:tinhTrang";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("startDate", startTimestamp);
            query.setParameter("endDate", endTimestamp);
            query.setParameter("tinhTrang", TinhTrangHoaDon.DA_THANH_TOAN);
            Long result = query.getSingleResult();
            if (result != null) {
                total = result;
            }
            return total;
        }
    }

    public List<SanPhamThongKe> getSanPhamBanChay() {
        List<SanPhamThongKe> results = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startDate = calendar.getTime();
            Date endDate = new Date();

            Long startTimestamp = new Timestamp(startDate.getTime()).getTime();
            Long endTimestamp = new Timestamp(endDate.getTime()).getTime();

            String hql = "SELECT TOP(4) SanPham.tenSP, SanPham.maSP, SUM(soLuong) as soLuong "
                    + "FROM HoaDonChiTiet "
                    + "JOIN SanPham ON HoaDonChiTiet.san_pham_id=SanPham.id "
                    + "WHERE HoaDonChiTiet.createdAt BETWEEN :startDate AND :endDate "
                    + "GROUP BY san_pham_id, SanPham.tenSP, SanPham.maSP "
                    + "ORDER BY SUM(soLuong) DESC";
            TypedQuery<Object[]> query = session.createSQLQuery(hql);
            query.setParameter("startDate", startTimestamp);
            query.setParameter("endDate", endTimestamp);
            List<Object[]> resultList = query.getResultList();
            for (Object[] obj : resultList) {
                String tenSP = (String) obj[0];
                String ma = (String) obj[1];
                int soLuong = (int) obj[2];
                results.add(new SanPhamThongKe(tenSP, ma, soLuong));
            }
        }
        return results;
    }

    public List<SanPhamThongKe> getSPTon() {
        List<SanPhamThongKe> results = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "SELECT TOP (10) tenSP, maSP, SUM(soLuongTon) "
                    + "FROM SanPham "
                    + "GROUP BY id, maSP, tenSP "
                    + "ORDER BY SUM(soLuongTon) DESC;";
            TypedQuery<Object[]> query = session.createSQLQuery(hql);
            List<Object[]> resultList = query.getResultList();
            for (Object[] obj : resultList) {
                String tenSP = (String) obj[0];
                String ma = (String) obj[1];
                int soLuong = (int) obj[2];
                results.add(new SanPhamThongKe(tenSP, ma, soLuong));
            }
        }
        return results;
    }

}
