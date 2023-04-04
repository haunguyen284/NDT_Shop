/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang;

import dto.khachhang.LoaiTheDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface LoaiTheService {

    List<LoaiTheDTO> findAll(int position, int pageSize);
    
    List<LoaiTheDTO> findAll();

    LoaiTheDTO findById(String id);
    
    LoaiTheDTO findByTen(String ten);

    String save(LoaiTheDTO dTO);

    boolean delete(String id);

    long totalCount();
}
