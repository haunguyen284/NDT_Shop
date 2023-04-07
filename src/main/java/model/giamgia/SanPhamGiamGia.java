/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.giamgia;

import comon.model.PrimaryModel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import model.sanpham.SanPham;

/**
 *
 * @author nguyenth28
 */
@Getter
@Setter
@Table(name = "SanPhamGiamGia")
@Entity
public class SanPhamGiamGia extends PrimaryModel implements Serializable {

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "giam_gia_id")
    private GiamGia giamGia;

    @Column
    private float donGia;
    
    @Column
    private float soTienConLai;
    
    
}
