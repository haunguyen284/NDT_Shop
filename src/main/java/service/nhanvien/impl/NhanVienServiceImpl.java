/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.nhanvien.impl;

import comon.utilities.Mapper;
import dto.nhanvien.NhanVienDTO;
import java.util.ArrayList;
import java.util.List;
import model.nhanvien.NhanVien;
import repository.nhanvien.NhanVienRepository;
import service.nhanvien.NhanVienService;

/**
 *
 * @author ADMIN KH
 */
public class NhanVienServiceImpl implements NhanVienService {

    private final NhanVienRepository repository;

    public NhanVienServiceImpl() {
        this.repository = new NhanVienRepository();
    }

    @Override
    public List<NhanVienDTO> findAll(int position) {
        List<NhanVienDTO> listDTO = new ArrayList<>();
        List<NhanVien> listModel = repository.findAll(position);
        for (NhanVien model : listModel) {
            listDTO.add(Mapper.modelMapper().map(model, NhanVienDTO.class));
        }
        return listDTO;
    }

    

    @Override
    public NhanVienDTO findById(String id) {
        NhanVien model = repository.findById(id);
        return Mapper.modelMapper().map(model, NhanVienDTO.class);
    }

    @Override
    public String create(NhanVienDTO dto) {
        
        NhanVien model = Mapper.modelMapper().map(dto, NhanVien.class);
        String result;
        if (repository.save(model) != null) {
            result = "Thêm thành công";
        } else {
            result = "Thêm thất bại";
        }
        return result;
    }

    @Override
    public String update(NhanVienDTO dto) {
         NhanVien model = Mapper.modelMapper().map(dto, NhanVien.class);
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
    public List<NhanVienDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String findId(String maNV) {
        return repository.findId(maNV);
    }

    

}
