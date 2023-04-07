/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiQuanAo;
import dto.sanpham.SizeAoDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface SizeAoService {

    List<SizeAoDTO> findAll(TrangThaiQuanAo trangThai);

    String create(SizeAoDTO dto);

    String update(SizeAoDTO dto);
    
    SizeAoDTO findByMa(String ma);
}
