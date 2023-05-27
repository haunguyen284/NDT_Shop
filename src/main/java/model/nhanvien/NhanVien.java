/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.nhanvien;

import comon.constant.ModelProperties;
import comon.constant.nhanvien.TrangThaiNhanVien;
import comon.model.PrimaryModel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import model.hoadon.HoaDon;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author nguyenth28
 */

@Getter
@Setter
@Table(name = "NhanVien")
@Entity
public class NhanVien extends PrimaryModel implements Serializable{
   
    @Column
    private String maNV;
    
    @Column(length = ModelProperties.LENGTH_NAME)
    @Nationalized
    private String ten;
    
    @Column
    private Long ngaySinh;
    
    @Column(length = ModelProperties.LENGTH_SEX)
    @Nationalized
    private String gioiTinh;
    
    @Column(length = ModelProperties.LENGTH_PHONE)
    private String sdt;
    
    @Column(length = ModelProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String diaChi;
    
    @Column(length = ModelProperties.LENGTH_EMAIL)
    @Nationalized
    private String email;
    
    @Column
    private TrangThaiNhanVien trangThaiNhanVien;
    
}
