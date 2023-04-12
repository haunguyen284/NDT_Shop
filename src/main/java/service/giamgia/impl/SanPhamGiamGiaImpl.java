/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.giamgia.impl;

import comon.utilities.Mapper;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import dto.giamgia.SanPhamGiamGiaDTO;
import dto.sanpham.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import model.giamgia.GiamGia;
import model.giamgia.SanPhamGiamGia;
import model.sanpham.SanPham;
import org.modelmapper.ModelMapper;
import repository.giamgia.SanPhamGiamGiaRepository;
import service.giamgia.SanPhamGiamGiaService;

/**
 *
 * @author ADMIN KH
 */
public class SanPhamGiamGiaImpl implements SanPhamGiamGiaService {

    private final SanPhamGiamGiaRepository repository;
    private final ModelMapper mapper;

    public SanPhamGiamGiaImpl() {
        this.repository = new SanPhamGiamGiaRepository();
        this.mapper = Mapper.modelMapper();
    }

    @Override
    public List<SanPhamGiamGiaDTO> getAll(int currentPage) {
        List<SanPhamGiamGia> listModel = repository.getAll(currentPage);
        List<SanPhamGiamGiaDTO> listDTO = new ArrayList<>();
        for (SanPhamGiamGia x : listModel) {
            listDTO.add(mapper.map(x, SanPhamGiamGiaDTO.class));
        }
        return listDTO;
    }

    @Override
    public Optional<SanPhamGiamGiaDTO> findById(String id) {
        Optional<SanPhamGiamGia> optional = repository.finByID(id);
        SanPhamGiamGia x = null;
        if (optional.isPresent()) {
            x = optional.get();
        } else {
            throw new RuntimeException(" Not found id !");
        }
        return Optional.ofNullable(mapper.map(x, SanPhamGiamGiaDTO.class));
    }

    @Override
    public String saveOrUpdate(String action, GiamGiaDTO giamGiaDTO, List<SanPhamDTO> listSPDTO, SanPhamGiamGiaDTO dto) {
        String result;
        List<SanPhamGiamGia> listSPGG = new ArrayList<>();
        for (SanPhamDTO sanPhamDTO : listSPDTO) {
            dto.setGiamGia(giamGiaDTO);
            dto.setSanPham(sanPhamDTO);

            listSPGG.add(mapper.map(dto, SanPhamGiamGia.class));
        }
        switch (action) {
            case "add" -> {
                if (repository.saveOrUpdate(listSPGG)) {
                    result = "Add successful !";
                } else {
                    result = "Add fail !";
                }
            }
            case "update" -> {
                if (repository.saveOrUpdate(listSPGG)) {
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
    public String delete(String id) {
        if (repository.delete(id)) {
            return "Delete successful !";
        } else {
            return "Delete Fail !";
        }
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public String deleteSanPhamByIdGiamGia(String id) {
        if (repository.deleteSanPhamByIdGiamGia(id)) {
            return "Delete successful !";
        } else {
            return "Delete Fail !";
        }
    }

    @Override
    public List<SanPhamGiamGiaDTO> searchByMa(int currentPage, String searchByMa) {
        List<SanPhamGiamGia> listModel = repository.searchByMaSpGG(currentPage, searchByMa);
        List<SanPhamGiamGiaDTO> listDTO = new ArrayList<>();
        for (SanPhamGiamGia x : listModel) {
            listDTO.add(mapper.map(x, SanPhamGiamGiaDTO.class));
        }
        return listDTO;
    }

}
