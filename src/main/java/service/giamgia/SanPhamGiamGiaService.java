/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.giamgia;

import comon.constant.giamgia.TrangThaiGiamGia;
import comon.constant.sanpham.LoaiSanPham;
import dto.giamgia.GiamGiaDTO;
import dto.giamgia.SanPhamGiamGiaDTO;
import dto.sanpham.SanPhamDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN KH
 */
public interface SanPhamGiamGiaService {

    List<SanPhamGiamGiaDTO> getAll(int currentPage,TrangThaiGiamGia trangThaiGiamGia,LoaiSanPham loaiSanPham,String maGG);

    Optional<SanPhamGiamGiaDTO> findById(String id);

    String saveOrUpdate(String action, GiamGiaDTO giamGiaDTO, List<SanPhamDTO> listSPDTO, SanPhamGiamGiaDTO sanPhamGiamGiaDTO);

    String delete(String id);

    String deleteSanPhamByIdGiamGia(String id);

    List<SanPhamGiamGiaDTO> listSanPhamTheoMaGG(int currentPage, String searchByMa);

    long count();
    

}
