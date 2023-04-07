/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import dto.sanpham.AoDTO;
import dto.sanpham.SearchAoDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface AoService {
    List<AoDTO> findAll(int position, SearchAoDTO searchDTO);
    AoDTO findById(String id);
    AoDTO findByMa(String ma);
    String create(AoDTO dto);
    String update(AoDTO dto);
    long totalCount();
}
