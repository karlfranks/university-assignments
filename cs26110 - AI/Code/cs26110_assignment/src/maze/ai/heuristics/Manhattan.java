package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeCell;
import maze.core.MazeExplorer;

public class Manhattan implements BestFirstHeuristic<MazeExplorer> {
    public int getDistance(MazeExplorer node, MazeExplorer goal) {
    	
    	//Sets up two MazeCell objects to be able to call the getManhattanDistance method there
        MazeCell current = new MazeCell(node.getLocation());
        MazeCell goal_node = new MazeCell(goal.getLocation());
        
        //Gets and returns current Manhattan Distance
        int man_dist = current.getManhattanDist(goal_node);
    	return man_dist;
    }
}