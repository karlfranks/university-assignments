package maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import maze.ai.core.BestFirstObject;

public class MazeExplorer implements BestFirstObject<MazeExplorer> {
	private Maze m;
	private MazeCell location;
	private TreeSet<MazeCell> treasureFound; 
	
	public MazeExplorer(Maze m, MazeCell location) {
		this.m = m;
		this.location = location;
		treasureFound = new TreeSet<MazeCell>();
	}
	
	public MazeCell getLocation() {return location;}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<MazeExplorer> getSuccessors() {
		ArrayList<MazeExplorer> result = new ArrayList<MazeExplorer>();
		
		//If the current cell is a treasure
		if(m.isTreasure(location)){
			treasureFound.add(location);
		}
		
		//Get the current node's neighbours
		ArrayList<MazeCell> neighbours = this.m.getNeighbors(this.location);
		
		//Loop over neighbours, check they're not walls
		for (MazeCell current: neighbours) {
		      if ((this.m.blocked(this.location, current)) == false) {
		        MazeExplorer child = new MazeExplorer(m, current);
		        child.treasureFound = (TreeSet<MazeCell>) treasureFound.clone();
		        result.add(child);
		      }
		}

        return result;
	}
	
	public void addTreasures(Collection<MazeCell> treasures) {
		treasureFound.addAll(treasures);
	}
	
	public String toString() {
		StringBuilder treasures = new StringBuilder();
		for (MazeCell t: treasureFound) {
			treasures.append(";");
			treasures.append(t.toString());
		}
		return "@" + location.toString() + treasures.toString();
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof MazeExplorer) {
			return achieves((MazeExplorer)other);
		} else {
			return false;
		}
	}

	@Override
	public boolean achieves(MazeExplorer goal) {
		return this.location.equals(goal.location) && this.treasureFound.equals(goal.treasureFound);
	}
	
	public int getTreasures() {
	    return treasureFound.size();
	  }
	
	public int getTotalTreasures(){
		return m.getTotalTreasures();
	}

}
