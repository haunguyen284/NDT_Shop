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
public class SizeQuanDTO extends AuditModelDTO {

    @NotBlank(message = "Mã Size quần - Không được để trống !")
    private String ma;

    @NotBlank(message = "Tên Size quần - Không được để trống !")
    private String ten;

    private String eoBeNhat;
    
    private String eoLonNhat;

    private String mongBeNhat;
    
    private String mongLonNhat;

    private String canNangBeNhat;
    
    private String canNangLonNhat;

    private TrangThaiQuanAo trangThaiQuanAo;

}
