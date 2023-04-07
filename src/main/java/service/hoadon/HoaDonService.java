/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.hoaDon;

import dto.hoadon.HoaDonDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaDonService {

    List<HoaDonDTO> findAll(int position, int pageSize);
    
    HoaDonDTO findById(String id);
    
    String findId(String maKH);

    String save(HoaDonDTO dTO);

    boolean delete(String id);

    long totalCount();
}
