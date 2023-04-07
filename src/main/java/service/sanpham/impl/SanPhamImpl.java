/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.utilities.Mapper;
import dto.sanpham.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.sanpham.SanPham;
import org.modelmapper.ModelMapper;
import repository.sanpham.SanPhamRepository;
import service.sanpham.SanPhamService;

/**
 *
 * @author ADMIN KH
 */
public class SanPhamImpl implements SanPhamService {

    private final SanPhamRepository repository;
    private final ModelMapper mapper;

    public SanPhamImpl() {
        this.repository = new SanPhamRepository();
        this.mapper = Mapper.modelMapper();
    }

    @Override
    public List<SanPhamDTO> getAll(int currentPage) {
        List<SanPham> listModel = repository.getAll(currentPage);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }

    @Override
    public Optional<SanPhamDTO> findById(String id) {
        Optional<SanPham> optional = repository.finByID(id);
        SanPham x = null;
        if (optional.isPresent()) {
            x = optional.get();
        } else {
            throw new RuntimeException(" Not found id !");
        }
        return Optional.ofNullable(mapper.map(x, SanPhamDTO.class));
    }

    @Override
    public long count() {
        return repository.count();
    }
}
