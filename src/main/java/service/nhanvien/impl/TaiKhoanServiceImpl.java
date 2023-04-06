/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.nhanvien.impl;

import comon.utilities.Mapper;
import dto.nhanvien.TaiKhoanDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.nhanvien.TaiKhoan;
import repository.nhanvien.TaiKhoanRepositoy;
import service.nhanvien.TaiKhoanService;

/**
 *
 * @author Dell
 */
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private final TaiKhoanRepositoy repository;

    public TaiKhoanServiceImpl() {
        this.repository = new TaiKhoanRepositoy();
    }

    @Override
    public List<TaiKhoanDTO> findAll(int position) {
        List<TaiKhoanDTO> listDTO = new ArrayList<>();
        List<TaiKhoan> listModel = repository.findAll(position);
        for (TaiKhoan model : listModel) {
            listDTO.add(Mapper.modelMapper().map(model, TaiKhoanDTO.class));
        }
        return listDTO;
    }

    @Override
    public List<TaiKhoanDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TaiKhoanDTO findById(String id) {
        TaiKhoan model = repository.findById(id);
        return Mapper.modelMapper().map(model, TaiKhoanDTO.class);
    }

    @Override
    public String create(TaiKhoanDTO dto) {
        dto.setId(null);
        TaiKhoan model = Mapper.modelMapper().map(dto, TaiKhoan.class);
        String result;
        if (repository.save(model) != null) {
            result = "Thêm thành công";
        } else {
            result = "Thêm thất bại";
        }
        return result;
    }

    @Override
    public String update(TaiKhoanDTO dto) {
        TaiKhoan model = Mapper.modelMapper().map(dto, TaiKhoan.class);
        String result;
        if (repository.save(model) != null) {
            result = "Sửa thành công";
        } else {
            result = "Sửa thất bại";
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public long totalCount() {
        return repository.totalCount();
    }

    @Override
    public TaiKhoanDTO login(String user, String pass) {
        TaiKhoan model = repository.login(user, pass);
        return Mapper.modelMapper().map(model, TaiKhoanDTO.class);
    }

    @Override
    public TaiKhoanDTO findByIdNhanVien(String idNV) {
        TaiKhoan model = repository.findByIdNhanVien(idNV);
        if(Objects.isNull(model)){
            return null;
        }
        return Mapper.modelMapper().map(model, TaiKhoanDTO.class);
    }

}
