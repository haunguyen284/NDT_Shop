/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.giamgia;

import comon.constant.ModelProperties;
import comon.constant.giamgia.LoaiGiamGia;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.model.PrimaryModel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author nguyenth28
 */
@Getter
@Setter
@Entity
@Table(name = "GiamGia")
public class GiamGia extends PrimaryModel implements Serializable {

    @Column(length = ModelProperties.LENGTH_CODE)
    private String maGg;

    @Column(length = ModelProperties.LENGTH_NAME)
    @Nationalized
    private String ten;

    @Column
    private Long ngayBatDau;

    @Column
    private Long ngayKetThuc;

    @Column
    private float giaTriGiamGia;

    @Column
    private float dieuKienGiamGia;

    @Column
    private TrangThaiGiamGia trangThaiGiamGia;

    @Column
    private LoaiGiamGia loaiGiamGia;

    @Column(length = ModelProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String moTa;

}
