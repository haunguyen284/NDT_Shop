package view.form.hoadon;

import comon.constant.TinhTrangHoaDon;
import comon.constant.dongiao.TrangThaiDonGiao;
import comon.constant.dongiao.YeuCauDonHang;
import comon.constant.khachhang.TrangThaiKhachHang;
import comon.constant.sanpham.TrangThaiSanPham;
import comon.utilities.DateTimeUtil;
import comon.utilities.QrScanner;
import comon.utilities.VndConvertUtil;
import dto.dongiao.DonGiaoDTO;
import dto.dongiao.ThongSoDTO;
import dto.hoadon.HoaDonChiTietDTO;
import dto.hoadon.HoaDonDTO;
import dto.khachhang.KhachHangDTO;
import dto.khachhang.LichSuTieuDiemDTO;
import dto.khachhang.LoaiTheDTO;
import dto.khachhang.QuyDoiDiemDTO;
import dto.khachhang.TheThanhVienDTO;
import dto.khachhang.ViDiemDTO;
import dto.nhanvien.TaiKhoanDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.persistence.NoResultException;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import service.HoaDon.impl.HoaDonServiceImpl;
import service.dongiao.DonGiaoService;
import service.dongiao.ThongSoService;
import service.dongiao.impl.DonGiaoServiceImpl;
import service.dongiao.impl.ThongSoServiceImpl;
import service.hoaDon.HoaDonService;
import service.hoadon.HoaDonChiTietService;
import service.hoadon.impl.HoaDonChiTietServiceImpl;
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
import service.nhanvien.NhanVienService;
import service.nhanvien.TaiKhoanService;
import service.nhanvien.impl.NhanVienServiceImpl;
import service.nhanvien.impl.TaiKhoanServiceImpl;
import service.sanpham.SanPhamService;
import service.sanpham.impl.SanPhamServiceImpl;
import view.dialog.Message;
import view.dialog.ShowMessageSuccessful;
import view.form.MainForm;
import view.main.Main;

public class ViewHoaDon extends javax.swing.JPanel {

    private final HoaDonService hoaDonService;
    private final SanPhamService sanPhamService;
    private final HoaDonChiTietService hoaDonChiTietService;
    private final KhachHangService khachHangService;
    private final NhanVienService nhanVienService;
    private final TaiKhoanService taiKhoanService;
    private final QuyDoiDiemService quyDoiDiemService;
    private final DonGiaoService donGiaoService;
    private final ThongSoService thongSoService;
    private final ViDiemService viDiemService;
    private final LichSuTieuDiemService lichSuTieuDiemService;
    private final LoaiTheService loaiTheService;
    private final TheThanhVienService theThanhVienService;
    private DefaultTableModel dtmGioHang;
    private final MainForm main;
    private final String user;
    private int oldValue;
    private ThongSoDTO thongSoDTO;
    private float giamGiaDoiDiem;

    /**
     *
     * @param main
     * @param user
     */
    public ViewHoaDon(MainForm main, String user) {
        initComponents();
        setOpaque(false);
        hoaDonService = new HoaDonServiceImpl();
        sanPhamService = new SanPhamServiceImpl();
        hoaDonChiTietService = new HoaDonChiTietServiceImpl();
        khachHangService = new KhachHangServiceImpl();
        nhanVienService = new NhanVienServiceImpl();
        taiKhoanService = new TaiKhoanServiceImpl();
        quyDoiDiemService = new QuyDoiDiemServiceImpl();
        donGiaoService = new DonGiaoServiceImpl();
        thongSoService = new ThongSoServiceImpl();
        viDiemService = new ViDiemServiceImpl();
        lichSuTieuDiemService = new LichSuTieuDiemServiceImpl();
        loaiTheService = new LoaiTheServiceImpl();
        theThanhVienService = new TheThanhVienServiceImpl();
        thongSoDTO = null;
        loadDataTableSanPham();
        loadDataTableHoaDon();
        dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
        tbGioHang.setModel(dtmGioHang);
        btnCapNhat.setEnabled(false);
        this.main = main;
        this.user = user;
        TaiKhoanDTO tkDTO = taiKhoanService.findByTenTaiKhoan(user);
        lbMaNV.setText(tkDTO.getNhanVien().getMaNV());
        lbTenNV.setText(tkDTO.getNhanVien().getTen());
        lbNgayTao.setText(DateTimeUtil.formatDate(new Date()));
        lbMaNV1.setText(tkDTO.getNhanVien().getMaNV());
        lbTenNV1.setText(tkDTO.getNhanVien().getTen());
        lbNgayTao1.setText(DateTimeUtil.formatDate(new Date()));
        tbGioHang.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row < 0 || column < 0 || oldValue == 0) {
                    return;
                }
                if (column == 3) {
                    TableModel model = (TableModel) e.getSource();
                    Object newValue = model.getValueAt(row, column);
                    if (Integer.parseInt(newValue.toString()) <= 0) {
                        btnXoaGioHang.doClick();
                        return;
                    }
                    System.out.println("Old value: " + oldValue);
                    System.out.println("New value: " + newValue);
                    float price = VndConvertUtil.vndToFloat(model.getValueAt(row, 4).toString());
                    float totalPrice = Integer.parseInt(newValue.toString()) * price;
                    model.setValueAt(VndConvertUtil.floatToVnd(totalPrice), row, 5); // Cập nhật giá trị của ô cột thành tiền 
                    int diff = Integer.parseInt(newValue.toString()) - oldValue;
                    SanPhamDTO sanPhamDTO = sanPhamService.findById(model.getValueAt(row, 0).toString());
                    sanPhamDTO.setSoLuongTon(Integer.parseInt(sanPhamDTO.getSoLuongTon()) - diff + "");
                    sanPhamService.update(sanPhamDTO);
                    loadDataTableSanPham();
                }
            }
        });
    }

    public final void loadDataTableSanPham() {
        List<SanPhamDTO> listDTOs = sanPhamService.findByTrangThai(TrangThaiSanPham.ACTIVE);
        DefaultTableModel dtm = (DefaultTableModel) tbSanPham.getModel();
        tbSanPham.setModel(dtm);
        dtm.setRowCount(0);
        for (SanPhamDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
    }

    public final void loadDataTableHoaDon() {
        TinhTrangHoaDon trangThai = TinhTrangHoaDon.CHUA_THANH_TOAN;
        switch (cbbLocHoaDon.getSelectedIndex()) {
            case 0:
                trangThai = TinhTrangHoaDon.CHUA_THANH_TOAN;
                break;
            case 1:
                trangThai = TinhTrangHoaDon.DA_HUY;
                break;
            case 2:
                trangThai = TinhTrangHoaDon.DA_THANH_TOAN;
                break;
            default:
                throw new AssertionError();
        }
        List<HoaDonDTO> listDTOs = hoaDonService.findByTinhTrang(trangThai);
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

    public String generateCustomerId(KhachHangDTO khachHang) {
        String hoTen = khachHang.getTen();
        Date ngaySinh = new Date(khachHang.getNgaySinh());
        LocalDate localDate = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String maKH = "";
        if (hoTen.contains(" ")) {
            String[] parts = hoTen.split(" ");
            maKH = parts[0].substring(0, 1) + parts[1].substring(0, 1);
        } else {
            maKH = hoTen.substring(0, 2);
        }

        String year = Integer.toString(localDate.getYear()).substring(2);
        maKH += year;

        String randomString = generateRandomString(5);
        maKH += randomString;

        return maKH;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private String convertedThanhTien(String donGia, String soLuong) {
        return String.format("%,.0f VND", Float.parseFloat(donGia) * Integer.parseInt(soLuong));
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
        dTO.setDonGia(VndConvertUtil.vndToFloat(donGia));
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
        float tongTienGoc = 0;
        for (int i = 0; i < dtmGioHang.getRowCount(); i++) {
            SanPhamDTO dTO = sanPhamService.findById(dtmGioHang.getValueAt(i, 0).toString());
            float giaGoc = Float.parseFloat(dTO.getGiaBan());
            int soLuong = Integer.parseInt(dtmGioHang.getValueAt(i, 3).toString());
            float thanhTienGoc = giaGoc * soLuong;
            tongTienGoc += thanhTienGoc;
        }
        float giamGia = VndConvertUtil.vndToFloat(lbGiamGia.getText());
        lbTongTien.setText(VndConvertUtil.floatToVnd(tongTienGoc));
        lbCanTra.setText(VndConvertUtil.floatToVnd(tongTienGoc - giamGia));
    }

    public void updateGiamGia() {
        float tongGiamGia = 0;
        for (int i = 0; i < dtmGioHang.getRowCount(); i++) {
            if (dtmGioHang.getValueAt(i, 6).toString().equals("Có")) {
                SanPhamDTO dTO = sanPhamService.findById(dtmGioHang.getValueAt(i, 0).toString());
                float giaGoc = Float.parseFloat(dTO.getGiaBan());
                float giaKM = VndConvertUtil.vndToFloat(dtmGioHang.getValueAt(i, 4).toString());
                int soLuong = Integer.parseInt(dtmGioHang.getValueAt(i, 3).toString());
                float thanhTienGoc = giaGoc * soLuong;
                float thanhTienKM = giaKM * soLuong;
                tongGiamGia += thanhTienGoc - thanhTienKM;
            }
        }
        lbGiamGia.setText(VndConvertUtil.floatToVnd(tongGiamGia + giamGiaDoiDiem));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tbpnlMain = new view.swing.MaterialTabbed();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        lbTenNV = new javax.swing.JLabel();
        lbNgayTao = new javax.swing.JLabel();
        tbpnlKhachHang = new view.swing.MaterialTabbed();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jdNgaySinh = new com.toedter.calendar.JDateChooser();
        txtDiaChi = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new view.swing.MyTextField();
        btnTimKH = new javax.swing.JButton();
        btnTimKhQR = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        lbMaKH = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbMaThe = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbLoaiThe = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbSoDiem = new javax.swing.JLabel();
        btnDoiDiem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbChoThanhToan = new javax.swing.JCheckBox();
        btnThanhToan = new javax.swing.JButton();
        cbNhanKemHoaDon = new javax.swing.JCheckBox();
        lbTongTien = new javax.swing.JLabel();
        lbGiamGia = new javax.swing.JLabel();
        lbCanTra = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        lbTienTraLai = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbIdHoaDon = new javax.swing.JLabel();
        pnlDatHang = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbMaNV1 = new javax.swing.JLabel();
        lbTenNV1 = new javax.swing.JLabel();
        lbNgayTao1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        lbHoTenDG = new javax.swing.JLabel();
        lbMaKHDG = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lbMaTheDG = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lbLoaiTheDG = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtDiaChiDonGiao = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChuDG = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lbMaDG = new javax.swing.JLabel();
        btnDonGiao = new javax.swing.JButton();
        lbTongTienDG = new javax.swing.JLabel();
        lbTienThuHo = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbMaHD = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        btnTaoMoi = new javax.swing.JButton();
        cbbYeuCauDG = new javax.swing.JComboBox<>();
        btnNhap = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        pnlGioHang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new view.swing.table.Table();
        pnlDsHoaDon = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDon = new view.swing.table.Table();
        cbbLocHoaDon = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlDsSP = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbSanPham = new view.swing.table.Table();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabel27 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnQuetMa = new javax.swing.JButton();
        btnXoaGioHang = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        btnLamMoiGioHang = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();

        tbpnlMain.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N

        pnlHoaDon.setBackground(new java.awt.Color(204, 229, 240));

        jPanel1.setBackground(new java.awt.Color(204, 229, 240));
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

        lbMaNV.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaNV.setText("XXXXXXXXX");

        lbTenNV.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTenNV.setText("Nguyễn Văn A");

        lbNgayTao.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbNgayTao.setText("DD/MM/YYY/HH/SS");

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
                        .addComponent(lbTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNgayTao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbMaNV))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbNgayTao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpnlKhachHang.setBackground(new java.awt.Color(204, 229, 240));

        jPanel3.setBackground(new java.awt.Color(204, 229, 240));

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
        jLabel12.setText("Ghi chú");

        jLabel13.setText("Giới tính");

        txtHoTen.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        txtGhiChu.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        cbbGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác", " " }));
        cbbGioiTinh.setBorder(null);

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
                    .addComponent(txtDiaChi)
                    .addComponent(jdNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbGioiTinh, 0, 87, Short.MAX_VALUE))
                    .addComponent(txtHoTen))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jdNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpnlKhachHang.addTab("Khách mới", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 229, 240));

        txtTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        txtTimKiem.setHint("Nhập vào mã khách hàng");

        btnTimKH.setBackground(new java.awt.Color(33, 105, 249));
        btnTimKH.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnTimKH.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKH.setText("Tìm");
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        btnTimKhQR.setBackground(new java.awt.Color(33, 105, 249));
        btnTimKhQR.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnTimKhQR.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKhQR.setText("QR");

        jLabel20.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(33, 105, 249));
        jLabel20.setText("Họ tên");

        lbHoTen.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbHoTen.setForeground(new java.awt.Color(0, 0, 0));
        lbHoTen.setText("N/A");

        lbMaKH.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaKH.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKH.setText("N/A");

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 105, 249));
        jLabel23.setText("Mã KH");

        lbMaThe.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaThe.setForeground(new java.awt.Color(0, 0, 0));
        lbMaThe.setText("Chưa có thẻ");

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(33, 105, 249));
        jLabel25.setText("Mã Thẻ");

        lbLoaiThe.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbLoaiThe.setForeground(new java.awt.Color(0, 0, 0));
        lbLoaiThe.setText("Chưa có thẻ");

        jLabel29.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(33, 105, 249));
        jLabel29.setText("Loại thẻ");

        jLabel30.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(33, 105, 249));
        jLabel30.setText("Số điểm");

        lbSoDiem.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbSoDiem.setForeground(new java.awt.Color(0, 0, 0));
        lbSoDiem.setText("0");

        btnDoiDiem.setBackground(new java.awt.Color(33, 105, 249));
        btnDoiDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiDiem.setText("Đổi điểm");
        btnDoiDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbLoaiThe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSoDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMaThe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKhQR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addComponent(btnDoiDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnTimKH, btnTimKhQR});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKhQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lbHoTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lbMaKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lbMaThe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lbLoaiThe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lbSoDiem)
                    .addComponent(btnDoiDiem))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tbpnlKhachHang.addTab("Khách cũ", jPanel4);

        jPanel2.setBackground(new java.awt.Color(204, 229, 240));
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

        buttonGroup1.add(cbChoThanhToan);
        cbChoThanhToan.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        cbChoThanhToan.setForeground(new java.awt.Color(33, 105, 249));
        cbChoThanhToan.setSelected(true);
        cbChoThanhToan.setText("Chờ thanh toán");
        cbChoThanhToan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbChoThanhToanStateChanged(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(33, 105, 249));
        btnThanhToan.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Xác nhận");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(cbNhanKemHoaDon);
        cbNhanKemHoaDon.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        cbNhanKemHoaDon.setForeground(new java.awt.Color(33, 105, 249));
        cbNhanKemHoaDon.setText("Nhận kèm hoá đơn");

        lbTongTien.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTongTien.setText("0");

        lbGiamGia.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbGiamGia.setText("0");

        lbCanTra.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbCanTra.setText("0");

        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        lbTienTraLai.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        lbTienTraLai.setText("0");

        jLabel19.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(33, 105, 249));
        jLabel19.setText("ID");

        lbIdHoaDon.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbIdHoaDon.setText("Chưa tạo hoá đơn");
        lbIdHoaDon.setToolTipText("");

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
                            .addComponent(cbChoThanhToan)
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
                                    .addComponent(cbNhanKemHoaDon)
                                    .addComponent(lbIdHoaDon))
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
                    .addComponent(cbChoThanhToan)
                    .addComponent(cbNhanKemHoaDon))
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
                    .addComponent(tbpnlKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpnlMain.addTab("Hoá đơn", pnlHoaDon);

        pnlDatHang.setBackground(new java.awt.Color(204, 229, 240));

        jPanel5.setBackground(new java.awt.Color(204, 229, 240));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(33, 105, 249));
        jLabel6.setText("Mã nhân viên");

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTenNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNgayTao1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbMaNV1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lbTenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lbNgayTao1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 229, 240));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(33, 105, 249));
        jLabel41.setText("Họ tên");

        lbHoTenDG.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbHoTenDG.setForeground(new java.awt.Color(0, 0, 0));
        lbHoTenDG.setText("N/A");

        lbMaKHDG.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaKHDG.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKHDG.setText("N/A");

        jLabel42.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(33, 105, 249));
        jLabel42.setText("Mã KH");

        lbMaTheDG.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaTheDG.setForeground(new java.awt.Color(0, 0, 0));
        lbMaTheDG.setText("Chưa có thẻ");

        jLabel43.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(33, 105, 249));
        jLabel43.setText("Mã Thẻ");

        lbLoaiTheDG.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbLoaiTheDG.setForeground(new java.awt.Color(0, 0, 0));
        lbLoaiTheDG.setText("Chưa có thẻ");

        jLabel44.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(33, 105, 249));
        jLabel44.setText("Loại thẻ");

        jLabel46.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(33, 105, 249));
        jLabel46.setText("Địa chỉ");

        jLabel47.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(33, 105, 249));
        jLabel47.setText("Ghi chú");

        txtGhiChuDG.setColumns(20);
        txtGhiChuDG.setRows(5);
        jScrollPane1.setViewportView(txtGhiChuDG);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel42)
                            .addComponent(jLabel41)
                            .addComponent(jLabel44))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbHoTenDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbMaKHDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbMaTheDG, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                            .addComponent(lbLoaiTheDG, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChiDonGiao)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lbHoTenDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(lbMaKHDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(lbMaTheDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lbLoaiTheDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtDiaChiDonGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 229, 240));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        jLabel35.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(33, 105, 249));
        jLabel35.setText("Tổng tiền");

        jLabel37.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(33, 105, 249));
        jLabel37.setText("Tiền thu hộ");

        jLabel38.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(33, 105, 249));
        jLabel38.setText("Mã DG");

        lbMaDG.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaDG.setText("Chưa tạo đơn giao");
        lbMaDG.setToolTipText("");

        btnDonGiao.setBackground(new java.awt.Color(33, 105, 249));
        btnDonGiao.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        btnDonGiao.setForeground(new java.awt.Color(255, 255, 255));
        btnDonGiao.setText("Tạo đơn giao");
        btnDonGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonGiaoActionPerformed(evt);
            }
        });

        lbTongTienDG.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTongTienDG.setText("0");

        lbTienThuHo.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbTienThuHo.setText("0");

        jLabel39.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(33, 105, 249));
        jLabel39.setText("Mã HD");

        lbMaHD.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lbMaHD.setText("Chưa tạo hoá đơn");
        lbMaHD.setToolTipText("");

        jLabel40.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(33, 105, 249));
        jLabel40.setText("Yêu cầu đơn hàng");

        jLabel45.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(33, 105, 249));
        jLabel45.setText("Thông số");

        btnTaoMoi.setBackground(new java.awt.Color(33, 105, 249));
        btnTaoMoi.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnTaoMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        cbbYeuCauDG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cho xem", "Cho thử", "Không cho xem" }));

        btnNhap.setBackground(new java.awt.Color(33, 105, 249));
        btnNhap.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnNhap.setText("Nhập");
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(33, 105, 249));
        btnSua.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setEnabled(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDonGiao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel45)
                            .addComponent(jLabel38))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTongTienDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTienThuHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbYeuCauDG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbMaDG)
                                    .addComponent(lbMaHD))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lbMaDG))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lbMaHD))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lbTongTienDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lbTienThuHo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(cbbYeuCauDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(btnNhap)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addComponent(btnDonGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoMoi)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout pnlDatHangLayout = new javax.swing.GroupLayout(pnlDatHang);
        pnlDatHang.setLayout(pnlDatHangLayout);
        pnlDatHangLayout.setHorizontalGroup(
            pnlDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        pnlDatHangLayout.setVerticalGroup(
            pnlDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpnlMain.addTab("Đặt hàng", pnlDatHang);

        pnlGioHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên", "Số lượng", "Đơn giá", "Thành tiền", "Giảm giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        pnlDsHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlDsHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)), "Danh sách hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Medium", 0, 14), new java.awt.Color(33, 105, 249))); // NOI18N

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));

        tbHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        tbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHoaDon.setOpaque(false);
        tbHoaDon.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbHoaDonMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbHoaDon);

        cbbLocHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã huỷ", "Đã thanh toán" }));
        cbbLocHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocHoaDonActionPerformed(evt);
            }
        });
        cbbLocHoaDon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbbLocHoaDonPropertyChange(evt);
            }
        });

        jLabel5.setText("Mã");

        jButton1.setBackground(new java.awt.Color(33, 105, 249));
        jButton1.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("T");

        jButton2.setBackground(new java.awt.Color(33, 105, 249));
        jButton2.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Q");

        javax.swing.GroupLayout pnlDsHoaDonLayout = new javax.swing.GroupLayout(pnlDsHoaDon);
        pnlDsHoaDon.setLayout(pnlDsHoaDonLayout);
        pnlDsHoaDonLayout.setHorizontalGroup(
            pnlDsHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDsHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDsHoaDonLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDsHoaDonLayout.createSequentialGroup()
                        .addComponent(cbbLocHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(38, 38, 38))))
        );
        pnlDsHoaDonLayout.setVerticalGroup(
            pnlDsHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsHoaDonLayout.createSequentialGroup()
                .addGroup(pnlDsHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(cbbLocHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDsSP.setBackground(new java.awt.Color(255, 255, 255));
        pnlDsSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 105, 249)));

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 105, 249));
        jLabel26.setText("Danh sách sản phẩm");
        jLabel26.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbSanPham.setBackground(new java.awt.Color(255, 255, 255));
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

        jScrollPane11.setViewportView(jTextPane4);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Mã ");

        jButton3.setBackground(new java.awt.Color(33, 105, 249));
        jButton3.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm kiếm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDsSPLayout = new javax.swing.GroupLayout(pnlDsSP);
        pnlDsSP.setLayout(pnlDsSPLayout);
        pnlDsSPLayout.setHorizontalGroup(
            pnlDsSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDsSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDsSPLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        pnlDsSPLayout.setVerticalGroup(
            pnlDsSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDsSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDsSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDsSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlDsSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
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
        btnXoaGioHang.setText("Xoá khỏi giỏ");
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
        btnLamMoiGioHang.setText("Làm mới giỏ ");
        btnLamMoiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiGioHangActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(33, 105, 249));
        btnCapNhat.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
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
                    .addComponent(pnlDsHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCapNhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoiGioHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaGioHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuetMa))
                    .addComponent(pnlDsSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhat, btnLamMoiGioHang, btnQuetMa, btnTaoHoaDon, btnXoaGioHang});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbpnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQuetMa)
                            .addComponent(btnXoaGioHang)
                            .addComponent(btnLamMoiGioHang)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat))
                        .addGap(9, 9, 9)
                        .addComponent(pnlDsHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDsSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoiGioHang, btnQuetMa, btnTaoHoaDon, btnXoaGioHang});

    }// </editor-fold>//GEN-END:initComponents

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
        int row = tbGioHang.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (Integer.parseInt(tbGioHang.getValueAt(row, 3).toString()) < 0) {
            return;
        }
        oldValue = Integer.parseInt(tbGioHang.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tbGioHangMouseClicked

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        int row = tbHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (evt.getClickCount() == 2) {
            HoaDonDTO hdDTO = hoaDonService.findById(tbHoaDon.getValueAt(row, 0).toString());
            lbIdHoaDon.setText(hdDTO.getMaHD());
            List<HoaDonChiTietDTO> hdctGioHang = hoaDonChiTietService.findByHoaDon(hdDTO.getId());
            dtmGioHang.setRowCount(0);
            for (HoaDonChiTietDTO hoaDonChiTietDTO : hdctGioHang) {
                SanPhamDTO dTO = sanPhamService.findById(hoaDonChiTietDTO.getSanPham().getId());
                Object[] spKM = sanPhamService.getByKhuyenMai(dTO.getId());
                String donGia = dTO.getGiaBan();
                String khuyenMai = "Không";
                if (spKM != null) {
                    donGia = String.valueOf(spKM[3]);
                    khuyenMai = "Có";
                }
                dtmGioHang.addRow(new Object[]{
                    dTO.getId(),
                    dTO.getMaSP(),
                    dTO.getTenSP(),
                    Integer.parseInt(hoaDonChiTietDTO.getSoLuong() + ""),
                    VndConvertUtil.floatToVnd(Float.parseFloat(donGia)),
                    convertedThanhTien(donGia, hoaDonChiTietDTO.getSoLuong() + ""), khuyenMai});
            }
//            loadLabel
            if (!Objects.isNull(hdDTO.getKhachHang())) {
                tbpnlKhachHang.setSelectedIndex(1);
                lbHoTen.setText(hdDTO.getKhachHang().getTen());
                lbMaKH.setText(hdDTO.getKhachHang().getMaKH());
                if (!Objects.isNull(hdDTO.getKhachHang().getTheThanhVien())) {
                    lbMaThe.setText(hdDTO.getKhachHang().getTheThanhVien().getMaTTV());
                    lbLoaiThe.setText(hdDTO.getKhachHang().getTheThanhVien().getLoaiThe().getTen());
                    lbSoDiem.setText(hdDTO.getKhachHang().getTheThanhVien().getViDiem().getTongDiem() + "");
                }
                btnTaoHoaDon.setEnabled(false);
                btnCapNhat.setEnabled(true);
            }
        }
        updateGiamGia();
        updateTongTien();
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int row = tbSanPham.getSelectedRow();
        if (row < 0) {
            return;
        }
        SanPhamDTO dTO = getSanPhamFromTable(row);
        if (evt.getClickCount() == 2) {
            Object[] spKM = sanPhamService.getByKhuyenMai(dTO.getId());
            ModalSoLuong modalSoLuong = new ModalSoLuong(null, true, dTO);
            modalSoLuong.setVisible(true);
            if (!modalSoLuong.isCheck()) {
                return;
            }
            try {
                String soLuong = modalSoLuong.getSoLuong();
                int rowIndex = isDuplicate(dTO);
                if (rowIndex >= 0) {
                    int soLuongCu = Integer.parseInt(dtmGioHang.getValueAt(rowIndex, 3).toString());
                    int soLuongMoi = soLuongCu + Integer.parseInt(soLuong);
                    dtmGioHang.setValueAt(soLuongMoi, rowIndex, 3);
                } else {
                    String donGia = dTO.getGiaBan();
                    String khuyenMai = "Không";
                    if (spKM != null) {
                        donGia = String.valueOf(spKM[3]);
                        khuyenMai = "Có";
                    }
                    dtmGioHang.addRow(new Object[]{
                        dTO.getId(),
                        dTO.getMaSP(),
                        dTO.getTenSP(),
                        Integer.parseInt(soLuong),
                        VndConvertUtil.floatToVnd(Float.parseFloat(donGia)),
                        convertedThanhTien(donGia, soLuong), khuyenMai});
                }
                updateGiamGia();
                updateTongTien();
                loadDataTableSanPham();
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getMessage());
            }
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

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
            ShowMessageSuccessful.showSuccessful("Xoá thành công");
            dtmGioHang.removeRow(row);
            dtmGioHang.fireTableDataChanged();
            updateGiamGia();
            updateTongTien();
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
            ShowMessageSuccessful.showSuccessful("Xoá thành công " + offset + " hàng!");
        } else {
            showMessage("Xoá thất bại");
        }
        dtmGioHang.fireTableDataChanged();
        updateGiamGia();
        updateTongTien();
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
                Object[] spKM = sanPhamService.getByKhuyenMai(dTO.getId());
                ModalSoLuong modalSoLuong = new ModalSoLuong(null, true, dTO);
                modalSoLuong.setVisible(true);
                modalSoLuong.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            String soLuong = modalSoLuong.getSoLuong();
                            SanPhamDTO dTO = sanPhamService.findById(qrScanner.getQrString());
                            System.out.println(dTO.getGiaBan() + "===============");
                            int rowIndex = isDuplicate(dTO);
                            if (rowIndex >= 0) {
                                int soLuongCu = Integer.parseInt(dtmGioHang.getValueAt(rowIndex, 3).toString());
                                int soLuongMoi = soLuongCu + Integer.parseInt(soLuong);
                                dtmGioHang.setValueAt(soLuongMoi, rowIndex, 3);
                            } else {
                                String donGia = dTO.getGiaBan();
                                String khuyenMai = "Không";
                                if (spKM != null) {
                                    donGia = String.valueOf(spKM[3]);
                                    khuyenMai = "Có";
                                }
                                dtmGioHang.addRow(new Object[]{
                                    dTO.getId(),
                                    dTO.getMaSP(),
                                    dTO.getTenSP(),
                                    Integer.parseInt(soLuong),
                                    VndConvertUtil.floatToVnd(Float.parseFloat(donGia)),
                                    convertedThanhTien(donGia, soLuong), khuyenMai});
                                updateGiamGia();
                                updateTongTien();
                            }
                            updateGiamGia();
                            updateTongTien();
                            loadDataTableSanPham();
                        } catch (NumberFormatException numberFormatException) {
                        }
                    }
                });
            }
        });
    }//GEN-LAST:event_btnQuetMaActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        if (dtmGioHang.getRowCount() <= 0) {
            showMessage("Vui lòng thêm sản phẩm vào giỏ trước!");
            return;
        }
//      Tạo hoá đơn
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        Float tongTien = VndConvertUtil.vndToFloat(lbTongTien.getText()) - VndConvertUtil.vndToFloat(lbGiamGia.getText());
        hoaDonDTO.setTongTien(tongTien);
        hoaDonDTO.setTinhTrangHoaDon(TinhTrangHoaDon.CHUA_THANH_TOAN);

        hoaDonDTO.setMaHD("HD" + generateRandomString(5));
        hoaDonDTO.setNhanVien(nhanVienService.findById(nhanVienService.findId(lbMaNV.getText())));
        KhachHangDTO khDTO = new KhachHangDTO();
//            Kiểm tra xem tabbedPane nào đang được bật (Khách mới hay khách cũ)
        if (tbpnlKhachHang.getSelectedIndex() == 0) {
//                Khi bật tab đầu tiên
            if (!txtHoTen.getText().isBlank()) {
                khDTO.setMaKH(generateCustomerId(khDTO));
                khachHangService.save(khDTO);
                khDTO = khachHangService.findById(khachHangService.findId(khDTO.getMaKH()));
                khDTO.setTen(txtHoTen.getText());
                khDTO.setDiaChi(txtDiaChi.getText());
                khDTO.setSdt(txtSDT.getText());
                khDTO.setGioiTinh(cbbGioiTinh.getSelectedItem().toString());
                khDTO.setEmail(txtEmail.getText());
                khDTO.setSoLanMua(0);
                khDTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_1);
                hoaDonDTO.setKhachHang(khDTO);
                hoaDonDTO.setTenKH(khDTO.getTen());
            }
        } else {
//                Khi bật tab còn lại
            khDTO = khachHangService.findById(khachHangService.findId(lbMaKH.getText()));
            if (!lbMaThe.equals("Chưa có thẻ")) {
                Long soTienTichDiem = quyDoiDiemService.findById("340EB4DB-4EDE-4F34-87B8-E3DE0F418091").getTienTichDiem();
                hoaDonDTO.setSoDiemQuyDoi((int) (VndConvertUtil.vndToFloat(lbTongTien.getText()) / soTienTichDiem));
                hoaDonDTO.setKhachHang(khDTO);
                hoaDonDTO.setTenKH(khDTO.getTen());
            }
        }
        hoaDonDTO = hoaDonService.save(hoaDonDTO);
        if (!Objects.isNull(hoaDonDTO)) {
            ShowMessageSuccessful.showSuccessful("Tạo hoá đơn thành công!");
            lbIdHoaDon.setText(hoaDonDTO.getMaHD());
        } else {
            showMessage("Tạo hoá đơn thất bại!");
            return;
        }
        int row = tbGioHang.getRowCount();
        for (int i = 0; i < row; i++) {
            HoaDonChiTietDTO dTO = getHoaDonChiTietFromTable(i);
            dTO.setHoaDon(hoaDonDTO);
            dTO.setTinhTrangHoaDon(TinhTrangHoaDon.CHUA_THANH_TOAN);
            hoaDonChiTietService.save(dTO);
        }
        loadDataTableHoaDon();
        btnTaoHoaDon.setEnabled(false);
        btnCapNhat.setEnabled(true);
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
//        Kiểm tra xem đã tạo hoá đơn hay chưa
        if (lbIdHoaDon.getText().equals("Chưa tạo hoá đơn")) {
            showMessage("Hãy tạo hoá đơn trước khi xác nhận");
            return;
        }
//        Kiểm tra trạng thái chờ hay thanh toán
        if (cbChoThanhToan.isSelected()) {
//            Khi tích chờ thanh toán
            HoaDonDTO hdDTO = hoaDonService.findById(hoaDonService.findId(lbIdHoaDon.getText()));
            KhachHangDTO khDTO = new KhachHangDTO();
//            Kiểm tra xem tabbedPane nào đang được bật (Khách mới hay khách cũ)
            if (tbpnlKhachHang.getSelectedIndex() == 0) {
//                Khi bật tab đầu tiên
                if (!lbHoTen.getText().equals("N/A")) {
                    khDTO.setMaKH(generateCustomerId(khDTO));
                    khachHangService.save(khDTO);
                    khDTO = khachHangService.findById(khachHangService.findId(khDTO.getMaKH()));
                    khDTO.setTen(txtHoTen.getText());
                    khDTO.setDiaChi(txtDiaChi.getText());
                    khDTO.setSdt(txtSDT.getText());
                    khDTO.setGioiTinh(cbbGioiTinh.getSelectedItem().toString());
                    khDTO.setEmail(txtEmail.getText());
                    khDTO.setSoLanMua(0);
                    khDTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_3);
                    hdDTO.setKhachHang(khDTO);
                    hdDTO.setTenKH(khDTO.getTen());
                    hdDTO.setDiaChi(khDTO.getDiaChi());
                    hdDTO.setTongTien(VndConvertUtil.vndToFloat(lbTongTien.getText()));
                }

            } else {
//                Khi bật tab còn lại
                khDTO = khachHangService.findById(khachHangService.findId(lbMaKH.getText()));
                hdDTO.setKhachHang(khDTO);
                hdDTO.setTenKH(khDTO.getTen());
                hdDTO.setDiaChi(khDTO.getDiaChi());
                hdDTO.setTongTien(VndConvertUtil.vndToFloat(lbTongTien.getText()));
            }

            hoaDonService.save(hdDTO);
            main.showForm(new ViewHoaDon(main, user));
        } else {
//          Khi tích thanh toán
            HoaDonDTO hdDTO = hoaDonService.findById(hoaDonService.findId(lbIdHoaDon.getText()));
            KhachHangDTO khDTO = new KhachHangDTO();
//            Kiểm tra xem tabbedPane nào đang được bật (Khách mới hay khách cũ)
            if (tbpnlKhachHang.getSelectedIndex() == 0) {
//                Khi bật tab đầu tiên
                khDTO.setTen(txtHoTen.getText());
                khDTO.setDiaChi(txtDiaChi.getText());
                khDTO.setSdt(txtSDT.getText());
                khDTO.setGioiTinh(cbbGioiTinh.getSelectedItem().toString());
                khDTO.setEmail(txtEmail.getText());
                khDTO.setSoLanMua(0);
                khDTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_1);
                if (!lbHoTen.getText().isBlank()) {
                    khDTO.setMaKH(generateCustomerId(khDTO));
                }
                khachHangService.save(khDTO);
                khDTO = khachHangService.findById(khachHangService.findId(khDTO.getMaKH()));
            } else {
//                Khi bật tab còn lại
                khDTO = khachHangService.findById(khachHangService.findId(lbMaKH.getText()));
                if (!lbMaThe.equals("Chưa có thẻ")) {
                    QuyDoiDiemDTO qdDTO = quyDoiDiemService.findById("340EB4DB-4EDE-4F34-87B8-E3DE0F418091");
                    float soTienTichDiem = qdDTO.getTienTichDiem();
                    float tongTienHD = VndConvertUtil.vndToFloat(lbTongTien.getText());
                    int soDiemQuyDoi = (int) (tongTienHD / soTienTichDiem);
                    int diemSuDung = (int) (giamGiaDoiDiem / soTienTichDiem);
                    ViDiemDTO vdDTO = khDTO.getTheThanhVien().getViDiem();
                    if (giamGiaDoiDiem <= 0) {
                        hdDTO.setSoDiemQuyDoi(soDiemQuyDoi);
                        vdDTO.setTongDiem(vdDTO.getTongDiem() + hdDTO.getSoDiemQuyDoi());
                    }
                    vdDTO.setDiemDaDung((int) (vdDTO.getDiemDaDung() + diemSuDung));
                    vdDTO.setDiemDaCong(vdDTO.getTongDiem() - vdDTO.getDiemDaDung());
                    viDiemService.save(vdDTO);
                    vdDTO = viDiemService.findById(vdDTO.getId());
                    LichSuTieuDiemDTO lstdDTO = new LichSuTieuDiemDTO();
                    lstdDTO.setViDiem(vdDTO);
                    lstdDTO.setQuyDoiDiem(qdDTO);
                    lstdDTO.setSoDiemCong(vdDTO.getDiemDaCong());
                    lstdDTO.setSoDiemDaDung(diemSuDung);
                    lstdDTO.setNgaySuDung(DateTimeUtil.stringToDate(lbNgayTao.getText()).getTime());
                    lichSuTieuDiemService.save(lstdDTO);
//                    Xét thăng hạng cho thẻ thành viên
                    TheThanhVienDTO ttvDTO = khDTO.getTheThanhVien();
                    List<LoaiTheDTO> ltDTOs = loaiTheService.findAll();
                    for (LoaiTheDTO ltDTO : ltDTOs) {
                        if (vdDTO.getTongDiem() >= ltDTO.getGiaTri()) {
                            ttvDTO.setLoaiThe(ltDTO);
                        }
                    }
                    theThanhVienService.save(ttvDTO);
                }
            }
            hdDTO.setKhachHang(khDTO);
            hdDTO.setTenKH(khDTO.getTen());
            hdDTO.setDiaChi(khDTO.getDiaChi());
            hdDTO.setTongTien(VndConvertUtil.vndToFloat(lbTongTien.getText()));
            hdDTO.setTinhTrangHoaDon(TinhTrangHoaDon.DA_THANH_TOAN);
            hdDTO.setNgayThanhToan(DateTimeUtil.stringToDate(lbNgayTao.getText()).getTime());
            hdDTO = hoaDonService.save(hdDTO);
            List<HoaDonChiTietDTO> hdctGioHang = hoaDonChiTietService.findByHoaDon(hdDTO.getId());
            for (HoaDonChiTietDTO hoaDonChiTietDTO : hdctGioHang) {
                hoaDonChiTietDTO.setTinhTrangHoaDon(TinhTrangHoaDon.DA_THANH_TOAN);
                hoaDonChiTietService.save(hoaDonChiTietDTO);
            }
            khDTO.setSoLanMua(khDTO.getSoLanMua() + 1);
            khachHangService.save(khDTO);
            if (!Objects.isNull(hdDTO)) {
                ShowMessageSuccessful.showSuccessful("Thanh toán thành công!");
                main.showForm(new ViewHoaDon(main, user));
            } else {
                showMessage("Thanh toán thất bại!");
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (!lbIdHoaDon.getText().isBlank()) {
            boolean check = false;
//            Lấy list hdct trên giỏ hàng
            List<HoaDonChiTietDTO> listHdct = hoaDonChiTietService.findByHoaDon(hoaDonService.findId(lbIdHoaDon.getText()));
//            Lấy list đối tượng được thay đổi
            for (int i = 0; i < dtmGioHang.getRowCount(); i++) {
                for (HoaDonChiTietDTO hoaDonChiTietDTO : listHdct) {
                    if (hoaDonChiTietDTO.getSanPham().getId().equals(dtmGioHang.getValueAt(i, 0))) {
                        hoaDonChiTietDTO.setSoLuong(Integer.parseInt(dtmGioHang.getValueAt(i, 3).toString()));
                        hoaDonChiTietService.save(hoaDonChiTietDTO);
                        check = true;
                        break;
                    }
                }
            }
            if (check) {
                ShowMessageSuccessful.showSuccessful("Cập nhật thành công hoá đơn!");
            } else {
                showMessage("Thất bại!");
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cbChoThanhToanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbChoThanhToanStateChanged
        if (!cbChoThanhToan.isSelected()) {
            btnThanhToan.setText("Thanh toán");
            btnThanhToan.setEnabled(!txtKhachTra.getText().isBlank());
        } else {
            btnThanhToan.setText("Xác nhận");
            btnThanhToan.setEnabled(true);
        }
    }//GEN-LAST:event_cbChoThanhToanStateChanged

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
        float tienKhach = 0;
        try {
            tienKhach = Float.parseFloat(txtKhachTra.getText());
            txtKhachTra.setBackground(Color.white);
        } catch (NumberFormatException numberFormatException) {
            txtKhachTra.setBackground(Color.red);
        }
        float canTra = VndConvertUtil.vndToFloat(lbCanTra.getText());
        float tienTraLai = tienKhach - canTra;
        if (cbNhanKemHoaDon.isSelected()) {
            btnThanhToan.setEnabled(tienTraLai >= 0);
        }
        try {
            lbTienTraLai.setText(VndConvertUtil.floatToVnd(tienTraLai));
            if (tienTraLai >= 0) {
                lbTienTraLai.setForeground(new Color(0, 128, 0));
            } else {
                lbTienTraLai.setForeground(Color.red);
            }
            txtKhachTra.setBackground(Color.white);
        } catch (Exception e) {
            txtKhachTra.setBackground(Color.red);
        }
    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        if (txtTimKiem.getText().isBlank()) {
            showMessage("Vui lòng nhập vào mã KH cần tìm kiếm");
            return;
        }
        try {
            KhachHangDTO khDTO = khachHangService.findById(khachHangService.findId(txtTimKiem.getText()));
            lbHoTen.setText(khDTO.getTen());
            lbMaKH.setText(khDTO.getMaKH());
            if (!Objects.isNull(khDTO.getTheThanhVien())) {
                lbMaThe.setText(khDTO.getTheThanhVien().getMaTTV());
                lbLoaiThe.setText(khDTO.getTheThanhVien().getLoaiThe().getTen());
                lbSoDiem.setText(khDTO.getTheThanhVien().getViDiem().getDiemDaCong() + "");
            }
        } catch (Exception e) {
            showMessage("Không tìm thấy mã KH " + txtTimKiem.getText());
        }

    }//GEN-LAST:event_btnTimKHActionPerformed

    private void cbbLocHoaDonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbbLocHoaDonPropertyChange
        loadDataTableHoaDon();
    }//GEN-LAST:event_cbbLocHoaDonPropertyChange

    private void cbbLocHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocHoaDonActionPerformed
        loadDataTableHoaDon();
    }//GEN-LAST:event_cbbLocHoaDonActionPerformed

    private void btnDonGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonGiaoActionPerformed
        if (lbMaDG.getText().equals("Chưa tạo đơn giao")) {
            showMessage("Vui lòng chọn tạo hoá đơn trước khi tạo đơn giao!");
            return;
        }
        DonGiaoDTO dgDTO = donGiaoService.findById(donGiaoService.findId(lbMaDG.getText()));
        dgDTO.setDiaChi(txtDiaChiDonGiao.getText());
        dgDTO.setGhiChu(txtGhiChu.getText());
        switch (cbbYeuCauDG.getSelectedIndex()) {
            case 0:
                dgDTO.setYeuCauDonHang(YeuCauDonHang.CHO_XEM);
                break;
            case 1:
                dgDTO.setYeuCauDonHang(YeuCauDonHang.CHO_THU);
                break;
            case 2:
                dgDTO.setYeuCauDonHang(YeuCauDonHang.KHONG_CHO_XEM);
                break;
        }
        dgDTO.setTrangThaiDonGiao(TrangThaiDonGiao.DANG_GIAO);
        if (Objects.isNull(donGiaoService.save(dgDTO))) {
            showMessage("Tạo đơn giao thất bại!");
        } else {
            ShowMessageSuccessful.showSuccessful("Tạo đơn giao thành công!");
        }
    }//GEN-LAST:event_btnDonGiaoActionPerformed

    private void tbHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tbHoaDon.rowAtPoint(evt.getPoint());
            // Hiển thị menu tạm thời
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuTaoDG = new JMenuItem("Tạo Đơn giao");
            menuTaoDG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    HoaDonDTO hdDTO = hoaDonService.findById(tbHoaDon.getValueAt(row, 0).toString());
                    if (Objects.isNull(hdDTO.getKhachHang())) {
                        showMessage("Vui lòng cập nhật thông tin KH trước khi tạo đơn giao!");
                        MouseListener mouseListener = tbHoaDon.getMouseListeners()[0];
                        mouseListener.mouseClicked(new MouseEvent(tbHoaDon, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, evt.getX(), evt.getY(), 1, false));
                        return;
                    } else {
                        tbpnlMain.setSelectedIndex(1);
                        if (!Objects.isNull(hdDTO.getKhachHang().getTheThanhVien())) {
                            lbMaTheDG.setText(hdDTO.getKhachHang().getTheThanhVien().getMaTTV());
                            lbLoaiTheDG.setText(hdDTO.getKhachHang().getTheThanhVien().getLoaiThe().getTen());
                        }
                        lbHoTenDG.setText(hdDTO.getKhachHang().getTen());
                        lbMaKHDG.setText(hdDTO.getKhachHang().getMaKH());
                    }
                    DonGiaoDTO dgDTO = new DonGiaoDTO();
                    dgDTO.setHoaDon(hdDTO);
                    dgDTO.setMaDG("DG" + generateRandomString(5));
                    dgDTO.setNgayGiao(DateTimeUtil.stringToDate(lbNgayTao.getText()).getTime());
                    dgDTO.setSdtNguoiNhan(dgDTO.getHoaDon().getKhachHang().getSdt());
                    if (hdDTO.getTinhTrangHoaDon() == TinhTrangHoaDon.DA_THANH_TOAN) {
                        dgDTO.setTienThuHo(0);
                    } else {
                        dgDTO.setTienThuHo(dgDTO.getHoaDon().getTongTien());
                    }
                    if (!Objects.isNull(donGiaoService.save(dgDTO))) {
                        showMessage("OK");
                    } else {
                        showMessage("Lỗi");
                        return;
                    }

                    lbMaDG.setText(dgDTO.getMaDG());
                    lbMaHD.setText(dgDTO.getHoaDon().getMaHD());
                    lbTongTienDG.setText(VndConvertUtil.floatToVnd(dgDTO.getHoaDon().getTongTien()));
                    lbTienThuHo.setText(VndConvertUtil.floatToVnd(dgDTO.getTienThuHo()));
                }
            });
            popupMenu.add(menuTaoDG);
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbHoaDonMousePressed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        main.showForm(new ViewHoaDon(main, user));
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        if (lbMaDG.getText().isBlank()) {
            showMessage("Vui lòng tạo đơn giao!");
            return;
        }
        ModalThongSo modalThongSo = new ModalThongSo(null, true);
        modalThongSo.setVisible(true);

        DonGiaoDTO dgDTO = donGiaoService.findById(donGiaoService.findId(lbMaDG.getText()));
        thongSoDTO = thongSoService.findById(modalThongSo.getThongSoDTO().getMa());
        dgDTO.setThongSo(thongSoDTO);
        if (modalThongSo.isCheck()) {
            donGiaoService.save(dgDTO);
        }
        btnSua.setEnabled(true);
        btnNhap.setEnabled(false);
    }//GEN-LAST:event_btnNhapActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ModalThongSo modalThongSo = new ModalThongSo(null, true, thongSoDTO);
        modalThongSo.setVisible(true);
        DonGiaoDTO dgDTO = donGiaoService.findById(donGiaoService.findId(lbMaDG.getText()));
        thongSoDTO = thongSoService.findById(modalThongSo.getThongSoDTO().getMa());
        dgDTO.setThongSo(thongSoDTO);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnDoiDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiDiemActionPerformed
        if (lbMaThe.getText().equals("N/A")) {
            showMessage("Khách hàng này chưa có thẻ, không thể đổi điểm");
            return;
        }

        if (lbSoDiem.getText().equals("0")) {
            showMessage("Không đủ điểm để đổi");
            return;
        }
        KhachHangDTO dTO = khachHangService.findById(khachHangService.findId(lbMaKH.getText()));
        ViDiemDTO vdDTO = dTO.getTheThanhVien().getViDiem();
        QuyDoiDiemDTO qdDTO = quyDoiDiemService.findById("340EB4DB-4EDE-4F34-87B8-E3DE0F418091");
        ModalViDiem modalViDiem = new ModalViDiem(null, true, vdDTO);
        modalViDiem.setVisible(true);
        giamGiaDoiDiem = (float) 1.0 * modalViDiem.getDiemDoi() * qdDTO.getTienTichDiem();
        lbSoDiem.setText(vdDTO.getDiemDaCong() - modalViDiem.getDiemDoi() + "");
        updateGiamGia();
        updateTongTien();
    }//GEN-LAST:event_btnDoiDiemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnDoiDiem;
    private javax.swing.JButton btnDonGiao;
    private javax.swing.JButton btnLamMoiGioHang;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnQuetMa;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnTimKhQR;
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbChoThanhToan;
    private javax.swing.JCheckBox cbNhanKemHoaDon;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> cbbLocHoaDon;
    private javax.swing.JComboBox<String> cbbYeuCauDG;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane4;
    private com.toedter.calendar.JDateChooser jdNgaySinh;
    private javax.swing.JLabel lbCanTra;
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbHoTenDG;
    private javax.swing.JLabel lbIdHoaDon;
    private javax.swing.JLabel lbLoaiThe;
    private javax.swing.JLabel lbLoaiTheDG;
    private javax.swing.JLabel lbMaDG;
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaKHDG;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMaNV1;
    private javax.swing.JLabel lbMaThe;
    private javax.swing.JLabel lbMaTheDG;
    private javax.swing.JLabel lbNgayTao;
    private javax.swing.JLabel lbNgayTao1;
    private javax.swing.JLabel lbSoDiem;
    private javax.swing.JLabel lbTenNV;
    private javax.swing.JLabel lbTenNV1;
    private javax.swing.JLabel lbTienThuHo;
    private javax.swing.JLabel lbTienTraLai;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTongTienDG;
    private javax.swing.JPanel pnlDatHang;
    private javax.swing.JPanel pnlDsHoaDon;
    private javax.swing.JPanel pnlDsSP;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlHoaDon;
    private view.swing.table.Table tbGioHang;
    private view.swing.table.Table tbHoaDon;
    private view.swing.table.Table tbSanPham;
    private view.swing.MaterialTabbed tbpnlKhachHang;
    private view.swing.MaterialTabbed tbpnlMain;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiDonGiao;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextArea txtGhiChuDG;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtSDT;
    private view.swing.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
