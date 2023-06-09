/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.khachhang;

import comon.model.AuditModelDTO;
import dto.hoadon.HoaDonDTO;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class LichSuTichDiemDTO extends AuditModelDTO {

    private ViDiemDTO viDiem;

    private List<HoaDonDTO> hoaDon;

    private QuyDoiDiemDTO quyDoiDiem;

    @NotNull(message = "Số điểm đã dùng - Không được để trống !")
    private int soDiemDaDung;

    @NotNull(message = "Số điểm cộng - Không được để trống !")
    private int soDiemCong;

    @NotNull(message = "Ngày sử dụng - Không được để trống !")
    private Long ngaySuDung;
}
