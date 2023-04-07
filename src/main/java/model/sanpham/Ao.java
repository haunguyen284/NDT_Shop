/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sanpham;

import comon.constant.ModelProperties;
import comon.model.PrimaryModel;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author nguyenth28
 */
@Getter
@Setter
@Table(name = "Ao")
@Entity
public class Ao extends PrimaryModel implements Serializable {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "san_pham_id", referencedColumnName = "id")
    private SanPham sanPham;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "size_ao_id", referencedColumnName = "id")
    private SizeAo sizeAo;
}
