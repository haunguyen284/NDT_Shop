/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang.impl;

import comon.utilities.Mapper;
import dto.khachhang.LoaiTheDTO;
import java.util.ArrayList;
import java.util.List;
import model.khachhang.LoaiThe;
import org.modelmapper.ModelMapper;
import repository.khachhang.LoaiTheRepository;
import service.khachhang.LoaiTheService;


/**
 *
 * @author Admin
 */
public class LoaiTheServiceImpl implements LoaiTheService{

    private final LoaiTheRepository loaiTheRepository;
    private final ModelMapper mapper;

    public LoaiTheServiceImpl() {
        this.loaiTheRepository = new LoaiTheRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<LoaiTheDTO> findAll(int position, int pageSize) {
        List<LoaiTheDTO> listDTO = new ArrayList<>();
        List<LoaiThe> listModel = loaiTheRepository.findAll(position, pageSize);
        for (LoaiThe LoaiThe : listModel) {
            listDTO.add(mapper.map(LoaiThe, LoaiTheDTO.class));
        }
        return listDTO;
    }

    @Override
    public LoaiTheDTO findById(String id) {
        LoaiThe model = loaiTheRepository.findById(id);
        return mapper.map(model, LoaiTheDTO.class);
    }

    @Override
    public String save(LoaiTheDTO dTO) {
        dTO.setId(null);
        LoaiThe model = mapper.map(dTO, LoaiThe.class);
        if(loaiTheRepository.save(model)!=null){
            return "Thêm thành công";
        }else{
            return "Thêm thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
        return loaiTheRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return loaiTheRepository.totalCount();
    }

    @Override
    public List<LoaiTheDTO> findAll() {
        List<LoaiTheDTO> listDTO = new ArrayList<>();
        List<LoaiThe> listModel = loaiTheRepository.findAll();
        for (LoaiThe LoaiThe : listModel) {
            listDTO.add(mapper.map(LoaiThe, LoaiTheDTO.class));
        }
        return listDTO;
    }

    @Override
    public LoaiTheDTO findByTen(String ten) {
        LoaiThe model = loaiTheRepository.findByTen(ten);
        return mapper.map(model, LoaiTheDTO.class);
    }
    
    
    
}
