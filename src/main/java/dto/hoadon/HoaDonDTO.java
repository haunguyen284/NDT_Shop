/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.hoadon;

import comon.constant.TinhTrangHoaDon;
import comon.model.AuditModelDTO;
import dto.khachhang.KhachHangDTO;
import dto.giamgia.GiamGiaDTO;
import dto.nhanvien.NhanVienDTO;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class HoaDonDTO extends AuditModelDTO {

    @NotBlank(message = "Mã hóa đơn - Không được để trống !")
    private String maHD;

    private KhachHangDTO khachHang;

    private NhanVienDTO nhanVien;

    private Long ngayThanhToan;

    @NotNull(message = "Tổng tiền - Không được để trống !")
    private float tongTien;

    @NotBlank(message = "Tên khách hàng - Không được để trống !")
    private String tenKH;

    @NotBlank(message = "Địa chỉ - Không được để trống !")
    private String diaChi;

    @NotNull(message = "Số điểm đã dùng - Không được để trống !")
    private int soDiemSuDung;

    @NotNull(message = "Số điểm quy đổi - Không được để trống !")
    private int soDiemQuyDoi;

    private TinhTrangHoaDon tinhTrangHoaDon;
    
    private String convertTrangThai(){
        String converted = "";
        switch(this.tinhTrangHoaDon){
            case CHUA_THANH_TOAN:
                converted = "Chưa thanh toán";
                break;
            case DA_HUY:
                converted = "Đã huỷ";
                break;
            case DA_THANH_TOAN:
                converted = "Đã thanh toán";
                break;
            case HOAN_TRA:
                converted = "Hoàn trả";
                break;
            case DOI_HANG:
                converted = "Đổi hàng";
                break;
        }
        return converted;
    }
    
    public Object[] toDataRow(){
        return new Object[]{getId(), Objects.isNull(nhanVien) ? "Null" : nhanVien.getMaNV(), Objects.isNull(khachHang) ? "Null" : khachHang.getMaKH(), tongTien, convertTrangThai()};
    }
    
    public Object[] toDataRowLichSu() {
        return new Object[]{maHD, khachHang != null ? khachHang.getTen() : tenKH, nhanVien.getTen(),ngayThanhToan!=null?new Date(ngayThanhToan):null, tongTien+"vnđ", convertTrangThai()};
    }
}
