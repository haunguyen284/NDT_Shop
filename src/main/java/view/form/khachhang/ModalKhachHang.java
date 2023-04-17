/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.khachhang;

import comon.constant.khachhang.TrangThaiKhachHang;
import comon.validator.NDTValidator;
import dto.khachhang.KhachHangDTO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import service.khachhang.KhachHangService;
import service.khachhang.TheThanhVienService;
import service.khachhang.impl.KhachHangServiceImpl;
import service.khachhang.impl.TheThanhVienServiceImpl;
import view.dialog.Message;
import view.dialog.ShowMessageSuccessful;
import view.main.Main;

/**
 *
 * @author Admin
 */
public class ModalKhachHang extends javax.swing.JDialog {

    private final KhachHangService khachHangService;
    private final TheThanhVienService theThanhVienService;

    /**
     * Creates new form ModalKhachHang
     */
    public ModalKhachHang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        khachHangService = new KhachHangServiceImpl();
        theThanhVienService = new TheThanhVienServiceImpl();
        initComponents();
    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    public void fill(KhachHangDTO dTO) {
        lbMaKH.setText(dTO.getMaKH());
        lbMaTTV.setText(Objects.isNull(dTO.getTheThanhVien()) ? "" : dTO.getTheThanhVien().getMaTTV());
        txtHoTen.setText(dTO.getTen());
        txtSDT.setText(dTO.getSdt());
        txtEmail.setText(dTO.getEmail());
        jdNgaySinh.setDate(new Date(dTO.getNgaySinh()));
        txtDiaChi.setText(dTO.getDiaChi());
        cbxGioiTinh.setSelectedItem(dTO.getGioiTinh());
        txtGhiChu.setText(dTO.getGhiChu());
        txtSoLanMua.setText(dTO.getSoLanMua() + "");
        cbxTrangThai.setSelectedItem(dTO.getTrangThaiKhachHang());
    }

    public void clear() {
        lbMaKH.setText("");
        lbMaTTV.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        jdNgaySinh.setDate(new Date());
        txtDiaChi.setText("");
        cbxGioiTinh.setSelectedIndex(0);
        txtGhiChu.setText("");
        txtSoLanMua.setText("");
        cbxTrangThai.setSelectedIndex(0);
    }

    public String generateCustomerId(KhachHangDTO khachHang) {
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

    public KhachHangDTO getObjectFromInput() {
        KhachHangDTO dTO = new KhachHangDTO();
        String ten = txtHoTen.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        Long ngaySinh = jdNgaySinh.getDate().getTime();
        String diaChi = txtDiaChi.getText();
        String gioiTinh = cbxGioiTinh.getSelectedItem().toString();
        String ghiChu = txtGhiChu.getText();
        String soLanMua = txtSoLanMua.getText();
        String trangThai = cbxTrangThai.getSelectedItem().toString();

        dTO.setTen(ten);
        dTO.setSdt(sdt);
        dTO.setEmail(email);
        dTO.setNgaySinh(ngaySinh);
        dTO.setDiaChi(diaChi);
        dTO.setGioiTinh(gioiTinh);
        dTO.setGhiChu(ghiChu);
        dTO.setSoLanMua(soLanMua.equals("") ? 0 : Integer.parseInt(soLanMua));
        Validator validator = NDTValidator.getValidator();
        Set<ConstraintViolation<KhachHangDTO>> violations = validator.validate(dTO);
        if (!violations.isEmpty()) {
            String errorMessages = "";
            for (ConstraintViolation<KhachHangDTO> violation : violations) {
                errorMessages += violation.getMessage() + "\n";
            }
            JOptionPane.showMessageDialog(this, errorMessages);
            return null;
        }
        if (lbMaKH.getText().isBlank()) {
            dTO.setMaKH(generateCustomerId(dTO));
        } else {
            dTO.setMaKH(lbMaKH.getText());
        }
        if (!lbMaTTV.getText().isBlank()) {
            dTO.setTheThanhVien(theThanhVienService.findByMaTTV(lbMaTTV.getText()));
        }
        dTO.setId(khachHangService.findId(dTO.getMaKH()));
        switch (trangThai) {
            case "Khách hàng mới":
                dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_1);
                break;
            case "Đã là thành viên":
                dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_2);
                break;
            case "Đã huỷ":
                dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_3);
                break;
        }
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
        jLabel14 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        txtSoLanMua = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jdNgaySinh = new com.toedter.calendar.JDateChooser();
        cbxTrangThai = new javax.swing.JComboBox<>();
        btnSave = new view.swing.Button();
        btnExit = new view.swing.Button();
        cbxGioiTinh = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lbMaKH = new javax.swing.JLabel();
        lbMaTTV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        heading.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        heading.setForeground(new java.awt.Color(76, 76, 76));
        heading.setText("Nhập dữ liệu");

        jLabel1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel1.setText("Mã KH");

        jLabel11.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel11.setText("Họ tên");

        jLabel12.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel12.setText("Email");

        jLabel13.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel13.setText("SDT");

        jLabel14.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel14.setText("Ghi chú");

        jLabel15.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel15.setText("Giới tính");

        jLabel16.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel16.setText("Địa chỉ");

        jLabel17.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel17.setText("Số lần mua");

        jLabel18.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel18.setText("Ngày sinh");

        jLabel19.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel19.setText("Trạng thái");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách hàng mới", "Đã là thành viên", "Đã huỷ" }));

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

        jLabel2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel2.setText("Mã Thẻ TV");

        lbMaKH.setForeground(new java.awt.Color(255, 0, 0));

        lbMaTTV.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbxTrangThai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jdNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(bgLayout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtGhiChu)
                                                    .addComponent(txtSoLanMua, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel14)
                                                    .addComponent(jLabel17))))
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15))))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(43, 43, 43)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbMaKH)
                                    .addComponent(lbMaTTV))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(heading))
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
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel1)
                    .addComponent(lbMaKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lbMaTTV)))
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addComponent(cbxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLanMua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        bgLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnExit, btnSave});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        KhachHangDTO dTO = getObjectFromInput();
        if(Objects.isNull(dTO)) return;
        String result = khachHangService.save(dTO);
        this.dispose();
        if(result.equals("Cập nhật thành công")){
            ShowMessageSuccessful.showSuccessful(result);
        }else{
            showMessage(result);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        new ViewKhachHang().loadDataTable();
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private view.swing.Button btnExit;
    private view.swing.Button btnSave;
    private javax.swing.JComboBox<String> cbxGioiTinh;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private com.toedter.calendar.JDateChooser jdNgaySinh;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaTTV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLanMua;
    // End of variables declaration//GEN-END:variables
}
