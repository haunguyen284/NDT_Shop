/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiSanPham;
import comon.utilities.Mapper;
import dto.sanpham.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.sanpham.SanPham;
import org.modelmapper.ModelMapper;
import repository.sanpham.SanPhamRepository;
import service.sanpham.SanPhamService;

/**
 *
 * @author nguyenth28
 */
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository repository;
    private final ModelMapper mapper;

    public SanPhamServiceImpl() {
        this.repository = new SanPhamRepository();
        this.mapper = Mapper.modelMapper();
    }

    @Override
    public SanPhamDTO create(SanPhamDTO dto) {
        dto.setId(null);
        SanPham model = Mapper.modelMapper().map(dto, SanPham.class);
        if (repository.findByMa(dto.getMaSP()) != null) {
            return null;
        }
        SanPham newModel = repository.save(model);
        if (model != null) {
            return Mapper.modelMapper().map(newModel, SanPhamDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public SanPhamDTO update(SanPhamDTO dto) {
        SanPham model = Mapper.modelMapper().map(dto, SanPham.class);
        SanPham newModel = repository.save(model);
        if (model != null) {
            return Mapper.modelMapper().map(newModel, SanPhamDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public Optional<SanPhamDTO> findByID(String id) {
        Optional<SanPham> optional = repository.finByID(id);
        SanPham x = null;
        if (optional.isPresent()) {
            x = optional.get();
        } else {
            throw new RuntimeException(" Not found id !");
        }
        return Optional.ofNullable(mapper.map(x, SanPhamDTO.class));
    }

    @Override
    public List<SanPhamDTO> getAll(int currentPage) {
        List<SanPham> listModel = repository.getAll(currentPage);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }
    
    @Override
    public List<SanPhamDTO> findAll(String ma) {
        List<SanPham> listModel = repository.findAll(ma);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public SanPhamDTO findById(String id) {
        SanPham model = repository.findById(id);
        return mapper.map(model, SanPhamDTO.class);
    }

    @Override
    public List<SanPhamDTO> searchByGiaBan(int currentPage, float giaBan,String id) {
        List<SanPham> listModel = repository.searchSanPhamByGiaBan(currentPage, giaBan,id);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }

    @Override
    public List<SanPhamDTO> searchByMa(int currentPage, String searchByMa) {
        List<SanPham> listModel = repository.searchByMaSp(currentPage, searchByMa);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }

    @Override
    public List<SanPhamDTO> listSanPhamTheoMaGG(int currentPage, String searchByMa) {
        List<SanPham> listModel = repository.listSanPhamTheoMaGG(currentPage, searchByMa);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }

    @Override
    public Object[] getByKhuyenMai(String id) {
        return repository.getByKhuyenMai(id);
    }

    @Override
    public List<SanPhamDTO> findByTrangThai(TrangThaiSanPham trangThaiSanPham) {
        List<SanPham> listModel = repository.findByTrangThai(trangThaiSanPham);
        List<SanPhamDTO> listDTO = new ArrayList<>();
        for (SanPham x : listModel) {
            listDTO.add(mapper.map(x, SanPhamDTO.class));
        }
        return listDTO;
    }

}
