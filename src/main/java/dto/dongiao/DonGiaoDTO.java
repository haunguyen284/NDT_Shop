/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.dongiao;

import comon.constant.ModelProperties;
import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.model.AuditModelDTO;
import dto.hoadon.HoaDonDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class DonGiaoDTO extends AuditModelDTO {

//    @NotBlank(message = "Mã Đơn Giao - Không được để trống !")
    private String maDG;

    private HoaDonDTO hoaDon;

//    @NotBlank(message = "Địa chỉ - Không được để trống !")
    private String diaChi;

//    @NotNull(message = "Tiền thu hộ - Không được để trống")
    private float tienThuHo;

    private Long ngayGiao;
//
//    @NotNull(message = "Trạng thái  - Không được để trống")
    private TrangThaiDonGiao trangThaiDonGiao;

    private YeuCauDonHang yeuCauDonHang;

//    @NotBlank(message = "Số điện thoại - Không được để trống !")
    @Pattern(regexp = ModelProperties.REGEX_PHONE_NUMBER, message = "Số điện thoại - Không đúng định dạng !")
    private String sdtNguoiNhan;

    private String ghiChu;

    private ThongSoDTO thongSo;

    public String convertTrangThai() {
        String converted = "";
        switch (this.trangThaiDonGiao) {
            case CHUA_GIAO:
                converted = "Chưa giao";
                break;
            case DANG_GIAO:
                converted = "Đang giao";
                break;
            case DA_GIAO:
                converted = "Đã giao";
                break;
        }
        return converted;
    }

    public String convertYeuCauDonHang() {
        String converted = "";
        switch (this.yeuCauDonHang) {
            case KHONG_CHO_XEM:
                converted = "Không cho xem";
                break;
            case CHO_THU:
                converted = "Cho thử";
                break;
            case CHO_XEM:
                converted = "Cho xem";
                break;
        }
        return converted;
    }

    public Object[] toDataRow() {
        return new Object[]{getId(), maDG, hoaDon.getMaHD(), hoaDon.getKhachHang().getTen() != null ? hoaDon.getKhachHang().getTen() : null,
            diaChi, tienThuHo, new Date(ngayGiao), sdtNguoiNhan, thongSo.getId(), convertYeuCauDonHang(), convertTrangThai()};
    }
}
