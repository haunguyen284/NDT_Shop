/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiMauSac;
import comon.utilities.Mapper;
import dto.sanpham.MauSacDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.MauSac;
import repository.sanpham.MauSacRepository;
import service.sanpham.MauSacService;

/**
 *
 * @author nguyenth28
 */
public class MauSacServiceImpl implements MauSacService {

    private final MauSacRepository repository;

    public MauSacServiceImpl() {
        this.repository = new MauSacRepository();
    }

    @Override
    public List<MauSacDTO> findAll(TrangThaiMauSac trangThai) {
        List<MauSacDTO> listDTO = new ArrayList<>();
        List<MauSac> listModel = repository.findAll(trangThai);
        for (MauSac m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, MauSacDTO.class));
        }
        return listDTO;
    }

    @Override
    public String create(MauSacDTO dto) {
        dto.setId(null);
        MauSac model = Mapper.modelMapper().map(dto, MauSac.class);
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
    public String update(MauSacDTO dto) {
        MauSac model = Mapper.modelMapper().map(dto, MauSac.class);
        String result;
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

}
