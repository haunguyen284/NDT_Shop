/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

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

    Optional<SanPhamDTO> findById(String id);

    List<SanPhamDTO> getAll(int currentPage);

    long count();
}
