/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.giamgia;

import dto.giamgia.GiamGiaDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN KH
 */
public interface GiamGiaService {

    List<GiamGiaDTO> getAll(int currentPage);

    Optional<GiamGiaDTO> findById(String id);

    String saveOrUpdate(String action, GiamGiaDTO x);

    String delete(String id);

    long count();

}
