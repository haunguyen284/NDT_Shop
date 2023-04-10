/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.thongke.impl;

import comon.constant.TinhTrangHoaDon;
import dto.thongke.DoanhThuTheoThang;
import dto.thongke.SanPhamThongKe;
import java.util.List;
import repository.thongke.ThongKeRepository;
import service.thongke.ThongKeService;

/**
 *
 * @author nguyenth28
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeRepository repository;

    public ThongKeServiceImpl() {
        this.repository = new ThongKeRepository();
    }

    @Override
    public List<DoanhThuTheoThang> getDoanhThuTheoTungThang(int year, TinhTrangHoaDon tinhTrang) {
        return repository.getDoanhThuTheoTungThang(year, tinhTrang);
    }

    @Override
    public double getDoanhThuNgay() {
        return repository.getDoanhThuNgay();
    }

    @Override
    public double getDoanhThuThang() {
        return repository.getDoanhThuThang();
    }

    @Override
    public double getDoanhThuNam() {
        return repository.getDoanhThuNam();
    }

    @Override
    public long getTongDonNgay() {
        return repository.getTongDonNgay();
    }

    @Override
    public List<SanPhamThongKe> getSPBanChay() {
        return repository.getSanPhamBanChay();
    }

    @Override
    public List<SanPhamThongKe> getSPTon() {
        return repository.getSPTon();
    }
}
