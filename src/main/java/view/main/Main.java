package view.main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import service.giamgia.GiamGiaService;
import service.giamgia.impl.GiamGiaImpl;
import view.component.Header;
import view.component.Menu;
import view.dialog.Message;
import view.dialog.ShowMessage;
import view.event.EventMenuSelected;
import view.event.EventShowPopupMenu;
import view.form.thongke.ViewDoanhThu;
import view.form.MainForm;
import view.form.doitra.ViewDoiTra;
import view.form.dongiao.ViewDonGiao;
import view.form.giamgia.ViewGiamGiamSp;
import view.form.hoadon.ViewHoaDon;
import view.form.hoadon.ViewLichSuHoaDon;
import view.form.khachhang.ViewKhachHang;
import view.form.khachhang.ViewTheThanhVien;
import view.form.nhanvien.ModalDoiMatKhau;
import view.form.nhanvien.ViewNhanVien;
import view.form.sanpham.ViewAo;
import view.form.sanpham.ViewQuan;
import view.form.thongke.ViewTKSanPham;
import view.swing.MenuItem;
import view.swing.PopupMenu;
import view.swing.icon.GoogleMaterialDesignIcons;
import view.swing.icon.IconFontSwing;

@Getter
@Setter
public class Main extends javax.swing.JFrame implements Runnable {

    private GiamGiaService giamGiaService;

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private String role;
    private String ten;

    /**
     *
     * @param ten
     * @param role
     */
    public Main(String ten, String role) {
        initComponents();
        this.ten = ten;
        this.role = role;
        init();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header(ten, role);
        main = new MainForm();
        giamGiaService = new GiamGiaImpl();
        new Thread(this).start();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                switch (menuIndex) {
                    case 0:
                        main.showForm(new ViewHoaDon(main, ten));
                    case 1:
                        if (subMenuIndex == 0) {
                            main.showForm(new ViewDoanhThu());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new ViewTKSanPham());
                        }
                        break;
                    case 2:
                        if (subMenuIndex == 0) {
                            main.showForm(new ViewDoiTra(main, ten));
                        }
                        if (subMenuIndex == 1) {
                            main.showForm(new ViewLichSuHoaDon());
                        }
                        break;
                    case 3:
                        if (subMenuIndex == 0) {
                            main.showForm(new ViewAo());
                        }
                        if (subMenuIndex == 1) {
                            main.showForm(new ViewQuan());
                        }
                        break;
                    case 4:
                        main.showForm(new ViewGiamGiamSp());
                        break;
                    case 5:
                        if (role.equals("STAFF")) {
                            showMessage("Chỉ ADMIN mới có quyền quản lý nhân viên");
                            return;
                        }
                        main.showForm(new ViewNhanVien());
                        break;
                    case 6:
                        if (subMenuIndex == 0) {
                            main.showForm(new ViewKhachHang());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new ViewTheThanhVien(main));
                        }
                        break;
                    case 7:
                        new ModalDoiMatKhau(null, true, ten).setVisible(true);
                        break;
                    case 8:
                        main.showForm(new ViewDonGiao());
                        break;
                    case 9:
                        if (ShowMessage.show("Bạn chắc chắn muốn thoát ?")) {
                            System.exit(0);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new ViewDoanhThu());
    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main("admin", "ADMIN").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {
            try {
                Calendar c = Calendar.getInstance();
                int h = c.get(Calendar.HOUR_OF_DAY);
                int m = c.get(Calendar.MINUTE);
                int s = c.get(Calendar.SECOND);
                int day = c.get(Calendar.DATE);
                int month = c.get(Calendar.MONTH) + 1;
                int year = c.get(Calendar.YEAR);
                String am_pm = c.get(Calendar.AM_PM) == 1 ? "PM" : "AM";
                String time = h + ":" + m + ":" + s + " " + am_pm + " - " + day + "/" + month + "/" + year;
                header.lbCurrentTime.setText(time);
                Long ngayHienTai = new Date().getTime();
                giamGiaService.checkTrangThai(ngayHienTai);
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
