/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.nhanvien;

import comon.constant.nhanvien.TrangThaiNhanVien;
import comon.validator.NDTValidator;
import dto.nhanvien.NhanVienDTO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import service.nhanvien.NhanVienService;
import service.nhanvien.impl.NhanVienServiceImpl;
import view.dialog.Message;
import view.main.Main;

/**
 *
 * @author Admin
 */
public class ModalNhanVien extends javax.swing.JDialog {

    private final NhanVienService nhanVienService;

    /**
     * Creates new form ModalKhachHang
     */
    public ModalNhanVien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        nhanVienService = new NhanVienServiceImpl();
        initComponents();
    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    public void fill(NhanVienDTO dTO) {
        lbMaNV.setText(dTO.getMaNV());
        txtHoTen.setText(dTO.getTen());
        txtSDT.setText(dTO.getSdt());
        txtEmail.setText(dTO.getEmail());
        jdNgaySinh.setDate(new Date(dTO.getNgaySinh()));
        txtDiaChi.setText(dTO.getDiaChi());
        cbxGioiTinh.setSelectedItem(dTO.getGioiTinh());
        cbTrangThai.setSelectedItem(dTO.convertTrangThaiNhanVien());
    }

    public void clear() {
        lbMaNV.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        jdNgaySinh.setDate(new Date());
        txtDiaChi.setText("");
        cbxGioiTinh.setSelectedIndex(0);
    }

    public String generateCustomerId(NhanVienDTO khachHang) {
        String hoTen = khachHang.getTen();
        Date ngaySinh = new Date(khachHang.getNgaySinh());
        LocalDate localDate = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String maKH = "";
        if (hoTen.contains(" ")) {
            String[] parts = hoTen.split(" ");
            maKH = parts[0].substring(0, 1) + parts[1].substring(0, 1);
        } else {
            maKH = hoTen.substring(0, 2);
        }

        String year = Integer.toString(localDate.getYear()).substring(2);
        maKH += year;

        String randomString = generateRandomString(5);
        maKH += randomString;

        return maKH;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public NhanVienDTO getObjectFromInput() {
        NhanVienDTO dTO = new NhanVienDTO();
        String ten = txtHoTen.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        Long ngaySinh = jdNgaySinh.getDate().getTime();
        String diaChi = txtDiaChi.getText();
        String gioiTinh = cbxGioiTinh.getSelectedItem().toString();

        dTO.setTen(ten);
        dTO.setSdt(sdt);
        dTO.setEmail(email);
        dTO.setNgaySinh(ngaySinh);
        dTO.setDiaChi(diaChi);
        dTO.setGioiTinh(gioiTinh);
        switch (cbTrangThai.getSelectedIndex()) {
            case 0:
                dTO.setTrangThaiNhanVien(TrangThaiNhanVien.DANG_LAM);
                break;
            case 1:
                dTO.setTrangThaiNhanVien(TrangThaiNhanVien.DA_NGHI);
                break;
            default:
                throw new AssertionError();
        }
        Validator validator = NDTValidator.getValidator();
        Set<ConstraintViolation<NhanVienDTO>> violations = validator.validate(dTO);
        if (!violations.isEmpty()) {
            String errorMessages = "";
            for (ConstraintViolation<NhanVienDTO> violation : violations) {
                errorMessages += violation.getMessage() + "\n";
            }
            JOptionPane.showMessageDialog(this, errorMessages);
            return null;
        }
        if (lbMaNV.getText().isBlank()) {
            dTO.setMaNV(generateCustomerId(dTO));
        } else {
            dTO.setMaNV(lbMaNV.getText());
        }

        dTO.setId(nhanVienService.findId(dTO.getMaNV()));

        return dTO;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        heading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jdNgaySinh = new com.toedter.calendar.JDateChooser();
        btnSave = new view.swing.Button();
        btnExit = new view.swing.Button();
        cbxGioiTinh = new javax.swing.JComboBox<>();
        lbMaNV = new javax.swing.JLabel();
        cbTrangThai = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        heading.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        heading.setForeground(new java.awt.Color(76, 76, 76));
        heading.setText("Nhập dữ liệu");

        jLabel1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel1.setText("Mã NV");

        jLabel11.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel11.setText("Họ tên");

        jLabel12.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel12.setText("Email");

        jLabel13.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel13.setText("SDT");

        jLabel15.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel15.setText("Giới tính");

        jLabel16.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel16.setText("Địa chỉ");

        jLabel18.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel18.setText("Ngày sinh");

        btnSave.setBackground(new java.awt.Color(0, 102, 255));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu thay đổi");
        btnSave.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(0, 102, 255));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Thoát");
        btnExit.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        cbxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));

        lbMaNV.setForeground(new java.awt.Color(255, 0, 0));
        lbMaNV.setText("ê");

        cbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Đã nghỉ" }));

        jLabel17.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel17.setText("Trạng thái");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15))))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbMaNV)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(heading)
                            .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bgLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExit, btnSave});

        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(heading)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(174, 174, 174)))
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbMaNV)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        bgLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnExit, btnSave});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        NhanVienDTO dTO = getObjectFromInput();
        String result = nhanVienService.create(dTO);
        showMessage(result);
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(ModalNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModalNhanVien dialog = new ModalNhanVien(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel bg;
    private view.swing.Button btnExit;
    private view.swing.Button btnSave;
    private javax.swing.JComboBox<String> cbTrangThai;
    private javax.swing.JComboBox<String> cbxGioiTinh;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private com.toedter.calendar.JDateChooser jdNgaySinh;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
