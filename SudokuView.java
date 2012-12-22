import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuView extends JFrame
                        implements ActionListener, TableModelListener {
    private Graph g;
    private SudokuTableModel tabModel;
    private JTable table;
    private JButton solve;
    
    public SudokuView(Graph g) {
        super("Sudoku Solver");
        this.g = g;
    }
    
    public void setupGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        tabModel = new SudokuTableModel(this, g);
        tabModel.addTableModelListener(this);
        table = new JTable(tabModel);
        solve = new JButton("Solve");
        solve.addActionListener(this);
        mainPanel.add(table);
        mainPanel.add(solve);
        getContentPane().add(mainPanel);
        pack();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (solve == e.getSource()) {
            tabModel.setEditable(false);
            tabModel.fireTableStructureChanged();
            solve.setEnabled(false);
        }
    }
    
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int col = e.getColumn();
        if (row >= 0 && row < 9 && col >= 0 && col < 9) {
            Object o = ((TableModel)e.getSource()).getValueAt(row, col);
            System.err.println("Value of cell ("+row+", "+col+") changed "+
                               "to " + o);
        }
    }
}
