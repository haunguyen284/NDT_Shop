/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiQuanAo;
import comon.utilities.Mapper;
import dto.sanpham.SizeQuanDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.SizeQuan;
import repository.sanpham.SizeQuanRepository;
import service.sanpham.SizeQuanService;

/**
 *
 * @author nguyenth28
 */
public class SizeQuanServiceImpl implements SizeQuanService {

    private final SizeQuanRepository repository;

    public SizeQuanServiceImpl() {
        this.repository = new SizeQuanRepository();
    }

    @Override
    public List<SizeQuanDTO> findAll(TrangThaiQuanAo trangThai) {
        List<SizeQuanDTO> listDTO = new ArrayList<>();
        List<SizeQuan> listModel = repository.findAll(trangThai);
        for (SizeQuan m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, SizeQuanDTO.class));
        }
        return listDTO;
    }

    @Override
    public String create(SizeQuanDTO dto) {
        dto.setId(null);
        SizeQuan model = Mapper.modelMapper().map(dto, SizeQuan.class);
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
    public String update(SizeQuanDTO dto) {
        SizeQuan model = Mapper.modelMapper().map(dto, SizeQuan.class);
        String result;
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

    @Override
    public SizeQuanDTO findByMa(String ma) {
        SizeQuan model = repository.findByMa(ma);
        return Mapper.modelMapper().map(model, SizeQuanDTO.class);
    }

}
