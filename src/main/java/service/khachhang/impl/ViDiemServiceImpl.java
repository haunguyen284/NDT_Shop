/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang.impl;

import comon.utilities.Mapper;
import dto.khachhang.ViDiemDTO;
import java.util.ArrayList;
import java.util.List;
import model.khachhang.ViDiem;
import org.modelmapper.ModelMapper;
import repository.khachhang.ViDiemRepository;
import service.khachhang.ViDiemService;


/**
 *
 * @author Admin
 */
public class ViDiemServiceImpl implements ViDiemService{

    private final ViDiemRepository ViDiemRepository;
    private final ModelMapper mapper;

    public ViDiemServiceImpl() {
        this.ViDiemRepository = new ViDiemRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<ViDiemDTO> findAll(int position, int pageSize) {
        List<ViDiemDTO> listDTO = new ArrayList<>();
        List<ViDiem> listModel = ViDiemRepository.findAll(position, pageSize);
        for (ViDiem ViDiem : listModel) {
            listDTO.add(mapper.map(ViDiem, ViDiemDTO.class));
        }
        return listDTO;
    }

    @Override
    public ViDiemDTO findById(String id) {
        ViDiem model = ViDiemRepository.findById(id);
        return mapper.map(model, ViDiemDTO.class);
    }

    @Override
    public ViDiemDTO save(ViDiemDTO dTO) {
        ViDiem model = mapper.map(dTO, ViDiem.class);
        return mapper.map(ViDiemRepository.save(model), ViDiemDTO.class);
    }

    @Override
    public boolean delete(String id) {
        return ViDiemRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return ViDiemRepository.totalCount();
    }

    @Override
    public List<ViDiemDTO> findAll() {
        List<ViDiemDTO> listDTO = new ArrayList<>();
        List<ViDiem> listModel = ViDiemRepository.findAll();
        for (ViDiem ViDiem : listModel) {
            listDTO.add(mapper.map(ViDiem, ViDiemDTO.class));
        }
        return listDTO;
    }

    @Override
    public ViDiemDTO findByTen(String ten) {
        ViDiem model = ViDiemRepository.findByTen(ten);
        return mapper.map(model, ViDiemDTO.class);
    }
    
    
    
}
