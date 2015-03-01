package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeCell;
import maze.core.MazeExplorer;

public class NonAdmissible implements BestFirstHeuristic<MazeExplorer> {
    public int getDistance(MazeExplorer node, MazeExplorer goal) {
    	MazeCell current = new MazeCell(node.getLocation());
    	MazeCell goal_node = new MazeCell(goal.getLocation());
    	
    	int treasures = node.getTreasures();
    	int man_dist = current.getManhattanDist(goal_node);
    	
    	int total_treasures = node.getTotalTreasures();
    	
    	
    	if (total_treasures == 0){
    		return man_dist;
    	}
    	else {
    		int percent = (int) (treasures/total_treasures * 100);
    		return man_dist + (percent * 5);
    	}
    }
}