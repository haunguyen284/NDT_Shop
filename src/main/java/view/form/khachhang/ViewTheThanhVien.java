package view.form.khachhang;

import comon.constant.khachhang.TrangThaiKhachHang;
import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.constant.khachhang.TrangThaiViDiem;
import comon.utilities.DateTimeUtil;
import dto.khachhang.KhachHangDTO;
import dto.khachhang.LoaiTheDTO;
import dto.khachhang.TheThanhVienDTO;
import dto.khachhang.ViDiemDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
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
import view.main.Main;

public class ViewTheThanhVien extends javax.swing.JPanel {

    private final KhachHangService khachHangService;
    private final TheThanhVienService theThanhVienService;
    private final LoaiTheService loaiTheService;
    private final ViDiemService viDiemService;
    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long totalKhachHang;
    private final ModalKhachHang modalKhachHang;

    public ViewTheThanhVien() {
        initComponents();
        tbTheThanhVien.fixTable(jScrollPane1);
        setOpaque(false);
        pageSize = 5;
        currentPage = 1;
        khachHangService = new KhachHangServiceImpl();
        theThanhVienService = new TheThanhVienServiceImpl();
        viDiemService = new ViDiemServiceImpl();
        loaiTheService = new LoaiTheServiceImpl();
        modalKhachHang = new ModalKhachHang(null, true);
        loadDataTable();
    }

    public void loadDataTable() {
        List<TheThanhVienDTO> listDTOs = theThanhVienService.findAll(currentPage - 1, pageSize);
        DefaultTableModel dtm = (DefaultTableModel) tbTheThanhVien.getModel();
        tbTheThanhVien.setModel(dtm);
        dtm.setRowCount(0);
        for (TheThanhVienDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
        lbTotal.setText("Total: " + khachHangService.totalCount());
        totalPages = (int) (totalKhachHang / pageSize) + 1;
        setStatePagination();
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
        btnThemMoi = new view.swing.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jSpinner3 = new javax.swing.JSpinner();
        button2 = new view.swing.Button();
        button5 = new view.swing.Button();
        button6 = new view.swing.Button();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Khách hàng");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách khách hàng");
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 561, Short.MAX_VALUE)
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
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(lbPagination)
                    .addComponent(lbTotal)
                    .addComponent(jButton1))
                .addGap(11, 11, 11))
        );

        btnThemMoi.setBackground(new java.awt.Color(0, 102, 255));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel2.setText("Rộng vai");

        jLabel3.setText("Màu sắc");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Dài");

        jLabel4.setText("Chất liệu");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Tay áo");

        jLabel7.setText("Thương hiệu");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        button2.setBackground(new java.awt.Color(0, 102, 255));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Lọc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner2)
                    .addComponent(jComboBox2, 0, 158, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, 151, Short.MAX_VALUE)
                    .addComponent(jSpinner3))
                .addGap(37, 37, 37)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        button5.setBackground(new java.awt.Color(0, 102, 255));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setText("Nhập Excel");

        button6.setBackground(new java.awt.Color(0, 102, 255));
        button6.setForeground(new java.awt.Color(255, 255, 255));
        button6.setText("Xuất Excel");

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
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                        .addGap(290, 290, 290)
                        .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addGap(69, 69, 69)
                        .addComponent(button6, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addGap(112, 112, 112))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        modalKhachHang.clear();
        modalKhachHang.setVisible(true);
        modalKhachHang.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadDataTable();
            }
        });
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void tbTheThanhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTheThanhVienMouseClicked
        int row = this.tbTheThanhVien.getSelectedRow();
        if (row < 0) {
            return;
        }
        KhachHangDTO dTO = getObjectsFromTable(row);
        modalKhachHang.fill(dTO);
        modalKhachHang.setVisible(true);
        modalKhachHang.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadDataTable();
            }
        });
    }//GEN-LAST:event_tbTheThanhVienMouseClicked

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
    private view.swing.Button btnThemMoi;
    private view.swing.Button button2;
    private view.swing.Button button3;
    private view.swing.Button button5;
    private view.swing.Button button6;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbTotal;
    private view.swing.table.Table tbTheThanhVien;
    // End of variables declaration//GEN-END:variables
}
