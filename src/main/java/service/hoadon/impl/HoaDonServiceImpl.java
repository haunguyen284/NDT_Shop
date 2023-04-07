/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.HoaDon.impl;

import comon.utilities.Mapper;
import comon.validator.NDTValidator;
import dto.hoadon.HoaDonDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import model.hoadon.HoaDon;
import org.modelmapper.ModelMapper;
import repository.hoadon.HoaDonRepository;
import service.hoaDon.HoaDonService;


/**
 *
 * @author Admin
 */
public class HoaDonServiceImpl implements HoaDonService{

    private final HoaDonRepository HoaDonRepository;
    private final ModelMapper mapper;

    public HoaDonServiceImpl() {
        this.HoaDonRepository = new HoaDonRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<HoaDonDTO> findAll(int position, int pageSize) {
        List<HoaDonDTO> listDTO = new ArrayList<>();
        List<HoaDon> listModel = HoaDonRepository.findAll(position, pageSize);
        for (HoaDon HoaDon : listModel) {
            listDTO.add(mapper.map(HoaDon, HoaDonDTO.class));
        }
        return listDTO;
    }

    @Override
    public HoaDonDTO findById(String id) {
        HoaDon model = HoaDonRepository.findById(id);
        return mapper.map(model, HoaDonDTO.class);
    }

    @Override
    public String save(HoaDonDTO dTO) {
        HoaDon model = mapper.map(dTO, HoaDon.class);
        if(HoaDonRepository.save(model)!=null){
            return "Cập nhật thành công";
        }else{
            return "Cập nhật thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
        return HoaDonRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return HoaDonRepository.totalCount();
    }

    @Override
    public String findId(String maKH) {
        return HoaDonRepository.findId(maKH);
    }

}
