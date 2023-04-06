package view.form.khachhang;

import comon.constant.khachhang.TrangThaiQuyDoi;
import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.utilities.DateTimeUtil;
import dto.khachhang.KhachHangDTO;
import dto.khachhang.LichSuTieuDiemDTO;
import dto.khachhang.LoaiTheDTO;
import dto.khachhang.QuyDoiDiemDTO;
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
import service.khachhang.QuyDoiDiemService;
import service.khachhang.TheThanhVienService;
import service.khachhang.ViDiemService;
import service.khachhang.impl.KhachHangServiceImpl;
import service.khachhang.impl.LichSuTieuDiemServiceImpl;
import service.khachhang.impl.LoaiTheServiceImpl;
import service.khachhang.impl.QuyDoiDiemServiceImpl;
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
    private final QuyDoiDiemService quyDoiDiemService;
    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long total;
    private final String maTheTV;
    private ModalQuyDoiDiem modalQuyDoiDiem;

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
        quyDoiDiemService = new QuyDoiDiemServiceImpl();
        this.maTheTV = maTheTV;
        loadDataTable();
        loadLabel();
        loadQuyDoiDiem();
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
    
    public final void loadQuyDoiDiem(){
        QuyDoiDiemDTO dTO = quyDoiDiemService.findById("7bdd597b-1baf-404b-8be7-7ff51c4425bc");
        modalQuyDoiDiem = new ModalQuyDoiDiem(null, true, dTO);
        lbTien.setText(dTO.getTienTichDiem()+"");
        lbDiem.setText("= "+dTO.getTienTieuDiem());
    }

    public final void loadLabel() {
        TheThanhVienDTO theThanhVienDTO = theThanhVienService.findByMaTTV(maTheTV);
        KhachHangDTO khachHangDTO = khachHangService.findByIdTheThanhVien(theThanhVienDTO.getId());
        ViDiemDTO viDiemDTO = viDiemService.findById(theThanhVienDTO.getViDiem().getId());

//        label panelThongTin
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
//        label panelPreview
//        front
        lbCardFront_id.setText("ID "+maTheTV);
//        back
        lbCardBack_loaiThe.setText(theThanhVienDTO.getLoaiThe().getTen());
        lbCardBack_ngayPhatHanh.setText(DateTimeUtil.formatDate(new Date(theThanhVienDTO.getNgayPhatHanh())));
        lbCardBack_ngayHetHan.setText(DateTimeUtil.formatDate(new Date(theThanhVienDTO.getNgayHetHan())));
        lbCardBack_maKH.setText(khachHangDTO.getMaKH());
        lbCardBack_id.setText("ID "+maTheTV);
        lbCardBack_ten.setText(khachHangDTO.getTen());
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

        lbHeading = new javax.swing.JLabel();
        panelLichSuTieuDiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLichSuTieuDiem = new view.swing.table.Table();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbPagination = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
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
        jLabel14 = new javax.swing.JLabel();
        lbTien = new javax.swing.JLabel();
        lbTrangThai2 = new javax.swing.JLabel();
        lbDiem = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        btnDoi = new view.swing.ButtonOutLine();
        panelPreview = new javax.swing.JPanel();
        lpPreviewBack = new javax.swing.JLayeredPane();
        lpBack = new javax.swing.JLayeredPane();
        lpContextBack = new javax.swing.JLayeredPane();
        lbCardBack_shop = new javax.swing.JLabel();
        lbCardBack_loaiThe = new javax.swing.JLabel();
        lbCardBack_ngayPhatHanh = new javax.swing.JLabel();
        lbCardBack_ngayHetHan = new javax.swing.JLabel();
        lbCardBack_maKH = new javax.swing.JLabel();
        lpCode = new javax.swing.JLayeredPane();
        lbCardBack = new javax.swing.JLabel();
        lbCardBack_maVach = new javax.swing.JLabel();
        lbCardBack_id = new javax.swing.JLabel();
        lbCardBack_ten = new javax.swing.JLabel();
        lbBgBack = new javax.swing.JLabel();
        lpPreviewFront = new javax.swing.JLayeredPane();
        lpFront = new javax.swing.JLayeredPane();
        lpContextFront = new javax.swing.JLayeredPane();
        lbCardFront_shop = new javax.swing.JLabel();
        lbCardFront = new javax.swing.JLabel();
        lbCardFront_id = new javax.swing.JLabel();
        lbBgFront = new javax.swing.JLabel();
        lbPreview = new javax.swing.JLabel();

        lbHeading.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbHeading.setForeground(new java.awt.Color(4, 72, 210));
        lbHeading.setText("Thẻ thành viên / Ví điểm");

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
                    .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(lbPagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTotal))
                    .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(lbTotal))
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

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Quy Đổi Điểm:");

        lbTien.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbTien.setForeground(new java.awt.Color(0, 0, 0));
        lbTien.setText("lbTien");

        lbTrangThai2.setBackground(new java.awt.Color(204, 102, 0));
        lbTrangThai2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        lbTrangThai2.setForeground(new java.awt.Color(255, 255, 255));
        lbTrangThai2.setText("VND");
        lbTrangThai2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
        lbTrangThai2.setOpaque(true);

        lbDiem.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        lbDiem.setForeground(new java.awt.Color(0, 0, 0));
        lbDiem.setText("=lbDiem");

        lb.setBackground(new java.awt.Color(0, 102, 153));
        lb.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Điểm");
        lb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        lb.setOpaque(true);

        btnDoi.setBackground(new java.awt.Color(255, 0, 0));
        btnDoi.setForeground(new java.awt.Color(255, 0, 0));
        btnDoi.setText("Đổi");
        btnDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThongTinKHLayout = new javax.swing.GroupLayout(panelThongTinKH);
        panelThongTinKH.setLayout(panelThongTinKHLayout);
        panelThongTinKHLayout.setHorizontalGroup(
            panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinKHLayout.createSequentialGroup()
                .addGap(22, 22, 22)
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
                    .addComponent(jLabel13)
                    .addGroup(panelThongTinKHLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTien)))
                .addGap(31, 31, 31)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinKHLayout.createSequentialGroup()
                        .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDiemDaDung)
                            .addComponent(lbTongDiem))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelThongTinKHLayout.createSequentialGroup()
                        .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongTinKHLayout.createSequentialGroup()
                                .addComponent(lbTrangThai2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb)
                                .addGap(18, 18, 18)
                                .addComponent(btnDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTenKH)
                            .addComponent(lbTrangThai)
                            .addComponent(lbDiemDaCong)
                            .addComponent(lbMaKH)
                            .addComponent(lbMaThe)
                            .addComponent(lbLoaiThe)
                            .addComponent(lbNgayHetHan)
                            .addComponent(lbNgayPhatHanh))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelThongTinKHLayout.setVerticalGroup(
            panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinKHLayout.createSequentialGroup()
                .addContainerGap()
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
                .addGap(9, 9, 9)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbTien)
                    .addComponent(lbTrangThai2)
                    .addComponent(lbDiem)
                    .addComponent(lb)
                    .addComponent(btnDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelPreview.setBackground(new java.awt.Color(255, 255, 255));
        panelPreview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbCardBack_shop.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCardBack_shop.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_shop.setText("NDT SHOP");

        lbCardBack_loaiThe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_loaiThe.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_loaiThe.setText("BRONZE");

        lbCardBack_ngayPhatHanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_ngayPhatHanh.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_ngayPhatHanh.setText("DD/MM/YY");

        lbCardBack_ngayHetHan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_ngayHetHan.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_ngayHetHan.setText("DD/MM/YY");

        lbCardBack_maKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_maKH.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_maKH.setText("XXXXXXXXX");

        lbCardBack.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lbCardBack.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardBack.setText("MEMBER CARD");

        lbCardBack_maVach.setBackground(new java.awt.Color(204, 204, 204));
        lbCardBack_maVach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_maVach.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_maVach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardBack_maVach.setText("Mã vạch");
        lbCardBack_maVach.setOpaque(true);

        lbCardBack_id.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lbCardBack_id.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardBack_id.setText("ID XXXXXXXXXXXXXXXXX");

        lpCode.setLayer(lbCardBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpCode.setLayer(lbCardBack_maVach, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpCode.setLayer(lbCardBack_id, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpCodeLayout = new javax.swing.GroupLayout(lpCode);
        lpCode.setLayout(lpCodeLayout);
        lpCodeLayout.setHorizontalGroup(
            lpCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpCodeLayout.createSequentialGroup()
                .addComponent(lbCardBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lbCardBack_maVach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCardBack_id, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );
        lpCodeLayout.setVerticalGroup(
            lpCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpCodeLayout.createSequentialGroup()
                .addComponent(lbCardBack)
                .addGap(0, 0, 0)
                .addComponent(lbCardBack_maVach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbCardBack_id))
        );

        lbCardBack_ten.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbCardBack_ten.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_ten.setText("Nguyễn Văn A");

        lpContextBack.setLayer(lbCardBack_shop, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextBack.setLayer(lbCardBack_loaiThe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextBack.setLayer(lbCardBack_ngayPhatHanh, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextBack.setLayer(lbCardBack_ngayHetHan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextBack.setLayer(lbCardBack_maKH, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextBack.setLayer(lpCode, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextBack.setLayer(lbCardBack_ten, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpContextBackLayout = new javax.swing.GroupLayout(lpContextBack);
        lpContextBack.setLayout(lpContextBackLayout);
        lpContextBackLayout.setHorizontalGroup(
            lpContextBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpContextBackLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(lpContextBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbCardBack_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lpContextBackLayout.createSequentialGroup()
                        .addGroup(lpContextBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCardBack_loaiThe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCardBack_ngayPhatHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCardBack_ngayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCardBack_maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCardBack_shop))
                        .addGap(46, 46, 46)
                        .addComponent(lpCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        lpContextBackLayout.setVerticalGroup(
            lpContextBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpContextBackLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lbCardBack_shop)
                .addGap(10, 10, 10)
                .addComponent(lbCardBack_ten)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCardBack_loaiThe, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lpContextBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpContextBackLayout.createSequentialGroup()
                        .addComponent(lbCardBack_ngayPhatHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCardBack_ngayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCardBack_maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lpCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        lbBgBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Member_Card_Back.png"))); // NOI18N

        lpBack.setVisible(true);

        lpBack.setLayer(lpContextBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpBack.setLayer(lbBgBack, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpBackLayout = new javax.swing.GroupLayout(lpBack);
        lpBack.setLayout(lpBackLayout);
        lpBackLayout.setHorizontalGroup(
            lpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
            .addGroup(lpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpBackLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lpContextBack)
                    .addContainerGap()))
            .addGroup(lpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpBackLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbBgBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        lpBackLayout.setVerticalGroup(
            lpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(lpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpBackLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lpContextBack)
                    .addContainerGap()))
            .addGroup(lpBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpBackLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbBgBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        lpPreviewBack.setLayer(lpBack, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpPreviewBackLayout = new javax.swing.GroupLayout(lpPreviewBack);
        lpPreviewBack.setLayout(lpPreviewBackLayout);
        lpPreviewBackLayout.setHorizontalGroup(
            lpPreviewBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lpBack)
        );
        lpPreviewBackLayout.setVerticalGroup(
            lpPreviewBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lpBack)
        );

        panelPreview.add(lpPreviewBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 33, -1, -1));

        lbCardFront_shop.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCardFront_shop.setForeground(new java.awt.Color(255, 255, 255));
        lbCardFront_shop.setText("NDT SHOP");

        lbCardFront.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCardFront.setForeground(new java.awt.Color(255, 255, 255));
        lbCardFront.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardFront.setText("MEMBER CARD");

        lbCardFront_id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCardFront_id.setForeground(new java.awt.Color(255, 255, 255));
        lbCardFront_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardFront_id.setText("ID XXXXXXXXXXXXXXXXX");

        lpContextFront.setLayer(lbCardFront_shop, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextFront.setLayer(lbCardFront, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpContextFront.setLayer(lbCardFront_id, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpContextFrontLayout = new javax.swing.GroupLayout(lpContextFront);
        lpContextFront.setLayout(lpContextFrontLayout);
        lpContextFrontLayout.setHorizontalGroup(
            lpContextFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpContextFrontLayout.createSequentialGroup()
                .addGroup(lpContextFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpContextFrontLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCardFront, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                    .addGroup(lpContextFrontLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCardFront_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(lpContextFrontLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(lbCardFront_shop)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lpContextFrontLayout.setVerticalGroup(
            lpContextFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpContextFrontLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbCardFront_shop)
                .addGap(42, 42, 42)
                .addComponent(lbCardFront)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCardFront_id)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        lbBgFront.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Member_Card_Front.png"))); // NOI18N

        lpBack.setVisible(true);

        lpFront.setLayer(lpContextFront, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpFront.setLayer(lbBgFront, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpFrontLayout = new javax.swing.GroupLayout(lpFront);
        lpFront.setLayout(lpFrontLayout);
        lpFrontLayout.setHorizontalGroup(
            lpFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
            .addGroup(lpFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpFrontLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lpContextFront)
                    .addContainerGap()))
            .addGroup(lpFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpFrontLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbBgFront, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        lpFrontLayout.setVerticalGroup(
            lpFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
            .addGroup(lpFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpFrontLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lpContextFront)
                    .addContainerGap()))
            .addGroup(lpFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lpFrontLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbBgFront, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        lpPreviewFront.setLayer(lpFront, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpPreviewFrontLayout = new javax.swing.GroupLayout(lpPreviewFront);
        lpPreviewFront.setLayout(lpPreviewFrontLayout);
        lpPreviewFrontLayout.setHorizontalGroup(
            lpPreviewFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lpFront)
        );
        lpPreviewFrontLayout.setVerticalGroup(
            lpPreviewFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lpFront)
        );

        panelPreview.add(lpPreviewFront, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        lbPreview.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbPreview.setForeground(new java.awt.Color(4, 72, 210));
        lbPreview.setText("Preview");
        panelPreview.add(lbPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbHeading)
                    .addComponent(panelLichSuTieuDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelThongTinKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelLichSuTieuDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiActionPerformed
        modalQuyDoiDiem.setVisible(true);
        modalQuyDoiDiem.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadQuyDoiDiem();
            }
        });
    }//GEN-LAST:event_btnDoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.ButtonOutLine btnDoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button button3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbBgBack;
    private javax.swing.JLabel lbBgFront;
    private javax.swing.JLabel lbCardBack;
    private javax.swing.JLabel lbCardBack_id;
    private javax.swing.JLabel lbCardBack_loaiThe;
    private javax.swing.JLabel lbCardBack_maKH;
    private javax.swing.JLabel lbCardBack_maVach;
    private javax.swing.JLabel lbCardBack_ngayHetHan;
    private javax.swing.JLabel lbCardBack_ngayPhatHanh;
    private javax.swing.JLabel lbCardBack_shop;
    private javax.swing.JLabel lbCardBack_ten;
    private javax.swing.JLabel lbCardFront;
    private javax.swing.JLabel lbCardFront_id;
    private javax.swing.JLabel lbCardFront_shop;
    private javax.swing.JLabel lbDiem;
    private javax.swing.JLabel lbDiemDaCong;
    private javax.swing.JLabel lbDiemDaDung;
    private javax.swing.JLabel lbHeading;
    private javax.swing.JLabel lbLoaiThe;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaThe;
    private javax.swing.JLabel lbNgayHetHan;
    private javax.swing.JLabel lbNgayPhatHanh;
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbPreview;
    private javax.swing.JLabel lbTenKH;
    private javax.swing.JLabel lbTien;
    private javax.swing.JLabel lbTongDiem;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JLabel lbTrangThai2;
    private javax.swing.JLayeredPane lpBack;
    private javax.swing.JLayeredPane lpCode;
    private javax.swing.JLayeredPane lpContextBack;
    private javax.swing.JLayeredPane lpContextFront;
    private javax.swing.JLayeredPane lpFront;
    private javax.swing.JLayeredPane lpPreviewBack;
    private javax.swing.JLayeredPane lpPreviewFront;
    private javax.swing.JPanel panelLichSuTieuDiem;
    private javax.swing.JPanel panelPreview;
    private javax.swing.JPanel panelThongTinKH;
    private view.swing.table.Table tbLichSuTieuDiem;
    // End of variables declaration//GEN-END:variables
}
