/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.khachhang;

import comon.constant.khachhang.TrangThaiViDiem;
import comon.model.AuditModelDTO;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ViDiemDTO extends AuditModelDTO {

    @NotNull(message = "Điểm đã cộng - Không được để trống !")
    private int diemDaCong;

    @NotNull(message = "Điểm đã dùng - Không được để trống !")
    private int diemDaDung;

    @NotNull(message = "Tổng điểm - Không được để trống !")
    private int tongDiem;

    @NotNull(message = "Trạng thái - Không được để trống !")
    private TrangThaiViDiem trangThaiViDiem;
    
    public String convertTrangThai(){
        switch (trangThaiViDiem) {
            case TRANG_THAI_1:
                return "Đang hoạt động";
            case TRANG_THAI_2:
                return "Dừng hoạt động";
            default:
                throw new AssertionError();
        }
    }
}
