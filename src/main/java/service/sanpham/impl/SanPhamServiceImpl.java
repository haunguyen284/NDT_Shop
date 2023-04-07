/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.utilities.Mapper;
import dto.sanpham.SanPhamDTO;
import model.sanpham.SanPham;
import repository.sanpham.SanPhamRepository;
import service.sanpham.SanPhamService;

/**
 *
 * @author nguyenth28
 */
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository repository;

    public SanPhamServiceImpl() {
        this.repository = new SanPhamRepository();
    }

    @Override
    public SanPhamDTO create(SanPhamDTO dto) {
        dto.setId(null);
        SanPham model = Mapper.modelMapper().map(dto, SanPham.class);
        if (repository.findByMa(dto.getMaSP()) != null) {
            return null;
        }
        SanPham newModel = repository.save(model);
        if (model != null){
            return Mapper.modelMapper().map(newModel, SanPhamDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public SanPhamDTO update(SanPhamDTO dto) {
        SanPham model = Mapper.modelMapper().map(dto, SanPham.class);
        SanPham newModel = repository.save(model);
        if (model != null){
            return Mapper.modelMapper().map(newModel, SanPhamDTO.class);
        } else {
            return null;
        }
    }

}
