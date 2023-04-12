package view.form.thongke;

import comon.constant.TinhTrangHoaDon;
import dto.thongke.DoanhThuTheoThang;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.Icon;
import service.thongke.ThongKeService;
import service.thongke.impl.ThongKeServiceImpl;
import view.dialog.Message;
import view.main.Main;
import view.model.ModelCard;
import view.swing.chart.ModelChart;
import view.swing.icon.GoogleMaterialDesignIcons;
import view.swing.icon.IconFontSwing;

public class ViewDoanhThu extends javax.swing.JPanel {

    private ThongKeService thongKeService;

    public ViewDoanhThu() {
        initComponents();
        thongKeService = new ThongKeServiceImpl();
        tbHienThi.fixTable(jScrollPane1);
        setOpaque(false);
        initData();
    }

    private void initData() {
        initCardData();
        initChart();
        loadCombobox();
        loadDataDoanhThu();
    }

    private void loadCombobox() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = year; i > year - 4; i--) {
            cbbNam.addItem(String.valueOf(i));
        }
        cbbNam.setSelectedIndex(0);
    }

    private void initChart() {

        chart.addLegend("Doanh thu theo tháng", new Color(245, 189, 135));

    }

    public void loadDataDoanhThu() {
        chart.clearData();
        tbHienThi.clearAllRow();
        TinhTrangHoaDon tinhTrangHoaDon;
        if (cbbNam1.getSelectedIndex() == 0) {
            tinhTrangHoaDon = TinhTrangHoaDon.DA_THANH_TOAN;
        } else {
            tinhTrangHoaDon = TinhTrangHoaDon.DA_HUY;
        }
        int year = Integer.parseInt(cbbNam.getSelectedItem().toString());
        List<DoanhThuTheoThang> listDoanhThuTheoThang = thongKeService.getDoanhThuTheoTungThang(year, tinhTrangHoaDon);
        for (DoanhThuTheoThang doanhThuTheoThang : listDoanhThuTheoThang) {
            DecimalFormat df = new DecimalFormat("#,##0.##");
            tbHienThi.addRow(new Object[]{
                doanhThuTheoThang.getMonth(),
                df.format(doanhThuTheoThang.getSumTongTien()) + " VNĐ"
            });
            chart.addData(new ModelChart(String.valueOf(doanhThuTheoThang.getMonth()), new double[]{doanhThuTheoThang.getSumTongTien()}));
        }
    }

    private void initCardData() {
        thongKeService.getSPBanChay();
        Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card1.setData(new ModelCard("Tổng đơn ngày", thongKeService.getTongDonNgay(), 20, icon1));
        Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card2.setData(new ModelCard("Doanh thu ngày", thongKeService.getDoanhThuNgay(), 60, icon2));
        Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card3.setData(new ModelCard("Doanh thu tháng", thongKeService.getDoanhThuThang(), 80, icon3));
        Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card4.setData(new ModelCard("Doanh thu năm", thongKeService.getDoanhThuNam(), 95, icon4));
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        chart = new view.swing.chart.Chart();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new view.swing.table.Table();
        jLabel6 = new javax.swing.JLabel();
        cbbNam = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbbNam1 = new javax.swing.JComboBox<>();
        card1 = new view.component.Card();
        card2 = new view.component.Card();
        card3 = new view.component.Card();
        card4 = new view.component.Card();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Thống kê / Doanh thu");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setText("Biểu đồ");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(105, 105, 105));
        jLabel3.setText("Biểu đồ doanh thu");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 419, Short.MAX_VALUE))
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Doanh thu"
            }
        ));
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThi);

        jLabel6.setText("Năm");

        cbbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamActionPerformed(evt);
            }
        });

        jLabel7.setText("Tình trạng");

        cbbNam1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Đã huỷ" }));
        cbbNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNam1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cbbNam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );

        card1.setColorGradient(new java.awt.Color(211, 28, 215));
        card1.setPreferredSize(new java.awt.Dimension(252, 110));

        card2.setBackground(new java.awt.Color(10, 30, 214));
        card2.setColorGradient(new java.awt.Color(72, 111, 252));
        card2.setPreferredSize(new java.awt.Dimension(252, 110));

        card3.setBackground(new java.awt.Color(204, 102, 0));
        card3.setColorGradient(new java.awt.Color(255, 212, 99));
        card3.setPreferredSize(new java.awt.Dimension(252, 110));

        card4.setBackground(new java.awt.Color(51, 153, 0));
        card4.setColorGradient(new java.awt.Color(208, 255, 90));
        card4.setPreferredSize(new java.awt.Dimension(252, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)
                                .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(card4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNam1ActionPerformed
        loadDataDoanhThu();
    }//GEN-LAST:event_cbbNam1ActionPerformed

    private void cbbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamActionPerformed
        loadDataDoanhThu();
    }//GEN-LAST:event_cbbNamActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        int row = tbHienThi.getSelectedRow();
        if (row == -1) {
            return;
        }

    }//GEN-LAST:event_tbHienThiMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.Card card1;
    private view.component.Card card2;
    private view.component.Card card3;
    private view.component.Card card4;
    private javax.swing.JComboBox<String> cbbNam;
    private javax.swing.JComboBox<String> cbbNam1;
    private view.swing.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private view.swing.table.Table tbHienThi;
    // End of variables declaration//GEN-END:variables
}
