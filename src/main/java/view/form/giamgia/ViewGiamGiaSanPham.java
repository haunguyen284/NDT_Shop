package view.form.giamgia;

import comon.constant.ModelProperties;
import comon.constant.PaginationConstant;
import comon.constant.giamgia.TrangThaiGiamGia;
import comon.constant.sanpham.LoaiSanPham;
import comon.constant.sanpham.TrangThaiQuanAo;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import dto.giamgia.SanPhamGiamGiaDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import raven.cell.PanelAction;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import service.giamgia.GiamGiaService;
import service.giamgia.SanPhamGiamGiaService;
import service.giamgia.impl.GiamGiaImpl;
import service.giamgia.impl.SanPhamGiamGiaImpl;
import service.sanpham.SanPhamService;
import service.sanpham.impl.SanPhamServiceImpl;
import view.dialog.Message;
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;
import view.main.Main;
import view.model.ModelStudent;
import view.swing.table.EventAction;

@Getter
@Setter
public class ViewGiamGiaSanPham extends javax.swing.JPanel {

    private final DefaultTableModel dtm;
    private final GiamGiaService service;
    private final SanPhamGiamGiaService sanPhamGiamGiaService;
    private final SanPhamService sanPhamService;
    private final Validator validator;
    private int currentPage = 1;
    private int totalPage = 1;
    private ModalAddSanPhamGiamGia modalAddSpGG;
    private ModalDetailSanPhamGiamGia detailSanPhamGiamGia;
    private DefaultComboBoxModel cboTrangThai;
    private DefaultComboBoxModel cboLoaiSp;
    private final GiamGiaDTO giamGiaDTO;

    public ViewGiamGiaSanPham() {
        initComponents();
        tblSanPhamGiamGia.fixTable(jScrollPane1);
        setOpaque(false);
        this.dtm = new DefaultTableModel();
        this.service = new GiamGiaImpl();
        this.sanPhamGiamGiaService = new SanPhamGiamGiaImpl();
        this.sanPhamService = new SanPhamServiceImpl();
        this.validator = NDTValidator.getValidator();
        this.modalAddSpGG = new ModalAddSanPhamGiamGia(null, true);
        this.cboLoaiSp = new DefaultComboBoxModel();
        this.cboTrangThai = new DefaultComboBoxModel();
        this.giamGiaDTO = new GiamGiaDTO();
        this.detailSanPhamGiamGia = new ModalDetailSanPhamGiamGia(null, true);
        cboTrangThai();
        cboLoai();
        loadData();
        createButton();
    }

    private void createButton() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                modalAddSpGG.getBtnSave().setVisible(true);
                modalAddSpGG.getTxtSave().setVisible(true);
                modalAddSpGG.getBtnSave().setText("Update");

                row = tblSanPhamGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblSanPhamGiamGia.getValueAt(row, 0).toString();
                String maGG = tblSanPhamGiamGia.getValueAt(row, 1).toString();
                Optional<SanPhamGiamGiaDTO> optional = sanPhamGiamGiaService.findById(id);
                if (optional.isPresent()) {
                    List<GiamGiaDTO> listGiamGia = service.getAll(currentPage, maGG);
                    List<SanPhamGiamGiaDTO> listSanPham = sanPhamGiamGiaService.listSanPhamTheoMaGG(currentPage, maGG);
                    modalAddSpGG.showDataGiamGia(listGiamGia);
                    modalAddSpGG.loadSearchSanPhamGiamGiaTheoMaGG(listSanPham);
                    modalAddSpGG.setVisible(true);
                    modalAddSpGG.setIdSpGG(maGG);
                    modalAddSpGG.getSanPhamGiamGiaDTO().setId(id);

                    loadData();
                }
            }

            @Override
            public void onDelete(int row) {
            }

            @Override
            public void onView(int row) {
                modalAddSpGG.getBtnSave().setVisible(false);
                modalAddSpGG.getTxtSave().setVisible(false);
                row = tblSanPhamGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblSanPhamGiamGia.getValueAt(row, 0).toString();
                Optional<SanPhamGiamGiaDTO> optional = sanPhamGiamGiaService.findById(id);
                if (optional.isPresent()) {
                    detailSanPhamGiamGia.fill(optional.get());
                    detailSanPhamGiamGia.setVisible(true);
                }
            }
        };
        tblSanPhamGiamGia.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        tblSanPhamGiamGia.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
    }

    public void loadData() {
        String[] columns = {"ID", "MÃ", "TÊN GIẢM GIÁ", "GIÁ TRỊ MỨC GIAM GIÁ ", "NGÀY BẮT ĐẦU ", "NGÀY KẾT THÚC", "TÊN SẢN PHẨM", "LOẠI SẢN PHẨM", "TRẠNG THÁI", "CHỨC NĂNG"};
        dtm.setColumnIdentifiers(columns);
        tblSanPhamGiamGia.setModel(dtm);
        TrangThaiGiamGia trangThaiGiamGia = null;
        if (cboTrangThaiGG.getSelectedItem().toString().equals("Đang hoạt động")) {
            trangThaiGiamGia = TrangThaiGiamGia.DANG_HOAT_DONG;
        } else if (cboTrangThaiGG.getSelectedItem().toString().equals("Ngừng hoạt động")) {
            trangThaiGiamGia = TrangThaiGiamGia.NGUNG_HOAT_DONG;
        }

        LoaiSanPham loaiSanPham = null;
        if (cboLoaiSanPham.getSelectedItem().toString().equals("ÁO")) {
            loaiSanPham = LoaiSanPham.AO;
        } else if (cboLoaiSanPham.getSelectedItem().toString().equals("QUẦN")) {
            loaiSanPham = LoaiSanPham.QUAN;
        }
        String searchByMa = txtSearch.getText();
        List<SanPhamGiamGiaDTO> listSanPhamGiamGiaDTO = sanPhamGiamGiaService.getAll(currentPage, trangThaiGiamGia, loaiSanPham, searchByMa);
        dtm.setRowCount(0);
        for (SanPhamGiamGiaDTO x : listSanPhamGiamGiaDTO) {
            dtm.addRow(x.toDataRow());
        }
        showPaganation();
        createButton();
    }

    public void showPaganation() {
        long countTotalRow = sanPhamGiamGiaService.count();
        totalPage = (int) Math.ceil(Double.valueOf(countTotalRow) / Double.valueOf(PaginationConstant.DEFAULT_SIZE));
        if (totalPage == 1) {
            currentPage = 1;
            btnFirst.setEnabled(false);
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
        }
        lblPage.setText(currentPage + " of " + totalPage);
        lblCount.setText("Total " + countTotalRow);
    }

    private void cboTrangThai() {
        cboTrangThai.addElement("");
        cboTrangThai.addElement("Đang hoạt động");
        cboTrangThai.addElement("Ngừng hoạt động");
        cboTrangThaiGG.setModel(cboTrangThai);
    }

    private void cboLoai() {
        cboLoaiSp.addElement("");
        cboLoaiSp.addElement("ÁO");
        cboLoaiSp.addElement("QUẦN");
        cboLoaiSanPham.setModel(cboLoaiSp);
    }

    private void clear() {
        cboLoaiSanPham.setSelectedIndex(0);
        cboTrangThaiGG.setSelectedIndex(0);
        txtSearch.setText("");
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamGiamGia = new view.swing.table.Table();
        lblPage = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        btnSearch = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSearch = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnAdd = new view.swing.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboTrangThaiGG = new javax.swing.JComboBox<>();
        btnLoc = new view.swing.Button();
        btnClear = new view.swing.Button();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Giam giá / Danh sách giảm giá");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách sản phẩm giảm giá");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tblSanPhamGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Giá bán", "Số lượng tồn", "Màu sắc", "Chất liệu", "Cỡ", "Thương hiệu", "Xuất xứ"
            }
        ));
        tblSanPhamGiamGia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSanPhamGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamGiamGia);
        if (tblSanPhamGiamGia.getColumnModel().getColumnCount() > 0) {
            tblSanPhamGiamGia.getColumnModel().getColumn(0).setResizable(false);
            tblSanPhamGiamGia.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        lblPage.setText("1/1");

        lblCount.setText("Total: 0");

        btnSearch.setBackground(new java.awt.Color(0, 102, 255));
        btnSearch.setBorder(null);
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtSearch);

        jLabel9.setText("Mã");

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

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

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPage)
                        .addGap(11, 11, 11)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCount))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 628, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCount)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst)
                            .addComponent(btnPrevious)
                            .addComponent(btnNext)
                            .addComponent(btnLast)
                            .addComponent(lblPage))
                        .addContainerGap())))
        );

        btnAdd.setBackground(new java.awt.Color(0, 102, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tạo sản phẩm giảm  giá");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel3.setText("Loại sản phẩm");

        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ÁO", "QUẦN" }));

        jLabel4.setText("Trạng thái");

        cboTrangThaiGG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Ngừng hoạt động" }));

        btnLoc.setBackground(new java.awt.Color(0, 102, 255));
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 102, 255));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTrangThaiGG, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(146, 146, 146))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThaiGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addGap(983, 983, 983))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        currentPage = 1;
        btnNext.setEnabled(true);
        btnPrevious.setEnabled(false);
        loadData();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (currentPage <= 1) {
            btnPrevious.setEnabled(false);
            return;
        }
        currentPage--;
        btnNext.setEnabled(true);
        loadData();
        showPaganation();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage >= totalPage) {
            btnNext.setEnabled(false);
            return;
        }
        currentPage++;
        btnPrevious.setEnabled(true);
        loadData();
        showPaganation();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage;
        btnNext.setEnabled(false);
        btnPrevious.setEnabled(true);
        loadData();
        showPaganation();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        modalAddSpGG.loadDataSanPham();
        modalAddSpGG.loadData();
        modalAddSpGG.getBtnSave().setVisible(true);
        modalAddSpGG.getTxtSave().setVisible(true);
        modalAddSpGG.getBtnSave().setText("Save");
        modalAddSpGG.setVisible(true);
        modalAddSpGG.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadData();
            }
        });
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblSanPhamGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamGiamGiaMouseClicked
//        int row = tblGiamGia.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
//        String id = tblGiamGia.getValueAt(row, 0).toString();
//        Optional<GiamGiaDTO> optional = service.findById(id);
//        if (optional.isPresent()) {
//            viewModal.fill(optional.get());
//            viewModal.setVisible(true);
//        }
    }//GEN-LAST:event_tblSanPhamGiamGiaMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadData();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        loadData();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnAdd;
    private view.swing.Button btnClear;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private view.swing.Button btnLoc;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button btnSearch;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JComboBox<String> cboTrangThaiGG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblPage;
    private view.swing.table.Table tblSanPhamGiamGia;
    private javax.swing.JTextPane txtSearch;
    // End of variables declaration//GEN-END:variables
}
