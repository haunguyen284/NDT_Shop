/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.sanpham;

import comon.constant.sanpham.LoaiSanPham;
import comon.constant.sanpham.TrangThaiSanPham;
import comon.model.AuditModelDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class SanPhamDTO extends AuditModelDTO {

    private XuatXuDTO xuatXu;


    private ChatLieuDTO chatLieu;

    private MauSacDTO mauSac;

    private ThuongHieuDTO thuongHieu;

    @NotBlank(message = "Mã sản phẩm - Không được để trống !")
    private String maSP;

    @NotBlank(message = "Tên sản phẩm - Không được để trống !")
    private String tenSP;

    private String giaNhap;

    private String giaBan;

    private String soLuongTon;

    private String moTa;

    private LoaiSanPham loaiSp;

    private TrangThaiSanPham trangThaiSanPham;
}
