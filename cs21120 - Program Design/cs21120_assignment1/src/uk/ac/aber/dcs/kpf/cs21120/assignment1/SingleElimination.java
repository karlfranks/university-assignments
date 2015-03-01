package uk.ac.aber.dcs.kpf.cs21120.assignment1;

import java.util.ArrayList;

public class SingleElimination implements IManager{
	/*Variables*/
	ArrayList<String> temp;
	CompetitorQueue playerList;
	String tempInput;
	String competitor1;
	String competitor2;
	
	
	//Sets up a queue of players with the inputted data
	@Override
	public void setPlayers(ArrayList<String> players) {
		ArrayList<String> temp = new ArrayList<String>(players);
		int temp_size = temp.size();
		
		playerList = new CompetitorQueue(temp_size);

		
		for(int i=0;i<temp_size;i++){
			tempInput = temp.get(i);
			playerList.push(tempInput);
		}
	}

	
	 //If there are more players to play, a next match must be possible
	@Override
	public boolean hasNextMatch() {
		
		if(playerList.morePlayers()){
			return true;
		}
		else{
			return false;
		}		

	}

	
	//Gets the front two values and returns the Match with these values
	@Override
	public Match nextMatch() throws NoNextMatchException {
		
		if(playerList.isEmpty()){
			throw new NoNextMatchException("No next match!");
		}
		else{

			competitor1 = playerList.pop();
			competitor2 = playerList.pop();

			Match match = new Match(competitor1, competitor2);
			
			return match;
		}
		
	}

	
	//If player1 equals true, push that player back onto the queue, and vice versa
	@Override
	public void setMatchWinner(boolean player1) {
		if(player1){
			playerList.push(competitor1);
		}
		else {
			playerList.push(competitor2);
		}
	}

	
	//Returns null if a next match is possible, else returns requested value
	@Override
	public String getPosition(int n) {
		if(hasNextMatch()){
			return null;
		}
		if(n==0){
			return playerList.front();
		}
		else{
			return playerList.getRunnerUp();
		}
	}
}
