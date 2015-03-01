package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeCell;
import maze.core.MazeExplorer;

//Gets the straight line distance between node and goal
public class Admissible implements BestFirstHeuristic<MazeExplorer> {
    public int getDistance(MazeExplorer node, MazeExplorer goal) {
    	
    	MazeCell current = new MazeCell(node.getLocation());
        MazeCell goal_node = new MazeCell(goal.getLocation());
    	
        
    	int a = current.X() + 1;
    	int b = (goal_node.Y() + 1) - (current.Y() + 1);
    	
    	//Simple Pythagoras formula
    	double result = Math.sqrt((a*a) + (b*b));

        return (int) result;
    }
}
