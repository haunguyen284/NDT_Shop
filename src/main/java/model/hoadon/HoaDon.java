/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.hoadon;

import comon.constant.ModelProperties;
import model.nhanvien.NhanVien;
import model.khachhang.KhachHang;
import comon.constant.TinhTrangHoaDon;
import comon.model.PrimaryModel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author nguyenth28
 */
@Getter
@Setter
@Table(name = "HoaDon")
@Entity
public class HoaDon extends PrimaryModel implements Serializable {

    @Column(length = ModelProperties.LENGTH_CODE)
    private String maHD;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;


    @Column
    private Long ngayThanhToan;

    @Column
    private float tongTien;

    @OneToMany(mappedBy = "hoaDon")
    private Set<HoaDonChiTiet> dsHoaDonChiTiet;

    @Column(length = ModelProperties.LENGTH_NAME)
    @Nationalized
    private String tenKH;
    @Column
    private String diaChi;
    @Column
    private int soDiemSuDung;

    @Column
    private int soDiemQuyDoi;

    @Column
    private TinhTrangHoaDon tinhTrangHoaDon;
}
