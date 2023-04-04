package view.form.khachhang;

import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.utilities.DateTimeUtil;
import dto.khachhang.KhachHangDTO;
import dto.khachhang.LichSuTieuDiemDTO;
import dto.khachhang.LoaiTheDTO;
import dto.khachhang.TheThanhVienDTO;
import dto.khachhang.ViDiemDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import service.khachhang.KhachHangService;
import service.khachhang.LichSuTieuDiemService;
import service.khachhang.LoaiTheService;
import service.khachhang.TheThanhVienService;
import service.khachhang.ViDiemService;
import service.khachhang.impl.KhachHangServiceImpl;
import service.khachhang.impl.LichSuTieuDiemServiceImpl;
import service.khachhang.impl.LoaiTheServiceImpl;
import service.khachhang.impl.TheThanhVienServiceImpl;
import service.khachhang.impl.ViDiemServiceImpl;
import view.dialog.Message;
import view.main.Main;

public class ViewTheThanhVienChiTiet extends javax.swing.JPanel {

    private final KhachHangService khachHangService;
    private final TheThanhVienService theThanhVienService;
    private final LoaiTheService loaiTheService;
    private final ViDiemService viDiemService;
    private final LichSuTieuDiemService lichSuTieuDiemService;
    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long total;
    private final String maTheTV;

    public ViewTheThanhVienChiTiet(String maTheTV) {
        initComponents();
        tbLichSuTieuDiem.fixTable(jScrollPane1);
        setOpaque(false);
        pageSize = 5;
        currentPage = 1;
        khachHangService = new KhachHangServiceImpl();
        theThanhVienService = new TheThanhVienServiceImpl();
        viDiemService = new ViDiemServiceImpl();
        loaiTheService = new LoaiTheServiceImpl();
        lichSuTieuDiemService = new LichSuTieuDiemServiceImpl();
        this.maTheTV = maTheTV;
//        loadDataTable();
        loadLabel();
    }

    public final void loadDataTable() {
        List<LichSuTieuDiemDTO> listDTOs = lichSuTieuDiemService.findAll(currentPage - 1, pageSize);
        DefaultTableModel dtm = (DefaultTableModel) tbLichSuTieuDiem.getModel();
        tbLichSuTieuDiem.setModel(dtm);
        dtm.setRowCount(0);
        for (LichSuTieuDiemDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
        lbTotal.setText("Total: " + lichSuTieuDiemService.totalCount());
        totalPages = (int) (total / pageSize) + 1;
        setStatePagination();
    }

    public final void loadLabel() {
        TheThanhVienDTO theThanhVienDTO = theThanhVienService.findByMaTTV(maTheTV);
        KhachHangDTO khachHangDTO = khachHangService.findByIdTheThanhVien(theThanhVienDTO.getId());
        ViDiemDTO viDiemDTO = viDiemService.findById(theThanhVienDTO.getViDiem().getId());

        lbTenKH.setText(khachHangDTO.getTen());
        lbMaKH.setText(khachHangDTO.getMaKH());
        lbMaThe.setText(theThanhVienDTO.getMaTTV());
        lbNgayPhatHanh.setText(DateTimeUtil.formatDate(new Date(theThanhVienDTO.getNgayPhatHanh())));
        lbNgayHetHan.setText(DateTimeUtil.formatDate(new Date(theThanhVienDTO.getNgayHetHan())));
        lbLoaiThe.setText(theThanhVienDTO.getLoaiThe().getTen());
        lbTongDiem.setText(viDiemDTO.getTongDiem() + "");
        lbDiemDaDung.setText(viDiemDTO.getDiemDaDung() + "");
        lbDiemDaCong.setText(viDiemDTO.getDiemDaCong() + "");
        lbTrangThai.setText(theThanhVienDTO.convertTrangThai());
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
        panelLichSuTieuDiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLichSuTieuDiem = new view.swing.table.Table();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbPagination = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        button3 = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        panelThongTinKH = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbTenKH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbMaThe = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbNgayPhatHanh = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbNgayHetHan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbDiemDaDung = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbDiemDaCong = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbLoaiThe = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbTongDiem = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbMaKH = new javax.swing.JLabel();
        panelPreview = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Thẻ thành viên / Ví điểm");

        panelLichSuTieuDiem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Lịch sử tiêu điểm");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbLichSuTieuDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày sử dụng", "Số điểm cộng", "Số điểm đã dùng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLichSuTieuDiem.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbLichSuTieuDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLichSuTieuDiemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbLichSuTieuDiem);

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

        javax.swing.GroupLayout panelLichSuTieuDiemLayout = new javax.swing.GroupLayout(panelLichSuTieuDiem);
        panelLichSuTieuDiem.setLayout(panelLichSuTieuDiemLayout);
        panelLichSuTieuDiemLayout.setHorizontalGroup(
            panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(lbPagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(lbTotal))
                    .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 604, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        panelLichSuTieuDiemLayout.setVerticalGroup(
            panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(lbPagination)
                    .addComponent(lbTotal)
                    .addComponent(jButton1))
                .addGap(11, 11, 11))
        );

        panelThongTinKH.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Tên KH");

        lbTenKH.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbTenKH.setForeground(new java.awt.Color(0, 0, 0));
        lbTenKH.setText("lbTenKH");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("Mã Thẻ");

        lbMaThe.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbMaThe.setForeground(new java.awt.Color(0, 0, 0));
        lbMaThe.setText("lbMaThe");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("Ngày phát hành");

        lbNgayPhatHanh.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbNgayPhatHanh.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayPhatHanh.setText("lbNgayPhatHanh");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("Ngày hết hạn");

        lbNgayHetHan.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbNgayHetHan.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayHetHan.setText("lbNgayHetHan");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setText("Điểm đã dùng");

        lbDiemDaDung.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbDiemDaDung.setForeground(new java.awt.Color(0, 0, 0));
        lbDiemDaDung.setText("lbDiemDaDung");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 255));
        jLabel8.setText("Điểm đã cộng");

        lbDiemDaCong.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbDiemDaCong.setForeground(new java.awt.Color(0, 0, 0));
        lbDiemDaCong.setText("lbDiemDaCong");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 255));
        jLabel10.setText("Loại thẻ");

        lbLoaiThe.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbLoaiThe.setForeground(new java.awt.Color(0, 0, 0));
        lbLoaiThe.setText("lbLoaiThe");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 255));
        jLabel11.setText("Tổng điểm");

        lbTongDiem.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbTongDiem.setForeground(new java.awt.Color(0, 0, 0));
        lbTongDiem.setText("lbTongDiem");

        lbTrangThai.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(0, 0, 0));
        lbTrangThai.setText("lbTrangThai");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 255));
        jLabel12.setText("Trạng Thái");

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 255));
        jLabel13.setText("Mã KH");

        lbMaKH.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbMaKH.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKH.setText("lbMaKH");

        javax.swing.GroupLayout panelThongTinKHLayout = new javax.swing.GroupLayout(panelThongTinKH);
        panelThongTinKH.setLayout(panelThongTinKHLayout);
        panelThongTinKHLayout.setHorizontalGroup(
            panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinKHLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13))
                .addGap(69, 69, 69)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTenKH)
                    .addComponent(lbMaThe)
                    .addComponent(lbTrangThai)
                    .addComponent(lbDiemDaCong)
                    .addComponent(lbDiemDaDung)
                    .addComponent(lbTongDiem)
                    .addComponent(lbLoaiThe)
                    .addComponent(lbNgayHetHan)
                    .addComponent(lbNgayPhatHanh)
                    .addComponent(lbMaKH))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelThongTinKHLayout.setVerticalGroup(
            panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinKHLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbTenKH))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbMaKH))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbMaThe))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbNgayPhatHanh))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbNgayHetHan))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbLoaiThe))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbTongDiem))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbDiemDaDung))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbDiemDaCong))
                .addGap(14, 14, 14)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbTrangThai))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        panelPreview.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPreviewLayout = new javax.swing.GroupLayout(panelPreview);
        panelPreview.setLayout(panelPreviewLayout);
        panelPreviewLayout.setHorizontalGroup(
            panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPreviewLayout.setVerticalGroup(
            panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLichSuTieuDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelThongTinKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLichSuTieuDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tbLichSuTieuDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLichSuTieuDiemMouseClicked

    }//GEN-LAST:event_tbLichSuTieuDiemMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button button3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lbDiemDaCong;
    private javax.swing.JLabel lbDiemDaDung;
    private javax.swing.JLabel lbLoaiThe;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaThe;
    private javax.swing.JLabel lbNgayHetHan;
    private javax.swing.JLabel lbNgayPhatHanh;
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbTenKH;
    private javax.swing.JLabel lbTongDiem;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JPanel panelLichSuTieuDiem;
    private javax.swing.JPanel panelPreview;
    private javax.swing.JPanel panelThongTinKH;
    private view.swing.table.Table tbLichSuTieuDiem;
    // End of variables declaration//GEN-END:variables
}
