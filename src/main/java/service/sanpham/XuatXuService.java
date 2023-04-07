/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiXuatXu;
import dto.sanpham.XuatXuDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface XuatXuService {

    List<XuatXuDTO> findAll(TrangThaiXuatXu trangThai);

    String create(XuatXuDTO dto);

    String update(XuatXuDTO dto);
}
