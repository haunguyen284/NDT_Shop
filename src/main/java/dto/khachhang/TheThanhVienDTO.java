/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.khachhang;

import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.model.AuditModelDTO;
import comon.utilities.DateTimeUtil;
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
public class TheThanhVienDTO extends AuditModelDTO {

    @NotBlank(message = "Mã thẻ thành viên - Không được để trống !")
    private String maTTV;

    private ViDiemDTO viDiem;

    private LoaiTheDTO loaiThe;

    @NotNull(message = "Ngày phát hành - Không được để trống !")
    private Long ngayPhatHanh;

    @NotNull(message = "Ngày hêt hạn - Không được để trống !")
    private Long ngayHetHan;

    @NotNull(message = "Trạng thái thẻ  - Không được để trống !")
    private TrangThaiTheThanhVien trangThaiTheThanhVien;
    
    public String convertTrangThai(){
        String converted = "";
        switch(this.trangThaiTheThanhVien){
            case DANG_SU_DUNG:
                converted = "Đang sử dụng";
                break;
            case HET_HAN:
                converted = "Hết hạn";
                break;
        }
        return converted;
    }
    public Object[] toDataRow(){
        return new Object[]{maTTV, DateTimeUtil.formatDate(new Date(ngayPhatHanh)), DateTimeUtil.formatDate(new Date(ngayHetHan)), loaiThe.getTen(), viDiem.getTongDiem(), convertTrangThai()};
    }
}
