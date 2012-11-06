import javax.swing.*;
import java.awt.*;

public class SudokuView extends JFrame {
    private Graph g;
    
    public SudokuView(Graph g) {
        super("Sudoku Solver");
        this.g = g;
    }
    
    public void setupGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable table = new JTable(new SudokuTableModel(g));
        
        getContentPane().add(table);
        pack();
        setVisible(true);
    }
}
