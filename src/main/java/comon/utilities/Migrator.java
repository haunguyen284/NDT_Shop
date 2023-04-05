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
import comon.constant.sanpham.TrangThaiXuatXu;
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
        // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
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
//        dữ liệu nhân viên
        NhanVien nv1 = new NhanVien();
        nv1.setTen("Lê Văn Cặc");
        nv1.setGioiTinh("Nam");
        nv1.setMaNV("NV01");
        nv1.setSdt("0973207405");
        nv1.setEmail("duylvph29683@fppt.edu.vn");
        nv1.setDiaChi("Hà Tĩnh");
        session.save(nv1);

        NhanVien nv = new NhanVien();
        nv.setTen("Trần Hậu Nguyên");
        nv.setGioiTinh("Nữ");
        nv.setMaNV("NV02");
        nv.setSdt("0973207000");
        nv.setEmail("nguyenth29600@fppt.edu.vn");
        nv.setDiaChi("DakLak");
        session.save(nv);

        NhanVien nv2 = new NhanVien();
        nv2.setTen("Nguyễn Thị Tuấn");
        nv2.setGioiTinh("Nam");
        nv2.setMaNV("NV03");
        nv2.setSdt("0973200000");
        nv2.setEmail("tuanna290000@fppt.edu.vn");
        nv2.setDiaChi("Hà Nội");
        session.save(nv2);

        TaiKhoan tk = new TaiKhoan();
        tk.setNhanVien(nv);
        tk.setTenTaiKhoan("TaiKhoan1");
        tk.setMatKhau("MatKhau1");
        tk.setRole(Role.ADMIN);
        session.save(tk);

        TaiKhoan tk1 = new TaiKhoan();
        tk1.setNhanVien(nv2);
        tk1.setTenTaiKhoan("TaiKhoan2");
        tk1.setMatKhau("MatKhau2");
        tk1.setRole(Role.STAFF);
        session.save(tk1);

        GiaoCa gc1 = new GiaoCa();
        gc1.setNhanVien(nv);
        gc1.setTongTienMatCaTruoc(120000);
        gc1.setTongTienMatRut(120000);
        gc1.setTienBanDau(50000);
        gc1.setTongTienTrongCa(1700000);
        gc1.setMaGC("GC01");
        gc1.setTienPhatSinh(2000);
        gc1.setGhiChuPhatSinh("Mua nước đóng bình");
        gc1.setChuThich("Ca 1");
        gc1.setTongTienKhac(200);
        gc1.setIdNhanVienCaTiepTheo(gc1.getNhanVien().getId());
        session.save(gc1);

        GiaoCa gc12 = new GiaoCa();
        gc12.setNhanVien(nv);
        gc12.setTongTienMatCaTruoc(400000);
        gc12.setTongTienMatRut(400000);
        gc12.setTienBanDau(200000);
        gc12.setTongTienTrongCa(600000);
        gc12.setMaGC("GC02");
        gc12.setTienPhatSinh(2000);
        gc12.setGhiChuPhatSinh("Tiền sửa máy là quần áo");
        gc12.setChuThich("Ca 2");
        gc12.setTongTienKhac(300);
        gc12.setIdNhanVienCaTiepTheo(gc1.getNhanVien().getId());
        session.save(gc12);

//        dữ liệu sản phẩm
        ThuongHieu th = new ThuongHieu();
        th.setMa("TH01");
        th.setTen("Tên thương hiệu 1");
        th.setTrangThaiThuongHieu(TrangThaiThuongHieu.ACTIVE);
        session.save(th);

        ThuongHieu th1 = new ThuongHieu();
        th1.setMa("TH02");
        th1.setTen("Tên thương hiệu 2");
        th1.setTrangThaiThuongHieu(TrangThaiThuongHieu.ACTIVE);
        session.save(th1);

        ThuongHieu th2 = new ThuongHieu();
        th2.setMa("TH03");
        th2.setTen("Tên thương hiệu 3");
        th2.setTrangThaiThuongHieu(TrangThaiThuongHieu.ACTIVE);
        session.save(th2);

        ChatLieu cl = new ChatLieu();
        cl.setMa("CL1");
        cl.setTen("Nilong");
        cl.setTrangThaiChatLieu(TrangThaiChatLieu.ACTIVE);
        session.save(cl);

        ChatLieu cl1 = new ChatLieu();
        cl1.setMa("CL10322");
        cl1.setTen("N7 + cốt tông");
        cl1.setTrangThaiChatLieu(TrangThaiChatLieu.ACTIVE);
        session.save(cl1);

        MauSac ms = new MauSac();
        ms.setMa("MS1");
        ms.setTen("Blue");
        ms.setTrangThaiMauSac(TrangThaiMauSac.ACTIVE);
        session.save(ms);

        MauSac ms1 = new MauSac();
        ms1.setMa("MS2");
        ms1.setTen("Yellow");
        ms1.setTrangThaiMauSac(TrangThaiMauSac.ACTIVE);
        session.save(ms1);

        XuatXu x = new XuatXu();
        x.setMa("X01");
        x.setTen("Xuất xứ 1");
        x.setTrangThaiXuatXu(TrangThaiXuatXu.ACTIVE);
        session.save(x);

        XuatXu x2 = new XuatXu();
        x2.setMa("X02");
        x2.setTrangThaiXuatXu(TrangThaiXuatXu.ACTIVE);
        x2.setTen("Xuất xứ 2");
        session.save(x2);

        SizeQuan s = new SizeQuan();
        s.setMa("Sq1");
        s.setTen("M");
        s.setEo(58);
        s.setMong(70);
        s.setTen("Quần 1");
        s.setCanNang(55);
        s.setTrangThaiQuanAo(TrangThaiQuanAo.ACTIVE);
        session.save(s);

        SizeQuan s1 = new SizeQuan();
        s1.setMa("Sq2");
        s1.setTen("L");
        s1.setEo(65);
        s1.setMong(78);
        s1.setTen("Quần 2");
        s1.setCanNang(68);
        s1.setTrangThaiQuanAo(TrangThaiQuanAo.ACTIVE);
        session.save(s1);

        SizeAo s3 = new SizeAo();
        s3.setMa("Sa3");
        s3.setTen("L");
        s3.setRongVaiBeNhat(57);
        s3.setRongVaiLonNhat(70);
        s3.setDaiBeNhat(47);
        s3.setDaiLonNhat(70);
        s3.setTen("Aó 2");
        s3.setTayAoBeNhat(48);
        s3.setTayAoLonNhat(70);
        s3.setTrangThaiQuanAo(TrangThaiQuanAo.ACTIVE);
        session.save(s3);

        SizeAo s4 = new SizeAo();
        s4.setMa("Sa4");
        s4.setTen("L");
        s3.setRongVaiBeNhat(30);
        s3.setRongVaiLonNhat(70);
        s3.setDaiBeNhat(30);
        s3.setDaiLonNhat(70);
        s3.setTen("Aó 1");
        s3.setTayAoBeNhat(30);
        s3.setTayAoLonNhat(70);
        s4.setTrangThaiQuanAo(TrangThaiQuanAo.ACTIVE);
        session.save(s4);

        SanPham sp = new SanPham();
        sp.setMaSP("SP01");
        sp.setTenSP("Tên sản phẩm 1");
        sp.setMoTa("Sản phẩm phù hợp với thời tiết mùa hè");
        sp.setGiaNhap(6000);
        sp.setGiaBan(8000);
        sp.setSoLuongTon(35);
        sp.setLoaiSp(LoaiSanPham.AO);
        sp.setXuatXu(x2);
        sp.setChatLieu(cl1);
        sp.setMauSac(ms1);
        sp.setThuongHieu(th2);
        sp.setTrangThaiSanPham(TrangThaiSanPham.ACTIVE);
        session.save(sp);

        SanPham sp1 = new SanPham();
        sp1.setMaSP("SP02");
        sp1.setTenSP("Tên sản phẩm 2");
        sp1.setMoTa("Tao nhã , lịch sự");
        sp1.setGiaNhap(11000);
        sp1.setGiaBan(13000);
        sp1.setSoLuongTon(14);
        sp1.setLoaiSp(LoaiSanPham.AO);
        sp1.setXuatXu(x);
        sp1.setChatLieu(cl);
        sp1.setMauSac(ms);
        sp1.setThuongHieu(th);
        sp1.setTrangThaiSanPham(TrangThaiSanPham.ACTIVE);
        session.save(sp1);

        SanPham sp2 = new SanPham();
        sp2.setMaSP("SP03");
        sp2.setTenSP("Tên sản phẩm 3");
        sp2.setMoTa("Thoáng mát, giá thành rẻ");
        sp2.setGiaNhap(2000);
        sp2.setGiaBan(2100);
        sp2.setSoLuongTon(30);
        sp2.setLoaiSp(LoaiSanPham.QUAN);
        sp2.setXuatXu(x2);
        sp2.setChatLieu(cl);
        sp2.setMauSac(ms1);
        sp2.setThuongHieu(th);
        sp2.setTrangThaiSanPham(TrangThaiSanPham.ACTIVE);
        session.save(sp2);

        SanPham sp3 = new SanPham();
        sp3.setMaSP("SP04");
        sp3.setTenSP("Tên sản phẩm 4");
        sp3.setMoTa("Yêu thương đong đầy");
        sp3.setGiaNhap(35000);
        sp3.setGiaBan(4000);
        sp3.setSoLuongTon(20);
        sp3.setLoaiSp(LoaiSanPham.QUAN);
        sp3.setXuatXu(x2);
        sp3.setChatLieu(cl);
        sp3.setMauSac(ms1);
        sp3.setThuongHieu(th);
        sp3.setTrangThaiSanPham(TrangThaiSanPham.ACTIVE);
        session.save(sp3);

        Ao a = new Ao();
        a.setSanPham(sp1);
        a.setSizeAo(s4);
        session.save(a);

        Ao a1 = new Ao();
        a1.setSanPham(sp);
        a1.setSizeAo(s3);
        session.save(a1);

        Quan q = new Quan();
        q.setSanPham(sp3);
        q.setSizeQuan(s1);
        session.save(q);

        Quan q1 = new Quan();
        q1.setSanPham(sp2);
        q1.setSizeQuan(s);
        session.save(q1);

//        Giam giá
        GiamGia g = new GiamGia();
        g.setMaGg("G01");
        g.setMucGiamGiaPhanTram(5);
        g.setMucGiamGiaTienMat(300);
        g.setNgayBatDau(Long.MIN_VALUE);
        g.setNgayKetThuc(Long.MAX_VALUE);
        g.setTen("Tên giảm giá 1");
        g.setMoTa("Khuyến mãi giảm giá nhân dịp sinh nhất chủ của hàng Lê Duy");
        g.setTrangThaiGiamGia(TrangThaiGiamGia.TRANG_THAI_1);
        g.setLoaiGiamGia(LoaiGiamGia.LOAI_1);
        g.setDieuKienGiamGia(1200);
        session.save(g);

        GiamGia g1 = new GiamGia();
        g1.setMaGg("G02");
        g1.setMucGiamGiaPhanTram(10);
        g1.setMucGiamGiaTienMat(600);
        g1.setNgayBatDau(Long.MIN_VALUE);
        g1.setNgayKetThuc(Long.MAX_VALUE);
        g1.setTen("Tên giảm giá 2");
        g1.setMoTa("Khuyến mãi giảm giá nhân dịp pass môn dự án 1");
        g1.setTrangThaiGiamGia(TrangThaiGiamGia.TRANG_THAI_2);
        g1.setLoaiGiamGia(LoaiGiamGia.LOAI_2);
        g1.setDieuKienGiamGia(3000);
        session.save(g1);

        SanPhamGiamGia spg = new SanPhamGiamGia();
        spg.setDonGia(5000);
        spg.setSanPham(sp3);
        spg.setSoTienConLai(4600);
        spg.setGiamGia(g1);
        session.save(spg);

        SanPhamGiamGia spg1 = new SanPhamGiamGia();
        spg1.setDonGia(5000);
        spg1.setSanPham(sp2);
        spg1.setSoTienConLai(4700);
        spg1.setGiamGia(g);
        session.save(spg1);

//        Khach hang
        ViDiem v = new ViDiem();
        v.setDiemDaCong(20);
        v.setDiemDaDung(30);
        v.setTongDiem(70);
        v.setTrangThaiViDiem(TrangThaiViDiem.TRANG_THAI_1);
        session.save(v);

        ViDiem v1 = new ViDiem();
        v1.setDiemDaCong(50);
        v1.setDiemDaDung(10);
        v1.setTongDiem(90);
        v1.setTrangThaiViDiem(TrangThaiViDiem.TRANG_THAI_2);
        session.save(v1);

        QuyDoiDiem qd = new QuyDoiDiem();
        qd.setTienTichDiem(2000);
        qd.setTienTieuDiem(1200);
        qd.setTrangThaiQuyDoi(TrangThaiQuyDoi.TRANG_THAI_1);
        session.save(qd);

        QuyDoiDiem qd1 = new QuyDoiDiem();
        qd1.setTienTichDiem(1000);
        qd1.setTienTieuDiem(2200);
        qd1.setTrangThaiQuyDoi(TrangThaiQuyDoi.TRANG_THAI_2);
        session.save(qd1);

        LoaiThe lt = new LoaiThe();
        lt.setSoLuong(5);
        lt.setTen("Name Cart is 1");
        lt.setGiaTri(10);
        session.save(lt);

        LoaiThe lt1 = new LoaiThe();
        lt1.setSoLuong(10);
        lt1.setTen("Name Cart is 2");
        lt1.setGiaTri(100);
        session.save(lt1);

        TheThanhVien ttt = new TheThanhVien();
        ttt.setMaTTV("TTT01");
        ttt.setNgayHetHan(Long.MAX_VALUE);
        ttt.setNgayPhatHanh(Long.MIN_VALUE);
        ttt.setTrangThaiTheThanhVien(TrangThaiTheThanhVien.HET_HAN);
        ttt.setLoaiThe(lt1);
        ttt.setViDiem(v1);
        session.save(ttt);

        TheThanhVien tt = new TheThanhVien();
        tt.setMaTTV("TTT02");
        tt.setNgayHetHan(Long.MAX_VALUE);
        tt.setNgayPhatHanh(Long.MIN_VALUE);
        tt.setTrangThaiTheThanhVien(TrangThaiTheThanhVien.DANG_SU_DUNG);
        tt.setLoaiThe(lt);
        tt.setViDiem(v);
        session.save(tt);

        KhachHang kh = new KhachHang();
        kh.setMaKH("KH01");
        kh.setDiaChi("Tokyo");
        kh.setEmail("email@gmail.com");
        kh.setGioiTinh("Nam");
        kh.setNgaySinh(0);
        kh.setTen("Tên khách hàng  1");
        kh.setSdt("098888888");
        kh.setGhiChu("Ghi chú 1");
        kh.setSoLanMua(5);
        kh.setTheThanhVien(tt);
        kh.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_1);
        session.save(kh);

        KhachHang kh1 = new KhachHang();
        kh.setMaKH("KH02");
        kh1.setDiaChi("USA");
        kh1.setEmail("duyldph29683@ft.edu");
        kh1.setGioiTinh("Nữ");
        kh1.setNgaySinh(0);
        kh1.setTen("Tên khách hàng 2");
        kh1.setSdt("0326521111");
        kh1.setGhiChu("Ghi chú 2");
        kh1.setSoLanMua(12);
        kh1.setTheThanhVien(ttt);
        kh1.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_2);
        session.save(kh1);

//        Hóa đơn
        HoaDon hd = new HoaDon();
        hd.setKhachHang(kh1);
        hd.setNhanVien(nv);
        hd.setDiaChi("Hàn Quốc");
        hd.setTinhTrangHoaDon(TinhTrangHoaDon.DA_THANH_TOAN);
        hd.setSoDiemQuyDoi(20);
        hd.setSoDiemSuDung(50);
        hd.setTenKH("null");
        hd.setMaHD("HD01");
        hd.setNgayThanhToan(Long.MAX_VALUE);
        hd.setTongTien(3200);
        session.save(hd);

        HoaDon hd1 = new HoaDon();
        hd1.setKhachHang(kh);
        hd1.setNhanVien(nv1);
        hd1.setDiaChi("Thái Lan");
        hd1.setTinhTrangHoaDon(TinhTrangHoaDon.CHUA_THANH_TOAN);
        hd1.setSoDiemQuyDoi(50);
        hd1.setSoDiemSuDung(60);
        hd1.setTenKH("null");
        hd1.setMaHD("HD02");
        hd1.setNgayThanhToan(Long.MAX_VALUE);
        hd1.setTongTien(7200);
        session.save(hd1);

//        hóa đơn chi tiết
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHoaDon(hd1);
        hdct.setSanPham(sp1);
        hdct.setSoLuong(5);
        hdct.setDonGia(hdct.getSoLuong() * hdct.getSanPham().getGiaBan());
        hdct.setTinhTrangHoaDon(TinhTrangHoaDon.DA_THANH_TOAN);
        session.save(hdct);

        HoaDonChiTiet hdct1 = new HoaDonChiTiet();
        hdct1.setHoaDon(hd1);
        hdct1.setSanPham(sp2);
        hdct1.setSoLuong(2);
        hdct1.setDonGia(hdct.getSoLuong() * hdct.getSanPham().getGiaBan());
        session.save(hdct1);

        HoaDonChiTiet hdct2 = new HoaDonChiTiet();
        hdct.setHoaDon(hd);
        hdct2.setSanPham(sp3);
        hdct2.setSoLuong(1);
        hdct2.setDonGia(hdct.getSoLuong() * hdct.getSanPham().getGiaBan());
        session.save(hdct2);

        HoaDonChiTiet hdct3 = new HoaDonChiTiet();
        hdct.setHoaDon(hd);
        hdct3.setSanPham(sp);
        hdct3.setSoLuong(5);
        hdct3.setDonGia(hdct.getSoLuong() * hdct.getSanPham().getGiaBan());
        session.save(hdct3);

//        Lịch sử tiêu điểm
        LichSuTieuDiem ls = new LichSuTieuDiem();
        ls.setNgaySuDung(Long.MIN_VALUE);
        ls.setQuyDoiDiem(qd1);
        ls.setSoDiemDaDung(200);
        ls.setSoDiemCong(100);
        ls.setViDiem(v1);
        session.save(ls);

        LichSuTieuDiem ls1 = new LichSuTieuDiem();
        ls1.setNgaySuDung(Long.MIN_VALUE);
        ls1.setQuyDoiDiem(qd);
        ls1.setSoDiemDaDung(100);
        ls1.setSoDiemCong(400);
        ls1.setViDiem(v);
        session.save(ls1);

//        Don giao
        ThongSo t = new ThongSo();
        t.setDai(20);
        t.setRong(17);
        t.setKhoiLuong(3);
        t.setCao(5);
        session.save(t);

        ThongSo t1 = new ThongSo();
        t1.setDai(28);
        t1.setRong(13);
        t1.setKhoiLuong(6);
        t1.setCao(7);
        session.save(t1);

        DonGiao dg = new DonGiao();
        dg.setDiaChi("Nam Quốc Sơn Hà");
        dg.setGhiChu("Nam Đế Cư");
        dg.setHoaDon(hd1);
        dg.setNgayGiao(Long.MAX_VALUE);
        dg.setTienThuHo(300);
        dg.setYeuCauDonHang(YeuCauDonHang.CHO_XEM);
        dg.setThongSo(t);
        dg.setSdtNguoiNhan("093333333");
        dg.setTrangThaiDonGiao(TrangThaiDonGiao.DANG_GIAO);
        dg.setMaDG("DG01");
        session.save(dg);

        DonGiao dg1 = new DonGiao();
        dg1.setDiaChi("Địa chỉ kh");
        dg1.setGhiChu("Ghi chú 1");
        dg1.setHoaDon(hd);
        dg1.setNgayGiao(Long.MAX_VALUE);
        dg1.setTienThuHo(0);
        dg1.setYeuCauDonHang(YeuCauDonHang.KHONG_CHO_XEM);
        dg1.setThongSo(t1);
        dg1.setTrangThaiDonGiao(TrangThaiDonGiao.CHUA_GIAO);
        dg1.setMaDG("DG02");
        session.save(dg1);

        trans.commit();
    }
}
