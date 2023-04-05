/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.giamgia;

import comon.constant.giamgia.LoaiGiamGia;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import java.awt.Color;
import java.util.Date;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import service.giamgia.GiamGiaService;
import service.giamgia.impl.GiamGiaImpl;
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ViewModal extends javax.swing.JDialog {

    private final GiamGiaService service;
    private final Validator validator;
    private DefaultComboBoxModel cbb;
//    private final GiamGiaDTO giamGiaDTO;

    /**
     * Creates new form ViewModal
     */
    public ViewModal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.service = new GiamGiaImpl();
        this.validator = NDTValidator.getValidator();
        this.cbb = new DefaultComboBoxModel();
        loadCbb();
//        this.giamGiaDTO = giamGiaDTO;
    }

    private void loadCbb() {
        cbb.addElement("Đang hoạt động");
        cbb.addElement("Ngừng hoạt động");
        cboTrangThai.setModel(cbb);
    }

    public void fill(GiamGiaDTO x) {
        txtID.setText(x.getId());
        txtKetThuc.setDate(new Date(x.getNgayKetThuc()));
        txtBatDau.setDate(new Date(x.getNgayBatDau()));
        txtTen.setText(x.getTen());
        txtDieuKien.setText("" + x.getDieuKienGiamGia());
        cboLoai.setSelectedItem(x.getLoaiGiamGia());
        lblMoTa.setText(x.getMoTa());
        cboTrangThai.setSelectedItem(x.getLoaiGiamGia());
        txtGiaTri.setText(x.getGiaTriGiamGia() + "");
    }

    public GiamGiaDTO form() {
        GiamGiaDTO x = new GiamGiaDTO();
        x.setId(txtID.getText());
        x.setNgayBatDau(txtBatDau.getDate().getTime());
        x.setNgayKetThuc(txtKetThuc.getDate().getTime());
        x.setDieuKienGiamGia(Float.parseFloat(txtDieuKien.getText()));
        x.setTen(txtTen.getText());
        x.setMoTa(lblMoTa.getText());
        x.setGiaTriGiamGia(Float.parseFloat(txtGiaTri.getText()));
        switch (cboTrangThai.getSelectedItem().toString()) {
            case "Đang hoạt động" ->
                x.setTrangThaiGiamGia(TrangThaiGiamGia.DANG_HOAT_DONG);
            case "Ngừng hoạt động" ->
                x.setTrangThaiGiamGia(TrangThaiGiamGia.NGUNG_HOAT_DONG);
        }
        switch (cboLoai.getSelectedItem().toString()) {
            case "Giảm giá theo %" ->
                x.setLoaiGiamGia(LoaiGiamGia.GIAM_GIA_THEO_PHAN_TRAM);
            case "Giảm giá theo tiền mặt" ->
                x.setLoaiGiamGia(LoaiGiamGia.GIAM_GIA_THEO_TIEN_MAT);
        }
        return x;
    }

    public void clearForm() {
        txtBatDau.setDate(new Date());
        txtKetThuc.setDate(new Date());
        txtDieuKien.setText("");
        txtTen.setText("");
        txtID.setText("");
        txtGiaTri.setText("");
        lblMoTa.setText("");
    }

    public void saveOrUpdate(String action) {
        GiamGiaDTO qLGiamGia = form();
        Set<ConstraintViolation<GiamGiaDTO>> violations = validator.validate(qLGiamGia);
        if (!violations.isEmpty()) {
            String errors = "";
            for (ConstraintViolation<GiamGiaDTO> x : violations) {
                errors += x.getMessage() + "\n";
            }
           ShowMessage.show(errors);
            return;
        }
        if (action.equals("add")) {
            qLGiamGia.setId(null);
        }
//        if (action.equals("add")) {
//            for (GiamGiaDTO x : service.getAll(currentPage)) {
//                if (x.getMaGg().equals(qLGiamGia.getMaGg())) {
//                    JOptionPane.showMessageDialog(this, "Mã - này đã tồn tại !");
//                    return;
//                }
//            }
//        }
        String result = service.saveOrUpdate(action, qLGiamGia);
        ShowMessageSuccessful.showSuccessful(result);
        new ViewGiamGiamSp().loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg1 = new javax.swing.JPanel();
        citybg1 = new javax.swing.JLabel();
        favicon1 = new javax.swing.JLabel();
        userLabel6 = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        passLabel1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtSave = new javax.swing.JPanel();
        btnSave = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtGiaTri = new javax.swing.JTextField();
        userLabel7 = new javax.swing.JLabel();
        userLabel8 = new javax.swing.JLabel();
        userLabel9 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboLoai = new javax.swing.JComboBox<>();
        userLabel10 = new javax.swing.JLabel();
        userLabel11 = new javax.swing.JLabel();
        userLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblMoTa = new javax.swing.JTextPane();
        txtClose = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        txtBatDau = new com.toedter.calendar.JDateChooser();
        txtKetThuc = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg1.setBackground(new java.awt.Color(255, 255, 255));
        bg1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        citybg1.setBackground(new java.awt.Color(0, 134, 190));
        bg1.add(citybg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 0, -1, 500));

        favicon1.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        favicon1.setIcon(new javax.swing.ImageIcon("D:\\DuAnMot\\NDT_Shop\\src\\main\\resources\\icon\\logo.png")); // NOI18N
        favicon1.setText("NDT_Shop");
        bg1.add(favicon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        userLabel6.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel6.setText("ID");
        bg1.add(userLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        txtDieuKien.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDieuKien.setForeground(new java.awt.Color(204, 204, 204));
        txtDieuKien.setText("Vui lòng điền vào điều kiện giảm giá...");
        txtDieuKien.setBorder(null);
        txtDieuKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtDieuKienMousePressed(evt);
            }
        });
        bg1.add(txtDieuKien, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 290, 30));

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 290, 20));

        passLabel1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        passLabel1.setText("Giá trị giảm giá");
        bg1.add(passLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 290, 20));

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

        bg1.add(txtSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 130, 40));

        jSeparator7.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 290, 20));

        txtGiaTri.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtGiaTri.setForeground(new java.awt.Color(204, 204, 204));
        txtGiaTri.setText("Vui lòng điền vào giá trị giảm giá....");
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
        bg1.add(txtGiaTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 290, 30));

        userLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel7.setText("Tên giảm giá");
        bg1.add(userLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        userLabel8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel8.setText("Điều kiện");
        bg1.add(userLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        userLabel9.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel9.setText("Ngày kết thúc");
        bg1.add(userLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        txtTen.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTen.setForeground(new java.awt.Color(204, 204, 204));
        txtTen.setText("Vui lòng điền vào tên giảm giá....");
        txtTen.setBorder(null);
        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenMousePressed(evt);
            }
        });
        bg1.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 290, 30));

        txtID.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(204, 204, 204));
        txtID.setBorder(null);
        txtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtIDMousePressed(evt);
            }
        });
        bg1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 290, 30));

        jSeparator8.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 290, 20));

        cboTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTrangThai.setBorder(null);
        bg1.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 270, 30));

        cboLoai.setBackground(new java.awt.Color(255, 255, 255));
        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm giá theo %", "Giảm giá theo tiền mặt" }));
        cboLoai.setBorder(null);
        bg1.add(cboLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 270, 30));

        userLabel10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel10.setText("Loại");
        bg1.add(userLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        userLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel11.setText("Trạng thái");
        bg1.add(userLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        userLabel3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel3.setText("Ngày bắt đầu");
        bg1.add(userLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, -1, -1));

        lblMoTa.setBorder(javax.swing.BorderFactory.createTitledBorder("Mô tả"));
        jScrollPane1.setViewportView(lblMoTa);

        bg1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 720, 110));

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
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        txtCloseLayout.setVerticalGroup(
            txtCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtCloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg1.add(txtClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 550, -1, 40));

        txtBatDau.setBackground(new java.awt.Color(255, 255, 255));
        txtBatDau.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        txtBatDau.setForeground(new java.awt.Color(255, 255, 255));
        bg1.add(txtBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 270, 30));

        txtKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        txtKetThuc.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        bg1.add(txtKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 352, 270, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDieuKienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDieuKienMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDieuKienMousePressed

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

    private void txtGiaTriMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaTriMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriMousePressed

    private void txtTenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMousePressed

    private void txtIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDMousePressed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        new ViewGiamGiamSp().loadData();
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

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg1;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnSave;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel citybg1;
    private javax.swing.JLabel favicon1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextPane lblMoTa;
    private javax.swing.JLabel passLabel1;
    private com.toedter.calendar.JDateChooser txtBatDau;
    private javax.swing.JPanel txtClose;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtID;
    private com.toedter.calendar.JDateChooser txtKetThuc;
    private javax.swing.JPanel txtSave;
    private javax.swing.JTextField txtTen;
    private javax.swing.JLabel userLabel10;
    private javax.swing.JLabel userLabel11;
    private javax.swing.JLabel userLabel3;
    private javax.swing.JLabel userLabel6;
    private javax.swing.JLabel userLabel7;
    private javax.swing.JLabel userLabel8;
    private javax.swing.JLabel userLabel9;
    // End of variables declaration//GEN-END:variables
}
