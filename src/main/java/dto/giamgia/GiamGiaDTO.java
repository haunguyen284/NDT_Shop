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

//    @NotNull(message = "Trạng thái giảm giá  - Không được để trống !")
    private TrangThaiGiamGia trangThaiGiamGia;

//    @NotNull(message = "Loại giảm giá  - Không được để trống !")
    private LoaiGiamGia loaiGiamGia;

    private String moTa;

    public Object[] toDataRow() {
        return new Object[]{getId(), ten, giaTriGiamGia, dieuKienGiamGia, loaiGiamGia, trangThaiGiamGia, new Date(ngayBatDau), new Date(ngayKetThuc), moTa};
    }
}
