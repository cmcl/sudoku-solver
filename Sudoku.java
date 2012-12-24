import javax.swing.*;

/**
 * Main class that solves a Sudoku puzzle.
 * 
 * @author cmcl
 * @version 1.0
 */
public class Sudoku {
    public static final int NUM_VERTICES = 81; // 9 x 9 sudoku board.
    public static final int K = 9; // Max degree
    public static void main(String[] args) {
        Graph g = new Graph(NUM_VERTICES);
        
        for (Vertex v : g.getVertices()) {
            v.setNumber(0);
        }
        
        // show gui
        final SudokuView view = new SudokuView(g);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                view.setupGui();
            }
        });
        
        // input numbers in g from user
        
        // colour the graph g
        
        // update state to user; successful colouring, or no solution.
    
    }
}
