package view.form.sanpham;

import comon.constant.PaginationConstant;
import comon.constant.sanpham.TrangThaiChatLieu;
import comon.constant.sanpham.TrangThaiMauSac;
import comon.constant.sanpham.TrangThaiThuongHieu;
import dto.sanpham.QuanDTO;
import dto.sanpham.SearchQuanDTO;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import service.sanpham.ChatLieuService;
import service.sanpham.MauSacService;
import service.sanpham.QuanService;
import service.sanpham.ThuongHieuService;
import service.sanpham.impl.ChatLieuServiceImpl;
import service.sanpham.impl.MauSacServiceImpl;
import service.sanpham.impl.QuanServiceImpl;
import service.sanpham.impl.ThuongHieuServiceImpl;

public class ViewQuan extends javax.swing.JPanel {

    private final QuanService quanService;
    private final MauSacService mauSacService;
    private final ChatLieuService chatLieuService;
    private final ThuongHieuService thuongHieuService;
    private int currentPage;
    private int totalPages;
    private long totalRecords;

    public ViewQuan() {
        initComponents();
        tbHienThi.fixTable(jScrollPane1);
        setOpaque(false);

        currentPage = PaginationConstant.DEFAULT_PAGE;
        quanService = new QuanServiceImpl();
        mauSacService = new MauSacServiceImpl();
        chatLieuService = new ChatLieuServiceImpl();
        thuongHieuService = new ThuongHieuServiceImpl();
        initTable();
        loadCombobox();
        loadDataTable();
    }

    private void loadCombobox() {
        Object[] listMauSacDTO = mauSacService.findAll(TrangThaiMauSac.ACTIVE).stream().map(dto -> dto.getTen()).toArray();
        cbbMauSac.setModel(new DefaultComboBoxModel(listMauSacDTO));
        cbbMauSac.insertItemAt("", 0);
        cbbMauSac.setSelectedIndex(0);

        Object[] listChatLieuDTO = chatLieuService.findAll(TrangThaiChatLieu.ACTIVE).stream().map(dto -> dto.getTen()).toArray();
        cbbChatLieu.setModel(new DefaultComboBoxModel(listChatLieuDTO));
        cbbChatLieu.insertItemAt("", 0);
        cbbChatLieu.setSelectedIndex(0);

        Object[] listThuongHieuDTO = thuongHieuService.findAll(TrangThaiThuongHieu.ACTIVE).stream().map(dto -> dto.getTen()).toArray();
        cbbThuongHieu.setModel(new DefaultComboBoxModel(listThuongHieuDTO));
        cbbThuongHieu.insertItemAt("", 0);
        cbbThuongHieu.setSelectedIndex(0);
    }

    private void initTable() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Sửa bản ghi");

        popupMenu.add(editMenuItem);

        editMenuItem.addActionListener((ActionEvent e) -> {
            if (e.getSource() == editMenuItem) {
                String ma = getSelectedMaFromTable();
                if (ma == null) {
                    return;
                }
                QuanDTO dto = quanService.findByMa(ma);
                Frame parentWindow = (Frame) SwingUtilities.windowForComponent(this);
                new DialogQuan(parentWindow, true, dto).setVisible(true);
                loadDataTable();

            }
        });

        tbHienThi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = tbHienThi.getSelectedRow();
                    if (row >= 0) {
                        tbHienThi.setRowSelectionInterval(row, row);
                        popupMenu.show(tbHienThi, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    private void loadDataTable() {
        SearchQuanDTO searchDTO = new SearchQuanDTO();
        String eo = txtEo.getText();
        if (!eo.equals("")){
            searchDTO.setEo(eo);
        }
        
        String canNang = txtCanNang.getText();
        if (!canNang.equals("")){
            searchDTO.setCanNang(eo);
        }
        
        String mong = txtMong.getText();
        if (!mong.equals("")){
            searchDTO.setMong(eo);
        }
        searchDTO.setMa(txtMa.getText());
        searchDTO.setMauSac(cbbMauSac.getSelectedItem().toString());
        searchDTO.setChatLieu(cbbChatLieu.getSelectedItem().toString());
        searchDTO.setThuongHieu(cbbThuongHieu.getSelectedItem().toString());
        List<QuanDTO> listDTO = quanService.findAll(currentPage - 1, searchDTO);
        tbHienThi.clearAllRow();
        DecimalFormat df = new DecimalFormat("#,##0.##");
        for (QuanDTO dto : listDTO) {
            tbHienThi.addRow(new Object[]{
                dto.getSanPham().getMaSP(),
                dto.getSanPham().getTenSP(),
                df.format(Float.valueOf(dto.getSanPham().getGiaBan())) + " VNĐ",
                df.format(Float.valueOf(dto.getSanPham().getSoLuongTon())),
                dto.getSanPham().getMauSac().getTen(),
                dto.getSanPham().getChatLieu().getTen(),
                dto.getSizeQuan().getTen(),
                dto.getSanPham().getThuongHieu().getTen(),
                dto.getSanPham().getXuatXu().getTen()
            });
        }

        totalRecords = quanService.totalCount();
        lbTotalChucVu.setText("Total: " + totalRecords);
        totalPages = (int) (totalRecords / PaginationConstant.DEFAULT_SIZE) + 1;
        setStatePagination();
    }

    private void setStatePagination() {
        btnPrevious.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < totalPages);
        lbPagination.setText(currentPage + "/" + totalPages);
    }

    private String getSelectedMaFromTable() {
        int selectedRow = tbHienThi.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        } else {
            return tbHienThi.getValueAt(selectedRow, 0).toString();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new view.swing.table.Table();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbPagination = new javax.swing.JLabel();
        lbTotalChucVu = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnTimKiem = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMa = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        button1 = new view.swing.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        btnClearLoc = new view.swing.Button();
        txtEo = new javax.swing.JTextField();
        txtCanNang = new javax.swing.JTextField();
        txtMong = new javax.swing.JTextField();
        btnLoc = new view.swing.Button();
        button5 = new view.swing.Button();
        button6 = new view.swing.Button();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Sản phẩm / Quần");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách quần");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Giá bán", "Số lượng tồn", "Màu sắc", "Chất liệu", "Cỡ", "Thương hiệu", "Xuất xứ"
            }
        ));
        tbHienThi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbHienThi);
        if (tbHienThi.getColumnModel().getColumnCount() > 0) {
            tbHienThi.getColumnModel().getColumn(0).setResizable(false);
            tbHienThi.getColumnModel().getColumn(0).setPreferredWidth(15);
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

        lbTotalChucVu.setText("Total: 0");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hiển thị sản phẩm ẩn");

        btnTimKiem.setBackground(new java.awt.Color(0, 102, 255));
        btnTimKiem.setBorder(null);
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtMa);

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
                        .addComponent(lbTotalChucVu))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(lbPagination)
                    .addComponent(lbTotalChucVu)
                    .addComponent(jButton1))
                .addGap(11, 11, 11))
        );

        button1.setBackground(new java.awt.Color(0, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Thêm mới");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel2.setText("Eo");

        jLabel3.setText("Màu sắc");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Cân nặng");

        jLabel4.setText("Chất liệu");

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Mông");

        jLabel7.setText("Thương hiệu");

        cbbThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnClearLoc.setBackground(new java.awt.Color(0, 102, 255));
        btnClearLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnClearLoc.setText("Clear lọc");
        btnClearLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLocActionPerformed(evt);
            }
        });

        btnLoc.setBackground(new java.awt.Color(0, 102, 255));
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addGap(20, 20, 20)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbMauSac, 0, 154, Short.MAX_VALUE)
                    .addComponent(txtEo))
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbChatLieu, 0, 158, Short.MAX_VALUE)
                    .addComponent(txtCanNang))
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbThuongHieu, 0, 151, Short.MAX_VALUE)
                    .addComponent(txtMong))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClearLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtMong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtCanNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtEo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClearLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                        .addGap(182, 182, 182)
                        .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addGap(177, 177, 177)
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
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        Frame parentWindow = (Frame) SwingUtilities.windowForComponent(this);
        new DialogQuan(parentWindow, true).setVisible(true);
        loadDataTable();
    }//GEN-LAST:event_button1ActionPerformed

    private void clearBoLoc(){
        txtCanNang.setText("");
        txtEo.setText("");
        txtMong.setText("");
        cbbChatLieu.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbThuongHieu.setSelectedIndex(0);
    }
    
    private void btnClearLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLocActionPerformed
        clearBoLoc();
        loadDataTable();
    }//GEN-LAST:event_btnClearLocActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        loadDataTable();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        clearBoLoc();
        loadDataTable();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnClearLoc;
    private view.swing.Button btnLoc;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button btnTimKiem;
    private view.swing.Button button1;
    private view.swing.Button button5;
    private view.swing.Button button6;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel lbPagination;
    private javax.swing.JLabel lbTotalChucVu;
    private view.swing.table.Table tbHienThi;
    private javax.swing.JTextField txtCanNang;
    private javax.swing.JTextField txtEo;
    private javax.swing.JTextPane txtMa;
    private javax.swing.JTextField txtMong;
    // End of variables declaration//GEN-END:variables
}
