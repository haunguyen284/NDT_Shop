/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import dto.sanpham.AoDTO;
import dto.sanpham.QuanDTO;
import dto.sanpham.SearchAoDTO;
import dto.sanpham.SearchQuanDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface QuanService {
    List<QuanDTO> findAll(int position, SearchQuanDTO searchDTO);
    QuanDTO findById(String id);
    QuanDTO findByMa(String ma);
    String create(QuanDTO dto);
    String update(QuanDTO dto);
    long totalCount();
}
