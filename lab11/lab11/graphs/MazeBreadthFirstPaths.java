package lab11.graphs;

import java.util.LinkedList;

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
    private boolean targetFind;
    private Maze maze;
    LinkedList<Integer> ordQueue = new LinkedList<>();
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        targetFind = false;
        ordQueue.add(s);
        while (!ordQueue.isEmpty() && !targetFind) {
            int cur = ordQueue.remove();
            announce();
            if (cur == t) {
                targetFind = true;
            }
            if (targetFind) {
                return;
            }
            for (int neb : maze.adj(cur)) {
                if (!marked[neb]) {
                    ordQueue.add(neb);
                    distTo[neb] = distTo[cur] + 1;
                    edgeTo[neb] = cur;
                    marked[neb] = true;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

