/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.utilities.Mapper;
import dto.sanpham.AoDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.Ao;
import repository.sanpham.AoRepository;
import service.sanpham.AoService;

/**
 *
 * @author nguyenth28
 */
public class AoServiceImpl implements AoService {

    private final AoRepository repository;

    public AoServiceImpl() {
        this.repository = new AoRepository();
    }

    @Override
    public List<AoDTO> findAll(int position) {
        List<AoDTO> listDTO = new ArrayList<>();
        List<Ao> listModel = repository.findAll(position);
        for (Ao m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, AoDTO.class));
        }
        return listDTO;
    }

    @Override
    public AoDTO findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AoDTO findByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String create(AoDTO dto) {
        dto.setId(null);
        Ao model = Mapper.modelMapper().map(dto, Ao.class);
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
    public String update(AoDTO dto) {
        String result;
        Ao model = Mapper.modelMapper().map(dto, Ao.class);
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

    @Override
    public long totalCount() {
        return repository.totalCount();
    }

}
