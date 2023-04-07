package view.form.hoadon;

import view.form.khachhang.*;
import comon.constant.khachhang.TrangThaiKhachHang;
import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.constant.khachhang.TrangThaiViDiem;
import comon.utilities.DateTimeUtil;
import dto.khachhang.KhachHangDTO;
import dto.khachhang.LoaiTheDTO;
import dto.khachhang.TheThanhVienDTO;
import dto.khachhang.ViDiemDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
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

public class ViewHoaDon extends javax.swing.JPanel {

    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long totalKhachHang;

    public ViewHoaDon() {
        initComponents();
//        tbHoaDon.fixTable(jScrollPane1);
        setOpaque(false);
        pageSize = 8;
        currentPage = 1;
//        khachHangService = new KhachHangServiceImpl();
//        theThanhVienService = new TheThanhVienServiceImpl();
//        loaiTheService = new LoaiTheServiceImpl();
//        viDiemService = new ViDiemServiceImpl();
//        modalKhachHang = new ModalKhachHang(null, true);
    }

    public final void loadDataTable() {
        
    }

//    private void setStatePagination() {
//        btnPrevious.setEnabled(currentPage > 1);
//        btnNext.setEnabled(currentPage < totalPages);
//        lbPagination.setText(currentPage + "/" + totalPages);
//    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new view.swing.MaterialTabbed();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        materialTabbed2 = new view.swing.MaterialTabbed();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        pnlDatHang = new javax.swing.JPanel();
        pnlGioHang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLichSuTieuDiem = new view.swing.table.Table();
        button1 = new view.swing.Button();
        button2 = new view.swing.Button();
        button3 = new view.swing.Button();
        button4 = new view.swing.Button();
        pnlGioHang1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLichSuTieuDiem1 = new view.swing.table.Table();
        panelLichSuTieuDiem3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbLichSuTieuDiem5 = new view.swing.table.Table();
        btnPrevious3 = new javax.swing.JButton();
        btnNext3 = new javax.swing.JButton();
        lbPagination3 = new javax.swing.JLabel();
        lbTotal3 = new javax.swing.JLabel();
        button8 = new view.swing.Button();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabel27 = new javax.swing.JLabel();

        materialTabbed1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel1.setText("Mã nhân viên");

        jLabel2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel2.setText("Tên nhân viên");

        jLabel3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel3.setText("Ngày tạo");

        jLabel4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel4.setText("XXXXXXXXX");

        jLabel5.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel5.setText("Nguyễn Văn A");

        jLabel6.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel6.setText("DD/MM/YYY/HH/SS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6))
                .addGap(79, 79, 79))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel7.setText("Họ tên");

        jLabel8.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel8.setText("SDT");

        jLabel9.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel10.setText("Ngày sinh");

        jLabel11.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel11.setText("Địa chỉ");

        jLabel12.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel12.setText("Email");

        jLabel13.setText("Giới tính");

        jTextField1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField3)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed2.addTab("Khách mới", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Khách cũ", jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Chi tiết hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel14.setText("Tổng tiền");

        jLabel15.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel15.setText("Giảm giá");

        jLabel16.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel16.setText("Khách cần trả");

        jLabel17.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel17.setText("Khách trả");

        jLabel18.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel18.setText("Tiền trả lại");

        jCheckBox1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jCheckBox1.setText("Chờ thanh toán");

        jButton1.setBackground(new java.awt.Color(33, 105, 249));
        jButton1.setFont(new java.awt.Font("Roboto Medium", 0, 48)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thanh toán");

        jCheckBox3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jCheckBox3.setText("Nhận kèm hoá đơn");

        jLabel20.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel20.setText("lbTongTien");

        jLabel21.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel21.setText("lbGiamGia");

        jLabel22.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel22.setText("lbCanTra");

        jLabel23.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel23.setText("lbTienTraLai");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(materialTabbed2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Hoá đơn", pnlHoaDon);

        javax.swing.GroupLayout pnlDatHangLayout = new javax.swing.GroupLayout(pnlDatHang);
        pnlDatHang.setLayout(pnlDatHangLayout);
        pnlDatHangLayout.setHorizontalGroup(
            pnlDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        pnlDatHangLayout.setVerticalGroup(
            pnlDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Đặt hàng", pnlDatHang);

        pnlGioHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbLichSuTieuDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
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
        jScrollPane2.setViewportView(tbLichSuTieuDiem);

        javax.swing.GroupLayout pnlGioHangLayout = new javax.swing.GroupLayout(pnlGioHang);
        pnlGioHang.setLayout(pnlGioHangLayout);
        pnlGioHangLayout.setHorizontalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlGioHangLayout.setVerticalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        button1.setBackground(new java.awt.Color(33, 105, 249));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Quét mã");
        button1.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        button2.setBackground(new java.awt.Color(33, 105, 249));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Bỏ khỏi giỏ hàng");
        button2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        button3.setBackground(new java.awt.Color(33, 105, 249));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Tạo hoá đơn");
        button3.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        button4.setBackground(new java.awt.Color(33, 105, 249));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("Làm mới giỏ hàng");
        button4.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        pnlGioHang1.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Danh sách hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbLichSuTieuDiem1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Mã NV", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLichSuTieuDiem1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbLichSuTieuDiem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLichSuTieuDiem1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbLichSuTieuDiem1);

        javax.swing.GroupLayout pnlGioHang1Layout = new javax.swing.GroupLayout(pnlGioHang1);
        pnlGioHang1.setLayout(pnlGioHang1Layout);
        pnlGioHang1Layout.setHorizontalGroup(
            pnlGioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlGioHang1Layout.setVerticalGroup(
            pnlGioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        panelLichSuTieuDiem3.setBackground(new java.awt.Color(255, 255, 255));
        panelLichSuTieuDiem3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)));

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 105, 249));
        jLabel26.setText("Danh sách sản phẩm");
        jLabel26.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbLichSuTieuDiem5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên", "Giá", "Số lượng", "Loại", "Màu", "Chất liệu", "Brand", "Xuất xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLichSuTieuDiem5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbLichSuTieuDiem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLichSuTieuDiem5MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbLichSuTieuDiem5);

        btnPrevious3.setText("<");
        btnPrevious3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevious3ActionPerformed(evt);
            }
        });

        btnNext3.setText(">");
        btnNext3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext3ActionPerformed(evt);
            }
        });

        lbPagination3.setText("1/1");

        lbTotal3.setText("Total: 0");

        button8.setBackground(new java.awt.Color(0, 102, 255));
        button8.setBorder(null);
        button8.setForeground(new java.awt.Color(255, 255, 255));
        button8.setText("Tìm kiếm");

        jScrollPane11.setViewportView(jTextPane4);

        jLabel27.setText("Mã ");

        javax.swing.GroupLayout panelLichSuTieuDiem3Layout = new javax.swing.GroupLayout(panelLichSuTieuDiem3);
        panelLichSuTieuDiem3.setLayout(panelLichSuTieuDiem3Layout);
        panelLichSuTieuDiem3Layout.setHorizontalGroup(
            panelLichSuTieuDiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLichSuTieuDiem3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLichSuTieuDiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLichSuTieuDiem3Layout.createSequentialGroup()
                        .addComponent(btnPrevious3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext3)
                        .addGap(18, 18, 18)
                        .addComponent(lbPagination3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTotal3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLichSuTieuDiem3Layout.createSequentialGroup()
                        .addGroup(panelLichSuTieuDiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLichSuTieuDiem3Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        panelLichSuTieuDiem3Layout.setVerticalGroup(
            panelLichSuTieuDiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLichSuTieuDiem3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelLichSuTieuDiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLichSuTieuDiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious3)
                    .addComponent(btnNext3)
                    .addComponent(lbPagination3)
                    .addComponent(lbTotal3))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlGioHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLichSuTieuDiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlGioHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelLichSuTieuDiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbLichSuTieuDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLichSuTieuDiemMouseClicked

    }//GEN-LAST:event_tbLichSuTieuDiemMouseClicked

    private void tbLichSuTieuDiem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLichSuTieuDiem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLichSuTieuDiem1MouseClicked

    private void tbLichSuTieuDiem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLichSuTieuDiem5MouseClicked

    }//GEN-LAST:event_tbLichSuTieuDiem5MouseClicked

    private void btnPrevious3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevious3ActionPerformed
        if (currentPage > 1) {
            currentPage--;
        }
        loadDataTable();
    }//GEN-LAST:event_btnPrevious3ActionPerformed

    private void btnNext3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext3ActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
        }
        loadDataTable();
    }//GEN-LAST:event_btnNext3ActionPerformed

    private KhachHangDTO getObjectsFromTable(int row) throws NumberFormatException {
        return null;
    }

    public String generateMemberCardId(KhachHangDTO khachHang) {
        String maKH = khachHang.getMaKH();
        String tenKH = khachHang.getTen();
        String[] parts = tenKH.split(" ");
        Date ngaySinh = new Date(khachHang.getNgaySinh());
        LocalDate localDate = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String year = Integer.toString(localDate.getYear()).substring(2);
        String month = String.format("%02d", localDate.getMonthValue());
        String day = String.format("%02d", localDate.getDayOfMonth());
        String last = Normalizer.normalize(parts[2], Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        String memberCardId = maKH.substring(4) + year + month + day + last.toUpperCase() + parts[0].substring(0, 1) + parts[1].substring(0, 1);
        return memberCardId;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext3;
    private javax.swing.JButton btnPrevious3;
    private view.swing.Button button1;
    private view.swing.Button button2;
    private view.swing.Button button3;
    private view.swing.Button button4;
    private view.swing.Button button8;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JLabel lbPagination3;
    private javax.swing.JLabel lbTotal3;
    private view.swing.MaterialTabbed materialTabbed1;
    private view.swing.MaterialTabbed materialTabbed2;
    private javax.swing.JPanel panelLichSuTieuDiem3;
    private javax.swing.JPanel pnlDatHang;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlGioHang1;
    private javax.swing.JPanel pnlHoaDon;
    private view.swing.table.Table tbLichSuTieuDiem;
    private view.swing.table.Table tbLichSuTieuDiem1;
    private view.swing.table.Table tbLichSuTieuDiem5;
    // End of variables declaration//GEN-END:variables
}
