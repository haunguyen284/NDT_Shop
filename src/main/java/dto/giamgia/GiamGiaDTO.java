/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.giamgia;

import comon.constant.ModelProperties;
import comon.constant.giamgia.LoaiGiamGia;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.model.AuditModelDTO;
import java.util.Date;
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
public class GiamGiaDTO extends AuditModelDTO {

//    @NotBlank(message = "Mã Giam giá - Không được để trống !")
    private String maGg;

    @NotBlank(message = "Tên - Không được để trống !")
    private String ten;

    private Long ngayBatDau;

    private Long ngayKetThuc;

//    @NotNull(message = "Gía trị giảm giá  - Không được để trống !")
    private float giaTriGiamGia;

//    @NotNull(message = "Điều kiện giảm giá  - Không được để trống !")
    private float dieuKienGiamGia;

    private TrangThaiGiamGia trangThaiGiamGia;

    private LoaiGiamGia loaiGiamGia;

    private String moTa;

    public String convertGiamGia() {
        switch (trangThaiGiamGia) {
            case DANG_HOAT_DONG -> {
                return "Đang hoạt động";
            }
            case NGUNG_HOAT_DONG -> {
                return "Ngừng hoạt động";
            }
            default ->
                throw new AssertionError();
        }
    }

    public String convertLoaiGiamGia() {
        switch (loaiGiamGia) {
            case GIAM_GIA_THEO_PHAN_TRAM -> {
                return "Giảm giá theo %";
            }
            case GIAM_GIA_THEO_TIEN_MAT -> {
                return "Giảm giá theo tiền mặt";
            }
            default ->
                throw new AssertionError();
        }
    }

    public Object[] toDataRow() {
        return new Object[]{getId(), ten, giaTriGiamGia, dieuKienGiamGia,convertLoaiGiamGia(), convertGiamGia(), new Date(ngayBatDau), new Date(ngayKetThuc), moTa};
    }

}
