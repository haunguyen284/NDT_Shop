package view.form.khachhang;

import comon.constant.khachhang.TrangThaiKhachHang;
import comon.constant.khachhang.TrangThaiTheThanhVien;
import comon.constant.khachhang.TrangThaiViDiem;
import comon.utilities.DateTimeUtil;
import comon.utilities.QrScanner;
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
import java.util.Objects;
import javax.persistence.NoResultException;
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
import view.dialog.ShowMessageSuccessful;
import view.main.Main;

public class ViewKhachHang extends javax.swing.JPanel {

    private final KhachHangService khachHangService;
    private final TheThanhVienService theThanhVienService;
    private final LoaiTheService loaiTheService;
    private final ViDiemService viDiemService;
    private int currentPage;
    private int totalPages;
    private final int pageSize;
    private long totalKhachHang;
    private final ModalKhachHang modalKhachHang;

    public ViewKhachHang() {
        initComponents();
        tbKhachHang.fixTable(jScrollPane1);
        setOpaque(false);
        pageSize = 8;
        currentPage = 1;
        khachHangService = new KhachHangServiceImpl();
        theThanhVienService = new TheThanhVienServiceImpl();
        loaiTheService = new LoaiTheServiceImpl();
        viDiemService = new ViDiemServiceImpl();
        modalKhachHang = new ModalKhachHang(null, true);
        loadDataTable();
    }

    public final void loadDataTable() {
        TrangThaiKhachHang trangThai = TrangThaiKhachHang.TRANG_THAI_1;
        switch (cbbLocKH.getSelectedIndex()) {
            case 0:
                trangThai = TrangThaiKhachHang.TRANG_THAI_1;
                break;
            case 1:
                trangThai = TrangThaiKhachHang.TRANG_THAI_2;
                break;
            case 2:
                trangThai = TrangThaiKhachHang.TRANG_THAI_3;
                break;
            default:
                throw new AssertionError();
        }
        List<KhachHangDTO> listDTOs = khachHangService.findByTrangThai(trangThai);
        DefaultTableModel dtm = (DefaultTableModel) tbKhachHang.getModel();
        tbKhachHang.setModel(dtm);
        dtm.setRowCount(0);
        for (KhachHangDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
        totalKhachHang = khachHangService.totalCount();
        lbTotal.setText("Total: " + totalKhachHang);
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
        tbKhachHang = new view.swing.table.Table();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbPagination = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txpTimKiem = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnQuetMa = new view.swing.Button();
        cbbLocKH = new javax.swing.JComboBox<>();
        btnThemMoi = new view.swing.Button();
        btnCapNhat = new view.swing.Button();
        btnTaoThe = new view.swing.Button();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Khách hàng");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách khách hàng");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Mã Thẻ TV", "Họ tên", "SDT", "Email", "Ngày sinh", "Địa chỉ", "Giới tính", "Ghi chú", "Số lần mua", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachHang);

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

        txpTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txpTimKiemKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txpTimKiem);

        jLabel9.setText("Tên");

        btnQuetMa.setBackground(new java.awt.Color(0, 102, 255));
        btnQuetMa.setBorder(null);
        btnQuetMa.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetMa.setText("Quét mã");
        btnQuetMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetMaActionPerformed(evt);
            }
        });

        cbbLocKH.setBackground(new java.awt.Color(255, 255, 255));
        cbbLocKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách hàng mới", "Đã là thành viên", "Chưa phân loại" }));
        cbbLocKH.setBorder(null);
        cbbLocKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(lbPagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cbbLocKH, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbLocKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuetMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(lbPagination)
                    .addComponent(lbTotal))
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

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 255));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnTaoThe.setBackground(new java.awt.Color(0, 102, 255));
        btnTaoThe.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoThe.setText("Tạo thẻ");
        btnTaoThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoTheActionPerformed(evt);
            }
        });

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaoThe, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        loadDataTable();
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private KhachHangDTO getObjectsFromTable(int row) throws NumberFormatException {
        String maKH = Objects.isNull(this.tbKhachHang.getValueAt(row, 0)) ? "" : this.tbKhachHang.getValueAt(row, 0).toString();
        String maTTV = Objects.isNull(this.tbKhachHang.getValueAt(row, 1)) ? "" : this.tbKhachHang.getValueAt(row, 1).toString();
        String ten = Objects.isNull(this.tbKhachHang.getValueAt(row, 2)) ? "" : this.tbKhachHang.getValueAt(row, 2).toString();
        String sdt = Objects.isNull(this.tbKhachHang.getValueAt(row, 3)) ? "" : this.tbKhachHang.getValueAt(row, 3).toString();
        String email = Objects.isNull(this.tbKhachHang.getValueAt(row, 4)) ? "" : this.tbKhachHang.getValueAt(row, 4).toString();
        String ngaySinh = Objects.isNull(this.tbKhachHang.getValueAt(row, 5)) ? "" : this.tbKhachHang.getValueAt(row, 5).toString();
        String diaChi = Objects.isNull(this.tbKhachHang.getValueAt(row, 6)) ? "" : this.tbKhachHang.getValueAt(row, 6).toString();
        String gioiTinh = Objects.isNull(this.tbKhachHang.getValueAt(row, 7)) ? "" : this.tbKhachHang.getValueAt(row, 7).toString();
        String ghiChu = Objects.isNull(this.tbKhachHang.getValueAt(row, 8)) ? "" : this.tbKhachHang.getValueAt(row, 8).toString();
        String soLanMua = Objects.isNull(this.tbKhachHang.getValueAt(row, 9)) ? "" : this.tbKhachHang.getValueAt(row, 9).toString();
        String trangThai = Objects.isNull(this.tbKhachHang.getValueAt(row, 10)) ? "" : this.tbKhachHang.getValueAt(row, 10).toString();
        KhachHangDTO dTO = new KhachHangDTO();
        dTO.setMaKH(maKH);
        dTO.setId(khachHangService.findId(maKH));
        dTO.setTheThanhVien(theThanhVienService.findByMaTTV(maTTV));
        dTO.setTen(ten);
        dTO.setSdt(sdt);
        dTO.setEmail(email);
        dTO.setNgaySinh(DateTimeUtil.stringToDate(ngaySinh).getTime());
        dTO.setDiaChi(diaChi);
        dTO.setGioiTinh(gioiTinh);
        dTO.setGhiChu(ghiChu);
        dTO.setSoLanMua(Integer.parseInt(soLanMua));
        switch (trangThai) {
            case "Khách hàng mới":
                dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_1);
                break;
            case "Đã là thành viên":
                dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_2);
                break;
            case "Đã huỷ":
                dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_3);
                break;
        }
        return dTO;
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

    private void tbKhachHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseReleased
        // Kiểm tra xem người dùng đã nhấp chuột phải
        if (SwingUtilities.isRightMouseButton(evt)) {
            // Lấy vị trí hàng được chọn
            int row = tbKhachHang.rowAtPoint(evt.getPoint());
            // Hiển thị menu tạm thời
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuUpdate = new JMenuItem("Cập nhật");
            menuUpdate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    KhachHangDTO dTO = getObjectsFromTable(row);
                    modalKhachHang.fill(dTO);
                    modalKhachHang.setVisible(true);
                    loadDataTable();
                }
            });
            JMenuItem menuCreateMemberCard = new JMenuItem("Tạo thẻ");
            menuCreateMemberCard.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!tbKhachHang.getValueAt(row, 1).equals("Chưa có thẻ")) {
                        showMessage("Khách hàng này đã có thẻ!");
                        return;
                    }
                    KhachHangDTO dTO = getObjectsFromTable(row);
//        Tạo điểm tích luỹ
                    Date ngayPhatHanh = new Date();
                    Date ngayHetHan = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(ngayPhatHanh);
                    calendar.add(Calendar.YEAR, 2);
                    ngayHetHan = calendar.getTime();
//        Set giá trị mặc định khi khởi tạo điểm tích luỹ
                    ViDiemDTO viDiemDTO = new ViDiemDTO();
                    viDiemDTO.setTongDiem(0);
                    viDiemDTO.setDiemDaDung(0);
                    viDiemDTO.setDiemDaCong(0);
                    viDiemDTO.setTrangThaiViDiem(TrangThaiViDiem.TRANG_THAI_1);
//        Tạo thẻ thành viên.
//        Khi tạo thẻ thành viên, thẻ thành viên sẽ được mặc định phân loại là thẻ BRONZE với giá trị thẻ là 0.
                    TheThanhVienDTO theThanhVienDTO = new TheThanhVienDTO();
                    LoaiTheDTO loaiTheDTO = loaiTheService.findByTen("BRONZE");
//        Set giá trị mặc định khi khởi tạo thẻ thành viên
                    theThanhVienDTO.setViDiem(viDiemService.save(viDiemDTO));
                    theThanhVienDTO.setNgayPhatHanh(ngayPhatHanh.getTime());
                    theThanhVienDTO.setNgayHetHan(ngayHetHan.getTime());
                    theThanhVienDTO.setLoaiThe(loaiTheDTO);
                    theThanhVienDTO.setTrangThaiTheThanhVien(TrangThaiTheThanhVien.DANG_SU_DUNG);
                    theThanhVienDTO.setMaTTV(generateMemberCardId(dTO));
                    ShowMessageSuccessful.showSuccessful(theThanhVienService.save(theThanhVienDTO));
//        Cập nhật thẻ thành viên cho khách hàng ở CSDL
                    dTO.setTheThanhVien(theThanhVienService.findByNgayHetHan(ngayHetHan.getTime()));
                    dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_2);
                    khachHangService.save(dTO);
                    loadDataTable();
                }
            });
            popupMenu.add(menuUpdate);
            popupMenu.add(menuCreateMemberCard);
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbKhachHangMouseReleased

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        int row = tbKhachHang.getSelectedRow();
        if (row < 0) {
            showMessage("Vui lòng chọn hàng cần cập nhật");
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
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnTaoTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoTheActionPerformed
        int row = tbKhachHang.getSelectedRow();
        if (row < 0) {
            showMessage("Vui lòng chọn hàng cần tạo thẻ");
            return;
        }
        if (!tbKhachHang.getValueAt(row, 1).equals("Chưa có thẻ")) {
            showMessage("Khách hàng này đã có thẻ!");
            return;
        }
        KhachHangDTO dTO = getObjectsFromTable(row);
//        Tạo điểm tích luỹ
        Date ngayPhatHanh = new Date();
        Date ngayHetHan = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayPhatHanh);
        calendar.add(Calendar.YEAR, 2);
        ngayHetHan = calendar.getTime();
//        Set giá trị mặc định khi khởi tạo điểm tích luỹ
        ViDiemDTO viDiemDTO = new ViDiemDTO();
        viDiemDTO.setTongDiem(0);
        viDiemDTO.setDiemDaDung(0);
        viDiemDTO.setDiemDaCong(0);
        viDiemDTO.setTrangThaiViDiem(TrangThaiViDiem.TRANG_THAI_1);
//        Tạo thẻ thành viên.
//        Khi tạo thẻ thành viên, thẻ thành viên sẽ được mặc định phân loại là thẻ BRONZE với giá trị thẻ là 0.
        TheThanhVienDTO theThanhVienDTO = new TheThanhVienDTO();
        LoaiTheDTO loaiTheDTO = loaiTheService.findByTen("BRONZE");
//        Set giá trị mặc định khi khởi tạo thẻ thành viên
        theThanhVienDTO.setViDiem(viDiemService.save(viDiemDTO));
        theThanhVienDTO.setNgayPhatHanh(ngayPhatHanh.getTime());
        theThanhVienDTO.setNgayHetHan(ngayHetHan.getTime());
        theThanhVienDTO.setLoaiThe(loaiTheDTO);
        theThanhVienDTO.setTrangThaiTheThanhVien(TrangThaiTheThanhVien.DANG_SU_DUNG);
        theThanhVienDTO.setMaTTV(generateMemberCardId(dTO));
        showMessage(theThanhVienService.save(theThanhVienDTO));
//        Cập nhật thẻ thành viên cho khách hàng ở CSDL
        dTO.setTheThanhVien(theThanhVienService.findByNgayHetHan(ngayHetHan.getTime()));
        dTO.setTrangThaiKhachHang(TrangThaiKhachHang.TRANG_THAI_2);
        khachHangService.save(dTO);
        loadDataTable();
    }//GEN-LAST:event_btnTaoTheActionPerformed

    private void cbbLocKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocKHActionPerformed
        loadDataTable();
    }//GEN-LAST:event_cbbLocKHActionPerformed

    private void txpTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpTimKiemKeyReleased
        String ten = txpTimKiem.getText();
        List<KhachHangDTO> listDTOs = khachHangService.findByName(ten, currentPage - 1, pageSize);
        DefaultTableModel dtm = (DefaultTableModel) tbKhachHang.getModel();
        tbKhachHang.setModel(dtm);
        dtm.setRowCount(0);
        for (KhachHangDTO dto : listDTOs) {
            dtm.addRow(dto.toDataRow());
        }
        totalKhachHang = khachHangService.totalCount();
        lbTotal.setText("Total: " + totalKhachHang);
        totalPages = (int) (totalKhachHang / pageSize) + 1;
        setStatePagination();
    }//GEN-LAST:event_txpTimKiemKeyReleased

    private void btnQuetMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetMaActionPerformed
        QrScanner qrScanner = new QrScanner(null, true);
        qrScanner.setVisible(true);
        qrScanner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                KhachHangDTO dTO = null;
                try {
                    dTO = khachHangService.findById(qrScanner.getQrString());
                } catch (NoResultException ex) {
                    showMessage("Không tìm thấy khách hàng");
                }
                modalKhachHang.fill(dTO);
                modalKhachHang.setVisible(true);
                modalKhachHang.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadDataTable();
                    }
                });
            }
        });
    }//GEN-LAST:event_btnQuetMaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnCapNhat;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button btnQuetMa;
    private view.swing.Button btnTaoThe;
    private view.swing.Button btnThemMoi;
    private javax.swing.JComboBox<String> cbbLocKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbTotal;
    private view.swing.table.Table tbKhachHang;
    private javax.swing.JTextPane txpTimKiem;
    // End of variables declaration//GEN-END:variables
}
