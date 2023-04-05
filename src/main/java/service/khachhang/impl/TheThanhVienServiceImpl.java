/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang.impl;

import comon.utilities.Mapper;
import dto.khachhang.TheThanhVienDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.khachhang.TheThanhVien;
import org.modelmapper.ModelMapper;
import repository.khachhang.TheThanhVienRepository;
import service.khachhang.TheThanhVienService;


/**
 *
 * @author Admin
 */
public class TheThanhVienServiceImpl implements TheThanhVienService{

    private final TheThanhVienRepository TheThanhVienRepository;
    private final ModelMapper mapper;

    public TheThanhVienServiceImpl() {
        this.TheThanhVienRepository = new TheThanhVienRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<TheThanhVienDTO> findAll(int position, int pageSize) {
        List<TheThanhVienDTO> listDTO = new ArrayList<>();
        List<TheThanhVien> listModel = TheThanhVienRepository.findAll(position, pageSize);
        for (TheThanhVien TheThanhVien : listModel) {
            listDTO.add(mapper.map(TheThanhVien, TheThanhVienDTO.class));
        }
        return listDTO;
    }

    @Override
    public TheThanhVienDTO findById(String id) {
        TheThanhVien model = TheThanhVienRepository.findById(id);
        return mapper.map(model, TheThanhVienDTO.class);
    }

    @Override
    public String save(TheThanhVienDTO dTO) {
        TheThanhVien model = mapper.map(dTO, TheThanhVien.class);
        if(TheThanhVienRepository.save(model)!=null){
            return "Thêm thành công";
        }else{
            return "Thêm thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
        return TheThanhVienRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return TheThanhVienRepository.totalCount();
    }

    @Override
    public TheThanhVienDTO findByNgayHetHan(Long ngayHetHan) {
        TheThanhVien model = TheThanhVienRepository.findByNgayHetHan(ngayHetHan);
        return mapper.map(model, TheThanhVienDTO.class);
    }
    
    @Override
    public String findId(String maKH) {
        return TheThanhVienRepository.findId(maKH);
    }

    @Override
    public TheThanhVienDTO findByMaTTV(String maTTV) {
        TheThanhVien model = TheThanhVienRepository.findByMaTheTV(maTTV);
        if(Objects.isNull(model)){
            return null;
        }
        return mapper.map(model, TheThanhVienDTO.class);
    }
    
}
