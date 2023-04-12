/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.hoadon.impl;

import comon.utilities.Mapper;
import comon.validator.NDTValidator;
import dto.hoadon.HoaDonChiTietDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import model.hoadon.HoaDon;
import model.hoadon.HoaDonChiTiet;
import org.modelmapper.ModelMapper;
import repository.hoadon.HoaDonChiTietRepository;
import service.hoadon.HoaDonChiTietService;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    private final HoaDonChiTietRepository HoaDonChiTietRepository;
    private final ModelMapper mapper;

    public HoaDonChiTietServiceImpl() {
        this.HoaDonChiTietRepository = new HoaDonChiTietRepository();
        this.mapper = Mapper.modelMapper();
    }

    @Override
    public List<HoaDonChiTietDTO> findAll(int position, int pageSize) {
        List<HoaDonChiTietDTO> listDTO = new ArrayList<>();
        List<HoaDonChiTiet> listModel = HoaDonChiTietRepository.findAll(position, pageSize);
        for (HoaDonChiTiet HoaDon : listModel) {
            listDTO.add(mapper.map(HoaDon, HoaDonChiTietDTO.class));
        }
        return listDTO;
    }

    @Override
    public HoaDonChiTietDTO findById(String id) {
        HoaDonChiTiet model = HoaDonChiTietRepository.findById(id);
        return mapper.map(model, HoaDonChiTietDTO.class);
    }
    @Override
    public List<HoaDonChiTietDTO> findByMaHoaDon(String ma) {
        List<HoaDonChiTietDTO> listDTO = new ArrayList<>();
        List<HoaDonChiTiet> listModel = HoaDonChiTietRepository.findByMaHoaDon(ma);
        for (HoaDonChiTiet HoaDon : listModel) {
            listDTO.add(mapper.map(HoaDon, HoaDonChiTietDTO.class));
        }
        return listDTO;
    }

    @Override
    public HoaDonChiTietDTO save(HoaDonChiTietDTO dTO) {
        HoaDonChiTiet model = mapper.map(dTO, HoaDonChiTiet.class);
        model = HoaDonChiTietRepository.save(model);
        HoaDonChiTietDTO hoaDonChiTietDTO = mapper.map(model, HoaDonChiTietDTO.class);
        if (!Objects.isNull(hoaDonChiTietDTO)) {
            return hoaDonChiTietDTO;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        return HoaDonChiTietRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return HoaDonChiTietRepository.totalCount();
    }

    @Override
    public HoaDonChiTietDTO findCreatedAt(Long createdAt) {
        HoaDonChiTiet model = HoaDonChiTietRepository.findByCreatedAt(createdAt);
        return mapper.map(model, HoaDonChiTietDTO.class);
    }

    @Override
    public List<HoaDonChiTietDTO> findByHoaDon(String idHD) {
        List<HoaDonChiTietDTO> listDTO = new ArrayList<>();
        List<HoaDonChiTiet> listModel = HoaDonChiTietRepository.findByHoaDon(idHD);
        for (HoaDonChiTiet HoaDon : listModel) {
            listDTO.add(mapper.map(HoaDon, HoaDonChiTietDTO.class));
        }
        return listDTO;
    }
    
}
