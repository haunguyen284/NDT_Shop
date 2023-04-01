/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang;

import dto.khachhang.KhachHangDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface KhachHangService {

    List<KhachHangDTO> findAll(int position, int pageSize);
    
    List<KhachHangDTO> findByName(String ten, int position, int pageSize);

    KhachHangDTO findById(String id);

    String create(KhachHangDTO dTO);

    String update(KhachHangDTO dTO);

    boolean delete(String id);

    long totalCount();
}
