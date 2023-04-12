/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.dongiao;

import dto.dongiao.ThongSoDTO;
import java.util.List;

/**
 *
 * @author ADMIN KH
 */
public interface ThongSoService {

    ThongSoDTO findById(String id);

    ThongSoDTO save(ThongSoDTO dTO);
    
    ThongSoDTO findByDonGiao(String idDG);
}
