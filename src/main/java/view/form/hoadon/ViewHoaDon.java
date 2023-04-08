package view.form.hoadon;

import comon.constant.TinhTrangHoaDon;
import comon.utilities.QrScanner;
import dto.hoadon.HoaDonChiTietDTO;
import dto.hoadon.HoaDonDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.NoResultException;
import javax.swing.table.DefaultTableModel;
import service.HoaDon.impl.HoaDonServiceImpl;
import service.hoaDon.HoaDonService;
import service.hoadon.HoaDonChiTietService;
import service.hoadon.impl.HoaDonChiTietServiceImpl;
import service.sanpham.SanPhamService;
import service.sanpham.impl.SanPhamServiceImpl;
import view.dialog.Message;
import view.main.Main;

public class ViewHoaDon extends javax.swing.JPanel {

    private final HoaDonService hoaDonService;
    private final SanPhamService sanPhamService;
    private final HoaDonChiTietService hoaDonChiTietService;
    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long totalSanPham;
    private DefaultTableModel dtmGioHang;

    public ViewHoaDon() {
        initComponents();
        setOpaque(false);
        hoaDonService = new HoaDonServiceImpl();
        sanPhamService = new SanPhamServiceImpl();
        hoaDonChiTietService = new HoaDonChiTietServiceImpl();
        pageSize = 5;
        currentPage = 1;
        loadDataTableSanPham();
        loadDataTableHoaDon();
        dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
        tbGioHang.setModel(dtmGioHang);
    }

    public final void loadDataTableSanPham() {
        List<SanPhamDTO> listDTOs = sanPhamService.getAll(currentPage);
        DefaultTableModel dtm = (DefaultTableModel) tbSanPham.getModel();
        tbSanPham.setModel(dtm);
        dtm.setRowCount(0);
        for (SanPhamDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
        totalSanPham = sanPhamService.count();
        lbTotal.setText("Total: " + totalSanPham);
        totalPages = (int) (totalSanPham / pageSize) + 1;
        setStatePagination();
    }

    public final void loadDataTableHoaDon() {
        List<HoaDonDTO> listDTOs = hoaDonService.findAll(currentPage - 1, 5);
        DefaultTableModel dtm = (DefaultTableModel) tbHoaDon.getModel();
        tbHoaDon.setModel(dtm);
        dtm.setRowCount(0);
        for (HoaDonDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
//        totalSanPham = hoaDonService.totalCount();
//        lbTotal.setText("Total: " + totalSanPham);
//        totalPages = (int) (totalSanPham / pageSize) + 1;
//        setStatePagination();
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

    private SanPhamDTO getSanPhamFromTable(int row) throws NumberFormatException {
        return sanPhamService.findById(tbSanPham.getValueAt(row, 0).toString());
    }

    private HoaDonChiTietDTO getHoaDonChiTietFromTable(int row) {
        String idSP = tbGioHang.getValueAt(row, 0).toString();
        String soLuong = tbGioHang.getValueAt(row, 3).toString();
        String donGia = tbGioHang.getValueAt(row, 4).toString();

        HoaDonChiTietDTO dTO = new HoaDonChiTietDTO();
        dTO.setSanPham(sanPhamService.findById(idSP));
        dTO.setDonGia(Float.parseFloat(donGia));
        dTO.setTinhTrangHoaDon(TinhTrangHoaDon.CHUA_THANH_TOAN);
        dTO.setSoLuong(Integer.parseInt(soLuong));
        return dTO;
    }

    private int isDuplicate(SanPhamDTO dTO) {
        for (int i = 0; i < dtmGioHang.getRowCount(); i++) {
            String id = dtmGioHang.getValueAt(i, 0).toString();
            if (id.equals(dTO.getId())) {
                return i;
            }
        }
        return -1;
    }

    private void updateTongTien() {
        float tongTien = 0;
        for (int i = 0; i < dtmGioHang.getRowCount(); i++) {
            float thanhTien = Float.parseFloat(dtmGioHang.getValueAt(i, 5).toString());
            tongTien += thanhTien;
        }
        lbTongTien.setText(String.valueOf(tongTien));
        lbGiamGia.setText("0");
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
        btnThanhToan = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        lbTongTien = new javax.swing.JLabel();
        lbGiamGia = new javax.swing.JLabel();
        lbCanTra = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        lbTienTraLai = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbIdHoaDon = new javax.swing.JLabel();
        pnlDatHang = new javax.swing.JPanel();
        pnlGioHang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new view.swing.table.Table();
        pnlGioHang1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDon = new view.swing.table.Table();
        panelLichSuTieuDiem3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbSanPham = new view.swing.table.Table();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbPagination = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        button8 = new view.swing.Button();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabel27 = new javax.swing.JLabel();
        btnQuetMa = new javax.swing.JButton();
        btnXoaGioHang = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        btnLamMoiGioHang = new javax.swing.JButton();

        materialTabbed1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 105, 249));
        jLabel1.setText("Mã nhân viên");

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(33, 105, 249));
        jLabel2.setText("Tên nhân viên");

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(33, 105, 249));
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(33, 105, 249));
        jLabel7.setText("Họ tên");

        jLabel8.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 105, 249));
        jLabel8.setText("SDT");

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 105, 249));
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(33, 105, 249));
        jLabel10.setText("Ngày sinh");

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(33, 105, 249));
        jLabel11.setText("Địa chỉ");

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(33, 105, 249));
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
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGap(0, 358, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        materialTabbed2.addTab("Khách cũ", jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Chi tiết hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(33, 105, 249));
        jLabel14.setText("Tổng tiền");

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(33, 105, 249));
        jLabel15.setText("Giảm giá");

        jLabel16.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(33, 105, 249));
        jLabel16.setText("Khách cần trả");

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(33, 105, 249));
        jLabel17.setText("Khách trả");

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(33, 105, 249));
        jLabel18.setText("Tiền trả lại");

        jCheckBox1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(33, 105, 249));
        jCheckBox1.setText("Chờ thanh toán");

        btnThanhToan.setBackground(new java.awt.Color(33, 105, 249));
        btnThanhToan.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(33, 105, 249));
        jCheckBox3.setText("Nhận kèm hoá đơn");

        lbTongTien.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTongTien.setText("lbTongTien");

        lbGiamGia.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbGiamGia.setText("lbGiamGia");

        lbCanTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbCanTra.setText("lbCanTra");

        lbTienTraLai.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTienTraLai.setText("lbTienTraLai");

        jLabel19.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(33, 105, 249));
        jLabel19.setText("ID");

        lbIdHoaDon.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbIdHoaDon.setText("lbIDHoaDon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKhachTra)
                            .addComponent(lbTienTraLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIdHoaDon)
                                    .addComponent(jCheckBox3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lbIdHoaDon))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbGiamGia))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbCanTra))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lbTienTraLai))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan)
                .addContainerGap())
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, Short.MAX_VALUE))
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
            .addGap(0, 702, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Đặt hàng", pnlDatHang);

        pnlGioHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGioHang.setOpaque(false);
        tbGioHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbGioHang);

        javax.swing.GroupLayout pnlGioHangLayout = new javax.swing.GroupLayout(pnlGioHang);
        pnlGioHang.setLayout(pnlGioHangLayout);
        pnlGioHangLayout.setHorizontalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlGioHangLayout.setVerticalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlGioHang1.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Danh sách hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Mã NV", "Mã KH", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDon.setOpaque(false);
        tbHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHoaDon);

        javax.swing.GroupLayout pnlGioHang1Layout = new javax.swing.GroupLayout(pnlGioHang1);
        pnlGioHang1.setLayout(pnlGioHang1Layout);
        pnlGioHang1Layout.setHorizontalGroup(
            pnlGioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
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

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã SP", "Tên", "Giá", "Số lượng", "Loại", "Màu", "Chất liệu", "Thương hiệu", "Xuất xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.setOpaque(false);
        tbSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbSanPham);
        if (tbSanPham.getColumnModel().getColumnCount() > 0) {
            tbSanPham.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbSanPham.getColumnModel().getColumn(3).setPreferredWidth(50);
            tbSanPham.getColumnModel().getColumn(5).setPreferredWidth(50);
            tbSanPham.getColumnModel().getColumn(6).setPreferredWidth(50);
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

        button8.setBackground(new java.awt.Color(33, 105, 249));
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
                    .addComponent(jScrollPane10)
                    .addGroup(panelLichSuTieuDiem3Layout.createSequentialGroup()
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(lbPagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTotal))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLichSuTieuDiem3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(lbPagination)
                    .addComponent(lbTotal))
                .addGap(11, 11, 11))
        );

        btnQuetMa.setBackground(new java.awt.Color(33, 105, 249));
        btnQuetMa.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnQuetMa.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetMa.setText("Quét mã");
        btnQuetMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetMaActionPerformed(evt);
            }
        });

        btnXoaGioHang.setBackground(new java.awt.Color(33, 105, 249));
        btnXoaGioHang.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnXoaGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaGioHang.setText("Xoá khỏi giỏ hàng");
        btnXoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setBackground(new java.awt.Color(33, 105, 249));
        btnTaoHoaDon.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setText("Tạo hoá đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnLamMoiGioHang.setBackground(new java.awt.Color(33, 105, 249));
        btnLamMoiGioHang.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnLamMoiGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoiGioHang.setText("Làm mới giỏ hàng");
        btnLamMoiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlGioHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLichSuTieuDiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 107, Short.MAX_VALUE)
                        .addComponent(btnLamMoiGioHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoiGioHang, btnQuetMa, btnTaoHoaDon, btnXoaGioHang});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnQuetMa)
                                .addComponent(btnXoaGioHang)
                                .addComponent(btnLamMoiGioHang)))
                        .addGap(9, 9, 9)
                        .addComponent(pnlGioHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelLichSuTieuDiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoiGioHang, btnQuetMa, btnTaoHoaDon, btnXoaGioHang});

    }// </editor-fold>//GEN-END:initComponents

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked

    }//GEN-LAST:event_tbGioHangMouseClicked

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int row = tbSanPham.getSelectedRow();
        if (row < 0) {
            return;
        }
        SanPhamDTO dTO = getSanPhamFromTable(row);
        if (evt.getClickCount() == 2) {
            ModalSoLuong modalSoLuong = new ModalSoLuong(null, true, dTO);
            modalSoLuong.setVisible(true);
            modalSoLuong.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        String soLuong = modalSoLuong.getSoLuong();
                        SanPhamDTO dTO = getSanPhamFromTable(row);
                        int rowIndex = isDuplicate(dTO);
                        if (rowIndex >= 0) {
                            int soLuongCu = Integer.parseInt(dtmGioHang.getValueAt(rowIndex, 3).toString());
                            int soLuongMoi = soLuongCu + Integer.parseInt(soLuong);
                            Float thanhTien = Float.valueOf(dtmGioHang.getValueAt(rowIndex, 5).toString());
                            dtmGioHang.setValueAt(soLuongMoi, rowIndex, 3);
                            dtmGioHang.setValueAt(soLuongMoi*thanhTien, rowIndex, 5);
                        } else {
                            dtmGioHang.addRow(new Object[]{
                                dTO.getId(),
                                dTO.getMaSP(),
                                dTO.getTenSP(),
                                Integer.parseInt(soLuong),
                                dTO.getGiaBan(),
                                Float.parseFloat(soLuong) * Float.parseFloat(dTO.getGiaBan())
                            });
                        }
                        updateTongTien();
                        loadDataTableSanPham();
                    } catch (NumberFormatException numberFormatException) {
                    }
                }
            });
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage > 1) {
            currentPage--;
        }
        loadDataTableSanPham();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
        }
        loadDataTableSanPham();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed
        int row = tbGioHang.getSelectedRow();
        if (row < 0) {
            showMessage("Vui lòng chọn hàng để xoá khỏi hàng!");
            return;
        }
        String id = tbGioHang.getValueAt(row, 0).toString();
        String soLuong = tbGioHang.getValueAt(row, 3).toString();
        SanPhamDTO dtoGioHang = sanPhamService.findById(id);
        dtoGioHang.setSoLuongTon(Integer.parseInt(dtoGioHang.getSoLuongTon()) + Integer.parseInt(soLuong) + "");
        if (Objects.isNull(sanPhamService.update(dtoGioHang))) {
            showMessage("Xoá thất bại");
        } else {
            showMessage("Xoá thành công");
            dtmGioHang.removeRow(row);
            dtmGioHang.fireTableDataChanged();
        }
    }//GEN-LAST:event_btnXoaGioHangActionPerformed

    private void btnLamMoiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiGioHangActionPerformed
        int row = tbGioHang.getRowCount();
        int offset = 0;
        for (int i = 0; i < row; i++) {
            String id = tbGioHang.getValueAt(i - offset, 0).toString();
            String soLuong = tbGioHang.getValueAt(i - offset, 3).toString();
            SanPhamDTO dtoGioHang = sanPhamService.findById(id);
            dtoGioHang.setSoLuongTon(Integer.parseInt(dtoGioHang.getSoLuongTon()) + Integer.parseInt(soLuong) + "");
            if (!Objects.isNull(sanPhamService.update(dtoGioHang))) {
                dtmGioHang.removeRow(i - offset);
                offset++;
            }
        }
        if (offset > 0) {
            showMessage("Xoá thành công " + offset + " hàng!");
        } else {
            showMessage("Xoá thất bại");
        }
        dtmGioHang.fireTableDataChanged();
        loadDataTableSanPham();
    }//GEN-LAST:event_btnLamMoiGioHangActionPerformed

    private void btnQuetMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetMaActionPerformed
        QrScanner qrScanner = new QrScanner(null, true);
        qrScanner.setVisible(true);
        qrScanner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SanPhamDTO dTO = null;
                try {
                    dTO = sanPhamService.findById(qrScanner.getQrString());
                } catch (NoResultException ex) {
                    showMessage("Không tìm thấy sản phẩm");
                }
                ModalSoLuong modalSoLuong = new ModalSoLuong(null, true, dTO);
                modalSoLuong.setVisible(true);
                modalSoLuong.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            String soLuong = modalSoLuong.getSoLuong();
                            SanPhamDTO dTO = sanPhamService.findById(qrScanner.getQrString());
                            int rowIndex = isDuplicate(dTO);
                            if (rowIndex >= 0) {
                                int soLuongCu = Integer.parseInt(dtmGioHang.getValueAt(rowIndex, 3).toString());
                                int soLuongMoi = soLuongCu + Integer.parseInt(soLuong);
                                dtmGioHang.setValueAt(soLuongMoi, rowIndex, 3);
                            } else {
                                dtmGioHang.addRow(new Object[]{
                                    dTO.getId(),
                                    dTO.getMaSP(),
                                    dTO.getTenSP(),
                                    Integer.parseInt(soLuong),
                                    dTO.getGiaBan(),
                                    Float.parseFloat(dTO.getSoLuongTon()) * Float.parseFloat(dTO.getGiaBan())
                                });
                            }
                            loadDataTableSanPham();
                        } catch (NumberFormatException numberFormatException) {
                        }
                    }
                });
            }
        });
    }//GEN-LAST:event_btnQuetMaActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int row = tbGioHang.getRowCount();
        List<HoaDonChiTietDTO> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            HoaDonChiTietDTO dTO = getHoaDonChiTietFromTable(i);
            dTO = hoaDonChiTietService.save(dTO);
            if (!Objects.isNull(dTO)) {
                list.add(dTO);
            }
        }
        if (list.isEmpty()) {
            showMessage("Lỗi!");
            return;
        }
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        Float tongTien = Float.parseFloat(lbTongTien.getText()) - Float.parseFloat(lbGiamGia.getText());
        hoaDonDTO.setTongTien(tongTien);
        hoaDonDTO.setTinhTrangHoaDon(TinhTrangHoaDon.CHUA_THANH_TOAN);
        hoaDonDTO = hoaDonService.save(hoaDonDTO);
        if (!Objects.isNull(hoaDonDTO)) {
            showMessage("Tạo hoá đơn thành công!");
            for (int i = 0; i < list.size(); i++) {
                HoaDonChiTietDTO get = list.get(i);
                get.setHoaDon(hoaDonDTO);
                hoaDonChiTietService.save(get);
            }
            lbIdHoaDon.setText(hoaDonDTO.getId());
        } else {
            showMessage("Tạo hoá đơn thất bại!");
        }
        loadDataTableHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        
    }//GEN-LAST:event_btnThanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiGioHang;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnQuetMa;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaGioHang;
    private view.swing.Button button8;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JLabel lbCanTra;
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbIdHoaDon;
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbTienTraLai;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTotal;
    private view.swing.MaterialTabbed materialTabbed1;
    private view.swing.MaterialTabbed materialTabbed2;
    private javax.swing.JPanel panelLichSuTieuDiem3;
    private javax.swing.JPanel pnlDatHang;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlGioHang1;
    private javax.swing.JPanel pnlHoaDon;
    private view.swing.table.Table tbGioHang;
    private view.swing.table.Table tbHoaDon;
    private view.swing.table.Table tbSanPham;
    private javax.swing.JTextField txtKhachTra;
    // End of variables declaration//GEN-END:variables
}
