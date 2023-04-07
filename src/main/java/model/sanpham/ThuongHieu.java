/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sanpham;

import comon.constant.ModelProperties;
import comon.constant.sanpham.TrangThaiChatLieu;
import comon.constant.sanpham.TrangThaiMauSac;
import comon.constant.sanpham.TrangThaiThuongHieu;
import comon.model.PrimaryModel;
import java.io.Serializable;
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
@Table(name = "ThuongHieu")
@Entity
public class ThuongHieu extends PrimaryModel implements Serializable {

    @Column(length = ModelProperties.LENGTH_CODE, unique = true)
    private String ma;

    @Column(length = ModelProperties.LENGTH_NAME)
    @Nationalized
    private String ten;
    
    @Column(length = ModelProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private TrangThaiThuongHieu trangThaiThuongHieu;
}
