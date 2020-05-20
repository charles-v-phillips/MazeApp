package MazeAlgorithms;

import java.util.Queue;

public interface PathFindingAlgorithm {
    Queue<Integer[]> path(int rowStart, int colStart, int rowEnd,int colEnd);

}
