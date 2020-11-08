package lab11.graphs;

import java.util.Stack;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
    }

    @Override
    public void solve() {
        cycleHelper(s);
    }

    // Helper methods go here
    private void cycleHelper(int s) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);
        int old;
        int startV;
        int parent = s;
        while (!stack.isEmpty()) {
            boolean isEnd = true;
            int current = stack.peek();
            marked[current] = true;
            announce();
            for (int w : maze.adj(current)) {
                if (marked[w] && w != parent) {
                    startV = w;
                    edgeTo[startV] = current;
                    while (stack.peek() != startV) {
                        stack.pop();
                        edgeTo[current] = stack.peek();
                        current = stack.peek();
                        announce();
                    }
                    //System.out.println("done!");
                    return;
                } else if (marked[w]) {
                    continue;
                } else {
                    parent = stack.peek();
                    stack.push(w);
                    isEnd = false;
                    break;
                }
            }
            if (isEnd) {
                stack.pop();
            }
        }
    }
}

