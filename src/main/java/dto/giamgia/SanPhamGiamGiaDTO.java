/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.giamgia;

import comon.model.AuditModelDTO;
import dto.sanpham.SanPhamDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class SanPhamGiamGiaDTO extends AuditModelDTO {

    private SanPhamDTO sanPham;

    private GiamGiaDTO giamGia;

//    @NotNull(message = "Đơn giá  - Không được để trống !")
    private float donGia;

//    @NotNull(message = "Số tiền còn lại  - Không được để trống !")
    private float soTienConLai;

    public String convertLoaiSanPham() {
        switch (sanPham.getLoaiSp()) {
            case AO -> {
                return "ÁO";
            }
            case QUAN -> {
                return "QUẦN";
            }
            default ->
                throw new AssertionError();
        }
    }

    public Object[] toDataRow() {
        return new Object[]{getId(),giamGia.getMaGg(), giamGia.getTen(), giamGia.getGiaTriGiamGia(),
            new Date(giamGia.getNgayBatDau()),new Date(giamGia.getNgayKetThuc()) , sanPham.getTenSP(),
            convertLoaiSanPham(), giamGia.convertGiamGia()};
    }
    
    public Object[] toDataRowSanPhamGiamGiaTheoMaGG() {
        return new Object[]{getId(), sanPham.getTenSP(), sanPham.getGiaBan(), sanPham.getSoLuongTon(), convertLoaiSanPham(),sanPham.getThuongHieu().getTen(),sanPham.convertSanPham()};
    }
}
