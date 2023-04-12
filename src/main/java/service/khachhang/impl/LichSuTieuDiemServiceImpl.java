/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang.impl;

import comon.utilities.Mapper;
import dto.khachhang.LichSuTieuDiemDTO;
import java.util.ArrayList;
import java.util.List;
import model.khachhang.LichSuTieuDiem;
import org.modelmapper.ModelMapper;
import repository.khachhang.LichSuTieuDiemRepository;
import service.khachhang.LichSuTieuDiemService;


/**
 *
 * @author Admin
 */
public class LichSuTieuDiemServiceImpl implements LichSuTieuDiemService{

    private final LichSuTieuDiemRepository LichSuTieuDiemRepository;
    private final ModelMapper mapper;

    public LichSuTieuDiemServiceImpl() {
        this.LichSuTieuDiemRepository = new LichSuTieuDiemRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<LichSuTieuDiemDTO> findAll(int position, int pageSize) {
        List<LichSuTieuDiemDTO> listDTO = new ArrayList<>();
        List<LichSuTieuDiem> listModel = LichSuTieuDiemRepository.findAll(position, pageSize);
        for (LichSuTieuDiem LichSuTieuDiem : listModel) {
            listDTO.add(mapper.map(LichSuTieuDiem, LichSuTieuDiemDTO.class));
        }
        return listDTO;
    }
    @Override
    public List<LichSuTieuDiemDTO> findAllByViDiem(String idViDiem) {
        List<LichSuTieuDiemDTO> listDTO = new ArrayList<>();
        List<LichSuTieuDiem> listModel = LichSuTieuDiemRepository.findAllByViDiem(idViDiem);
        for (LichSuTieuDiem LichSuTieuDiem : listModel) {
            listDTO.add(mapper.map(LichSuTieuDiem, LichSuTieuDiemDTO.class));
        }
        return listDTO;
    }

    @Override
    public LichSuTieuDiemDTO findById(String id) {
        LichSuTieuDiem model = LichSuTieuDiemRepository.findById(id);
        return mapper.map(model, LichSuTieuDiemDTO.class);
    }

    @Override
    public String save(LichSuTieuDiemDTO dTO) {
        dTO.setId(null);
        LichSuTieuDiem model = mapper.map(dTO, LichSuTieuDiem.class);
        if(LichSuTieuDiemRepository.save(model)!=null){
            return "Thêm thành công";
        }else{
            return "Thêm thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
        return LichSuTieuDiemRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return LichSuTieuDiemRepository.totalCount();
    }

    @Override
    public List<LichSuTieuDiemDTO> findAll() {
        List<LichSuTieuDiemDTO> listDTO = new ArrayList<>();
        List<LichSuTieuDiem> listModel = LichSuTieuDiemRepository.findAll();
        for (LichSuTieuDiem LichSuTieuDiem : listModel) {
            listDTO.add(mapper.map(LichSuTieuDiem, LichSuTieuDiemDTO.class));
        }
        return listDTO;
    }

    @Override
    public LichSuTieuDiemDTO findByTen(String ten) {
        LichSuTieuDiem model = LichSuTieuDiemRepository.findByTen(ten);
        return mapper.map(model, LichSuTieuDiemDTO.class);
    }
    
    
    
}
