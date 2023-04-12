package view.main;

import dto.nhanvien.TaiKhoanDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import service.nhanvien.TaiKhoanService;
import service.nhanvien.impl.TaiKhoanServiceImpl;
import view.component.Header;
import view.component.Message;
import view.component.PanelCover;
import view.component.PanelLoading;
import view.component.PanelLogin;

@Getter
@Setter
public class Login extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoading loading;
    private PanelLogin loginPanel;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private TaiKhoanService taiKhoanService;
    private Main main;
    private Header header;

    public Login() {
        initComponents();
        init();
    }

    private void init() {
        taiKhoanService = new TaiKhoanServiceImpl();
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login();
            }
        };
        loginPanel = new PanelLogin(eventLogin);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginPanel.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginPanel, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  //  for smooth animation
        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginPanel, "width " + loginSize + "%, pos 1al 0 n 100%"); //  1al as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }

//    public void login() {
//        loading.setVisible(true);
//        String user = loginPanel.getUser();
//        String pass = loginPanel.getPass();
//        try {
//            TaiKhoanDTO dTO = taiKhoanService.login(user, pass);
//            if (!Objects.isNull(dTO)) {
//                loading.setVisible(false);
//                main = new Main(user, dTO.getRole().toString());
//                this.dispose();
//                main.setVisible(true);
//            }
//        } catch (Exception e) {
//            loading.setVisible(false);
//            e.printStackTrace();
//            showMessage(Message.MessageType.ERROR, "Tài khoản hoặc mật khẩu không chính xác");
//
//        }
//    }
    public void login() {
        String user = loginPanel.getUser();
        String pass = loginPanel.getPass();

        // Tạo một SwingWorker để thực hiện đăng nhập
        SwingWorker<TaiKhoanDTO, Void> worker = new SwingWorker<TaiKhoanDTO, Void>() {
            @Override
            protected TaiKhoanDTO doInBackground() throws Exception {
                // Hiển thị panelLoading
                SwingUtilities.invokeLater(() -> loading.setVisible(true));

                TaiKhoanDTO dTO = taiKhoanService.login(user, pass);
                return dTO;
            }

            @Override
            protected void done() {
                try {
                    TaiKhoanDTO dTO = taiKhoanService.login(user, pass);
                    if (!Objects.isNull(dTO)) {
                        // Ẩn panelLoading và hiển thị giao diện chính
                        SwingUtilities.invokeLater(() -> {
                            loading.setVisible(false);
                            main = new Main(user, dTO.getRole().toString());
                            dispose();
                            main.setVisible(true);
                        });
                    }
                } catch (Exception e) {
                    // Xử lý lỗi và ẩn panelLoading
                    SwingUtilities.invokeLater(() -> {
                        loading.setVisible(false);
                        e.printStackTrace();
                        showMessage(Message.MessageType.ERROR, "Tài khoản hoặc mật khẩu không chính xác");
                    });
                }
            }
        };

        // Thực hiện đăng nhập trong luồng mới
        worker.execute();
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
