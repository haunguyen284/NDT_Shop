/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.hoadon;

import dto.hoadon.HoaDonChiTietDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaDonChiTietService {

    List<HoaDonChiTietDTO> findAll(int position, int pageSize);
    
    List<HoaDonChiTietDTO> findByHoaDon(String idHD);
    
    HoaDonChiTietDTO findById(String id);
    
    List<HoaDonChiTietDTO> findByMaHoaDon(String ma);
    
    HoaDonChiTietDTO findCreatedAt(Long createdAt);
    
    HoaDonChiTietDTO save(HoaDonChiTietDTO dTO);

    boolean delete(String id);

    long totalCount();
}
