/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang;

import dto.khachhang.LichSuTieuDiemDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface LichSuTieuDiemService {

    List<LichSuTieuDiemDTO> findAll(int position, int pageSize);
    
    List<LichSuTieuDiemDTO> findAll();
    
    List<LichSuTieuDiemDTO> findAllByViDiem(String viDiemId);

    LichSuTieuDiemDTO findById(String id);
    
    LichSuTieuDiemDTO findByTen(String ten);

    String save(LichSuTieuDiemDTO dTO);

    boolean delete(String id);

    long totalCount();
}
