package MazeAlgorithms;

import java.util.List;
import java.util.Queue;

public interface PathFindingAlgorithm {
    List<Queue<Integer[]>> path(int rowStart, int colStart, int rowEnd, int colEnd);

}
