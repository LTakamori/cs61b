package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        int current = s;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(current);
        marked[current] = true;
        announce();

        if(current == t){
            marked[current] = true;
            targetFound = true;
            announce();
            return;
        }

        while (!queue.isEmpty()) {
            current = queue.remove();
            for (int w : maze.adj(current)) {

                if (true == marked[w]){
                    continue;
                }

                if (w == t) {
                    targetFound = true;
                }

                edgeTo[w] = current;
                distTo[w] = distTo[current] + 1;
                marked[w] = true;
                announce();
                queue.add(w);

                if (true == targetFound) {
                    return;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

