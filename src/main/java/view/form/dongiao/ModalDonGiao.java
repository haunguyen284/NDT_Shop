/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.dongiao;

import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.validator.NDTValidator;
import dto.dongiao.DonGiaoDTO;
import dto.dongiao.ThongSoDTO;
import java.awt.Color;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import service.dongiao.DonGiaoService;
import service.dongiao.impl.DonGiaoServiceImpl;
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;
import view.form.MainForm;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ModalDonGiao extends javax.swing.JDialog {

    private final DonGiaoService service;
    private final Validator validator;
    private final DonGiaoDTO donGiaoDTO;

    public ModalDonGiao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.service = new DonGiaoServiceImpl();
        this.validator = NDTValidator.getValidator();
        this.donGiaoDTO = new DonGiaoDTO();
    }

    public void fill(DonGiaoDTO x) {
        txtNgayGiao.setDate(new Date(x.getNgayGiao()));
        txtTenKh.setText(x.getHoaDon().getTenKH());
        txtDiaChi.setText(x.getDiaChi());
        cboYeuCauDonHang.setSelectedItem(x.convertYeuCauDonHang());
        cboTrangThai.setSelectedItem(x.convertTrangThai());
        txtSdt.setText(x.getSdtNguoiNhan());
        txtMaHd.setText(x.getHoaDon().getMaHD());
        txtIdThongSo.setText(x.getThongSo().getId());
        txtTienThuHo.setText("" + x.getTienThuHo());
        txtMaDG.setText(x.getMaDG());
        txtGhiChu.setText(x.getGhiChu());
        txtTenKh.setText(x.getHoaDon().getKhachHang() != null ? x.getHoaDon().getKhachHang().getTen() : x.getHoaDon().getTenKH());
    }

    public DonGiaoDTO form() {
        switch (cboTrangThai.getSelectedItem().toString()) {
            case "Chưa giao" ->
                donGiaoDTO.setTrangThaiDonGiao(TrangThaiDonGiao.CHUA_GIAO);
            case "Đang giao" ->
                donGiaoDTO.setTrangThaiDonGiao(TrangThaiDonGiao.DANG_GIAO);
            case "Đã giao" ->
                donGiaoDTO.setTrangThaiDonGiao(TrangThaiDonGiao.DA_GIAO);
        }

        switch (cboYeuCauDonHang.getSelectedItem().toString()) {
            case "Cho thử" ->
                donGiaoDTO.setYeuCauDonHang(YeuCauDonHang.CHO_THU);
            case "Cho xem" ->
                donGiaoDTO.setYeuCauDonHang(YeuCauDonHang.CHO_XEM);
            case "Không cho xem" ->
                donGiaoDTO.setYeuCauDonHang(YeuCauDonHang.KHONG_CHO_XEM);
        }
        donGiaoDTO.setDiaChi(txtDiaChi.getText());
        donGiaoDTO.setMaDG(txtMaDG.getText());
        donGiaoDTO.setTienThuHo(Float.parseFloat(txtTienThuHo.getText()));
        donGiaoDTO.setNgayGiao(new Date().getTime());
        donGiaoDTO.setSdtNguoiNhan(txtSdt.getText());
        donGiaoDTO.setGhiChu(txtGhiChu.getText());
        donGiaoDTO.getHoaDon().setTenKH(txtTenKh.getText());
        return donGiaoDTO;
    }

    public void saveOrUpdate(String action) {
        DonGiaoDTO gg = form();
        Set<ConstraintViolation<DonGiaoDTO>> violations = validator.validate(gg);
        if (!violations.isEmpty()) {
            String errors = "";
            for (ConstraintViolation<DonGiaoDTO> x : violations) {
                errors += x.getMessage() + "\n";
            }
            ShowMessage.show(errors);
            return;
        }
        if (action.equals("update")) {
            gg.setId(donGiaoDTO.getId());
        }
        String result = service.saveOrUpdate(action, gg);
        ShowMessageSuccessful.showSuccessful(result);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaHD = new javax.swing.JPanel();
        citybg1 = new javax.swing.JLabel();
        favicon1 = new javax.swing.JLabel();
        userLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        passLabel1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtSave = new javax.swing.JPanel();
        btnSave = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtSdt = new javax.swing.JTextField();
        userLabel7 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        txtMaHd = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboYeuCauDonHang = new javax.swing.JComboBox<>();
        userLabel10 = new javax.swing.JLabel();
        userLabel11 = new javax.swing.JLabel();
        userLabel3 = new javax.swing.JLabel();
        txtClose = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        txtNgayGiao = new com.toedter.calendar.JDateChooser();
        txtMaDG = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        userLabel4 = new javax.swing.JLabel();
        userLabel5 = new javax.swing.JLabel();
        txtTienThuHo = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        userLabel12 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtIdThongSo = new javax.swing.JTextField();
        userLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextPane();

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
        userLabel6.setText("Mã đơn giao");
        txtMaHD.add(userLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        txtDiaChi.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(204, 204, 204));
        txtDiaChi.setBorder(null);
        txtDiaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtDiaChiMousePressed(evt);
            }
        });
        txtMaHD.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 290, 30));

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 290, 20));

        passLabel1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        passLabel1.setText("SĐT");
        txtMaHD.add(passLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 290, 20));

        txtSave.setBackground(new java.awt.Color(51, 102, 255));

        btnSave.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSave.setText("Save");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });

        javax.swing.GroupLayout txtSaveLayout = new javax.swing.GroupLayout(txtSave);
        txtSave.setLayout(txtSaveLayout);
        txtSaveLayout.setHorizontalGroup(
            txtSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtSaveLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        txtSaveLayout.setVerticalGroup(
            txtSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtSaveLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtMaHD.add(txtSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 130, 40));

        jSeparator7.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 290, 20));

        txtSdt.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(204, 204, 204));
        txtSdt.setBorder(null);
        txtSdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSdtMousePressed(evt);
            }
        });
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });
        txtMaHD.add(txtSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 290, 30));

        userLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel7.setText("Tên khách hàng");
        txtMaHD.add(userLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        txtTenKh.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtTenKh.setForeground(new java.awt.Color(204, 204, 204));
        txtTenKh.setBorder(null);
        txtTenKh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenKhMousePressed(evt);
            }
        });
        txtMaHD.add(txtTenKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 290, 30));

        txtMaHd.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtMaHd.setForeground(new java.awt.Color(204, 204, 204));
        txtMaHd.setBorder(null);
        txtMaHd.setCaretColor(new java.awt.Color(204, 204, 204));
        txtMaHd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMaHdMousePressed(evt);
            }
        });
        txtMaHD.add(txtMaHd, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 290, 30));

        jSeparator8.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 290, 20));

        cboTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa giao", "Đang giao", "Đã giao" }));
        cboTrangThai.setBorder(null);
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });
        txtMaHD.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 290, 30));

        cboYeuCauDonHang.setBackground(new java.awt.Color(255, 255, 255));
        cboYeuCauDonHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không cho xem", "Cho thử", "Cho xem" }));
        cboYeuCauDonHang.setBorder(null);
        txtMaHD.add(cboYeuCauDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 290, 30));

        userLabel10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel10.setText("Yêu cầu đơn hàng");
        txtMaHD.add(userLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        userLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel11.setText("Trạng thái");
        txtMaHD.add(userLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        userLabel3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel3.setText("Địa chỉ");
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
            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        txtCloseLayout.setVerticalGroup(
            txtCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtCloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtMaHD.add(txtClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 430, 100, 40));

        txtNgayGiao.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayGiao.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        txtNgayGiao.setForeground(new java.awt.Color(255, 255, 255));
        txtMaHD.add(txtNgayGiao, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, 290, 30));

        txtMaDG.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtMaDG.setForeground(new java.awt.Color(204, 204, 204));
        txtMaDG.setBorder(null);
        txtMaDG.setCaretColor(new java.awt.Color(204, 204, 204));
        txtMaDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMaDGMousePressed(evt);
            }
        });
        txtMaHD.add(txtMaDG, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 290, 30));

        jSeparator9.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 290, 20));

        userLabel4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel4.setText("Ngày giao");
        txtMaHD.add(userLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, -1, -1));

        userLabel5.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel5.setText("ID thông số");
        txtMaHD.add(userLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, -1, -1));

        txtTienThuHo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtTienThuHo.setForeground(new java.awt.Color(204, 204, 204));
        txtTienThuHo.setBorder(null);
        txtTienThuHo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTienThuHoMousePressed(evt);
            }
        });
        txtMaHD.add(txtTienThuHo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 290, 30));

        jSeparator11.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, 290, 20));

        userLabel12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel12.setText("Tiền thu hộ");
        txtMaHD.add(userLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, -1));

        jSeparator12.setForeground(new java.awt.Color(153, 153, 153));
        txtMaHD.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 290, 20));

        txtIdThongSo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtIdThongSo.setForeground(new java.awt.Color(204, 204, 204));
        txtIdThongSo.setBorder(null);
        txtIdThongSo.setCaretColor(new java.awt.Color(204, 204, 204));
        txtIdThongSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtIdThongSoMousePressed(evt);
            }
        });
        txtMaHD.add(txtIdThongSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 290, 30));

        userLabel14.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel14.setText("Mã hóa đơn");
        txtMaHD.add(userLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ghi chú"));

        txtGhiChu.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(txtGhiChu);

        txtMaHD.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 660, 70));

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

    private void txtDiaChiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiaChiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiMousePressed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        if (ShowMessage.show("Bạn muốn lưu giảm giá này chứ ?")) {
            if (btnSave.getText().equals("Save")) {
                saveOrUpdate("add");
                return;
            }
            saveOrUpdate("update");
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        txtSave.setBackground(Color.GREEN);
        btnSave.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        txtSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSaveMouseExited

    private void txtSdtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSdtMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtMousePressed

    private void txtTenKhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenKhMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhMousePressed

    private void txtMaHdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaHdMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHdMousePressed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        new ViewDonGiao().loadData();
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

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void txtMaDGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaDGMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDGMousePressed

    private void txtTienThuHoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienThuHoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuHoMousePressed

    private void txtIdThongSoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdThongSoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdThongSoMousePressed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnSave;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboYeuCauDonHang;
    private javax.swing.JLabel citybg1;
    private javax.swing.JLabel favicon1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel passLabel1;
    private javax.swing.JPanel txtClose;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextPane txtGhiChu;
    private javax.swing.JTextField txtIdThongSo;
    private javax.swing.JTextField txtMaDG;
    private javax.swing.JPanel txtMaHD;
    private javax.swing.JTextField txtMaHd;
    private com.toedter.calendar.JDateChooser txtNgayGiao;
    private javax.swing.JPanel txtSave;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTienThuHo;
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
