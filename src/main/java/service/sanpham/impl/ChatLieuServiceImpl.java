/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiChatLieu;
import comon.utilities.Mapper;
import dto.sanpham.ChatLieuDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.ChatLieu;
import repository.sanpham.ChatLieuRepository;
import service.sanpham.ChatLieuService;

/**
 *
 * @author nguyenth28
 */
public class ChatLieuServiceImpl implements ChatLieuService {

    private ChatLieuRepository repository;

    public ChatLieuServiceImpl() {
        this.repository = new ChatLieuRepository();
    }

    @Override
    public List<ChatLieuDTO> findAll(TrangThaiChatLieu trangThai) {
        List<ChatLieuDTO> listDTO = new ArrayList<>();
        List<ChatLieu> listModel = repository.findAll(trangThai);
        for (ChatLieu m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, ChatLieuDTO.class));
        }
        return listDTO;
    }

    @Override
    public String create(ChatLieuDTO dto) {
        dto.setId(null);
        ChatLieu model = Mapper.modelMapper().map(dto, ChatLieu.class);
        if (repository.findByMa(dto.getMa()) != null) {
            return "Mã đã tồn tại";
        }

        String result;
        if (repository.save(model) != null) {
            result = "Thêm thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

    @Override
    public String update(ChatLieuDTO dto) {
        ChatLieu model = Mapper.modelMapper().map(dto, ChatLieu.class);
        String result;
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

}
