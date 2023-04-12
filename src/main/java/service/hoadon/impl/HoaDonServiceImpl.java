/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.HoaDon.impl;

import comon.constant.TinhTrangHoaDon;
import comon.utilities.Mapper;
import comon.validator.NDTValidator;
import dto.hoadon.HoaDonDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public List<HoaDonDTO> findByTinhTrang(TinhTrangHoaDon tinhTrangHoaDon) {
        List<HoaDonDTO> listDTO = new ArrayList<>();
        List<HoaDon> listModel = HoaDonRepository.findByTinhTrang(tinhTrangHoaDon);
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
    public HoaDonDTO save(HoaDonDTO dTO) {
        HoaDon model = mapper.map(dTO, HoaDon.class);
        model = HoaDonRepository.save(model);
        HoaDonDTO hoaDonDTO = mapper.map(model, HoaDonDTO.class);
        if(!Objects.isNull(hoaDonDTO)){
            return hoaDonDTO;
        }else{
            return null;
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
    
    @Override
    public List<HoaDonDTO> getAllLichSuHoaDon(int currentPage) {
        List<HoaDon> listModel = HoaDonRepository.getAllLichSuHoaDon(currentPage);
        List<HoaDonDTO> listDTO = new ArrayList<>();
        for (HoaDon x : listModel) {
            listDTO.add(mapper.map(x, HoaDonDTO.class));
        }
        return listDTO;
    }

    @Override
    public List<HoaDonDTO> searchByMa(int currentPage, String searchByMa) {
        List<HoaDon> listModel = HoaDonRepository.searchByMaHoaDon(currentPage, searchByMa);
        List<HoaDonDTO> listDTO = new ArrayList<>();
        for (HoaDon x : listModel) {
            listDTO.add(mapper.map(x, HoaDonDTO.class));
        }
        return listDTO;
    }
}
