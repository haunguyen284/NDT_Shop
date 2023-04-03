/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang;

import dto.khachhang.ViDiemDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ViDiemService {

    List<ViDiemDTO> findAll(int position, int pageSize);
    
    List<ViDiemDTO> findAll();

    ViDiemDTO findById(String id);
    
    ViDiemDTO findByTen(String ten);

    String create(ViDiemDTO dTO);

    String update(ViDiemDTO dTO);

    boolean delete(String id);

    long totalCount();
}
