package view.swing.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import raven.cell.PanelAction;

public class TableCellAction extends DefaultCellEditor {

    private ModelAction data;

    public TableCellAction() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable,Object o, boolean bln, int i, int i1) {
       
        data = (ModelAction) o;
        Action cell = new Action(data);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }

    //  This method to pass data to cell render when focus lose in cell
    @Override
    public Object getCellEditorValue() {
        return data;
    }
//    @Override
//    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
//        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
//        PanelAction action = new PanelAction();
//        if (isSeleted == false && row % 2 == 0) {
//            action.setBackground(Color.WHITE);
//        } else {
//            action.setBackground(com.getBackground().BLUE);
//        }
//        return action;
//    }
}
