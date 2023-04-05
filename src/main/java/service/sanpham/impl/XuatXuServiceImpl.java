/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sanpham.impl;

import comon.constant.sanpham.TrangThaiXuatXu;
import comon.utilities.Mapper;
import dto.sanpham.XuatXuDTO;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.XuatXu;
import repository.sanpham.XuatXuRepository;
import service.sanpham.XuatXuService;

/**
 *
 * @author nguyenth28
 */
public class XuatXuServiceImpl implements XuatXuService {

    private final XuatXuRepository repository;

    public XuatXuServiceImpl() {
        this.repository = new XuatXuRepository();
    }

    @Override
    public List<XuatXuDTO> findAll(TrangThaiXuatXu trangThai) {
        List<XuatXuDTO> listDTO = new ArrayList<>();
        List<XuatXu> listModel = repository.findAll(trangThai);
        for (XuatXu m : listModel) {
            listDTO.add(Mapper.modelMapper().map(m, XuatXuDTO.class));
        }
        return listDTO;
    }

    @Override
    public String create(XuatXuDTO dto) {
        dto.setId(null);
        XuatXu model = Mapper.modelMapper().map(dto, XuatXu.class);
        if (repository.findByMa(dto.getMa()) != null) {
            return "Mã đã tồn tại";
        }

        String result;
        if (repository.save(model) != null) {
            result = "Thêm thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

    @Override
    public String update(XuatXuDTO dto) {
        XuatXu model = Mapper.modelMapper().map(dto, XuatXu.class);
        String result;
        if (repository.save(model) != null) {
            result = "Update thành công";
        } else {
            result = "Mã đã tồn tại";
        }
        return result;
    }

}
