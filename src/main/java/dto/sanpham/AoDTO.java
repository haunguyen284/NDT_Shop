/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.sanpham;

import comon.model.AuditModelDTO;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class AoDTO extends AuditModelDTO{
 
    private SanPhamDTO sanPham;
  
    private SizeAoDTO sizeAo;
}
