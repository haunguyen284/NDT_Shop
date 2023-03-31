/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.sanpham;

import comon.constant.sanpham.TrangThaiChatLieu;
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
public class ChatLieuDTO extends AuditModelDTO {

    @NotBlank(message = "Mã chất liệu - Không được để trống !")
    private String ma;

    @NotBlank(message = "Tên chất liệu - Không được để trống !")
    private String ten;

    private TrangThaiChatLieu trangThaiChatLieu;
}
