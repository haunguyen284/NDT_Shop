/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.giamgia;

import comon.constant.PaginationConstant;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import service.giamgia.GiamGiaService;
import service.giamgia.SanPhamGiamGiaService;
import service.giamgia.impl.GiamGiaImpl;
import service.giamgia.impl.SanPhamGiamGiaImpl;
import service.sanpham.SanPhamService;
import service.sanpham.impl.SanPhamImpl;
import service.sanpham.impl.SanPhamServiceImpl;
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ViewModal1 extends javax.swing.JDialog {

    private final GiamGiaService service;
    private final SanPhamService serviceSp;
    private final Validator validator;
    private DefaultComboBoxModel cbb;
    private final DefaultTableModel dtm;
    private int currentPage = 1;
    private int totalPage = 1;
    private ViewThongTinGiamGia viewThongTin;
    private SanPhamGiamGiaService sanPhamGiamGiaService;

    /**
     * Creates new form ViewModal
     */
    public ViewModal1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.service = new GiamGiaImpl();
        this.serviceSp = new SanPhamServiceImpl();
        this.sanPhamGiamGiaService = new SanPhamGiamGiaImpl();
        this.validator = NDTValidator.getValidator();
        this.cbb = new DefaultComboBoxModel();
        this.dtm = new DefaultTableModel();
        this.viewThongTin = new ViewThongTinGiamGia(parent, true);
        loadData();
        loadDataSanPham();
    }

    public void loadData() {
        String[] columns = {"ID", "TÊN", "GIÁ TRỊ MỨC GIAM GIÁ ", "ĐIỀU KIỆN ", "LOẠI GIẢM GIÁ", "TRẠNG THÁI", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC", "MÔ TẢ", "FUNCTION"};
        dtm.setColumnIdentifiers(columns);
        tblGiamGia.setModel(dtm);
        List<GiamGiaDTO> listData = service.getAll(currentPage);
        dtm.setRowCount(0);
        for (GiamGiaDTO x : listData) {
            dtm.addRow(x.toDataRow());
        }
        showPaganation();
        createbutton();
    }

    public void loadDataSanPham() {
        List<SanPhamDTO> listData = serviceSp.getAll(currentPage);
        DefaultTableModel dtmSp = (DefaultTableModel) tblSanPham.getModel();
        tblSanPham.setModel(dtmSp);
        dtmSp.setRowCount(0);
        for (SanPhamDTO x : listData) {
            dtmSp.addRow(x.toDataRowSanPham());
        }
        showPaganationSp();
    }

    private void createbutton() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                viewThongTin.getBtnSave().setVisible(true);
                viewThongTin.getTxtSave().setVisible(true);
                viewThongTin.getBtnSave().setText("Update");

                row = tblGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblGiamGia.getValueAt(row, 0).toString();
                Optional<GiamGiaDTO> optional = service.findById(id);
                if (optional.isPresent()) {
                    viewThongTin.fill(optional.get());
                    viewThongTin.setVisible(true);
                }
            }

            @Override
            public void onDelete(int row) {
                row = tblGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                if (ShowMessage.show("Bạn muốn xóa giảm giá này chứ ?")) {
                    String id = tblGiamGia.getValueAt(row, 0).toString();
                    String result = service.delete(id);
                    ShowMessageSuccessful.showSuccessful(result);
                    loadData();
                }

            }

            @Override
            public void onView(int row) {
                viewThongTin.getBtnSave().setVisible(false);
                viewThongTin.getTxtSave().setVisible(false);
                row = tblGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblGiamGia.getValueAt(row, 0).toString();
                Optional<GiamGiaDTO> optional = service.findById(id);
                if (optional.isPresent()) {
                    viewThongTin.fill(optional.get());
                    viewThongTin.setVisible(true);
                }
            }
        };
        tblGiamGia.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        tblGiamGia.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
    }

    public void showPaganation() {
        long countTotalRow = service.count();
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

    public void showPaganationSp() {
        long countTotalRow = serviceSp.count();
        totalPage = (int) Math.ceil(Double.valueOf(countTotalRow) / Double.valueOf(PaginationConstant.DEFAULT_SIZE));
        if (totalPage == 1) {
            currentPage = 1;
            btnFirstSp.setEnabled(false);
            btnLastSp.setEnabled(false);
            btnNextSp.setEnabled(false);
            btnPrevious.setEnabled(false);
        } else {
            btnFirstSp.setEnabled(true);
            btnLastSp.setEnabled(true);
        }
        lblPageSp.setText(currentPage + " of " + totalPage);
        lblSp.setText("Total " + countTotalRow);
    }

    public GiamGiaDTO formGiamGia() {
        GiamGiaDTO x = new GiamGiaDTO();
        int row = tblGiamGia.getSelectedRow();
        String idGG = tblGiamGia.getValueAt(row, 0).toString();
        Optional<GiamGiaDTO> otp2 = service.findById(idGG);
        if (otp2.isPresent()) {
            x = otp2.get();
        }
        return x;
    }

    public List<SanPhamDTO> listsSP() {
        tblSanPham.setRowSelectionAllowed(true);
        List<SanPhamDTO> listSp = new ArrayList<>();
        int[] listRowSp = tblSanPham.getSelectedRows();
        for (int rowSP : listRowSp) {
            String idSP = tblSanPham.getValueAt(rowSP, 0).toString();
            Optional<SanPhamDTO> otp = serviceSp.findByID(idSP);
            if (otp.isPresent()) {
                listSp.add(otp.get());
            }
        }
        return listSp;
    }

    public void saveOrUpdate(String action) {
        int rowSp = tblSanPham.getSelectedRow();
        int rowGiamGia = tblGiamGia.getSelectedRow();
        if (rowSp < 0 || rowGiamGia < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào Sản Phẩm và Khuyến Mãi mà  bạn muốn thêm !", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GiamGiaDTO ggDTO = formGiamGia();
        List<SanPhamDTO> list = listsSP();
        String result = sanPhamGiamGiaService.saveOrUpdate(action, ggDTO, list);
        ShowMessageSuccessful.showSuccessful(result);
        new ViewGiamGiamSp().loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg1 = new javax.swing.JPanel();
        citybg1 = new javax.swing.JLabel();
        favicon1 = new javax.swing.JLabel();
        btnAdd = new view.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGiamGia = new view.swing.table.Table();
        lblPage = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        button3 = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new view.swing.table.Table();
        lblPageSp = new javax.swing.JLabel();
        lblSp = new javax.swing.JLabel();
        button4 = new view.swing.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        btnFirstSp = new javax.swing.JButton();
        btnPreviouSp = new javax.swing.JButton();
        btnNextSp = new javax.swing.JButton();
        btnLastSp = new javax.swing.JButton();
        txtSave = new javax.swing.JPanel();
        btnSave = new javax.swing.JLabel();
        txtClose = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg1.setBackground(new java.awt.Color(255, 255, 255));
        bg1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        citybg1.setBackground(new java.awt.Color(0, 134, 190));
        bg1.add(citybg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 0, -1, 500));

        favicon1.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        favicon1.setIcon(new javax.swing.ImageIcon("D:\\DuAnMot\\NDT_Shop\\src\\main\\resources\\icon\\logo.png")); // NOI18N
        favicon1.setText("NDT_Shop");
        bg1.add(favicon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        btnAdd.setBackground(new java.awt.Color(0, 102, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tạo giảm giá");
        btnAdd.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        bg1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 120, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("Danh sách giảm giá");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tblGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Giá bán", "Số lượng tồn", "Màu sắc", "Chất liệu", "Cỡ", "Thương hiệu", "Xuất xứ"
            }
        ));
        tblGiamGia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGiamGia);

        lblPage.setText("1/1");

        lblCount.setText("Total: 0");

        button3.setBackground(new java.awt.Color(0, 102, 255));
        button3.setBorder(null);
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Tìm kiếm");

        jScrollPane2.setViewportView(jTextPane1);

        jLabel9.setText("Tên");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
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
                                .addComponent(lblCount)
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 623, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
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

        bg1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 330));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(76, 76, 76));
        jLabel6.setText("Danh sách sản phẩm");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "TÊN SP", "Giá bán", "SỐ LƯỢNG TỒN", "LOẠI ", "TRẠNG THÁI"
            }
        ));
        tblSanPham.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        lblPageSp.setText("1/1");

        lblSp.setText("Total: 0");

        button4.setBackground(new java.awt.Color(0, 102, 255));
        button4.setBorder(null);
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("Tìm kiếm");

        jScrollPane4.setViewportView(jTextPane2);

        jLabel10.setText("Tên");

        btnFirstSp.setText("|<");
        btnFirstSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstSpActionPerformed(evt);
            }
        });

        btnPreviouSp.setText("<");
        btnPreviouSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviouSpActionPerformed(evt);
            }
        });

        btnNextSp.setText(">");
        btnNextSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextSpActionPerformed(evt);
            }
        });

        btnLastSp.setText(">|");
        btnLastSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnFirstSp, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPreviouSp)
                                .addGap(11, 11, 11)
                                .addComponent(lblPageSp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNextSp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLastSp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSp)
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 611, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstSp)
                    .addComponent(btnPreviouSp)
                    .addComponent(lblPageSp)
                    .addComponent(btnNextSp)
                    .addComponent(btnLastSp)
                    .addComponent(lblSp))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        bg1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 300));

        txtSave.setBackground(new java.awt.Color(51, 102, 255));

        btnSave.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSave.setText("Áp dụng giảm giá cho sản phẩm");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });

        javax.swing.GroupLayout txtSaveLayout = new javax.swing.GroupLayout(txtSave);
        txtSave.setLayout(txtSaveLayout);
        txtSaveLayout.setHorizontalGroup(
            txtSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        txtSaveLayout.setVerticalGroup(
            txtSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtSaveLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg1.add(txtSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 700, 250, 40));

        txtClose.setBackground(new java.awt.Color(51, 102, 255));

        btnClose.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setText("Close");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClose.setPreferredSize(new java.awt.Dimension(40, 40));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });

        javax.swing.GroupLayout txtCloseLayout = new javax.swing.GroupLayout(txtClose);
        txtClose.setLayout(txtCloseLayout);
        txtCloseLayout.setHorizontalGroup(
            txtCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtCloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        txtCloseLayout.setVerticalGroup(
            txtCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtCloseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg1.add(txtClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 700, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        if (ShowMessage.show("Bạn muốn lưu giảm giá này chứ ?")) {
            if (btnSave.getText().equals("Save")) {
                saveOrUpdate("add");
                return;
            }
            saveOrUpdate("update");
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited

    }//GEN-LAST:event_btnSaveMouseExited

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        new ViewGiamGiamSp().loadData();
        this.dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
  
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
 
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        viewThongTin.clearForm();
        viewThongTin.getBtnSave().setVisible(true);
        viewThongTin.getTxtSave().setVisible(true);
        viewThongTin.getBtnSave().setText("Save");
        viewThongTin.setVisible(true);
        viewThongTin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadData();
            }
        });
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamGiaMouseClicked

    }//GEN-LAST:event_tblGiamGiaMouseClicked

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
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentPage >= totalPage) {
            btnNext.setEnabled(false);
            return;
        }
        currentPage++;
        btnPrevious.setEnabled(true);
        loadData();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage;
        btnNext.setEnabled(false);
        btnPrevious.setEnabled(true);
        loadData();

    }//GEN-LAST:event_btnLastActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnFirstSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstSpActionPerformed
        currentPage = 1;
        btnNextSp.setEnabled(true);
        btnPreviouSp.setEnabled(false);
        loadDataSanPham();

    }//GEN-LAST:event_btnFirstSpActionPerformed

    private void btnPreviouSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviouSpActionPerformed
        if (currentPage <= 1) {
            btnPreviouSp.setEnabled(false);
            return;
        }
        currentPage--;
        btnNextSp.setEnabled(true);
        loadDataSanPham();

    }//GEN-LAST:event_btnPreviouSpActionPerformed

    private void btnNextSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextSpActionPerformed
        if (currentPage >= totalPage) {
            btnNextSp.setEnabled(false);
            return;
        }
        currentPage++;
        btnPreviouSp.setEnabled(true);
        loadDataSanPham();

    }//GEN-LAST:event_btnNextSpActionPerformed

    private void btnLastSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSpActionPerformed
        currentPage = totalPage;
        btnNextSp.setEnabled(false);
        btnPreviouSp.setEnabled(true);
        loadDataSanPham();
    }//GEN-LAST:event_btnLastSpActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg1;
    private view.swing.Button btnAdd;
    private javax.swing.JLabel btnClose;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirstSp;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLastSp;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNextSp;
    private javax.swing.JButton btnPreviouSp;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JLabel btnSave;
    private view.swing.Button button3;
    private view.swing.Button button4;
    private javax.swing.JLabel citybg1;
    private javax.swing.JLabel favicon1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblPageSp;
    private javax.swing.JLabel lblSp;
    private view.swing.table.Table tblGiamGia;
    private view.swing.table.Table tblSanPham;
    private javax.swing.JPanel txtClose;
    private javax.swing.JPanel txtSave;
    // End of variables declaration//GEN-END:variables
}
