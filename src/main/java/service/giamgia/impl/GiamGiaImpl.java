/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.giamgia.impl;

import comon.utilities.Mapper;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import model.giamgia.GiamGia;
import org.modelmapper.ModelMapper;
import repository.giamgia.GiamGiaRepository;
import service.giamgia.GiamGiaService;

/**
 *
 * @author ADMIN KH
 */
public class GiamGiaImpl implements GiamGiaService {

    private final GiamGiaRepository repository;
    private final ModelMapper mapper;
    private final Validator validator;

    public GiamGiaImpl() {
        this.repository = new GiamGiaRepository();
        this.mapper = Mapper.modelMapper();
        this.validator = NDTValidator.getValidator();
    }

    @Override
    public List<GiamGiaDTO> getAll(int currentPage) {
        List<GiamGia> listModel = repository.getAll(currentPage);
        List<GiamGiaDTO> listDTO = new ArrayList<>();
        for (GiamGia x : listModel) {
            listDTO.add(mapper.map(x, GiamGiaDTO.class));
        }
        return listDTO;
    }

    @Override
    public Optional<GiamGiaDTO> findById(String id) {
        Optional<GiamGia> optional = repository.finByID(id);
        GiamGia x = null;
        if (optional.isPresent()) {
            x = optional.get();
        } else {
            throw new RuntimeException(" Not found id !");
        }
        return Optional.ofNullable(mapper.map(x, GiamGiaDTO.class));
    }

    @Override
    public String saveOrUpdate(String action, GiamGiaDTO x) {
        String result;
        GiamGia model = mapper.map(x, GiamGia.class);
        Optional<GiamGia> optional = repository.finByID(model.getId());
        Set<ConstraintViolation<GiamGiaDTO>> violations = validator.validate(x);
        if (!violations.isEmpty()) {
            String errors = "";
            for (ConstraintViolation<GiamGiaDTO> x : violations) {
                errors += x.getMessage() + "\n";
            }
        }
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

}
