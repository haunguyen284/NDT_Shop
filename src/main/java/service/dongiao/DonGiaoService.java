/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.dongiao;

import dto.dongiao.DonGiaoDTO;
import java.util.List;
import java.util.Optional;
import model.dongiao.DonGiao;

/**
 *
 * @author ADMIN KH
 */
public interface DonGiaoService {

    DonGiaoDTO findById(String id);

    String findId(String maDG);

    DonGiaoDTO save(DonGiaoDTO dTO);
    
    DonGiaoDTO findByHoaDon(String idDG);
    
    List<DonGiaoDTO> getAll(int currentPage);

    String saveOrUpdate(String action, DonGiaoDTO x);

    List<DonGiaoDTO> searchByMa(int currentPage, String searchByMa);

    Optional<DonGiaoDTO> findByIdDuy(String id);

    long count();
}
