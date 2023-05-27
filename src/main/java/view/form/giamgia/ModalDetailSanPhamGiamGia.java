/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.giamgia;

import view.form.dongiao.*;
import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.constant.giamgia.LoaiGiamGia;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.constant.sanpham.LoaiSanPham;
import comon.validator.NDTValidator;
import dto.dongiao.DonGiaoDTO;
import dto.dongiao.ThongSoDTO;
import dto.giamgia.SanPhamGiamGiaDTO;
import java.awt.Color;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import model.sanpham.SanPham;
import service.dongiao.DonGiaoService;
import service.dongiao.impl.DonGiaoServiceImpl;
import service.giamgia.SanPhamGiamGiaService;
import service.giamgia.impl.SanPhamGiamGiaImpl;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ModalDetailSanPhamGiamGia extends javax.swing.JDialog {

    private final SanPhamGiamGiaService service;
    private final Validator validator;
    private final SanPhamGiamGiaDTO sanPhamGiamGiaDTO;

    public ModalDetailSanPhamGiamGia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.service = new SanPhamGiamGiaImpl();
        this.validator = NDTValidator.getValidator();
        this.sanPhamGiamGiaDTO = new SanPhamGiamGiaDTO();
    }

    public void fill(SanPhamGiamGiaDTO x) {
        txtNgayBatDau.setDate(new Date(x.getGiamGia().getNgayBatDau()));
        txtTenGiamGia.setText(x.getGiamGia().getTen());
        txtNgayKetThuc.setDate(new Date(x.getGiamGia().getNgayKetThuc()));
        cboLoaiGiamGia.setSelectedItem(x.getGiamGia().convertLoaiGiamGia());
        cboLoaiSanPham.setSelectedItem(x.getSanPham().convertLoaiSanPham());
        txtGiaTri.setText("" + x.getGiamGia().getGiaTriGiamGia());
        txtTenSanPham.setText(x.getSanPham().getTenSP());
        txtSoTienConLai.setText("" + x.getSoTienConLai());
        txtMaGG.setText(x.getGiamGia().getMaGg());
        txtGhiChu.setText(x.getGiamGia().getMoTa());
        cboTrangThai.setSelectedItem(x.getGiamGia().convertGiamGia());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaHD = new javax.swing.JPanel();
        citybg1 = new javax.swing.JLabel();
        favicon1 = new javax.swing.JLabel();
        userLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        passLabel1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        txtGiaTri = new javax.swing.JTextField();
        userLabel7 = new javax.swing.JLabel();
        txtTenGiamGia = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        cboLoaiGiamGia = new javax.swing.JComboBox<>();
        userLabel10 = new javax.swing.JLabel();
        userLabel11 = new javax.swing.JLabel();
        userLabel3 = new javax.swing.JLabel();
        txtClose = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtMaGG = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        userLabel4 = new javax.swing.JLabel();
        userLabel5 = new javax.swing.JLabel();
        userLabel12 = new javax.swing.JLabel();
        txtSoTienConLai = new javax.swing.JTextField();
        userLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextPane();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        cboTrangThai = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtMaHD.setBackground(new java.awt.Color(255, 255, 255));
        txtMaHD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        citybg1.setBackground(new java.awt.Color(0, 134, 190));
        txtMaHD.add(citybg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 0, -1, 500));

        favicon1.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        favicon1.setIcon(new javax.swing.ImageIcon("D:\\DuAnMot\\NDT_Shop\\src\\main\\resources\\icon\\logo.png")); // NOI18N
        favicon1.setText("NDT_Shop");
        txtMaHD.add(favicon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        userLabel6.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel6.setText("Mã GG");
        txtMaHD.add(userLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 290, 20));

        passLabel1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        passLabel1.setText("Giá trị giảm giá");
        txtMaHD.add(passLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 290, 20));

        jSeparator7.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 290, 20));

        txtGiaTri.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtGiaTri.setForeground(new java.awt.Color(204, 204, 204));
        txtGiaTri.setBorder(null);
        txtGiaTri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtGiaTriMousePressed(evt);
            }
        });
        txtGiaTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTriActionPerformed(evt);
            }
        });
        txtMaHD.add(txtGiaTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 290, 30));

        userLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel7.setText("Tên giảm giá");
        txtMaHD.add(userLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        txtTenGiamGia.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtTenGiamGia.setForeground(new java.awt.Color(204, 204, 204));
        txtTenGiamGia.setBorder(null);
        txtTenGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenGiamGiaMousePressed(evt);
            }
        });
        txtMaHD.add(txtTenGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 290, 30));

        txtTenSanPham.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtTenSanPham.setForeground(new java.awt.Color(204, 204, 204));
        txtTenSanPham.setBorder(null);
        txtTenSanPham.setCaretColor(new java.awt.Color(204, 204, 204));
        txtTenSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenSanPhamMousePressed(evt);
            }
        });
        txtMaHD.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 290, 30));

        jSeparator8.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 290, 20));

        cboLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ÁO", "QUẦN" }));
        cboLoaiSanPham.setBorder(null);
        cboLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPhamActionPerformed(evt);
            }
        });
        txtMaHD.add(cboLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 290, 30));

        cboLoaiGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        cboLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm giá theo %", "Giảm giá theo tiền mặt" }));
        cboLoaiGiamGia.setBorder(null);
        txtMaHD.add(cboLoaiGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 290, 30));

        userLabel10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel10.setText("Loại giảm giá");
        txtMaHD.add(userLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        userLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel11.setText("Trạng thái");
        txtMaHD.add(userLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        userLabel3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel3.setText("Loại sản phẩm");
        txtMaHD.add(userLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        txtClose.setBackground(new java.awt.Color(51, 102, 255));

        btnClose.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setText("Close");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClose.setPreferredSize(new java.awt.Dimension(40, 40));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });

        javax.swing.GroupLayout txtCloseLayout = new javax.swing.GroupLayout(txtClose);
        txtClose.setLayout(txtCloseLayout);
        txtCloseLayout.setHorizontalGroup(
            txtCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtCloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        txtCloseLayout.setVerticalGroup(
            txtCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtCloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtMaHD.add(txtClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 430, 100, 40));

        txtNgayBatDau.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayBatDau.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        txtNgayBatDau.setForeground(new java.awt.Color(255, 255, 255));
        txtMaHD.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 290, 30));

        txtMaGG.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtMaGG.setForeground(new java.awt.Color(204, 204, 204));
        txtMaGG.setBorder(null);
        txtMaGG.setCaretColor(new java.awt.Color(204, 204, 204));
        txtMaGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMaGGMousePressed(evt);
            }
        });
        txtMaHD.add(txtMaGG, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 290, 30));

        jSeparator9.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 290, 20));

        userLabel4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel4.setText("Ngày kết thúc");
        txtMaHD.add(userLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, -1, -1));

        userLabel5.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel5.setText("Số tiền còn lại");
        txtMaHD.add(userLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, -1, -1));

        userLabel12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel12.setText("Ngày bắt đầu");
        txtMaHD.add(userLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, -1));

        txtSoTienConLai.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtSoTienConLai.setForeground(new java.awt.Color(204, 204, 204));
        txtSoTienConLai.setBorder(null);
        txtSoTienConLai.setCaretColor(new java.awt.Color(204, 204, 204));
        txtSoTienConLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSoTienConLaiMousePressed(evt);
            }
        });
        txtMaHD.add(txtSoTienConLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 290, 30));

        userLabel14.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel14.setText("Tên sản phẩm");
        txtMaHD.add(userLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ghi chú"));

        txtGhiChu.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(txtGhiChu);

        txtMaHD.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 660, 70));

        txtNgayKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayKetThuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        txtNgayKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        txtMaHD.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, 290, 30));

        cboTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Ngừng hoạt động" }));
        cboTrangThai.setBorder(null);
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });
        txtMaHD.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 290, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGiaTriMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaTriMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriMousePressed

    private void txtTenGiamGiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenGiamGiaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenGiamGiaMousePressed

    private void txtTenSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenSanPhamMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamMousePressed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked

        this.dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        txtClose.setBackground(Color.red);
        btnClose.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        txtClose.setBackground(Color.BLUE);
        btnClose.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCloseMouseExited

    private void txtGiaTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriActionPerformed

    private void txtMaGGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaGGMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGGMousePressed

    private void txtSoTienConLaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoTienConLaiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienConLaiMousePressed

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JComboBox<String> cboLoaiGiamGia;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel citybg1;
    private javax.swing.JLabel favicon1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel passLabel1;
    private javax.swing.JPanel txtClose;
    private javax.swing.JTextPane txtGhiChu;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtMaGG;
    private javax.swing.JPanel txtMaHD;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSoTienConLai;
    private javax.swing.JTextField txtTenGiamGia;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JLabel userLabel10;
    private javax.swing.JLabel userLabel11;
    private javax.swing.JLabel userLabel12;
    private javax.swing.JLabel userLabel14;
    private javax.swing.JLabel userLabel3;
    private javax.swing.JLabel userLabel4;
    private javax.swing.JLabel userLabel5;
    private javax.swing.JLabel userLabel6;
    private javax.swing.JLabel userLabel7;
    // End of variables declaration//GEN-END:variables
}
