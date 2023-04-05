/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.nhanvien;

import dto.nhanvien.TaiKhoanDTO;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface TaiKhoanService {
    List<TaiKhoanDTO> findAll(int position);

    List<TaiKhoanDTO> findAll();

    TaiKhoanDTO findById(String id);
    
    TaiKhoanDTO login(String user, String pass);

    String create(TaiKhoanDTO dto);

    String update(TaiKhoanDTO dto);

    boolean delete(String id);

    long totalCount();
}
