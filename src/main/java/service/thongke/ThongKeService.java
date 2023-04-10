/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.thongke;

import comon.constant.TinhTrangHoaDon;
import dto.thongke.DoanhThuTheoThang;
import dto.thongke.SanPhamThongKe;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface ThongKeService {
    List<DoanhThuTheoThang> getDoanhThuTheoTungThang(int year, TinhTrangHoaDon tinhTrang);
    double getDoanhThuNgay();
     double getDoanhThuThang();
     double getDoanhThuNam();
     long getTongDonNgay();
     List<SanPhamThongKe> getSPBanChay();
     List<SanPhamThongKe> getSPTon();
}
