/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.sanpham;

import comon.constant.sanpham.TrangThaiSanPham;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nguyenth28
 */

@Getter
@Setter
public class SearchAoDTO {
    private String vai;
    private String dai;
    private String tayAo;
    private String mauSac;
    private String chatLieu;
    private String thuongHieu;
    private TrangThaiSanPham trangThaiSanPham;
    private String ma;
}
