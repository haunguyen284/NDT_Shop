/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.giamgia;

import comon.constant.PaginationConstant;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import dto.giamgia.SanPhamGiamGiaDTO;
import dto.sanpham.SanPhamDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
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
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;

/**
 *
 * @author ADMIN KH
 */
@Getter
@Setter
public class ModalAddSanPhamGiamGia extends javax.swing.JDialog {

    private final GiamGiaService service;
    private final SanPhamService serviceSp;
    private final Validator validator;
    private DefaultComboBoxModel cbb;
    private final DefaultTableModel dtmGiamGia;
    private final DefaultTableModel dtmSanPham;
    private int currentPage = 1;
    private int totalPage = 1;
    private ModalGiamGia viewThongTin;
    private SanPhamGiamGiaService sanPhamGiamGiaService;
    private String idSpGG;
    private final SanPhamGiamGiaDTO sanPhamGiamGiaDTO;

    /**
     * Creates new form ViewModal
     */
    public ModalAddSanPhamGiamGia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.service = new GiamGiaImpl();
        this.serviceSp = new SanPhamServiceImpl();
        this.sanPhamGiamGiaService = new SanPhamGiamGiaImpl();
        this.validator = NDTValidator.getValidator();
        this.cbb = new DefaultComboBoxModel();
        this.dtmGiamGia = new DefaultTableModel();
        this.dtmSanPham = new DefaultTableModel();
        this.viewThongTin = new ModalGiamGia(parent, true);
        this.sanPhamGiamGiaDTO = new SanPhamGiamGiaDTO();
        loadData();
        loadDataSanPham();
    }

    // Load dữ liệu cho bảng Giam giá
    public void loadData() {
        String[] columns = {"ID", "TÊN", "GIÁ TRỊ MỨC GIAM GIÁ ", "ĐIỀU KIỆN ", "LOẠI GIẢM GIÁ", "TRẠNG THÁI", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC", "MÔ TẢ", "FUNCTION"};
        dtmGiamGia.setColumnIdentifiers(columns);
        tblGiamGia.setModel(dtmGiamGia);
        String maGG = txtSearchByMaGg.getText();
        showDataGiamGia(service.getAll(currentPage, maGG));
        showPaganation();
        createButton();
    }

    public void showDataGiamGia(List<GiamGiaDTO> list) {
        dtmGiamGia.setRowCount(0);
        for (GiamGiaDTO x : list) {
            dtmGiamGia.addRow(x.toDataRow());
        }
    }

    // Load dữ liệu cho bảng sản phẩm
    public void loadDataSanPham() {
        String[] columns = {"ID", "TÊN", "GIÁ BÁN ", "SỐ LƯỢNG TỒN ", "LOẠI", "THƯƠNG HIỆU", "TRẠNG THÁI"};
        dtmSanPham.setColumnIdentifiers(columns);
        tblSanPham.setModel(dtmSanPham);
        showDataSanPham(serviceSp.getAll(currentPage));
        showPaganationSp();
    }

    public void showDataSanPham(List<SanPhamDTO> list) {
        dtmSanPham.setRowCount(0);
        for (SanPhamDTO x : list) {
            dtmSanPham.addRow(x.toDataRowSanPham());
        }
    }

    private void createButton() {
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
                    viewThongTin.getGiamGiaDTO().setId(id);
                    viewThongTin.setVisible(true);
                    loadData();
                }
            }

            @Override
            public void onDelete(int row) {
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

    public void exit() {
        this.dispose();
    }

    // Phân trang cho giảm giá
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

    // Phân trang cho sản phẩm
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

    // add sản phẩm + giảm giá => sản phẩm giảm giá
    public GiamGiaDTO formGiamGia() {
        GiamGiaDTO x = new GiamGiaDTO();
        int row = tblGiamGia.getSelectedRow();
        String idGG = tblGiamGia.getValueAt(row, 0).toString();
        Optional<GiamGiaDTO> modelGiamGia = service.findById(idGG);
        if (modelGiamGia.isPresent()) {
            x = modelGiamGia.get();
        }
        return x;
    }

    public List<SanPhamDTO> listsSP() {
        tblSanPham.setRowSelectionAllowed(true);
        List<SanPhamDTO> listSp = new ArrayList<>();
        int[] listRowSp = tblSanPham.getSelectedRows();
        for (int rowSP : listRowSp) {
            String idSP = tblSanPham.getValueAt(rowSP, 0).toString();
            Optional<SanPhamDTO> modelSanPham = serviceSp.findByID(idSP);
            if (modelSanPham.isPresent()) {
                listSp.add(modelSanPham.get());
            }
        }
        return listSp;
    }

    public void saveOrUpdate(String action) {
        int rowSp = tblSanPham.getSelectedRow();
        int rowGiamGia = tblGiamGia.getSelectedRow();
        if (rowSp < 0 || rowGiamGia < 0) {
            ShowMessage.show("Vui lòng chọn vào Sản phẩm và Giảm giá bạn muốn tạo !");
            return;
        }
        GiamGiaDTO ggDTO = formGiamGia();
        List<SanPhamDTO> listSanPham = listsSP();
        SanPhamGiamGiaDTO sanPhamGiamGiaDTO = new SanPhamGiamGiaDTO();
        for (SanPhamDTO x : listSanPham) {
            if (ggDTO.convertLoaiGiamGia().equals("Giảm giá theo %")) {
                sanPhamGiamGiaDTO.setSoTienConLai(Float.parseFloat(x.getGiaBan()) - (Float.parseFloat(x.getGiaBan()) * ggDTO.getGiaTriGiamGia() / 100));
            } else {
                sanPhamGiamGiaDTO.setSoTienConLai(Float.parseFloat(x.getGiaBan()) - ggDTO.getGiaTriGiamGia());
            }
        }
        String result = sanPhamGiamGiaService.saveOrUpdate(action, ggDTO, listSanPham, sanPhamGiamGiaDTO);
        ShowMessageSuccessful.showSuccessful(result);
        new ViewGiamGiaSanPham().loadData();
        this.dispose();
    }

    // end
    // load dữ liệu tìm kiếm theo điều kiện giảm giá table Sản phẩm
    public void loadSearchSanPhamGiamGiaTheoMaGG(List<SanPhamGiamGiaDTO> listSearch) {
        DefaultTableModel dtmGiamGiaSp = (DefaultTableModel) tblSanPham.getModel();
        dtmGiamGiaSp.setRowCount(0);
        for (SanPhamGiamGiaDTO x : listSearch) {
            dtmGiamGiaSp.addRow(x.toDataRowSanPhamGiamGiaTheoMaGG());
        }
        showPaganationSp();
    }

    ////
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
        btnSearchByMaGg = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSearchByMaGg = new javax.swing.JTextPane();
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
        btnSearchByMaSp = new view.swing.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSearchByMaSp = new javax.swing.JTextPane();
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
        favicon1.setText("NDT_Shop");
        bg1.add(favicon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        btnAdd.setBackground(new java.awt.Color(0, 102, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tạo giảm giá");
        btnAdd.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        bg1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 220, -1));

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

        btnSearchByMaGg.setBackground(new java.awt.Color(0, 102, 255));
        btnSearchByMaGg.setBorder(null);
        btnSearchByMaGg.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchByMaGg.setText("Tìm kiếm");
        btnSearchByMaGg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchByMaGgMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(txtSearchByMaGg);

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
                        .addComponent(btnSearchByMaGg, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnSearchByMaGg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "TÊN SP", "Giá bán", "SỐ LƯỢNG TỒN", "LOẠI ", "TRẠNG THÁI", "CHỨC NĂNG"
            }
        ));
        tblSanPham.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        lblPageSp.setText("1/1");

        lblSp.setText("Total: 0");

        btnSearchByMaSp.setBackground(new java.awt.Color(0, 102, 255));
        btnSearchByMaSp.setBorder(null);
        btnSearchByMaSp.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchByMaSp.setText("Tìm kiếm");
        btnSearchByMaSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchByMaSpMouseClicked(evt);
            }
        });

        jScrollPane4.setViewportView(txtSearchByMaSp);

        jLabel10.setText("Mã");

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
                        .addComponent(btnSearchByMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnSearchByMaSp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        int row = tblGiamGia.getSelectedRow();
        float dieuKien = (float) tblGiamGia.getValueAt(row, 3);
        String id = tblGiamGia.getValueAt(row, 0).toString();
        List<SanPhamDTO> listSearch = serviceSp.searchByGiaBan(currentPage, dieuKien, id);
        showDataSanPham(listSearch);

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

    private void btnSearchByMaGgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchByMaGgMouseClicked

        loadData();
    }//GEN-LAST:event_btnSearchByMaGgMouseClicked

    private void btnSearchByMaSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchByMaSpMouseClicked
        String maSp = txtSearchByMaSp.getText();
        List<SanPhamDTO> list = serviceSp.searchByMa(currentPage, maSp);
        showDataSanPham(list);
    }//GEN-LAST:event_btnSearchByMaSpMouseClicked

    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            tblSanPham.setRowSelectionAllowed(true);
            int[] listRowSp = tblSanPham.getSelectedRows();
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuUpdate = new JMenuItem("Gỡ sản phẩm");
            menuUpdate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (ShowMessage.show("Bạn muốn gỡ sản phẩm này khỏi giảm giá này chứ ?")) {
                        for (int rowSP : listRowSp) {
                            String idSP = tblSanPham.getValueAt(rowSP, 0).toString();
                            Optional<SanPhamGiamGiaDTO> modelSpGG = sanPhamGiamGiaService.findById(idSP);
                            if (modelSpGG.isPresent()) {
                                String result = sanPhamGiamGiaService.delete(idSP);
                            }
                        }
                        ShowMessageSuccessful.showSuccessful("Gỡ thành công !");
                        List<SanPhamGiamGiaDTO> listSanPham = sanPhamGiamGiaService.listSanPhamTheoMaGG(currentPage, idSpGG);
                        loadSearchSanPhamGiamGiaTheoMaGG(listSanPham);
                    }
                }
            });
            popupMenu.add(menuUpdate);
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }

    }//GEN-LAST:event_tblSanPhamMouseReleased

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
    private view.swing.Button btnSearchByMaGg;
    private view.swing.Button btnSearchByMaSp;
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
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblPageSp;
    private javax.swing.JLabel lblSp;
    private view.swing.table.Table tblGiamGia;
    private view.swing.table.Table tblSanPham;
    private javax.swing.JPanel txtClose;
    private javax.swing.JPanel txtSave;
    private javax.swing.JTextPane txtSearchByMaGg;
    private javax.swing.JTextPane txtSearchByMaSp;
    // End of variables declaration//GEN-END:variables
}
