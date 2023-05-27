package view.form.hoadon;

import view.form.giamgia.*;
import comon.constant.ModelProperties;
import comon.constant.PaginationConstant;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import dto.giamgia.SanPhamGiamGiaDTO;
import dto.hoadon.HoaDonDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import service.HoaDon.impl.HoaDonServiceImpl;
import service.hoaDon.HoaDonService;
import view.dialog.Message;
import view.main.Main;
import view.model.ModelStudent;
import view.swing.table.EventAction;

@Getter
@Setter
public class ViewLichSuHoaDon extends javax.swing.JPanel {
    
    private final DefaultTableModel dtm;
    private final Validator validator;
    private final HoaDonService hoaDonService;
    private int currentPage = 1;
    private int totalPage = 1;
    
    public ViewLichSuHoaDon() {
        initComponents();
        tblLichSuHoaDon.fixTable(jScrollPane1);
        setOpaque(false);
        initData();
        this.dtm = new DefaultTableModel();
        this.validator = NDTValidator.getValidator();
        this.hoaDonService = new HoaDonServiceImpl();
        loadData();
    }

    private void initData() {
        initTableData();
    }
    
    private void loadData() {
        String[] columns = {"MÃ", "TÊN KHÁCH HÀNG", "TÊN NHÂN VIÊN ", "NGÀY THANH TOÁN ", "TỔNG TIỀN", "TRẠNG THÁI"};
        dtm.setColumnIdentifiers(columns);
        tblLichSuHoaDon.setModel(dtm);
        showPaganation();
        showData(hoaDonService.getAllLichSuHoaDon(currentPage));
    }

    private void showData(List<HoaDonDTO> lists) {
        dtm.setRowCount(0);
        for (HoaDonDTO x : lists) {
            dtm.addRow(x.toDataRowLichSu());
        }
    }
    
    private void showPaganation() {
        long countTotalRow = hoaDonService.totalCount();
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
    
    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(ModelStudent student) {
                if (showMessage("Delete Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }
            
            @Override
            public void update(ModelStudent student) {
                if (showMessage("Update Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }
        };
        
    }
    
    public void loadDataSearch(List<SanPhamGiamGiaDTO> list) {
        dtm.setRowCount(0);
        for (SanPhamGiamGiaDTO sanPhamGiamGiaDTO : list) {
            dtm.addRow(sanPhamGiamGiaDTO.toDataRow());
        }
        showPaganation();
    }
    
    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new view.swing.table.Table();
        lblPage = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        btnSearch = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSearch = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        favicon1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Hóa đơn/Lịch sử hóa đơn");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách lịch sử hóa đơn");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tblLichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Giá bán", "Số lượng tồn", "Màu sắc", "Chất liệu", "Cỡ", "Thương hiệu", "Xuất xứ"
            }
        ));
        tblLichSuHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLichSuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLichSuHoaDon);
        if (tblLichSuHoaDon.getColumnModel().getColumnCount() > 0) {
            tblLichSuHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tblLichSuHoaDon.getColumnModel().getColumn(0).setPreferredWidth(15);
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

        jScrollPane2.setViewportView(txtSearch);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 650, Short.MAX_VALUE)
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
                    .addComponent(jLabel5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(favicon1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(favicon1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage >= totalPage) {
            btnNext.setEnabled(false);
            return;
        }
        currentPage++;
        btnPrevious.setEnabled(true);
        loadData();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage;
        btnNext.setEnabled(false);
        btnPrevious.setEnabled(true);
        loadData();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblLichSuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuHoaDonMouseClicked
//        int row = tblGiamGia.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
//        String id = tblGiamGia.getValueAt(row, 0).toString();
//        Optional<GiamGiaDTO> optional = service.findById(id);
//        if (optional.isPresent()) {
//            viewModal.fill(optional.get());
//            viewModal.setVisible(true);
//        }
    }//GEN-LAST:event_tblLichSuHoaDonMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchByMa = txtSearch.getText();
        List<HoaDonDTO> listSearchByMa = hoaDonService.searchByMa(currentPage, searchByMa);
        showData(listSearchByMa);

    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button btnSearch;
    private javax.swing.JLabel favicon1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblPage;
    private view.swing.table.Table tblLichSuHoaDon;
    private javax.swing.JTextPane txtSearch;
    // End of variables declaration//GEN-END:variables
}
