/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.giamgia;

import comon.constant.ModelProperties;
import comon.constant.giamgia.LoaiGiamGia;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.model.AuditModelDTO;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class GiamGiaDTO extends AuditModelDTO {

    @NotBlank(message = "Mã Giam giá - Không được để trống !")
    private String maGg;

    @NotBlank(message = "Tên - Không được để trống !")
    private String ten;

    private Long ngayBatDau;

    private Long ngayKetThuc;

    @NotNull(message = "Mức giảm giá phần trăm  - Không được để trống !")
    private float mucGiamGiaPhanTram;

    @NotNull(message = "Mức giảm giá tiền mặt  - Không được để trống !")
    private float mucGiamGiaTienMat;

    @NotNull(message = "Điều kiện giảm giá  - Không được để trống !")
    private float dieuKienGiamGia;

    @NotNull(message = "Trạng thái giảm giá  - Không được để trống !")
    private TrangThaiGiamGia trangThaiGiamGia;

    @NotNull(message = "Loại giảm giá  - Không được để trống !")
    private LoaiGiamGia loaiGiamGia;

    private String moTa;
}
