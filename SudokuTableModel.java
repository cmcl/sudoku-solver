import javax.swing.*;
import javax.swing.table.*;

public class SudokuTableModel extends AbstractTableModel {
    private JFrame f;
    private Graph g;
    private boolean isEditable;
    
    public SudokuTableModel(JFrame f, Graph g) {
        super();
        this.f = f;
        this.g = g;
        isEditable = true;
    }
    
    public boolean isEditable() { return isEditable; }
    public void setEditable(boolean b) { isEditable = b; }
    
    public int getRowCount() {
        return 9;
    }
    
    public int getColumnCount() {
        return 9;
    }
    
    public Object getValueAt(int row, int column) {
        return g.getVertex(row * 9 + column).getNumber();
    }
    
    public void setValueAt(Object value, int row, int col) {
        int n = g.getVertex(row * 9 + col).getNumber();
        try {
            n = Integer.parseInt(((String)value));
        } catch (NumberFormatException e) {
            sendErrorMsg("Invalid input", value+" is not an integer.");
            return;
        }
        if (n >= 1 && n <= 9) {
            g.getVertex(row * 9 + col).setNumber(n);
            fireTableCellUpdated(row, col);
        } else {
            sendErrorMsg("Invalid input", n+" is not between 1 and 9");
        }
    }
    
    private void sendErrorMsg(String title, String msg) {
        JOptionPane.showMessageDialog(f, msg, title,
                                      JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean isCellEditable(int row, int column) {
        return isEditable;
    }
}
