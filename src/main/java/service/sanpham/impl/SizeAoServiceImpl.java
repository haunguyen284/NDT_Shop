/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiQuanAo;
import comon.utilities.Mapper;
import dto.sanpham.MauSacDTO;
import dto.sanpham.SizeAoDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.MauSac;
import model.sanpham.SizeAo;
import repository.sanpham.SizeAoRepository;
import service.sanpham.SizeAoService;

/**
 *
 * @author nguyenth28
 */
public class SizeAoServiceImpl implements SizeAoService {

    private final SizeAoRepository repository;

    public SizeAoServiceImpl() {
        this.repository = new SizeAoRepository();
    }

    @Override
    public List<SizeAoDTO> findAll(TrangThaiQuanAo trangThai) {
        List<SizeAoDTO> listDTO = new ArrayList<>();
        List<SizeAo> listModel = repository.findAll(trangThai);
        for (SizeAo m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, SizeAoDTO.class));
        }
        return listDTO;
    }

    @Override
    public String create(SizeAoDTO dto) {
        dto.setId(null);
        SizeAo model = Mapper.modelMapper().map(dto, SizeAo.class);
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
    public String update(SizeAoDTO dto) {
        SizeAo model = Mapper.modelMapper().map(dto, SizeAo.class);
        String result;
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

}
