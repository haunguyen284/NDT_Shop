package view.component;

import dto.nhanvien.TaiKhoanDTO;
import java.awt.event.ActionListener;
import lombok.Getter;
import lombok.Setter;
import service.nhanvien.TaiKhoanService;
import service.nhanvien.impl.TaiKhoanServiceImpl;

@Getter
@Setter
public class Header extends javax.swing.JPanel {

    private final TaiKhoanService taiKhoanService;

    public Header(String ten, String role) {
        initComponents();
        taiKhoanService = new TaiKhoanServiceImpl();
        TaiKhoanDTO dTO = taiKhoanService.findByTenTaiKhoan(ten);
        lbRole.setText(role);
        lbUserName.setText(dTO.getNhanVien().getTen());
    }

    public void addMenuEvent(ActionListener event) {
        cmdMenu.addActionListener(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUserName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmdMenu = new view.swing.Button();
        pic = new view.swing.ImageAvatar();
        lbCurrentTime = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbUserName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(127, 127, 127));
        lbUserName.setText("NguyenDZ");

        lbRole.setForeground(new java.awt.Color(127, 127, 127));
        lbRole.setText("Admin");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile1.jpg"))); // NOI18N

        lbCurrentTime.setForeground(new java.awt.Color(127, 127, 127));
        lbCurrentTime.setText("18:20:12 20/11/2020");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(lbCurrentTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRole)
                    .addComponent(lbUserName))
                .addGap(20, 20, 20)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRole)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbCurrentTime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.Button cmdMenu;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lbCurrentTime;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUserName;
    private view.swing.ImageAvatar pic;
    // End of variables declaration//GEN-END:variables
}
