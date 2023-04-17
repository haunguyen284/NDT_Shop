/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.sanpham;

import comon.constant.sanpham.TrangThaiQuanAo;
import dto.sanpham.SizeAoDTO;
import java.util.List;
import javax.swing.JOptionPane;
import service.sanpham.SizeAoService;
import service.sanpham.impl.SizeAoServiceImpl;
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;

/**
 *
 * @author nguyenth28
 */
public class DialogSizeAo extends javax.swing.JDialog {

    private final SizeAoService sizeAoService;
    public SizeAoDTO selectedSizeAo;

    /**
     * Creates new form DialogMauSac
     */
    public DialogSizeAo(java.awt.Frame parent, boolean modal, SizeAoDTO dto) {
        super(parent, modal);
        initComponents();
        sizeAoService = new SizeAoServiceImpl();
        loadDataTable();
        if (dto != null) fillPhanTu(dto);
    }
    
    private void fillPhanTu(SizeAoDTO dto){
        txtTen.setText(dto.getTen());
        txtMa.setText(dto.getMa());
        txtDaiMax.setText(dto.getDaiLonNhat());
        txtDaiMin.setText(dto.getDaiBeNhat());
        txtTayAoMax.setText(dto.getTayAoLonNhat());
        txtTayAoMin.setText(dto.getTayAoBeNhat());
        txtVaiMax.setText(dto.getRongVaiLonNhat());
        txtVaiMin.setText(dto.getRongVaiBeNhat());
    }

    private void loadDataTable() {
        TrangThaiQuanAo trangThai = null;
        if (cbbHienThi.getSelectedItem().toString().equals("ACTIVE")) {
            trangThai = TrangThaiQuanAo.ACTIVE;
        } else if (cbbHienThi.getSelectedItem().toString().equals("IN ACTIVE")) {
            trangThai = TrangThaiQuanAo.IN_ACTIVE;
        }
        List<SizeAoDTO> listDTO = sizeAoService.findAll(trangThai);
        tbHienThi.clearAllRow();
        for (SizeAoDTO dto : listDTO) {
            tbHienThi.addRow(new Object[]{
                dto.getId(),
                dto.getMa(),
                dto.getTen(),
                dto.getTrangThaiQuanAo() == TrangThaiQuanAo.ACTIVE ? "ACTIVE" : "IN ACTIVE"
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new view.swing.Button();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        btnSua = new view.swing.Button();
        btnChon = new view.swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new view.swing.table.Table();
        jLabel5 = new javax.swing.JLabel();
        cbbHienThi = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtVaiMin = new javax.swing.JTextField();
        txtVaiMax = new javax.swing.JTextField();
        txtDaiMin = new javax.swing.JTextField();
        txtDaiMax = new javax.swing.JTextField();
        txtTayAoMin = new javax.swing.JTextField();
        txtTayAoMax = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Mã");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("QUẢN LÝ SIZE ÁO");
        jLabel1.setToolTipText("");

        btnThem.setBackground(new java.awt.Color(0, 102, 255));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel4.setText("Trạng thái");

        jLabel3.setText("Tên");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "IN ACTIVE" }));

        btnSua.setBackground(new java.awt.Color(0, 102, 255));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnChon.setBackground(new java.awt.Color(0, 102, 255));
        btnChon.setForeground(new java.awt.Color(255, 255, 255));
        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHienThi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThi);

        jLabel5.setText("Hiển thị");

        cbbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "IN ACTIVE", "ALL" }));
        cbbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHienThiActionPerformed(evt);
            }
        });

        jLabel6.setText("Vai");

        jLabel7.setText("đến");

        jLabel8.setText("Dài");

        jLabel9.setText("đến");

        jLabel10.setText("Tay áo");

        jLabel11.setText("đến");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbHienThi, javax.swing.GroupLayout.Alignment.TRAILING, 0, 362, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtVaiMin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVaiMax, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDaiMin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDaiMax, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTayAoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTayAoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtVaiMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVaiMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtDaiMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDaiMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtTayAoMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTayAoMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private SizeAoDTO getDTOFromInput() {
        SizeAoDTO dto = new SizeAoDTO();
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String vaiMin = txtVaiMin.getText();
        String vaiMax = txtVaiMax.getText();
        String daiMin = txtDaiMin.getText();
        String daiMax = txtDaiMax.getText();
        String tayAoMin = txtTayAoMin.getText();
        String tayAoMax = txtTayAoMax.getText();
        TrangThaiQuanAo trangThai = TrangThaiQuanAo.IN_ACTIVE;
        if (cbbTrangThai.getSelectedIndex() == 0) {
            trangThai = TrangThaiQuanAo.ACTIVE;
        }
        dto.setMa(ma);
        dto.setTen(ten);
        dto.setDaiBeNhat(daiMin);
        dto.setDaiLonNhat(daiMax);
        dto.setRongVaiBeNhat(vaiMin);
        dto.setRongVaiLonNhat(vaiMax);
        dto.setTayAoBeNhat(tayAoMin);
        dto.setTayAoLonNhat(tayAoMax);
        dto.setTrangThaiQuanAo(trangThai);
        return dto;
    }

    private String getSelectedIdFromTable() {
        int selectedRow = tbHienThi.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        } else {
            return tbHienThi.getValueAt(selectedRow, 0).toString();
        }
    }

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        String selectedId = getSelectedIdFromTable();
        if (selectedId == null) {
            ShowMessage.show("Chưa chọn hàng");
            return;
        }
        selectedSizeAo = getDTOFromInput();
        selectedSizeAo.setId(selectedId);
        this.dispose();
    }//GEN-LAST:event_btnChonActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        int selectedRow = tbHienThi.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        String selectedId = getSelectedIdFromTable();
        String ma = tbHienThi.getValueAt(selectedRow, 1).toString();
        selectedSizeAo = sizeAoService.findByMa(ma);

        if (selectedSizeAo.getTrangThaiQuanAo() == TrangThaiQuanAo.ACTIVE) {
            cbbTrangThai.setSelectedIndex(0);
        } else {
            cbbTrangThai.setSelectedIndex(1);
        }
        txtMa.setText(ma);
        txtTen.setText(selectedSizeAo.getTen());
        txtDaiMax.setText(selectedSizeAo.getDaiLonNhat());
        txtDaiMin.setText(selectedSizeAo.getDaiBeNhat());
        txtTayAoMax.setText(selectedSizeAo.getTayAoLonNhat());
        txtTayAoMin.setText(selectedSizeAo.getTayAoBeNhat());
        txtVaiMax.setText(selectedSizeAo.getRongVaiLonNhat());
        txtVaiMin.setText(selectedSizeAo.getRongVaiBeNhat());

        if (evt.getClickCount() == 2) {
            selectedSizeAo = getDTOFromInput();
            selectedSizeAo.setId(selectedId);
            this.dispose();
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SizeAoDTO dto = getDTOFromInput();
        String result = sizeAoService.create(dto);
        if(result.contains("thành công")){
            ShowMessageSuccessful.showSuccessful(result);
            loadDataTable();
            return;
        }
        ShowMessage.show(result);

        loadDataTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cbbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHienThiActionPerformed
        loadDataTable();
    }//GEN-LAST:event_cbbHienThiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String selectedId = getSelectedIdFromTable();
        if (selectedId == null) {
            return;
        }
        SizeAoDTO dto = getDTOFromInput();
        dto.setId(selectedId);
        String result = sizeAoService.update(dto);
        if(result.contains("thành công")){
            ShowMessageSuccessful.showSuccessful(result);
            loadDataTable();
            return;
        }
        ShowMessage.show(result);

        loadDataTable();
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnChon;
    private view.swing.Button btnSua;
    private view.swing.Button btnThem;
    private javax.swing.JComboBox<String> cbbHienThi;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private view.swing.table.Table tbHienThi;
    private javax.swing.JTextField txtDaiMax;
    private javax.swing.JTextField txtDaiMin;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTayAoMax;
    private javax.swing.JTextField txtTayAoMin;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtVaiMax;
    private javax.swing.JTextField txtVaiMin;
    // End of variables declaration//GEN-END:variables
}
