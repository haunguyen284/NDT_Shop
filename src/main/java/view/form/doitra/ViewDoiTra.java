package view.form.doitra;

import comon.constant.TinhTrangHoaDon;
import comon.utilities.DateTimeUtil;
import comon.utilities.QrScanner;
import comon.utilities.VndConvertUtil;
import dto.hoadon.HoaDonChiTietDTO;
import dto.hoadon.HoaDonDTO;
import dto.nhanvien.TaiKhoanDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import service.HoaDon.impl.HoaDonServiceImpl;
import service.dongiao.impl.DonGiaoServiceImpl;
import service.dongiao.impl.ThongSoServiceImpl;
import service.hoaDon.HoaDonService;
import service.hoadon.HoaDonChiTietService;
import service.hoadon.impl.HoaDonChiTietServiceImpl;
import service.khachhang.impl.KhachHangServiceImpl;
import service.khachhang.impl.QuyDoiDiemServiceImpl;
import service.khachhang.impl.ViDiemServiceImpl;
import service.nhanvien.TaiKhoanService;
import service.nhanvien.impl.NhanVienServiceImpl;
import service.nhanvien.impl.TaiKhoanServiceImpl;
import service.sanpham.SanPhamService;
import service.sanpham.impl.SanPhamServiceImpl;
import view.dialog.Message;
import view.dialog.ShowMessageSuccessful;
import view.form.MainForm;
import view.form.hoadon.ModalSoLuongDoiTra;

public class ViewDoiTra extends javax.swing.JPanel {

    private final HoaDonService hoaDonService;
    private final SanPhamService sanPhamService;
    private final HoaDonChiTietService hoaDonChiTietService;
    private final TaiKhoanService taiKhoanService;
    private final DefaultTableModel dtmGioHangKhachTra;
    private final String user;
    private final MainForm main;

    /**
     *
     * @param main
     * @param user
     */
    public ViewDoiTra(MainForm main, String user) {
        initComponents();
        hoaDonService = new HoaDonServiceImpl();
        sanPhamService = new SanPhamServiceImpl();
        hoaDonChiTietService = new HoaDonChiTietServiceImpl();
        taiKhoanService = new TaiKhoanServiceImpl();
        dtmGioHangKhachTra = (DefaultTableModel) tbGioHangKhachTra.getModel();
        tbGioHangKhachTra.setModel(dtmGioHangKhachTra);
        this.user = user;
        this.main = main;
        TaiKhoanDTO tkDTO = taiKhoanService.findByTenTaiKhoan(user);
        lbMaNV1.setText(tkDTO.getNhanVien().getMaNV());
        lbTenNV1.setText(tkDTO.getNhanVien().getTen());
        lbNgayTao1.setText(DateTimeUtil.formatDate(new Date()));
        loadDataTableHoaDon();
        initTable();
        loadTableDoiTra();
    }

    private void updateTongTienHoaDon(String maHD) {
        String id = hoaDonService.findId(maHD);
        HoaDonDTO hoaDonDTO = hoaDonService.findById(id);
        int tongTien = 0;
        List<HoaDonChiTietDTO> listHDCTDTO = hoaDonChiTietService.findByMaHoaDon(maHD);
        int count = 0;
        for (HoaDonChiTietDTO hoaDonChiTietDTO : listHDCTDTO) {
            if (hoaDonChiTietDTO.getTinhTrangHoaDon() == TinhTrangHoaDon.DOI_HANG) {
                count++;
            }
            if (hoaDonChiTietDTO.getTinhTrangHoaDon() != TinhTrangHoaDon.DA_THANH_TOAN) {
                continue;
            }
            tongTien += (hoaDonChiTietDTO.getSoLuong() * hoaDonChiTietDTO.getDonGia());
        }
        if (count == listHDCTDTO.size()) {
            hoaDonDTO.setTinhTrangHoaDon(TinhTrangHoaDon.DOI_HANG);
        }
        hoaDonDTO.setTongTien(tongTien);
        hoaDonService.save(hoaDonDTO);
    }

    private String getSelectedIdFromTable() {
        int selectedRow = tbDoiTra.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        } else {
            return tbDoiTra.getValueAt(selectedRow, 0).toString();
        }
    }

    private void initTable() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Đổi sản phẩm");

        popupMenu.add(editMenuItem);

        editMenuItem.addActionListener((ActionEvent e) -> {
            if (e.getSource() == editMenuItem) {
                String id = getSelectedIdFromTable();
                if (id == null) {
                    return;
                }
                HoaDonChiTietDTO dto = hoaDonChiTietService.findById(id);
                if (dto.getTinhTrangHoaDon() != TinhTrangHoaDon.DA_THANH_TOAN) {
                    showMessage("Đơn hàng này không được phép đổi");
                    return;
                }
                int soLuongBanDau = dto.getSoLuong();
                Frame parentWindow = (Frame) SwingUtilities.windowForComponent(this);
                ModalSoLuongDoiTra modalSoLuongDoiTra = new ModalSoLuongDoiTra(parentWindow, true, dto.getSanPham());
                modalSoLuongDoiTra.setVisible(true);
                if ("".equals(modalSoLuongDoiTra.getSoLuong())) {
                    return;
                }
                int soLuongDoi = Integer.parseInt(modalSoLuongDoiTra.getSoLuong());
                if (soLuongDoi > soLuongBanDau) {
                    showMessage("Số lượng đổi không được quá số lượng đã mua");
                    return;
                } else if (soLuongDoi == soLuongBanDau) {
                    dto.setTinhTrangHoaDon(TinhTrangHoaDon.DOI_HANG);
                    hoaDonChiTietService.save(dto);
                } else {
                    // giữ lại số lượng không đổi
                    dto.setSoLuong(soLuongBanDau - soLuongDoi);
                    hoaDonChiTietService.save(dto);

                    // thêm mới hoáhoaDonChiTietService đơn chi tiết bị đổi
                    dto.setId(null);
                    dto.setSoLuong(soLuongDoi);
                    dto.setTinhTrangHoaDon(TinhTrangHoaDon.DOI_HANG);
                    hoaDonChiTietService.save(dto);

                }
                updateTongTienHoaDon(txtMaHD.getText());
                loadTableDoiTra();
            }
        });

        tbDoiTra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = tbDoiTra.getSelectedRow();
                    if (row >= 0) {
                        tbDoiTra.setRowSelectionInterval(row, row);
                        popupMenu.show(tbDoiTra, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    public final void loadDataTableHoaDon() {
        TinhTrangHoaDon trangThai = TinhTrangHoaDon.DA_THANH_TOAN;
        List<HoaDonDTO> listDTOs = hoaDonService.findByTinhTrang(trangThai);
        DefaultTableModel dtm = (DefaultTableModel) tbHoaDonKhachTra.getModel();
        tbHoaDonKhachTra.setModel(dtm);
        dtm.setRowCount(0);
        for (HoaDonDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
    }

    private void loadTableDoiTra() {
        String maHD = txtMaHD.getText();
        tbDoiTra.clearAllRow();
        List<HoaDonChiTietDTO> listHDCTDTO = hoaDonChiTietService.findByMaHoaDon(maHD);
        for (HoaDonChiTietDTO hoaDonChiTietDTO : listHDCTDTO) {
            tbDoiTra.addRow(new Object[]{
                hoaDonChiTietDTO.getId(),
                hoaDonChiTietDTO.getSanPham().getMaSP(),
                hoaDonChiTietDTO.getSanPham().getTenSP(),
                hoaDonChiTietDTO.getSoLuong(),
                hoaDonChiTietDTO.getDonGia(),
                hoaDonChiTietDTO.getSoLuong() * hoaDonChiTietDTO.getDonGia(),
                hoaDonChiTietDTO.convertedTinhTrang(),});
        }
    }

    private void updateTongTien() {
        float tongTienMoi = 0;
        float tongTienGoc = VndConvertUtil.vndToFloat(lbTongTienKhachTra.getText());
        for (int i = 0; i < dtmGioHangKhachTra.getRowCount(); i++) {
            if (dtmGioHangKhachTra.getValueAt(i, 6).toString().equals("Hoàn trả")) {
                continue;
            }
            HoaDonChiTietDTO hdctDTO = hoaDonChiTietService.findById(dtmGioHangKhachTra.getValueAt(i, 0).toString());
            SanPhamDTO dTO = hdctDTO.getSanPham();
            float giaGoc = Float.parseFloat(dTO.getGiaBan());
            int soLuong = Integer.parseInt(dtmGioHangKhachTra.getValueAt(i, 3).toString());
            float thanhTienGoc = giaGoc * soLuong;
            tongTienMoi += thanhTienGoc;
        }
        lbTongTienMoiKhachTra.setText(VndConvertUtil.floatToVnd(tongTienMoi));
        lbHoanTra.setText(VndConvertUtil.floatToVnd(tongTienGoc - tongTienMoi));
    }

    private boolean showMessage(String message) {
        Message obj = new Message(view.main.Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        materialTabbed1 = new view.swing.MaterialTabbed();
        jPanel8 = new javax.swing.JPanel();
        pnlDsHoaDon2 = new javax.swing.JPanel();
        txtMaHD = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btnTimHD = new javax.swing.JButton();
        btnQuetHD = new javax.swing.JButton();
        pnlGioHang2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbDoiTra = new view.swing.table.Table();
        btnCapNhat1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnlDsHoaDon1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDonKhachTra = new view.swing.table.Table();
        txtTimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnTimHoaDon = new javax.swing.JButton();
        btnQuetHoaDon = new javax.swing.JButton();
        pnlGioHang1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbGioHangKhachTra = new view.swing.table.Table();
        pnlHoaDon1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbMaNV1 = new javax.swing.JLabel();
        lbTenNV1 = new javax.swing.JLabel();
        lbNgayTao1 = new javax.swing.JLabel();
        tbpnlKhachHang1 = new view.swing.MaterialTabbed();
        jPanel9 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        lbHoTenKhachTra = new javax.swing.JLabel();
        lbMaKhachTra = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lbMaTheKhachTra = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lbLoaiTheKhachTra = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lbSoDiemKhachTra = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lbSDTKhachTra = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        lbEmailKhachTra = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lbDiaChiKhachTra = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lbGhiChuKhachTra = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        lbTongTienKhachTra = new javax.swing.JLabel();
        lbTongTienMoiKhachTra = new javax.swing.JLabel();
        lbHoanTra = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lbIdHoaDonKhachTra = new javax.swing.JLabel();
        cbSanPhamLoi = new javax.swing.JCheckBox();
        cbSanPhamOK = new javax.swing.JCheckBox();
        btnHuy = new javax.swing.JButton();

        setOpaque(false);

        materialTabbed1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N

        pnlDsHoaDon2.setBackground(new java.awt.Color(255, 255, 255));
        pnlDsHoaDon2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Tìm hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel24.setText("Mã");

        btnTimHD.setBackground(new java.awt.Color(33, 105, 249));
        btnTimHD.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnTimHD.setForeground(new java.awt.Color(255, 255, 255));
        btnTimHD.setText("Tìm kiếm");
        btnTimHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHDActionPerformed(evt);
            }
        });

        btnQuetHD.setBackground(new java.awt.Color(33, 105, 249));
        btnQuetHD.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnQuetHD.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetHD.setText("Quét mã");
        btnQuetHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDsHoaDon2Layout = new javax.swing.GroupLayout(pnlDsHoaDon2);
        pnlDsHoaDon2.setLayout(pnlDsHoaDon2Layout);
        pnlDsHoaDon2Layout.setHorizontalGroup(
            pnlDsHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsHoaDon2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuetHD, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlDsHoaDon2Layout.setVerticalGroup(
            pnlDsHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsHoaDon2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlDsHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(btnTimHD)
                    .addComponent(btnQuetHD))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pnlGioHang2.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Đơn hàng đã mua", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbDoiTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên", "Số lượng", "Đơn giá", "Thành tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDoiTra.setOpaque(false);
        tbDoiTra.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbDoiTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDoiTraMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbDoiTra);

        javax.swing.GroupLayout pnlGioHang2Layout = new javax.swing.GroupLayout(pnlGioHang2);
        pnlGioHang2.setLayout(pnlGioHang2Layout);
        pnlGioHang2Layout.setHorizontalGroup(
            pnlGioHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlGioHang2Layout.setVerticalGroup(
            pnlGioHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang2Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnCapNhat1.setBackground(new java.awt.Color(33, 105, 249));
        btnCapNhat1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCapNhat1.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat1.setText("Cập nhật");
        btnCapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnCapNhat1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlDsHoaDon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlGioHang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDsHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCapNhat1)
                .addGap(16, 16, 16)
                .addComponent(pnlGioHang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Đổi", jPanel8);

        jPanel2.setOpaque(false);

        pnlDsHoaDon1.setBackground(new java.awt.Color(255, 255, 255));
        pnlDsHoaDon1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Danh sách hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setForeground(new java.awt.Color(255, 255, 255));

        tbHoaDonKhachTra.setBackground(new java.awt.Color(255, 255, 255));
        tbHoaDonKhachTra.setForeground(new java.awt.Color(255, 255, 255));
        tbHoaDonKhachTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã NV", "Mã KH", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDonKhachTra.setOpaque(false);
        tbHoaDonKhachTra.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbHoaDonKhachTra.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHoaDonKhachTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonKhachTraMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHoaDonKhachTra);

        jLabel6.setText("Mã");

        btnTimHoaDon.setBackground(new java.awt.Color(33, 105, 249));
        btnTimHoaDon.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnTimHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTimHoaDon.setText("T");
        btnTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHoaDonActionPerformed(evt);
            }
        });

        btnQuetHoaDon.setBackground(new java.awt.Color(33, 105, 249));
        btnQuetHoaDon.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnQuetHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetHoaDon.setText("Q");
        btnQuetHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDsHoaDon1Layout = new javax.swing.GroupLayout(pnlDsHoaDon1);
        pnlDsHoaDon1.setLayout(pnlDsHoaDon1Layout);
        pnlDsHoaDon1Layout.setHorizontalGroup(
            pnlDsHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsHoaDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDsHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDsHoaDon1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDsHoaDon1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuetHoaDon)
                        .addGap(38, 38, 38))))
        );
        pnlDsHoaDon1Layout.setVerticalGroup(
            pnlDsHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsHoaDon1Layout.createSequentialGroup()
                .addGroup(pnlDsHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnTimHoaDon)
                    .addComponent(btnQuetHoaDon))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlGioHang1.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbGioHangKhachTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên", "Số lượng", "Đơn giá", "Thành tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGioHangKhachTra.setOpaque(false);
        tbGioHangKhachTra.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbGioHangKhachTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbGioHangKhachTraMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tbGioHangKhachTra);

        javax.swing.GroupLayout pnlGioHang1Layout = new javax.swing.GroupLayout(pnlGioHang1);
        pnlGioHang1.setLayout(pnlGioHang1Layout);
        pnlGioHang1Layout.setHorizontalGroup(
            pnlGioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        pnlGioHang1Layout.setVerticalGroup(
            pnlGioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHang1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlHoaDon1.setBackground(new java.awt.Color(204, 229, 240));

        jPanel7.setBackground(new java.awt.Color(204, 229, 240));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(33, 105, 249));
        jLabel4.setText("Mã nhân viên");

        jLabel21.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(33, 105, 249));
        jLabel21.setText("Tên nhân viên");

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(33, 105, 249));
        jLabel22.setText("Ngày tạo");

        lbMaNV1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaNV1.setText("XXXXXXXXX");

        lbTenNV1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTenNV1.setText("Nguyễn Văn A");

        lbNgayTao1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbNgayTao1.setText("DD/MM/YYY/HH/SS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTenNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNgayTao1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbMaNV1))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lbTenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lbNgayTao1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpnlKhachHang1.setBackground(new java.awt.Color(204, 229, 240));

        jPanel9.setBackground(new java.awt.Color(204, 229, 240));

        jLabel34.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(33, 105, 249));
        jLabel34.setText("Họ tên");

        lbHoTenKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbHoTenKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbHoTenKhachTra.setText("N/A");

        lbMaKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKhachTra.setText("N/A");

        jLabel35.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(33, 105, 249));
        jLabel35.setText("Mã KH");

        lbMaTheKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaTheKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbMaTheKhachTra.setText("Chưa có thẻ");

        jLabel36.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(33, 105, 249));
        jLabel36.setText("Mã Thẻ");

        lbLoaiTheKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbLoaiTheKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbLoaiTheKhachTra.setText("Chưa có thẻ");

        jLabel37.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(33, 105, 249));
        jLabel37.setText("Loại thẻ");

        jLabel38.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(33, 105, 249));
        jLabel38.setText("Số điểm");

        lbSoDiemKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbSoDiemKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbSoDiemKhachTra.setText("0");

        jLabel45.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(33, 105, 249));
        jLabel45.setText("SDT");

        lbSDTKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbSDTKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbSDTKhachTra.setText("N/A");

        jLabel46.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(33, 105, 249));
        jLabel46.setText("Email");

        lbEmailKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbEmailKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbEmailKhachTra.setText("N/A");

        jLabel47.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(33, 105, 249));
        jLabel47.setText("Địa chỉ");

        lbDiaChiKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbDiaChiKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbDiaChiKhachTra.setText("N/A");

        jLabel48.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(33, 105, 249));
        jLabel48.setText("Ghi chú");

        lbGhiChuKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbGhiChuKhachTra.setForeground(new java.awt.Color(0, 0, 0));
        lbGhiChuKhachTra.setText("N/A");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbHoTenKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMaKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLoaiTheKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSoDiemKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMaTheKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSDTKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmailKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDiaChiKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGhiChuKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lbHoTenKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lbMaKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lbMaTheKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lbLoaiTheKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lbSoDiemKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lbSDTKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(lbEmailKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lbDiaChiKhachTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(lbGhiChuKhachTra))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpnlKhachHang1.addTab("Thông tin khách hàng", jPanel9);

        jPanel10.setBackground(new java.awt.Color(204, 229, 240));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Chi tiết hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(33, 105, 249));
        jLabel39.setText("Tổng tiền cũ");

        jLabel40.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(33, 105, 249));
        jLabel40.setText("Tổng tiền mới");

        jLabel41.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(33, 105, 249));
        jLabel41.setText("Hoàn trả khách");

        btnXacNhan.setBackground(new java.awt.Color(33, 105, 249));
        btnXacNhan.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        lbTongTienKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTongTienKhachTra.setText("0");

        lbTongTienMoiKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTongTienMoiKhachTra.setText("0");

        lbHoanTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbHoanTra.setText("0");

        jLabel44.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(33, 105, 249));
        jLabel44.setText("ID");

        lbIdHoaDonKhachTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbIdHoaDonKhachTra.setText("N/A");
        lbIdHoaDonKhachTra.setToolTipText("");

        buttonGroup1.add(cbSanPhamLoi);
        cbSanPhamLoi.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        cbSanPhamLoi.setForeground(new java.awt.Color(33, 105, 249));
        cbSanPhamLoi.setSelected(true);
        cbSanPhamLoi.setText("Sản phẩm lỗi");

        buttonGroup1.add(cbSanPhamOK);
        cbSanPhamOK.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        cbSanPhamOK.setForeground(new java.awt.Color(33, 105, 249));
        cbSanPhamOK.setText("Sản phẩm còn bán được");

        btnHuy.setBackground(new java.awt.Color(33, 105, 249));
        btnHuy.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel44))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTongTienKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTongTienMoiKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbHoanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbIdHoaDonKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(cbSanPhamLoi)
                        .addGap(18, 18, 18)
                        .addComponent(cbSanPhamOK)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lbIdHoaDonKhachTra))
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lbTongTienKhachTra))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lbTongTienMoiKhachTra))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lbHoanTra))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSanPhamLoi)
                    .addComponent(cbSanPhamOK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXacNhan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlHoaDon1Layout = new javax.swing.GroupLayout(pnlHoaDon1);
        pnlHoaDon1.setLayout(pnlHoaDon1Layout);
        pnlHoaDon1Layout.setHorizontalGroup(
            pnlHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbpnlKhachHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHoaDon1Layout.setVerticalGroup(
            pnlHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpnlKhachHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDsHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlGioHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlDsHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlGioHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        materialTabbed1.addTab("Trả", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        int row = tbHoaDonKhachTra.getSelectedRow();
        if (row < 0) {
            showMessage("Vui lòng chọn hoá đơn cần trả hàng!");
            return;
        }
//        Kiểm tra tình trạng sản phẩm
        String idHD = tbHoaDonKhachTra.getValueAt(row, 0).toString();

        List<HoaDonChiTietDTO> hdctGioHang = hoaDonChiTietService.findByHoaDon(idHD);
        for (HoaDonChiTietDTO hoaDonChiTietDTO : hdctGioHang) {
            for (int i = 0; i < dtmGioHangKhachTra.getRowCount(); i++) {
                if (Objects.isNull(dtmGioHangKhachTra.getValueAt(i, 0))) {
                    continue;
                }
                String idHdct = dtmGioHangKhachTra.getValueAt(i, 0).toString();
                int soLuongMoi = Integer.parseInt(dtmGioHangKhachTra.getValueAt(i, 3).toString());
                int soLuongBanDau = hoaDonChiTietDTO.getSoLuong();
                if (hoaDonChiTietDTO.getId().equals(idHdct)) {
                    if (dtmGioHangKhachTra.getValueAt(i, 6).toString().equals("Hoàn trả")) {
                        hoaDonChiTietDTO.setSoLuong(0);
                        hoaDonChiTietDTO.setTinhTrangHoaDon(TinhTrangHoaDon.HOAN_TRA);
                        hoaDonChiTietService.save(hoaDonChiTietDTO);
                        if (!cbSanPhamLoi.isSelected()) {
                            SanPhamDTO sanPhamDTO = hoaDonChiTietDTO.getSanPham();
                            sanPhamDTO.setSoLuongTon(Integer.parseInt(sanPhamDTO.getSoLuongTon()) + soLuongBanDau + "");
                            sanPhamService.update(sanPhamDTO);
                        }
                        continue;
                    }
                    if (!cbSanPhamLoi.isSelected()) {
                        SanPhamDTO sanPhamDTO = hoaDonChiTietDTO.getSanPham();
                        sanPhamDTO.setSoLuongTon(Integer.parseInt(sanPhamDTO.getSoLuongTon()) + (soLuongBanDau - soLuongMoi) + "");
                        sanPhamService.update(sanPhamDTO);
                    }
                    hoaDonChiTietDTO.setSoLuong(soLuongMoi);
                    hoaDonChiTietService.save(hoaDonChiTietDTO);
                }
            }
        }
        for (int i = 0; i < dtmGioHangKhachTra.getRowCount(); i++) {
            HoaDonChiTietDTO hoaDonChiTietDTO = new HoaDonChiTietDTO();
            if (Objects.isNull(dtmGioHangKhachTra.getValueAt(i, 0))) {
                String soLuong = dtmGioHangKhachTra.getValueAt(i, 3).toString();
                String maSP = dtmGioHangKhachTra.getValueAt(i, 1).toString();
                for (int j = 0; j < dtmGioHangKhachTra.getRowCount(); j++) {
                    String maSpCheck = dtmGioHangKhachTra.getValueAt(i, 1).toString();
                    if (maSP.equals(maSpCheck) && !Objects.isNull(dtmGioHangKhachTra.getValueAt(j, 0))) {
                        String id = dtmGioHangKhachTra.getValueAt(j, 0).toString();
                        HoaDonChiTietDTO hdctDTO = hoaDonChiTietService.findById(id);
                        hoaDonChiTietDTO = hdctDTO;
                    }
                }
                HoaDonChiTietDTO hdctDTO = new HoaDonChiTietDTO();
                hdctDTO.setSanPham(hoaDonChiTietDTO.getSanPham());
                hdctDTO.setHoaDon(hoaDonService.findById(idHD));
                hdctDTO.setDonGia(hoaDonChiTietDTO.getDonGia());
                hdctDTO.setSoLuong(Integer.parseInt(soLuong));
                hdctDTO.setTinhTrangHoaDon(TinhTrangHoaDon.HOAN_TRA);
            }
        }
        ShowMessageSuccessful.showSuccessful("Trả hàng thành công");
        HoaDonDTO hdDTO = hoaDonService.findById(idHD);
        hdDTO.setTongTien(VndConvertUtil.vndToFloat(lbTongTienKhachTra.getText()));
        int count = 0;
        for (int i = 0; i < dtmGioHangKhachTra.getRowCount(); i++) {
            if (dtmGioHangKhachTra.getValueAt(i, 6).toString().equals("Hoàn trả")) {
                count++;
            }
        }
        if (count == dtmGioHangKhachTra.getRowCount()) {
            hdDTO.setTinhTrangHoaDon(TinhTrangHoaDon.HOAN_TRA);
        }
        hoaDonService.save(hdDTO);
        loadDataTableHoaDon();
        new MainForm().showForm(new ViewDoiTra(main, user));
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void tbHoaDonKhachTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonKhachTraMouseClicked
        int row = tbHoaDonKhachTra.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (evt.getClickCount() == 2) {
            HoaDonDTO hdDTO = hoaDonService.findById(tbHoaDonKhachTra.getValueAt(row, 0).toString());
            lbIdHoaDonKhachTra.setText(hdDTO.getMaHD());
            List<HoaDonChiTietDTO> hdctGioHang = hoaDonChiTietService.findByHoaDon(hdDTO.getId());
            dtmGioHangKhachTra.setRowCount(0);
            for (HoaDonChiTietDTO hoaDonChiTietDTO : hdctGioHang) {
                SanPhamDTO dTO = sanPhamService.findById(hoaDonChiTietDTO.getSanPham().getId());
                Object[] spKM = sanPhamService.getByKhuyenMai(dTO.getId());
                String donGia = dTO.getGiaBan();
                if (spKM != null) {
                    donGia = String.valueOf(spKM[3]);
                }
                dtmGioHangKhachTra.addRow(new Object[]{
                    hoaDonChiTietDTO.getId(),
                    dTO.getMaSP(),
                    dTO.getTenSP(),
                    Integer.parseInt(hoaDonChiTietDTO.getSoLuong() + ""),
                    VndConvertUtil.floatToVnd(Float.parseFloat(donGia)),
                    VndConvertUtil.floatToVnd(Float.parseFloat(donGia) * hoaDonChiTietDTO.getSoLuong()), hoaDonChiTietDTO.convertedTinhTrang()});
            }
//            loadLabel
            if (!Objects.isNull(hdDTO.getKhachHang())) {
                lbHoTenKhachTra.setText(hdDTO.getKhachHang().getTen());
                lbMaKhachTra.setText(hdDTO.getKhachHang().getMaKH());
                lbSDTKhachTra.setText(hdDTO.getKhachHang().getSdt());
                lbDiaChiKhachTra.setText(hdDTO.getKhachHang().getDiaChi());
                lbEmailKhachTra.setText(hdDTO.getKhachHang().getEmail());
                lbGhiChuKhachTra.setText(hdDTO.getKhachHang().getGhiChu());
                lbTongTienKhachTra.setText(VndConvertUtil.floatToVnd(hdDTO.getTongTien()));
                if (!Objects.isNull(hdDTO.getKhachHang().getTheThanhVien())) {
                    lbMaTheKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getMaTTV());
                    lbLoaiTheKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getLoaiThe().getTen());
                    lbSoDiemKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getViDiem().getTongDiem() + "");
                }
            }
        }
        updateTongTien();
    }//GEN-LAST:event_tbHoaDonKhachTraMouseClicked

    private void tbGioHangKhachTraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangKhachTraMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tbGioHangKhachTra.rowAtPoint(evt.getPoint());
            // Hiển thị menu tạm thời
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuTaoDG = new JMenuItem("Trả hàng");
            menuTaoDG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    HoaDonChiTietDTO hdctDTO = hoaDonChiTietService.findById(tbGioHangKhachTra.getValueAt(row, 0).toString());
                    SanPhamDTO spDTO = hdctDTO.getSanPham();
                    ModalSoLuongDoiTra modalSoLuongDoiTra = new ModalSoLuongDoiTra(null, true, spDTO);
                    modalSoLuongDoiTra.setVisible(true);
                    int soLuongTra = Integer.parseInt(modalSoLuongDoiTra.getSoLuong());
                    int soLuongBanDau = Integer.parseInt(tbGioHangKhachTra.getValueAt(row, 3).toString());
                    if (soLuongTra > soLuongBanDau) {
                        showMessage("Số lượng trả không hợp lệ");
                        return;
                    }
                    if (soLuongTra == soLuongBanDau) {
                        tbGioHangKhachTra.setValueAt(0, row, 3);
                        tbGioHangKhachTra.setValueAt("Hoàn trả", row, 6);
                    } else {
                        tbGioHangKhachTra.setValueAt(soLuongBanDau - soLuongTra, row, 3);
                        dtmGioHangKhachTra.addRow(new Object[]{
                            null,
                            spDTO.getMaSP(),
                            spDTO.getTenSP(),
                            soLuongTra,
                            VndConvertUtil.floatToVnd(Float.parseFloat(spDTO.getGiaBan())),
                            VndConvertUtil.floatToVnd(Float.parseFloat(spDTO.getGiaBan()) * soLuongTra), "Hoàn trả"});
                    }
                    updateTongTien();
                }
            });
            popupMenu.add(menuTaoDG);
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbGioHangKhachTraMousePressed

    private void btnTimHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHDActionPerformed
        loadTableDoiTra();
    }//GEN-LAST:event_btnTimHDActionPerformed

    private void tbDoiTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDoiTraMouseClicked

    }//GEN-LAST:event_tbDoiTraMouseClicked

    private void btnCapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat1ActionPerformed

    }//GEN-LAST:event_btnCapNhat1ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        main.showForm(new ViewDoiTra(main, user));
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed
        String maHD = txtTimKiem.getText();
        HoaDonDTO hdDTO = new HoaDonDTO();
        try {
            String id = hoaDonService.findId(maHD);
            hdDTO = hoaDonService.findById(id);
            if (hdDTO.getTinhTrangHoaDon() != TinhTrangHoaDon.DA_THANH_TOAN) {
                showMessage("Hoá đơn phải được thanh toán mới có thể trả hàng!");
                return;
            }
        } catch (Exception e) {
            showMessage("Không tìm thấy hoá đơn");
        }
        tbHoaDonKhachTra.clearAllRow();
        tbHoaDonKhachTra.addRow(hdDTO.toDataRow());
        tbHoaDonKhachTra.setRowSelectionInterval(0, 0);

        tbGioHangKhachTra.clearAllRow();
        List<HoaDonChiTietDTO> listHDCTDTO = hoaDonChiTietService.findByMaHoaDon(maHD);
        for (HoaDonChiTietDTO hoaDonChiTietDTO : listHDCTDTO) {
            tbGioHangKhachTra.addRow(new Object[]{
                hoaDonChiTietDTO.getId(),
                hoaDonChiTietDTO.getSanPham().getMaSP(),
                hoaDonChiTietDTO.getSanPham().getTenSP(),
                hoaDonChiTietDTO.getSoLuong(),
                hoaDonChiTietDTO.getDonGia(),
                hoaDonChiTietDTO.getSoLuong() * hoaDonChiTietDTO.getDonGia(),
                hoaDonChiTietDTO.convertedTinhTrang(),});
        }
        lbIdHoaDonKhachTra.setText(hdDTO.getMaHD());
        if (!Objects.isNull(hdDTO.getKhachHang())) {
            lbHoTenKhachTra.setText(hdDTO.getKhachHang().getTen());
            lbMaKhachTra.setText(hdDTO.getKhachHang().getMaKH());
            lbSDTKhachTra.setText(hdDTO.getKhachHang().getSdt());
            lbDiaChiKhachTra.setText(hdDTO.getKhachHang().getDiaChi());
            lbEmailKhachTra.setText(hdDTO.getKhachHang().getEmail());
            lbGhiChuKhachTra.setText(hdDTO.getKhachHang().getGhiChu());
            lbTongTienKhachTra.setText(VndConvertUtil.floatToVnd(hdDTO.getTongTien()));
            if (!Objects.isNull(hdDTO.getKhachHang().getTheThanhVien())) {
                lbMaTheKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getMaTTV());
                lbLoaiTheKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getLoaiThe().getTen());
                lbSoDiemKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getViDiem().getTongDiem() + "");
            }
        }
        updateTongTien();
    }//GEN-LAST:event_btnTimHoaDonActionPerformed

    private void btnQuetHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetHoaDonActionPerformed
        QrScanner qrScanner = new QrScanner(null, true);
        qrScanner.setVisible(true);
        qrScanner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HoaDonDTO hdDTO = new HoaDonDTO();
                try {
                    String id = hoaDonService.findId(qrScanner.getQrString());
                    hdDTO = hoaDonService.findById(id);
                    if (hdDTO.getTinhTrangHoaDon() != TinhTrangHoaDon.DA_THANH_TOAN) {
                        showMessage("Hoá đơn phải được thanh toán mới có thể trả hàng!");
                        return;
                    }
                } catch (Exception ex) {
                    showMessage("Không tìm thấy hoá đơn");
                }
                tbHoaDonKhachTra.clearAllRow();
                tbHoaDonKhachTra.addRow(hdDTO.toDataRow());
                tbHoaDonKhachTra.setRowSelectionInterval(0, 0);

                tbGioHangKhachTra.clearAllRow();
                List<HoaDonChiTietDTO> listHDCTDTO = hoaDonChiTietService.findByMaHoaDon(hdDTO.getMaHD());
                for (HoaDonChiTietDTO hoaDonChiTietDTO : listHDCTDTO) {
                    tbGioHangKhachTra.addRow(new Object[]{
                        hoaDonChiTietDTO.getId(),
                        hoaDonChiTietDTO.getSanPham().getMaSP(),
                        hoaDonChiTietDTO.getSanPham().getTenSP(),
                        hoaDonChiTietDTO.getSoLuong(),
                        hoaDonChiTietDTO.getDonGia(),
                        hoaDonChiTietDTO.getSoLuong() * hoaDonChiTietDTO.getDonGia(),
                        hoaDonChiTietDTO.convertedTinhTrang(),});
                }
                lbIdHoaDonKhachTra.setText(hdDTO.getMaHD());
                if (!Objects.isNull(hdDTO.getKhachHang())) {
                    lbHoTenKhachTra.setText(hdDTO.getKhachHang().getTen());
                    lbMaKhachTra.setText(hdDTO.getKhachHang().getMaKH());
                    lbSDTKhachTra.setText(hdDTO.getKhachHang().getSdt());
                    lbDiaChiKhachTra.setText(hdDTO.getKhachHang().getDiaChi());
                    lbEmailKhachTra.setText(hdDTO.getKhachHang().getEmail());
                    lbGhiChuKhachTra.setText(hdDTO.getKhachHang().getGhiChu());
                    lbTongTienKhachTra.setText(VndConvertUtil.floatToVnd(hdDTO.getTongTien()));
                    if (!Objects.isNull(hdDTO.getKhachHang().getTheThanhVien())) {
                        lbMaTheKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getMaTTV());
                        lbLoaiTheKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getLoaiThe().getTen());
                        lbSoDiemKhachTra.setText(hdDTO.getKhachHang().getTheThanhVien().getViDiem().getTongDiem() + "");
                    }
                }
                updateTongTien();
            }
        });
    }//GEN-LAST:event_btnQuetHoaDonActionPerformed

    private void btnQuetHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetHDActionPerformed
        QrScanner qrScanner = new QrScanner(null, true);
        qrScanner.setVisible(true);
        qrScanner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HoaDonDTO hdDTO = new HoaDonDTO();
                try {
                    String id = hoaDonService.findId(qrScanner.getQrString());
                    hdDTO = hoaDonService.findById(id);
                    if (hdDTO.getTinhTrangHoaDon() != TinhTrangHoaDon.DA_THANH_TOAN) {
                        showMessage("Hoá đơn phải được thanh toán mới có thể trả hàng!");
                        return;
                    }
                } catch (Exception ex) {
                    showMessage("Không tìm thấy hoá đơn");
                }

                tbDoiTra.clearAllRow();
                List<HoaDonChiTietDTO> listHDCTDTO = hoaDonChiTietService.findByMaHoaDon(hdDTO.getMaHD());
                for (HoaDonChiTietDTO hoaDonChiTietDTO : listHDCTDTO) {
                    tbDoiTra.addRow(new Object[]{
                        hoaDonChiTietDTO.getId(),
                        hoaDonChiTietDTO.getSanPham().getMaSP(),
                        hoaDonChiTietDTO.getSanPham().getTenSP(),
                        hoaDonChiTietDTO.getSoLuong(),
                        hoaDonChiTietDTO.getDonGia(),
                        hoaDonChiTietDTO.getSoLuong() * hoaDonChiTietDTO.getDonGia(),
                        hoaDonChiTietDTO.convertedTinhTrang(),});
                }
            }
        });
    }//GEN-LAST:event_btnQuetHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat1;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnQuetHD;
    private javax.swing.JButton btnQuetHoaDon;
    private javax.swing.JButton btnTimHD;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbSanPhamLoi;
    private javax.swing.JCheckBox cbSanPhamOK;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbDiaChiKhachTra;
    private javax.swing.JLabel lbEmailKhachTra;
    private javax.swing.JLabel lbGhiChuKhachTra;
    private javax.swing.JLabel lbHoTenKhachTra;
    private javax.swing.JLabel lbHoanTra;
    private javax.swing.JLabel lbIdHoaDonKhachTra;
    private javax.swing.JLabel lbLoaiTheKhachTra;
    private javax.swing.JLabel lbMaKhachTra;
    private javax.swing.JLabel lbMaNV1;
    private javax.swing.JLabel lbMaTheKhachTra;
    private javax.swing.JLabel lbNgayTao1;
    private javax.swing.JLabel lbSDTKhachTra;
    private javax.swing.JLabel lbSoDiemKhachTra;
    private javax.swing.JLabel lbTenNV1;
    private javax.swing.JLabel lbTongTienKhachTra;
    private javax.swing.JLabel lbTongTienMoiKhachTra;
    private view.swing.MaterialTabbed materialTabbed1;
    private javax.swing.JPanel pnlDsHoaDon1;
    private javax.swing.JPanel pnlDsHoaDon2;
    private javax.swing.JPanel pnlGioHang1;
    private javax.swing.JPanel pnlGioHang2;
    private javax.swing.JPanel pnlHoaDon1;
    private view.swing.table.Table tbDoiTra;
    private view.swing.table.Table tbGioHangKhachTra;
    private view.swing.table.Table tbHoaDonKhachTra;
    private view.swing.MaterialTabbed tbpnlKhachHang1;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
