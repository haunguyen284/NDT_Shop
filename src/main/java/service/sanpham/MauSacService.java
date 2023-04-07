/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiMauSac;
import dto.sanpham.MauSacDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface MauSacService {
    List<MauSacDTO> findAll(TrangThaiMauSac trangThai);
    String create(MauSacDTO dto);
    String update(MauSacDTO dto);
}
