/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comon.utilities;

import comon.constant.Role;
import comon.constant.TinhTrangHoaDon;
import comon.constant.TrangThaiThanhToan;
import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.constant.giamgia.LoaiGiamGia;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.constant.khachhang.TrangThaiKhachHang;
import comon.constant.khachhang.TrangThaiQuyDoi;
import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.constant.khachhang.TrangThaiViDiem;
import comon.constant.sanpham.LoaiSanPham;
import comon.constant.sanpham.TrangThaiChatLieu;
import comon.constant.sanpham.TrangThaiDanhMuc;
import comon.constant.sanpham.TrangThaiMauSac;
import comon.constant.sanpham.TrangThaiQuanAo;
import comon.constant.sanpham.TrangThaiSanPham;
import comon.constant.sanpham.TrangThaiThuongHieu;
import java.util.Calendar;
import java.util.Random;
import javax.persistence.TypedQuery;
import model.dongiao.DonGiao;
import model.dongiao.ThongSo;
import model.giamgia.GiamGia;
import model.hoadon.HoaDon;
import model.hoadon.HoaDonChiTiet;
import model.khachhang.ViDiem;
import model.khachhang.KhachHang;
import model.khachhang.LoaiThe;
import model.khachhang.LichSuTieuDiem;
import model.khachhang.TheThanhVien;
import model.giamgia.SanPhamGiamGia;
import model.khachhang.QuyDoiDiem;
import model.nhanvien.GiaoCa;
import model.nhanvien.NhanVien;
import model.nhanvien.TaiKhoan;
import model.sanpham.Ao;
import model.sanpham.ChatLieu;
import model.sanpham.SizeQuan;
import model.sanpham.MauSac;
import model.sanpham.XuatXu;
import model.sanpham.Quan;
import model.sanpham.SanPham;
import model.sanpham.SizeAo;
import model.sanpham.ThuongHieu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author nguyenth28
 */
public class Migrator {

    //Tạo DB trong SQL SERVER = SOFT2041_PTPM
    //Sau đó tiến hành chạy đển zen bảng
    public static void main(String[] args) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("META-INF/hibernate.cfg.xml")
                .applySetting("hibernate.hbm2ddl.auto", "update")
                .build();

        // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder().build();

        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            String hql = "SELECT c FROM KhachHang c ORDER BY NEWID()";
            TypedQuery<KhachHang> query1 = session.createQuery(hql, KhachHang.class);
            query1.setMaxResults(1);
            KhachHang kh = query1.getSingleResult();

            String hql2 = "SELECT c FROM NhanVien c ORDER BY NEWID()";
            TypedQuery<NhanVien> query2 = session.createQuery(hql2, NhanVien.class);
            query2.setMaxResults(1);
            NhanVien nv = query2.getSingleResult();

            String hql3 = "SELECT c FROM SanPham c ORDER BY NEWID()";
            TypedQuery<SanPham> query3 = session.createQuery(hql3, SanPham.class);
            query3.setMaxResults(1);
            SanPham sp = query3.getSingleResult();

            Calendar calendar = Calendar.getInstance();
            int rdNgay = random.nextInt(28) + 1;
            calendar.set(2023, i, rdNgay);
            long timestamp = calendar.getTimeInMillis();

            String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int length = 6;
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int index = random.nextInt(allowedChars.length());
                char randomChar = allowedChars.charAt(index);
                sb.append(randomChar);
            }
            String randomString = sb.toString();

            HoaDon hd = new HoaDon();
            hd.setKhachHang(kh);
            hd.setNhanVien(nv);
            hd.setDiaChi("Việt Nam");
            hd.setTinhTrangHoaDon(TinhTrangHoaDon.DA_HUY);
            hd.setSoDiemQuyDoi(0);
            hd.setSoDiemSuDung(0);
            hd.setTenKH("Nguyen");
            hd.setMaHD("HD_" + randomString);
            hd.setNgayThanhToan(timestamp);
            int rdTien = random.nextInt(4900000) + 100000;
            hd.setTongTien(rdTien);
            session.save(hd);

            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hd);
            hdct.setSanPham(sp);
            int rdSL = random.nextInt(15) + 1;
            hdct.setSoLuong(rdSL);
            hdct.setDonGia(hdct.getSoLuong() * hdct.getSanPham().getGiaBan());
            hdct.setTinhTrangHoaDon(TinhTrangHoaDon.DA_HUY);
            session.save(hdct);
        }

        trans.commit();
    }}
