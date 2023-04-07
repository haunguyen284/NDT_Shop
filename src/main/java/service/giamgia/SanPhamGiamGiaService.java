/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.giamgia;

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

    List<SanPhamGiamGiaDTO> getAll(int currentPage);

    Optional<SanPhamGiamGiaDTO> findById(String id);

    String saveOrUpdate(String action, GiamGiaDTO giamGiaDTO, List<SanPhamDTO> listSPDTO);

    String delete(String id);

    long count();

}
