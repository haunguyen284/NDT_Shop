/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.dongiao.impl;

import comon.utilities.Mapper;
import dto.dongiao.ThongSoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.dongiao.ThongSo;
import org.modelmapper.ModelMapper;
import repository.dongiao.ThongSoRepository;
import service.dongiao.ThongSoService;

/**
 *
 * @author ADMIN KH
 */
public class ThongSoServiceImpl implements ThongSoService{
    
    private final ThongSoRepository ThongSoRepository;
    private final ModelMapper mapper;
    
    public ThongSoServiceImpl() {
        this.ThongSoRepository = new ThongSoRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    @Override
    public ThongSoDTO findById(String id) {
        ThongSo model = ThongSoRepository.findById(id);
        return mapper.map(model, ThongSoDTO.class);
    }

    @Override
    public ThongSoDTO save(ThongSoDTO dTO) {
        ThongSo model = mapper.map(dTO, ThongSo.class);
        model = ThongSoRepository.save(model);
        ThongSoDTO ThongSoDTO = mapper.map(model, ThongSoDTO.class);
        if(!Objects.isNull(ThongSoDTO)){
            return dTO;
        }else{
            return null;
        }
    }
    
    @Override
    public ThongSoDTO findByDonGiao(String idDG) {
        ThongSo model = ThongSoRepository.findByDonGiao(idDG);
        return mapper.map(model, ThongSoDTO.class);
    }

}
