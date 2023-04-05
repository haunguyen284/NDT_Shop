/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang;

import dto.khachhang.QuyDoiDiemDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface QuyDoiDiemService {

    List<QuyDoiDiemDTO> findAll(int position, int pageSize);
    
    List<QuyDoiDiemDTO> findAll();

    QuyDoiDiemDTO findById(String id);
    
    QuyDoiDiemDTO findByTen(String ten);

    String save(QuyDoiDiemDTO dTO);

    boolean delete(String id);

    long totalCount();
}
