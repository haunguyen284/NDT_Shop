package view.form.khachhang;

import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.utilities.DateTimeUtil;
import comon.utilities.QrScanner;
import dto.khachhang.LoaiTheDTO;
import dto.khachhang.TheThanhVienDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import service.khachhang.KhachHangService;
import service.khachhang.LoaiTheService;
import service.khachhang.TheThanhVienService;
import service.khachhang.ViDiemService;
import service.khachhang.impl.KhachHangServiceImpl;
import service.khachhang.impl.LoaiTheServiceImpl;
import service.khachhang.impl.TheThanhVienServiceImpl;
import service.khachhang.impl.ViDiemServiceImpl;
import view.dialog.Message;
import view.form.MainForm;
import view.main.Main;

public class ViewTheThanhVien extends javax.swing.JPanel {

    private final KhachHangService khachHangService;
    private final TheThanhVienService theThanhVienService;
    private final LoaiTheService loaiTheService;
    private final ViDiemService viDiemService;
    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long total;
    private final MainForm main;

    public ViewTheThanhVien(MainForm main) {
        initComponents();
        tbTheThanhVien.fixTable(jScrollPane1);
        setOpaque(false);
        pageSize = 5;
        currentPage = 1;
        khachHangService = new KhachHangServiceImpl();
        theThanhVienService = new TheThanhVienServiceImpl();
        viDiemService = new ViDiemServiceImpl();
        loaiTheService = new LoaiTheServiceImpl();
        loadDataTable();
        loadDataTableLoaiThe();
        this.main = main;
    }

    public final void loadDataTable() {
        List<TheThanhVienDTO> listDTOs = theThanhVienService.findAll(currentPage - 1, pageSize);
        DefaultTableModel dtm = (DefaultTableModel) tbTheThanhVien.getModel();
        tbTheThanhVien.setModel(dtm);
        dtm.setRowCount(0);
        for (TheThanhVienDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
        lbTotal.setText("Total: " + theThanhVienService.totalCount());
        totalPages = (int) (total / pageSize) + 1;
        setStatePagination();
    }

    public final void loadDataTableLoaiThe() {
        List<LoaiTheDTO> listDTOs = loaiTheService.findAll(currentPage - 1, pageSize);
        DefaultTableModel dtm = (DefaultTableModel) tbLoaiThe.getModel();
        tbLoaiThe.setModel(dtm);
        dtm.setRowCount(0);
        for (LoaiTheDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
    }

    private void setStatePagination() {
        btnPrevious.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < totalPages);
        lbPagination.setText(currentPage + "/" + totalPages);
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
        tbTheThanhVien = new view.swing.table.Table();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbPagination = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        button3 = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        button1 = new view.swing.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLoaiThe = new view.swing.table.Table();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Thẻ thành viên");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách thẻ thành viên");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbTheThanhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thẻ TV", "Ngày phát hành", "Ngày hết hạn", "Loại thẻ", "Ví điểm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTheThanhVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbTheThanhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTheThanhVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTheThanhVien);
        if (tbTheThanhVien.getColumnModel().getColumnCount() > 0) {
            tbTheThanhVien.getColumnModel().getColumn(4).setHeaderValue("Ví điểm");
            tbTheThanhVien.getColumnModel().getColumn(5).setHeaderValue("Trạng thái");
        }

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

        lbPagination.setText("1/1");

        lbTotal.setText("Total: 0");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hiển thị sản phẩm ẩn");

        button3.setBackground(new java.awt.Color(0, 102, 255));
        button3.setBorder(null);
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Tìm kiếm");

        jScrollPane2.setViewportView(jTextPane1);

        jLabel9.setText("Mã ");

        button1.setBackground(new java.awt.Color(0, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Loz Duy");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
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
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(lbPagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(lbTotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(lbPagination)
                    .addComponent(lbTotal)
                    .addComponent(jButton1))
                .addGap(11, 11, 11))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(76, 76, 76));
        jLabel10.setText("Danh sách loại thẻ");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbLoaiThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Giá trị", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLoaiThe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbLoaiThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLoaiTheMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbLoaiThe);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(9, 919, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage > 1) {
            currentPage--;
        }
        loadDataTable();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
        }
        loadDataTable();
    }//GEN-LAST:event_btnNextActionPerformed

    private void tbTheThanhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTheThanhVienMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tbTheThanhVien.getSelectedRow();
            main.showForm(new ViewTheThanhVienChiTiet(tbTheThanhVien.getValueAt(row, 0).toString()));
            // Xử lý khi double click vào row của JTable
        }
    }//GEN-LAST:event_tbTheThanhVienMouseClicked

    private void tbLoaiTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLoaiTheMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLoaiTheMouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        QrScanner qrScanner = new QrScanner(null, true);
        qrScanner.setVisible(true);
        qrScanner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                main.showForm(new ViewTheThanhVienChiTiet(qrScanner.getQrString()));
            }
        });
    }//GEN-LAST:event_button1ActionPerformed

    private TheThanhVienDTO getObjectsFromTable(int row) throws NumberFormatException {
        String maTTV = this.tbTheThanhVien.getValueAt(row, 0).toString();
        String ngayPhatHanh = this.tbTheThanhVien.getValueAt(row, 1).toString();
        String ngayHetHan = this.tbTheThanhVien.getValueAt(row, 2).toString();
        String loaiThe = this.tbTheThanhVien.getValueAt(row, 3).toString();
        String viDiem = this.tbTheThanhVien.getValueAt(row, 4).toString();
        String trangThai = this.tbTheThanhVien.getValueAt(row, 5).toString();
        TheThanhVienDTO dTO = new TheThanhVienDTO();
        dTO.setMaTTV(maTTV);
        dTO.setNgayPhatHanh(DateTimeUtil.stringToDate(ngayPhatHanh).getTime());
        dTO.setNgayHetHan(DateTimeUtil.stringToDate(ngayHetHan).getTime());
        dTO.setLoaiThe(loaiTheService.findByTen(loaiThe));
        dTO.setViDiem(viDiemService.findById(viDiem));
        switch (trangThai) {
            case "Đang sử dụng":
                dTO.setTrangThaiTheThanhVien(TrangThaiTheThanhVien.DANG_SU_DUNG);
                break;
            case "Hết hạn":
                dTO.setTrangThaiTheThanhVien(TrangThaiTheThanhVien.HET_HAN);
                break;
        }
        return dTO;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button button1;
    private view.swing.Button button3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbTotal;
    private view.swing.table.Table tbLoaiThe;
    private view.swing.table.Table tbTheThanhVien;
    // End of variables declaration//GEN-END:variables
}
