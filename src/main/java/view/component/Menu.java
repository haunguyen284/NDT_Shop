package view.component;

import comon.utilities.ImageHelper;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import view.event.EventMenu;
import view.event.EventMenuSelected;
import view.event.EventShowPopupMenu;
import view.model.ModelMenu;
import view.swing.MenuAnimation;
import view.swing.MenuItem;
import view.swing.scrollbar.ScrollBarCustom;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/Basket.png")), 24, 24), "Màn hình thu ngân"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/analysis.png")), 24, 24), "Thống kê", "Thống kê doanh thu", "Thống kê sản phẩm"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/invoice.png")), 24, 24), "Quản lý hoá đơn", "Đổi/Trả", "Lịch sử"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/groom-suit.png")), 24, 24), "Quản lý sản phẩm", "Áo", "Quần"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/discounts.png")), 24, 24), "Quản lý giảm giá"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/teamwork.png")), 24, 24), "Quản lý nhân viên"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/target.png")), 24, 24), "Quản lý khách Hàng", "Khách hàng", "Thẻ thành viên"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/key.png")), 24, 24), "Đổi mật khẩu"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/Call.png")), 24, 24), "Quản lí đơn giao"));
        addMenu(new ModelMenu(ImageHelper.resizeImageIcon(new ImageIcon(getClass().getResource("/icon/power.png")), 24, 24), "Thoát"));
    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new view.component.Profile();

        setPreferredSize(new java.awt.Dimension(230, 609));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);
        panel.setPreferredSize(new java.awt.Dimension(312, 523));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private view.component.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
