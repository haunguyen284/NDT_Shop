/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dongiao;

import comon.model.AuditModel;
import comon.model.PrimaryModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
@Table(name = "ThongSo")
@Entity
public class ThongSo extends PrimaryModel implements Serializable {

    @Column
    private float dai;

    @Column
    private float rong;

    @Column
    private float cao;

    @Column
    private float khoiLuong;
    
    @Column
    private String ma;

}
