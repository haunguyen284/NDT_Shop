/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.sanpham;

import comon.constant.sanpham.LoaiSanPham;
import comon.constant.sanpham.TrangThaiSanPham;
import comon.utilities.MyPrintable;
import dto.sanpham.AoDTO;
import dto.sanpham.ChatLieuDTO;
import dto.sanpham.MauSacDTO;
import dto.sanpham.SanPhamDTO;
import dto.sanpham.SizeAoDTO;
import dto.sanpham.ThuongHieuDTO;
import dto.sanpham.XuatXuDTO;
import java.awt.Frame;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import service.sanpham.AoService;
import service.sanpham.SanPhamService;
import service.sanpham.impl.AoServiceImpl;
import service.sanpham.impl.SanPhamServiceImpl;
import view.dialog.ShowMessage;

/**
 *
 * @author nguyenth28
 */
public class DemoPrint extends javax.swing.JDialog {

    private MauSacDTO selectedMau;
    private ChatLieuDTO selectedChatLieu;
    private ThuongHieuDTO selectedThuongHieu;
    private XuatXuDTO selectedXuatXu;
    private SizeAoDTO selectedSizeAo;
    private AoService aoService;
    private SanPhamService sanPhamService;
    private boolean isUpdate;
    private AoDTO updateDTO;

    /**
     * Creates new form DialogAo
     */
    public DemoPrint(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        aoService = new AoServiceImpl();
        sanPhamService = new SanPhamServiceImpl();
        isUpdate = false;
        lbTitle.setText("THÊM MỚI ÁO");
    }

    public DemoPrint(java.awt.Frame parent, boolean modal, AoDTO aoDTO) {
        super(parent, modal);
        initComponents();
        aoService = new AoServiceImpl();
        sanPhamService = new SanPhamServiceImpl();
        selectedMau = aoDTO.getSanPham().getMauSac();
        selectedChatLieu = aoDTO.getSanPham().getChatLieu();
        selectedThuongHieu = aoDTO.getSanPham().getThuongHieu();
        selectedXuatXu = aoDTO.getSanPham().getXuatXu();
        selectedSizeAo = aoDTO.getSizeAo();
        loadLabelSelected();
        txtGiaBan.setText(aoDTO.getSanPham().getGiaBan());
        txtGiaNhap.setText(aoDTO.getSanPham().getGiaNhap());
        txtMa.setText(aoDTO.getSanPham().getMaSP());
        txtNoiDungIn.setText(aoDTO.getSanPham().getMoTa());
        txtSoLuong.setText(aoDTO.getSanPham().getSoLuongTon());
        txtTen.setText(aoDTO.getSanPham().getTenSP());
        isUpdate = true;
        updateDTO = aoDTO;
        lbTitle.setText("CẬP NHẬT ÁO");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoiDungIn = new javax.swing.JTextArea();
        lbMau = new javax.swing.JLabel();
        lbChatLieu = new javax.swing.JLabel();
        lbThuongHieu = new javax.swing.JLabel();
        lbXuatXu = new javax.swing.JLabel();
        lbKichCo = new javax.swing.JLabel();
        btnHuy = new view.swing.Button();
        btnLuu = new view.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(0, 102, 255));
        lbTitle.setText("CHI TIẾT SẢN PHẨM");
        lbTitle.setToolTipText("");

        txtNoiDungIn.setColumns(20);
        txtNoiDungIn.setRows(5);
        txtNoiDungIn.setText("**************** Hoá đơn ******************\nTên sản phẩm: Lê Văn Cặc\nNgười bán: Tuấn\nNgười mua: Cặc\nĐịa chỉ: Hà Nội\nTổng tiền: 100000");
        jScrollPane2.setViewportView(txtNoiDungIn);

        btnHuy.setBackground(new java.awt.Color(255, 51, 51));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(0, 153, 0));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu lại");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(lbTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMau)
                            .addComponent(lbChatLieu)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbThuongHieu)
                                    .addComponent(lbXuatXu)
                                    .addComponent(lbKichCo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(146, 146, 146)
                                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbTitle)
                .addGap(0, 0, 0)
                .addComponent(lbMau)
                .addGap(18, 18, 18)
                .addComponent(lbChatLieu)
                .addGap(18, 18, 18)
                .addComponent(lbThuongHieu)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lbXuatXu)
                        .addGap(41, 41, 41)
                        .addComponent(lbKichCo))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            txtNoiDungIn.print();
        } catch (PrinterException ex) {
            Logger.getLogger(DemoPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void loadLabelSelected() {
        lbXuatXu.setText(selectedXuatXu != null ? selectedXuatXu.getTen() : "");
        lbChatLieu.setText(selectedChatLieu != null ? selectedChatLieu.getTen() : "");
        lbMau.setText(selectedMau != null ? selectedMau.getTen() : "");
        lbThuongHieu.setText(selectedThuongHieu != null ? selectedThuongHieu.getTen() : "");
        lbKichCo.setText(selectedSizeAo != null ? selectedSizeAo.getTen() : "");
    }
    public static void main(String[] args) {
        new DemoPrint(null, true).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnHuy;
    private view.swing.Button btnLuu;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbChatLieu;
    private javax.swing.JLabel lbKichCo;
    private javax.swing.JLabel lbMau;
    private javax.swing.JLabel lbThuongHieu;
    public javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbXuatXu;
    private javax.swing.JTextArea txtNoiDungIn;
    // End of variables declaration//GEN-END:variables
}