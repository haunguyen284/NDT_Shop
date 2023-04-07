/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiQuanAo;
import dto.sanpham.SizeAoDTO;
import dto.sanpham.SizeQuanDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface SizeQuanService {

    List<SizeQuanDTO> findAll(TrangThaiQuanAo trangThai);

    String create(SizeQuanDTO dto);

    String update(SizeQuanDTO dto);
    
    SizeQuanDTO findByMa(String ma);
}
