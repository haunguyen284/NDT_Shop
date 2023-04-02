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
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import service.giamgia.GiamGiaService;
import service.giamgia.impl.GiamGiaImpl;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class Modal extends javax.swing.JDialog {

    int xMouse, yMouse;
    DefaultComboBoxModel cbb = new DefaultComboBoxModel();
    private final DefaultTableModel dtm;
    private final GiamGiaService service;
    private final Validator validator;
    private int currentPage = 1;
    private int totalPage = 1;

    public Modal() {
        initComponents();
        setLocationRelativeTo(null);
        this.dtm = new DefaultTableModel();
        this.service = new GiamGiaImpl();
        this.validator = NDTValidator.getValidator();
        loadCbbLoai(service.getAll(currentPage));
    }
    public void showData(List<GiamGiaDTO> lists) {
        dtm.setRowCount(0);
        for (GiamGiaDTO x : lists) {
            dtm.addRow(x.toDataRow());
        }
    }

    public void fill(GiamGiaDTO x) {
        txtTen.setText(x.getTen());
        txtKetThuc.setDate(new Date(x.getNgayKetThuc()));
        txtBatDau.setDate(new Date(x.getNgayBatDau()));
        txtGiaTri.setText("" + x.getGiaTriGiamGia());
        txtDieuKien.setText("" + x.getDieuKienGiamGia());
        cboLoai.setSelectedItem(x.getLoaiGiamGia());
        lblMoTa.setText(x.getMoTa());
    }

    public GiamGiaDTO form() {
        GiamGiaDTO x = new GiamGiaDTO();
        x.setNgayBatDau(txtBatDau.getDate().getTime());
        x.setNgayKetThuc(txtKetThuc.getDate().getTime());
        x.setDieuKienGiamGia(Float.parseFloat(txtDieuKien.getText()));
        x.setLoaiGiamGia((LoaiGiamGia) cbb.getSelectedItem());
        x.setTen(txtTen.getText());
        x.setMoTa(lblMoTa.getText());
        return x;
    }

    public void saveOrUpdate(String action) {
        GiamGiaDTO qLGiamGia = form();
//        Set<ConstraintViolation<GiamGiaDTO>> violations = new ViewGiamGiamSp().validate(qLGiamGia);
//        if (!violations.isEmpty()) {
//            String errors = "";
//            for (ConstraintViolation<GiamGiaDTO> x : violations) {
//                errors += x.getMessage() + "\n";
//            }
//            JOptionPane.showMessageDialog(this, errors, "ERRORS", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        int row = new ViewGiamGiamSp().getTblGiamGia().getSelectedRow();
//        String idCV = new ViewGiamGiamSp().getTblGiamGia().getValueAt(row, 0).toString();
//        if (action.equals("update")) {
//            qLGiamGia.setId(idCV);
//        }
//        if (action.equals("add")) {
//            for (GiamGiaDTO x : service.getAll(currentPage)) {
//                if (x.getMaGg().equals(qLGiamGia.getMaGg())) {
//                    JOptionPane.showMessageDialog(this, "Mã - này đã tồn tại !");
//                    return;
//                }
//            }
//        }
        String result = service.saveOrUpdate(action, qLGiamGia);
        JOptionPane.showMessageDialog(this, result);
        showData(service.getAll(currentPage));
        new ViewGiamGiamSp().showData(service.getAll(currentPage));
        new ViewGiamGiamSp().showPaganation();
    }

    public void loadCbbLoai(List<GiamGiaDTO> list) {
        for (GiamGiaDTO x : list) {
            cbb.addElement(x.getLoaiGiamGia());
        }
        cboLoai.setModel(cbb);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        loginBtn1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtGiamGiaTheoTienMat = new javax.swing.JTextField();
        userLabel7 = new javax.swing.JLabel();
        userLabel8 = new javax.swing.JLabel();
        userLabel9 = new javax.swing.JLabel();
        txtGiaTri = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboLoai = new javax.swing.JComboBox<>();
        userLabel10 = new javax.swing.JLabel();
        userLabel11 = new javax.swing.JLabel();
        userLabel3 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblMoTa = new javax.swing.JTextPane();
        exitBtn = new javax.swing.JPanel();
        exitTxt = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
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
        userLabel6.setText("Tên");
        bg1.add(userLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        txtDieuKien.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDieuKien.setForeground(new java.awt.Color(204, 204, 204));
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
        passLabel1.setText("Mức giảm giá tiền mặt");
        bg1.add(passLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 290, 20));

        loginBtn1.setBackground(new java.awt.Color(0, 134, 190));

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

        javax.swing.GroupLayout loginBtn1Layout = new javax.swing.GroupLayout(loginBtn1);
        loginBtn1.setLayout(loginBtn1Layout);
        loginBtn1Layout.setHorizontalGroup(
            loginBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBtn1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        loginBtn1Layout.setVerticalGroup(
            loginBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBtn1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg1.add(loginBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 130, 40));

        jSeparator7.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 290, 20));

        txtGiamGiaTheoTienMat.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtGiamGiaTheoTienMat.setForeground(new java.awt.Color(204, 204, 204));
        txtGiamGiaTheoTienMat.setText("Mức giảm giá tiền mặt...");
        txtGiamGiaTheoTienMat.setBorder(null);
        txtGiamGiaTheoTienMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtGiamGiaTheoTienMatMousePressed(evt);
            }
        });
        bg1.add(txtGiamGiaTheoTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 290, 30));

        userLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel7.setText("Gía trị mức giảm giá ");
        bg1.add(userLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        userLabel8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel8.setText("Điều kiện");
        bg1.add(userLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        userLabel9.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel9.setText("Ngày kết thúc");
        bg1.add(userLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        txtGiaTri.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtGiaTri.setForeground(new java.awt.Color(204, 204, 204));
        txtGiaTri.setText("Ingrese su nombre de usuario");
        txtGiaTri.setBorder(null);
        txtGiaTri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtGiaTriMousePressed(evt);
            }
        });
        bg1.add(txtGiaTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 290, 30));

        txtTen.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTen.setForeground(new java.awt.Color(204, 204, 204));
        txtTen.setText("Ingrese su nombre de usuario");
        txtTen.setBorder(null);
        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenMousePressed(evt);
            }
        });
        bg1.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 290, 30));

        jSeparator8.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 290, 20));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bg1.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 270, 30));

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        loginBtn.setBackground(new java.awt.Color(0, 134, 190));

        btnClose.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setText("Close");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        javax.swing.GroupLayout loginBtnLayout = new javax.swing.GroupLayout(loginBtn);
        loginBtn.setLayout(loginBtnLayout);
        loginBtnLayout.setHorizontalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        loginBtnLayout.setVerticalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 550, -1, -1));

        lblMoTa.setBorder(javax.swing.BorderFactory.createTitledBorder("Mô tả"));
        jScrollPane1.setViewportView(lblMoTa);

        bg1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 720, 110));

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        exitTxt.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        exitTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitTxt.setText("X");
        exitTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exitTxt.setPreferredSize(new java.awt.Dimension(40, 40));
        exitTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bg1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(153, 153, 153));
        bg1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 270, 20));

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

    private void exitTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseExited
        exitBtn.setBackground(Color.white);
        exitTxt.setForeground(Color.black);
    }//GEN-LAST:event_exitTxtMouseExited

    private void exitTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseEntered
        exitBtn.setBackground(Color.red);
        exitTxt.setForeground(Color.white);
    }//GEN-LAST:event_exitTxtMouseEntered

    private void exitTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseClicked
        this.dispose();
        new ViewGiamGiamSp().loadData();
    }//GEN-LAST:event_exitTxtMouseClicked

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        loginBtn.setBackground(new Color(0, 134, 190));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        loginBtn.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked

    }//GEN-LAST:event_btnCloseMouseClicked

    private void txtTenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMousePressed

    private void txtGiaTriMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaTriMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriMousePressed

    private void txtGiamGiaTheoTienMatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiamGiaTheoTienMatMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaTheoTienMatMousePressed

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        loginBtn.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
        saveOrUpdate("add");
        new ViewGiamGiamSp().showData(service.getAll(currentPage));

    }//GEN-LAST:event_btnSaveMouseClicked

    private void txtDieuKienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDieuKienMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDieuKienMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modal dialog = new Modal();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg1;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnSave;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel citybg1;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JLabel exitTxt;
    private javax.swing.JLabel favicon1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextPane lblMoTa;
    private javax.swing.JPanel loginBtn;
    private javax.swing.JPanel loginBtn1;
    private javax.swing.JLabel passLabel1;
    private com.toedter.calendar.JDateChooser txtBatDau;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtGiamGiaTheoTienMat;
    private com.toedter.calendar.JDateChooser txtKetThuc;
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
