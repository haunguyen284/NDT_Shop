/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiThuongHieu;
import comon.utilities.Mapper;
import dto.sanpham.ThuongHieuDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.ThuongHieu;
import repository.sanpham.ThuongHieuRepository;
import service.sanpham.ThuongHieuService;

/**
 *
 * @author nguyenth28
 */
public class ThuongHieuServiceImpl implements ThuongHieuService {

    private final ThuongHieuRepository repository;

    public ThuongHieuServiceImpl() {
        this.repository = new ThuongHieuRepository();
    }

    @Override
    public List<ThuongHieuDTO> findAll(TrangThaiThuongHieu trangThai) {
        List<ThuongHieuDTO> listDTO = new ArrayList<>();
        List<ThuongHieu> listModel = repository.findAll(trangThai);
        for (ThuongHieu m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, ThuongHieuDTO.class));
        }
        return listDTO;
    }

    @Override
    public String create(ThuongHieuDTO dto) {
        dto.setId(null);
        ThuongHieu model = Mapper.modelMapper().map(dto, ThuongHieu.class);
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
    public String update(ThuongHieuDTO dto) {
        ThuongHieu model = Mapper.modelMapper().map(dto, ThuongHieu.class);
        String result;
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

}
