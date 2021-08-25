import javax.swing.*;
import javax.swing.table.AbstractTableModel;

class JTableButtonModel extends AbstractTableModel {
    private Object[][] rows = {{"Button1", new JButton("Button1")},{"Button2", new JButton("Button2")},{"Button3", new JButton("Button3")}, {"Button4", new JButton("Button4")}};
    private String[] columns = {"Count", "Buttons"};
    public String getColumnName(int column) {
        return columns[column];
    }
    public int getRowCount() {
        return rows.length;
    }
    public int getColumnCount() {
        return columns.length;
    }
    public Object getValueAt(int row, int column) {
        return rows[row][column];
    }
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    public Class getColumnClass(int column) {
        if(getValueAt(0,column) == null)
            return null;
        return getValueAt(0, column).getClass();
    }
    public JTableButtonModel(Object[][] rows, String[] columns){
        this.rows = rows;
        this.columns = columns;
    }
}