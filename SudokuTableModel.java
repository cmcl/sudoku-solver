import javax.swing.*;
import javax.swing.table.*;

public class SudokuTableModel extends AbstractTableModel {
    private Graph g;
    
    
    public SudokuTableModel(Graph g) {
        super();
        this.g = g;
    }
    
    public int getRowCount() {
        return 9;
    }
    
    public int getColumnCount() {
        return 9;
    }
    
    public Object getValueAt(int row, int column) {
        return g.getVertex(row * 9 + column).getNumber();
    }
}
