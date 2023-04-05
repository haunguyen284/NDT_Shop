/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang.impl;

import comon.utilities.Mapper;
import comon.validator.NDTValidator;
import dto.khachhang.KhachHangDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import model.khachhang.KhachHang;
import org.modelmapper.ModelMapper;
import repository.khachhang.KhachHangRepository;
import service.khachhang.KhachHangService;


/**
 *
 * @author Admin
 */
public class KhachHangServiceImpl implements KhachHangService{

    private final KhachHangRepository khachHangRepository;
    private final ModelMapper mapper;

    public KhachHangServiceImpl() {
        this.khachHangRepository = new KhachHangRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<KhachHangDTO> findAll(int position, int pageSize) {
        List<KhachHangDTO> listDTO = new ArrayList<>();
        List<KhachHang> listModel = khachHangRepository.findAll(position, pageSize);
        for (KhachHang khachHang : listModel) {
            listDTO.add(mapper.map(khachHang, KhachHangDTO.class));
        }
        return listDTO;
    }

    @Override
    public KhachHangDTO findById(String id) {
        KhachHang model = khachHangRepository.findById(id);
        return mapper.map(model, KhachHangDTO.class);
    }

    @Override
    public String save(KhachHangDTO dTO) {
        KhachHang model = mapper.map(dTO, KhachHang.class);
        if(khachHangRepository.save(model)!=null){
            return "Cập nhật thành công";
        }else{
            return "Cập nhật thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
        return khachHangRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return khachHangRepository.totalCount();
    }

    @Override
    public List<KhachHangDTO> findByName(String ten, int position, int pageSize) {
        List<KhachHangDTO> listDTO = new ArrayList<>();
        List<KhachHang> listModel = khachHangRepository.findByName(ten, position, pageSize);
        for (KhachHang khachHang : listModel) {
            listDTO.add(mapper.map(khachHang, KhachHangDTO.class));
        }
        return listDTO;
    }

    @Override
    public String findId(String maKH) {
        return khachHangRepository.findId(maKH);
    }

    @Override
    public KhachHangDTO findByIdTheThanhVien(String idTTV) {
        KhachHang model = khachHangRepository.findByIdTheThanhVien(idTTV);
        return mapper.map(model, KhachHangDTO.class);
    }
    
    
}
