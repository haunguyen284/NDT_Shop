/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiSanPham;
import dto.sanpham.AoDTO;
import dto.sanpham.SanPhamDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN KH
 */
public interface SanPhamService {

    SanPhamDTO create(SanPhamDTO dto);

    SanPhamDTO update(SanPhamDTO dto);

    Optional<SanPhamDTO> findByID(String id);

    SanPhamDTO findById(String id);
    
    List<SanPhamDTO> getAll(int currentPage);
    
    List<SanPhamDTO> findAll();
    
    List<SanPhamDTO> findByTrangThai(TrangThaiSanPham trangThaiSanPham);

    List<SanPhamDTO> searchByMa(int currentPage, String searchByMa);

    List<SanPhamDTO> searchByGiaBan(int currentPage, float giaBan);

    List<SanPhamDTO> listSanPhamTheoMaGG(int currentPage, String searchByMa);

    long count();
    
    Object[] getByKhuyenMai(String id);
}
