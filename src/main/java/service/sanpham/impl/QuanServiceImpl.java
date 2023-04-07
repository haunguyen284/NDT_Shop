/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.utilities.Mapper;
import dto.sanpham.QuanDTO;
import dto.sanpham.SearchQuanDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.Quan;
import repository.sanpham.QuanRepository;
import service.sanpham.QuanService;

/**
 *
 * @author nguyenth28
 */
public class QuanServiceImpl implements QuanService{

    private final QuanRepository repository;

    public QuanServiceImpl() {
        this.repository = new QuanRepository();
    }

    @Override
    public List<QuanDTO> findAll(int position, SearchQuanDTO searchDTO) {
        List<QuanDTO> listDTO = new ArrayList<>();
        List<Quan> listModel = repository.findAll(position, searchDTO);
        for (Quan m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, QuanDTO.class));
        }
        return listDTO;
    }

    @Override
    public QuanDTO findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public QuanDTO findByMa(String ma) {
        return Mapper.modelMapper().map(repository.findByMa(ma), QuanDTO.class);
    }

    @Override
    public String create(QuanDTO dto) {
        dto.setId(null);
        Quan model = Mapper.modelMapper().map(dto, Quan.class);
        if (repository.findByMa(dto.getSanPham().getMaSP()) != null) {
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
    public String update(QuanDTO dto) {
        String result;
        Quan model = Mapper.modelMapper().map(dto, Quan.class);
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
