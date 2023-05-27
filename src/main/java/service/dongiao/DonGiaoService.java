/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.dongiao;

import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import dto.dongiao.DonGiaoDTO;
import dto.giamgia.GiamGiaDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN KH
 */
public interface DonGiaoService {

    List<DonGiaoDTO> getAll(int currentPage,String maDG,YeuCauDonHang yeuCauDonHang ,TrangThaiDonGiao trangThaiDonGiao);
    
    DonGiaoDTO findById(String id);

    String saveOrUpdate(String action, DonGiaoDTO x);

    Optional<DonGiaoDTO> findByIdDuy(String id);

    long count();
    
    String findId(String maDG);

    DonGiaoDTO save(DonGiaoDTO dTO);
    
    DonGiaoDTO findByHoaDon(String idDG);
}
