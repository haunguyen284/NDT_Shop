package view.form.khachhang;

import comon.utilities.DateTimeUtil;
import comon.utilities.QRCodeGenerator;
import dto.khachhang.KhachHangDTO;
import dto.khachhang.LichSuTieuDiemDTO;
import dto.khachhang.QuyDoiDiemDTO;
import dto.khachhang.TheThanhVienDTO;
import dto.khachhang.ViDiemDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
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
        TheThanhVienDTO theThanhVienDTO = theThanhVienService.findByMaTTV(maTheTV);
        ViDiemDTO viDiemDTO = viDiemService.findById(theThanhVienDTO.getViDiem().getId());
        List<LichSuTieuDiemDTO> listDTOs = lichSuTieuDiemService.findAllByViDiem(viDiemDTO.getId());
        DefaultTableModel dtm = (DefaultTableModel) tbLichSuTieuDiem.getModel();
        tbLichSuTieuDiem.setModel(dtm);
        dtm.setRowCount(0);
        for (LichSuTieuDiemDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
    }

    public final void loadQuyDoiDiem() {
        QuyDoiDiemDTO dTO = quyDoiDiemService.findById("340EB4DB-4EDE-4F34-87B8-E3DE0F418091");
        modalQuyDoiDiem = new ModalQuyDoiDiem(null, true, dTO);
        lbTien.setText(dTO.getTienTichDiem() + "");
        lbDiem.setText("= " + dTO.getTienTieuDiem());
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
        lbCardFront_id.setText("ID " + maTheTV);
//        back
        lbCardBack_loaiThe.setText(theThanhVienDTO.getLoaiThe().getTen());
        lbCardBack_ngayPhatHanh.setText(DateTimeUtil.formatDate(new Date(theThanhVienDTO.getNgayPhatHanh())));
        lbCardBack_ngayHetHan.setText(DateTimeUtil.formatDate(new Date(theThanhVienDTO.getNgayHetHan())));
        lbCardBack_maKH.setText(khachHangDTO.getMaKH());
        lbCardBack_id.setText("ID " + maTheTV);
        lbCardBack_ten.setText(khachHangDTO.getTen());
        ImageIcon icon = new QRCodeGenerator().generateQRCode(lbCardBack_id.getText(), 60, 60);
        lbCardBack_maQR.setIcon(icon);
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
        panelThongTinKH = new javax.swing.JPanel();
        btnDoi = new view.swing.ButtonOutLine();
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
        lbCardBack_maQR = new javax.swing.JLabel();
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

        javax.swing.GroupLayout panelLichSuTieuDiemLayout = new javax.swing.GroupLayout(panelLichSuTieuDiem);
        panelLichSuTieuDiem.setLayout(panelLichSuTieuDiemLayout);
        panelLichSuTieuDiemLayout.setHorizontalGroup(
            panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLichSuTieuDiemLayout.setVerticalGroup(
            panelLichSuTieuDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLichSuTieuDiemLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelThongTinKH.setBackground(new java.awt.Color(255, 255, 255));

        btnDoi.setBackground(new java.awt.Color(255, 0, 0));
        btnDoi.setForeground(new java.awt.Color(255, 0, 0));
        btnDoi.setText("Đổi");
        btnDoi.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiActionPerformed(evt);
            }
        });

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

        lbTien.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        lbTien.setForeground(new java.awt.Color(0, 0, 0));
        lbTien.setText("lbTien");

        lbTrangThai2.setBackground(new java.awt.Color(204, 102, 0));
        lbTrangThai2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        lbTrangThai2.setForeground(new java.awt.Color(255, 255, 255));
        lbTrangThai2.setText("VND");
        lbTrangThai2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
        lbTrangThai2.setOpaque(true);

        lbDiem.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        lbDiem.setForeground(new java.awt.Color(0, 0, 0));
        lbDiem.setText("=lbDiem");

        lb.setBackground(new java.awt.Color(0, 102, 153));
        lb.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Điểm");
        lb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        lb.setOpaque(true);

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
                    .addComponent(jLabel14))
                .addGap(74, 74, 74)
                .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinKHLayout.createSequentialGroup()
                        .addGroup(panelThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTongDiem)
                            .addComponent(lbLoaiThe)
                            .addComponent(lbNgayHetHan)
                            .addComponent(lbDiemDaDung)
                            .addComponent(lbTenKH)
                            .addComponent(lbTrangThai)
                            .addComponent(lbDiemDaCong)
                            .addComponent(lbMaKH)
                            .addComponent(lbMaThe)
                            .addComponent(lbNgayPhatHanh))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelThongTinKHLayout.createSequentialGroup()
                        .addComponent(lbTien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTrangThai2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelPreview.setBackground(new java.awt.Color(255, 255, 255));
        panelPreview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lpContextBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbCardBack_shop.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCardBack_shop.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_shop.setText("NDT SHOP");
        lpContextBack.add(lbCardBack_shop, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 78, -1, -1));

        lbCardBack_loaiThe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_loaiThe.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_loaiThe.setText("BRONZE");
        lpContextBack.add(lbCardBack_loaiThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 157, 100, 12));

        lbCardBack_ngayPhatHanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_ngayPhatHanh.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_ngayPhatHanh.setText("DD/MM/YY");
        lpContextBack.add(lbCardBack_ngayPhatHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 175, 100, 12));

        lbCardBack_ngayHetHan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_ngayHetHan.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_ngayHetHan.setText("DD/MM/YY");
        lpContextBack.add(lbCardBack_ngayHetHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 193, 100, 12));

        lbCardBack_maKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_maKH.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_maKH.setText("XXXXXXXXX");
        lpContextBack.add(lbCardBack_maKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 211, 100, 12));

        lbCardBack.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lbCardBack.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardBack.setText("MEMBER CARD");

        lbCardBack_maQR.setBackground(new java.awt.Color(204, 204, 204));
        lbCardBack_maQR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCardBack_maQR.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_maQR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbCardBack_id.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lbCardBack_id.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCardBack_id.setText("ID XXXXXXXXXXXXXXXXX");

        lpCode.setLayer(lbCardBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpCode.setLayer(lbCardBack_maQR, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpCode.setLayer(lbCardBack_id, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpCodeLayout = new javax.swing.GroupLayout(lpCode);
        lpCode.setLayout(lpCodeLayout);
        lpCodeLayout.setHorizontalGroup(
            lpCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbCardBack_maQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCardBack_id, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(lbCardBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lpCodeLayout.setVerticalGroup(
            lpCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpCodeLayout.createSequentialGroup()
                .addComponent(lbCardBack)
                .addGap(0, 0, 0)
                .addComponent(lbCardBack_maQR, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lbCardBack_id))
        );

        lpContextBack.add(lpCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 144, -1, 90));

        lbCardBack_ten.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbCardBack_ten.setForeground(new java.awt.Color(0, 0, 0));
        lbCardBack_ten.setText("Nguyễn Văn A");
        lpContextBack.add(lbCardBack_ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 113, 313, -1));

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
                .addContainerGap()
                .addGroup(lpContextFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCardFront, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addComponent(lbCardFront_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        panelPreview.add(lbPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

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
                    .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbBgBack;
    private javax.swing.JLabel lbBgFront;
    private javax.swing.JLabel lbCardBack;
    private javax.swing.JLabel lbCardBack_id;
    private javax.swing.JLabel lbCardBack_loaiThe;
    private javax.swing.JLabel lbCardBack_maKH;
    private javax.swing.JLabel lbCardBack_maQR;
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
    private javax.swing.JLabel lbPreview;
    private javax.swing.JLabel lbTenKH;
    private javax.swing.JLabel lbTien;
    private javax.swing.JLabel lbTongDiem;
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
