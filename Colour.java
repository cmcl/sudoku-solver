import java.util.*;

/** 
 * Algorithm to be based on George and Appel graph colouring
 * algorithm.
 *
 * @author cmcl
 * @version 1.0
 * 
 */
public class Colour implements GraphColouring {

    private Graph g;
    private HashSet<Vertex> initial, simplify, spill, selectStack;
    private int[] degree;
    
    public Colour() {
        initial = new HashSet<Vertex>();
        simplify = new HashSet<Vertex>();
        spill = new HashSet<Vertex>();
        selectStack = new HashSet<Vertex>();
    }
    /**
     * Assigns a number to each node in the graph, representing
     * the colouring of the graph.
     */
    public void colour(Graph g) {
        this.g = g;
        init();
        makeWorklists();
        do {
            if (!simplify.isEmpty()) simplify();
            else if (!spill.isEmpty()) selectSpill();
        } while (!simplify.isEmpty() && !spill.isEmpty());
        // simplify
        // coalesce
        // freeze?
        // spill
    }
    
    private void init() {
        for (Vertex v : g.getVertices()) initial.add(v);
        degree = new int[g.size()];
        for (int i = 0; i < g.size(); i++) {
            degree[i] = g.getVertex(i).vertexDegree();
        }
    }
    
    private void makeWorklists() {
        for (Vertex v : g.getVertices()) {
            if (initial.remove(v)) {
                if (v.vertexDegree() >= Sudoku.K) {
                    spill.add(v);
                } else simplify.add(v);
            }
        }
    }
    
    private void simplify() {
        Vertex v = get(simplify);
        simplify.remove(v);
        selectStack.add(v);
        for (AdjListNode node : v.getAdjList()) {
            Vertex u = g.getVertex(node.getVertexNumber());
            decrementDegree(u);
        }
    }
    
    private Vertex get(HashSet<Vertex> set) {
        Iterator<Vertex> iter = set.iterator();
        return iter.next();
    }
    
    private void decrementDegree(Vertex v) {
        int d = degree[v.getNumber()];
        degree[v.getNumber()] = d - 1;
        if (d == Sudoku.K) {
            spill.remove(v);
            simplify.add(v);
        }
    }
    
    private void selectSpill() {
        Vertex v = get(spill);
        spill.remove(v);
        simplify.add(v);
    }
}
