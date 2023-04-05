package view.form.giamgia;

import comon.constant.ModelProperties;
import comon.constant.PaginationConstant;
import comon.validator.NDTValidator;
import dto.giamgia.GiamGiaDTO;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import model.giamgia.GiamGia;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import service.giamgia.GiamGiaService;
import service.giamgia.impl.GiamGiaImpl;
import view.dialog.Message;
import view.dialog.ShowMessage;
import view.dialog.ShowMessageSuccessful;
import view.main.Main;
import view.model.ModelStudent;
import view.swing.table.EventAction;

@Getter
@Setter
public class ViewGiamGiamSp extends javax.swing.JPanel {

    private final DefaultTableModel dtm;
    private final GiamGiaService service;
    private final Validator validator;
    private int currentPage = 1;
    private int totalPage = 1;
    private ViewModal viewModal;

    public ViewGiamGiamSp() {
        initComponents();
        tblGiamGia.fixTable(jScrollPane1);
        setOpaque(false);
        initData();
        this.dtm = new DefaultTableModel();
        this.service = new GiamGiaImpl();
        this.validator = NDTValidator.getValidator();
        this.viewModal = new ViewModal(null, true);
        loadData();
        createbutton();
    }

    private void createbutton() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                viewModal.getBtnSave().setVisible(true);
                viewModal.getTxtSave().setVisible(true);
                viewModal.getBtnSave().setText("Update");

                row = tblGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblGiamGia.getValueAt(row, 0).toString();
                Optional<GiamGiaDTO> optional = service.findById(id);
                if (optional.isPresent()) {
                    viewModal.fill(optional.get());
                    viewModal.setVisible(true);
                }
            }

            @Override
            public void onDelete(int row) {
                row = tblGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                if(ShowMessage.show("Bạn muốn xóa giảm giá này chứ ?")){
                         String id = tblGiamGia.getValueAt(row, 0).toString();
                    String result = service.delete(id);
                    ShowMessageSuccessful.showSuccessful(result);
                    loadData();
                }

            }

            @Override
            public void onView(int row) {
                viewModal.getBtnSave().setVisible(false);
                viewModal.getTxtSave().setVisible(false);
                row = tblGiamGia.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String id = tblGiamGia.getValueAt(row, 0).toString();
                Optional<GiamGiaDTO> optional = service.findById(id);
                if (optional.isPresent()) {
                    viewModal.fill(optional.get());
                    viewModal.setVisible(true);
                }
            }
        };
        tblGiamGia.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        tblGiamGia.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
    }

    private void initData() {
        initTableData();
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

    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(ModelStudent student) {
                if (showMessage("Delete Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }

            @Override
            public void update(ModelStudent student) {
                if (showMessage("Update Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }
        };

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
        tblGiamGia = new view.swing.table.Table();
        lblPage = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        button3 = new view.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnAdd = new view.swing.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        button2 = new view.swing.Button();
        button5 = new view.swing.Button();
        button6 = new view.swing.Button();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Giam giá / Danh sách giảm giá");

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
        if (tblGiamGia.getColumnModel().getColumnCount() > 0) {
            tblGiamGia.getColumnModel().getColumn(0).setResizable(false);
            tblGiamGia.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        lblPage.setText("1/1");

        lblCount.setText("Total: 0");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hiển thị sản phẩm ẩn");

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
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(lblCount))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 704, Short.MAX_VALUE)
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
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCount)
                            .addComponent(jButton1))
                        .addGap(11, 11, 11))
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
        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel3.setText("Loại giảm giá");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Gía trị giảm giá");

        jLabel4.setText("Trạng thái");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        button2.setBackground(new java.awt.Color(0, 102, 255));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Lọc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner2)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 154, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addGap(320, 320, 320)
                        .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addGap(69, 69, 69)
                        .addComponent(button6, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addGap(112, 112, 112))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        currentPage = 1;
        btnNext.setEnabled(true);
        btnPrevious.setEnabled(false);
        loadData();
//        showPaganation();
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
        // TODO add your handling code here:
        viewModal.clearForm();
        viewModal.getBtnSave().setVisible(true);
        viewModal.getTxtSave().setVisible(true);
        viewModal.getBtnSave().setText("Save");
        viewModal.setVisible(true);
        viewModal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadData();
            }
        });
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamGiaMouseClicked
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
    }//GEN-LAST:event_tblGiamGiaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button btnAdd;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private view.swing.Button button2;
    private view.swing.Button button3;
    private view.swing.Button button5;
    private view.swing.Button button6;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblPage;
    private view.swing.table.Table tblGiamGia;
    // End of variables declaration//GEN-END:variables
}
