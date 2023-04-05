/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.khachhang;

import comon.constant.ModelProperties;
import comon.model.PrimaryModel;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import model.hoadon.HoaDon;
import model.hoadon.HoaDonChiTiet;

/**
 *
 * @author nguyenth28
 */
@Getter
@Setter
@Table(name = "LichSuTieuDiem")
@Entity
public class LichSuTieuDiem extends PrimaryModel implements Serializable {

    @ManyToOne
    @JoinColumn(name = "vi_diem_id")
    private ViDiem viDiem;
    
    @ManyToOne
    @JoinColumn(name = "quy_doi_diem_id")
    private QuyDoiDiem quyDoiDiem;

    @Column
    private int soDiemDaDung;

    @Column
    private int soDiemCong;

    @Column
    private Long ngaySuDung;

}
