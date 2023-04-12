package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import view.swing.Button;
import view.swing.MyPasswordField;
import view.swing.MyTextField;

public class PanelLogin extends javax.swing.JLayeredPane {

    private String user;
    private String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public PanelLogin(ActionListener eventLogin) {
        initComponents();
        initLogin(eventLogin);
        login.setVisible(true);
        register.setVisible(true);
    }

    private void initLogin(ActionListener eventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Đăng nhập");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0,102,255));
        login.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setBackground(new Color(204,229,240));
        txtUser.setForeground(Color.black);
        txtUser.setHint("Tài khoản");
        login.add(txtUser, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setBackground(new Color(204,229,240));
        txtPass.setForeground(Color.black);
        txtPass.setHint("Mật khẩu");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Quên mật khẩu?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(0,102,255));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogin);
        cmd.setText("ĐĂNG NHẬP");
        login.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                user = txtUser.getText().trim();
                pass = String.valueOf(txtPass.getPassword());
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            login.setVisible(true);
        } else {
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
