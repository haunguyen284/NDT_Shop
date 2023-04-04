/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.khachhang.impl;

import comon.utilities.Mapper;
import dto.khachhang.QuyDoiDiemDTO;
import java.util.ArrayList;
import java.util.List;
import model.khachhang.QuyDoiDiem;
import org.modelmapper.ModelMapper;
import repository.khachhang.QuyDoiDiemRepository;
import service.khachhang.QuyDoiDiemService;


/**
 *
 * @author Admin
 */
public class QuyDoiDiemServiceImpl implements QuyDoiDiemService{

    private final QuyDoiDiemRepository QuyDoiDiemRepository;
    private final ModelMapper mapper;

    public QuyDoiDiemServiceImpl() {
        this.QuyDoiDiemRepository = new QuyDoiDiemRepository();
        this.mapper = Mapper.modelMapper();
    }
    
    

    @Override
    public List<QuyDoiDiemDTO> findAll(int position, int pageSize) {
        List<QuyDoiDiemDTO> listDTO = new ArrayList<>();
        List<QuyDoiDiem> listModel = QuyDoiDiemRepository.findAll(position, pageSize);
        for (QuyDoiDiem QuyDoiDiem : listModel) {
            listDTO.add(mapper.map(QuyDoiDiem, QuyDoiDiemDTO.class));
        }
        return listDTO;
    }

    @Override
    public QuyDoiDiemDTO findById(String id) {
        QuyDoiDiem model = QuyDoiDiemRepository.findById(id);
        return mapper.map(model, QuyDoiDiemDTO.class);
    }

    @Override
    public String save(QuyDoiDiemDTO dTO) {
        dTO.setId(null);
        QuyDoiDiem model = mapper.map(dTO, QuyDoiDiem.class);
        if(QuyDoiDiemRepository.save(model)!=null){
            return "Thêm thành công";
        }else{
            return "Thêm thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
        return QuyDoiDiemRepository.delete(id);
    }

    @Override
    public long totalCount() {
        return QuyDoiDiemRepository.totalCount();
    }

    @Override
    public List<QuyDoiDiemDTO> findAll() {
        List<QuyDoiDiemDTO> listDTO = new ArrayList<>();
        List<QuyDoiDiem> listModel = QuyDoiDiemRepository.findAll();
        for (QuyDoiDiem QuyDoiDiem : listModel) {
            listDTO.add(mapper.map(QuyDoiDiem, QuyDoiDiemDTO.class));
        }
        return listDTO;
    }

    @Override
    public QuyDoiDiemDTO findByTen(String ten) {
        QuyDoiDiem model = QuyDoiDiemRepository.findByTen(ten);
        return mapper.map(model, QuyDoiDiemDTO.class);
    }
    
    
    
}
