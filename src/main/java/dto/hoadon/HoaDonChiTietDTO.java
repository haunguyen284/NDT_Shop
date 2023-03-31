/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.hoadon;

import comon.constant.TinhTrangHoaDon;
import dto.sanpham.SanPhamDTO;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class HoaDonChiTietDTO {

    private HoaDonDTO hoaDon;

    private SanPhamDTO sanPham;

    @NotNull(message = "Số lượng  - Không được để trống !")
    private int soLuong;

    @NotNull(message = "Đơn giá  - Không được để trống !")
    private float donGia;

    @NotNull(message = "Tình trạng hóa đơn  - Không được để trống !")
    private TinhTrangHoaDon tinhTrangHoaDon;
}
