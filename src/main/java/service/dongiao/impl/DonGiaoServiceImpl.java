/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.dongiao.impl;

import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.utilities.Mapper;
import dto.dongiao.DonGiaoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import model.dongiao.DonGiao;
import org.modelmapper.ModelMapper;
import repository.dongiao.DonGiaoRepository;
import service.dongiao.DonGiaoService;

/**
 *
 * @author ADMIN KH
 */
public class DonGiaoServiceImpl implements DonGiaoService {

    private final DonGiaoRepository repository;
    private final ModelMapper mapper;

    public DonGiaoServiceImpl() {
        this.repository = new DonGiaoRepository();
        this.mapper = Mapper.modelMapper();
    }

    @Override
    public List<DonGiaoDTO> getAll(int currentPage, String maDG, YeuCauDonHang yeuCauDonHang, TrangThaiDonGiao trangThaiDonGiao) {
        List<DonGiao> listModel = repository.getAll(currentPage, maDG, trangThaiDonGiao, yeuCauDonHang);
        List<DonGiaoDTO> listDTO = new ArrayList<>();
        for (DonGiao x : listModel) {
            listDTO.add(mapper.map(x, DonGiaoDTO.class));
        }
        return listDTO;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public String saveOrUpdate(String action, DonGiaoDTO x) {
        String result;
        DonGiao model = mapper.map(x, DonGiao.class);
        Optional<DonGiao> optional = repository.finByID(model.getId());
        switch (action) {
            case "add" -> {
                model.setId(null);
                if (optional.isPresent()) {
                    return "Giam giá này đã tồn tại !";
                }
                if (repository.saveOrUpdate(model)) {
                    result = "Add successful !";
                } else {
                    result = "Add fail !";
                }
            }
            case "update" -> {
                if (optional.isEmpty()) {
                    return "Giam giá này không tồn tại !";
                }
                if (repository.saveOrUpdate(model)) {
                    result = "Update successful !";
                } else {
                    result = "Update fail !";
                }
            }
            default ->
                throw new AssertionError();
        }
        return result;
    }

    @Override
    public Optional<DonGiaoDTO> findByIdDuy(String id) {
        Optional<DonGiao> optional = repository.finByID(id);
        DonGiao x = null;
        if (optional.isPresent()) {
            x = optional.get();
        } else {
            throw new RuntimeException(" Not found id !");
        }
        return Optional.ofNullable(mapper.map(x, DonGiaoDTO.class));
    }
    
    @Override
    public String findId(String maDG) {
        return repository.findId(maDG);
    }

    @Override
    public DonGiaoDTO save(DonGiaoDTO dTO) {
        DonGiao model = mapper.map(dTO, DonGiao.class);
        model = repository.save(model);
        DonGiaoDTO DonGiaoDTO = mapper.map(model, DonGiaoDTO.class);
        if (!Objects.isNull(DonGiaoDTO)) {
            return dTO;
        } else {
            return null;
        }
    }

    @Override
    public DonGiaoDTO findByHoaDon(String idDG) {
        DonGiao model = repository.findByHoaDon(idDG);
        return mapper.map(model, DonGiaoDTO.class);
    }
    
    @Override
    public DonGiaoDTO findById(String id) {
        DonGiao model = repository.findById(id);
        return mapper.map(model, DonGiaoDTO.class);
    }
}
