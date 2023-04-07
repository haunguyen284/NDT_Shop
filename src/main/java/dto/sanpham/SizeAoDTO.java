/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.sanpham;

import comon.constant.sanpham.TrangThaiQuanAo;
import comon.model.AuditModelDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class SizeAoDTO extends AuditModelDTO {

    @NotBlank(message = "Mã Size áo - Không được để trống !")
    private String ma;

    @NotBlank(message = "Tên Size áo - Không được để trống !")
    private String ten;

    private String rongVaiBeNhat;
    
    private String rongVaiLonNhat;

    private String daiBeNhat;
    
    private String daiLonNhat;

    private String tayAoBeNhat;
    
    private String tayAoLonNhat;

    private TrangThaiQuanAo trangThaiQuanAo;

}
