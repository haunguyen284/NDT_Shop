/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.nhanvien;

import dto.nhanvien.NhanVienDTO;
import java.util.List;

/**
 *
 * @author ADMIN KH
 */
public interface NhanVienService {

    List<NhanVienDTO> findAll(int position);

    List<NhanVienDTO> findAll();

    NhanVienDTO findById(String id);

    String create(NhanVienDTO dto);

    String update(NhanVienDTO dto);

    String findId(String maNV);

    boolean delete(String id);

    long totalCount();
}
