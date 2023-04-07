/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiThuongHieu;
import dto.sanpham.ThuongHieuDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface ThuongHieuService {

    List<ThuongHieuDTO> findAll(TrangThaiThuongHieu trangThai);

    String create(ThuongHieuDTO dto);

    String update(ThuongHieuDTO dto);
}
