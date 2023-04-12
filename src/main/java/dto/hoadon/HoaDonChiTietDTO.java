/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.hoadon;

import comon.constant.TinhTrangHoaDon;
import comon.model.AuditModelDTO;
import comon.utilities.VndConvertUtil;
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
public class HoaDonChiTietDTO extends AuditModelDTO{

    private HoaDonDTO hoaDon;

    private SanPhamDTO sanPham;

    @NotNull(message = "Số lượng  - Không được để trống !")
    private int soLuong;

    @NotNull(message = "Đơn giá  - Không được để trống !")
    private float donGia;

    @NotNull(message = "Tình trạng hóa đơn  - Không được để trống !")
    private TinhTrangHoaDon tinhTrangHoaDon;
    
    public String convertedTinhTrang(){
        switch (tinhTrangHoaDon) {
            case CHUA_THANH_TOAN:
                return "Chưa thanh toán";
            case DA_HUY:
                return "Đã huỷ";
            case DA_THANH_TOAN:
                return "Đã thanh toán";
            case HOAN_TRA:
                return "Hoàn trả";
            case DOI_HANG:
                return "Đổi hàng";
            default:
                throw new AssertionError();
        }
    }
    
    public Object[] toDataRow(){
        return new Object[]{getSanPham().getId(),getSanPham().getMaSP(),getSanPham().getTenSP(),soLuong,donGia,VndConvertUtil.floatToVnd(donGia*soLuong)};
    }
}
