/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.hoaDon;

import comon.constant.TinhTrangHoaDon;
import dto.hoadon.HoaDonDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaDonService {

    List<HoaDonDTO> findByTinhTrang(TinhTrangHoaDon tinhTrangHoaDon);
    
    HoaDonDTO findById(String id);
    
    String findId(String maKH);

    HoaDonDTO save(HoaDonDTO dTO);

    boolean delete(String id);

    long totalCount();
    
    List<HoaDonDTO> getAllLichSuHoaDon(int currentPage);

    List<HoaDonDTO> searchByMa(int currentPage, String searchByMa);
}
