/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.dongiao;

import comon.model.AuditModelDTO;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ThongSoDTO extends AuditModelDTO {

    @NotNull(message = "Dài  - Không được để trống")
    private float dai;

    @NotNull(message = "Rộng  - Không được để trống")
    private float rong;

    @NotNull(message = "Cao  - Không được để trống")
    private float cao;

    @NotNull(message = "Khối lượng  - Không được để trống")
    private float khoiLuong;

    private String ma;
}
