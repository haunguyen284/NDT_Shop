package view.form.dongiao;

import view.form.giamgia.*;
import comon.constant.ModelProperties;
import comon.constant.PaginationConstant;
import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.validator.NDTValidator;
import dto.dongiao.DonGiaoDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import model.dongiao.DonGiao;
import raven.cell.PanelAction;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import service.dongiao.DonGiaoService;
import service.dongiao.impl.DonGiaoServiceImpl;

@Getter
@Setter
public class ViewDonGiao extends javax.swing.JPanel {

    private final DefaultTableModel dtm;
    private final Validator validator;
    private int currentPage = 1;
    private int totalPage = 1;
    private final DonGiaoService donGiaoService;
    private final ModalDonGiao showThongTinDonGiao;

    public ViewDonGiao() {
        initComponents();
        tblDonGiao.fixTable(jScrollPane1);
        setOpaque(false);
        this.dtm = new DefaultTableModel();
        this.validator = NDTValidator.getValidator();
        this.donGiaoService = new DonGiaoServiceImpl();
        this.showThongTinDonGiao = new ModalDonGiao(null, true);
        cboYeuCau.insertItemAt("", 0);
        cboYeuCau.setSelectedIndex(0);
        cboTrangThai.insertItemAt("", 0);
        cboTrangThai.setSelectedIndex(0);
        loadData();
        createButton();
    }

    private void createButton() {
        TableActionEvent event;
        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                showThongTinDonGiao.getBtnSave().setVisible(true);
                showThongTinDonGiao.getTxtSave().setVisible(true);
                showThongTinDonGiao.getBtnSave().setText("Update");

                row = tblDonGiao.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblDonGiao.getValueAt(row, 0).toString();
                Optional<DonGiaoDTO> optional = donGiaoService.findByIdDuy(id);
                if (optional.isPresent()) {
                    showThongTinDonGiao.fill(optional.get());
                    showThongTinDonGiao.getDonGiaoDTO().setId(id);
                    showThongTinDonGiao.getDonGiaoDTO().setThongSo(optional.get().getThongSo());
                    showThongTinDonGiao.getDonGiaoDTO().setHoaDon(optional.get().getHoaDon());
                    showThongTinDonGiao.setVisible(true);
                    loadData();
                }
            }

            @Override
            public void onDelete(int row) {
            }

            @Override
            public void onView(int row) {
                showThongTinDonGiao.getBtnSave().setVisible(false);
                showThongTinDonGiao.getTxtSave().setVisible(false);
                row = tblDonGiao.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblDonGiao.getValueAt(row, 0).toString();
                Optional<DonGiaoDTO> optional = donGiaoService.findByIdDuy(id);
                if (optional.isPresent()) {
                    showThongTinDonGiao.fill(optional.get());
                    showThongTinDonGiao.getDonGiaoDTO().setId(id);
                    showThongTinDonGiao.setVisible(true);
                }
            }
        };
        tblDonGiao.getColumnModel().getColumn(11).setCellRenderer(new TableActionCellRender());
        tblDonGiao.getColumnModel().getColumn(11).setCellEditor(new TableActionCellEditor(event));
    }

    public void loadData() {
        String[] columns = {"ID", "MÃ ĐG", "MÃ HĐ", "TÊN KHÁCH HÀNG", "ĐỊA CHỈ ", "TIỀN THU HỘ", "NGÀY GIAO", "SĐT", "THÔNG SỐ", "YÊU CẦU", "TRẠNG THÁI", "CHỨC NĂNG"};
        dtm.setColumnIdentifiers(columns);
        tblDonGiao.setModel(dtm);
        String maDG = txtMaDG.getText();
        TrangThaiDonGiao trangThaiDonGiao = null;
        switch (cboTrangThai.getSelectedItem().toString()) {
            case "Chưa giao" ->
                trangThaiDonGiao = TrangThaiDonGiao.CHUA_GIAO;
            case "Đang giao" ->
                trangThaiDonGiao = TrangThaiDonGiao.DANG_GIAO;
            case "Đã giao" ->
                trangThaiDonGiao = TrangThaiDonGiao.DA_GIAO;
        }
        YeuCauDonHang yeuCauDonHang = null;
        switch (cboYeuCau.getSelectedItem().toString()) {
            case "Cho thử" ->
                yeuCauDonHang = YeuCauDonHang.CHO_THU;
            case "Cho xem" ->
                yeuCauDonHang = YeuCauDonHang.CHO_XEM;
            case "Không cho xem" ->
                yeuCauDonHang = YeuCauDonHang.KHONG_CHO_XEM;
        }
        showData(donGiaoService.getAll(currentPage, maDG, yeuCauDonHang, trangThaiDonGiao));
        showPaganation();
        createButton();
    }

    public void showData(List<DonGiaoDTO> list) {
        dtm.setRowCount(0);
        for (DonGiaoDTO x : list) {
            dtm.addRow(x.toDataRow());
        }
    }

    public void showPaganation() {
        long countTotalRow = donGiaoService.count();
        totalPage = (int) Math.ceil(Double.valueOf(countTotalRow) / Double.valueOf(PaginationConstant.DEFAULT_SIZE));
        if (totalPage == 1) {
            currentPage = 1;
            btnFirst.setEnabled(false);
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
        }
        lblPage.setText(currentPage + " of " + totalPage);
        lblCount.setText("Total " + countTotalRow);
    }

    private void clear() {
        cboTrangThai.setSelectedIndex(0);
        cboYeuCau.setSelectedIndex(0);
        txtMaDG.setText("");
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonGiao = new view.swing.table.Table();
        lblPage = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        btnSearch = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMaDG = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboYeuCau = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        btnLoc = new view.swing.Button();
        btnClear = new view.swing.Button();
        favicon1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Danh sách đơn giao");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách đơn giao");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tblDonGiao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Giá bán", "Số lượng tồn", "Màu sắc", "Chất liệu", "Cỡ", "Thương hiệu", "Xuất xứ"
            }
        ));
        tblDonGiao.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDonGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonGiaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonGiao);
        if (tblDonGiao.getColumnModel().getColumnCount() > 0) {
            tblDonGiao.getColumnModel().getColumn(0).setResizable(false);
            tblDonGiao.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        lblPage.setText("1/1");

        lblCount.setText("Total: 0");

        btnSearch.setBackground(new java.awt.Color(0, 102, 255));
        btnSearch.setBorder(null);
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtMaDG);

        jLabel9.setText("Mã");

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrevious.setText("<");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPage)
                        .addGap(11, 11, 11)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCount))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCount)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst)
                            .addComponent(btnPrevious)
                            .addComponent(btnNext)
                            .addComponent(btnLast)
                            .addComponent(lblPage))
                        .addContainerGap())))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel3.setText("Yêu cầu");

        cboYeuCau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cho thử", "Cho xem", "Không cho xem" }));

        jLabel4.setText("Trạng thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa giao", "Đang giao", "Đã giao" }));

        btnLoc.setBackground(new java.awt.Color(0, 102, 255));
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 102, 255));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(cboYeuCau, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 334, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboYeuCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        favicon1.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        favicon1.setIcon(new javax.swing.ImageIcon("D:\\DuAnMot\\NDT_Shop\\src\\main\\resources\\icon\\logo.png")); // NOI18N
        favicon1.setText("NDT_Shop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(favicon1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(favicon1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        currentPage = 1;
        btnNext.setEnabled(true);
        btnPrevious.setEnabled(false);
        loadData();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage <= 1) {
            btnPrevious.setEnabled(false);
            return;
        }
        currentPage--;
        btnNext.setEnabled(true);
        loadData();
        showPaganation();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage >= totalPage) {
            btnNext.setEnabled(false);
            return;
        }
        currentPage++;
        btnPrevious.setEnabled(true);
        loadData();
        showPaganation();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage;
        btnNext.setEnabled(false);
        btnPrevious.setEnabled(true);
        loadData();
        showPaganation();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblDonGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonGiaoMouseClicked
//        int row = tblGiamGia.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
//        String id = tblGiamGia.getValueAt(row, 0).toString();
//        Optional<GiamGiaDTO> optional = service.findByIdDuy(id);
//        if (optional.isPresent()) {
//            viewModal.fill(optional.get());
//            viewModal.setVisible(true);
//        }
    }//GEN-LAST:event_tblDonGiaoMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadData();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        loadData();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();    }//GEN-LAST:event_btnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnClear;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private view.swing.Button btnLoc;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button btnSearch;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboYeuCau;
    private javax.swing.JLabel favicon1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblPage;
    private view.swing.table.Table tblDonGiao;
    private javax.swing.JTextPane txtMaDG;
    // End of variables declaration//GEN-END:variables
}
