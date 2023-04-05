/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.sanpham;

import comon.constant.sanpham.TrangThaiChatLieu;
import dto.sanpham.ChatLieuDTO;
import java.util.List;

/**
 *
 * @author nguyenth28
 */
public interface ChatLieuService {

    List<ChatLieuDTO> findAll(TrangThaiChatLieu trangThai);

    String create(ChatLieuDTO dto);

    String update(ChatLieuDTO dto);
}
