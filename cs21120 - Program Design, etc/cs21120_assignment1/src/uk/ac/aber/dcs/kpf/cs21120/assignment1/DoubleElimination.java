package uk.ac.aber.dcs.kpf.cs21120.assignment1;

import java.util.ArrayList;

public class DoubleElimination implements IManager{
	/*Variables*/
	ArrayList<String> temp;
	CompetitorQueue winnerQueue;
	CompetitorQueue loserQueue;
	String tempInput;
	String competitor1;
	String competitor2;
	boolean loserOrWinnerMatch; //true = winner queue match, false = loser queue match
	
	//Sets up the winners and losers queues, and copies initial data into winners queue
	@Override
	public void setPlayers(ArrayList<String> players) {
		ArrayList<String> temp = new ArrayList<String>(players);
		int temp_size = temp.size();
		
		winnerQueue = new CompetitorQueue(temp_size);
		loserQueue = new CompetitorQueue(temp_size);
		
		for(int i=0;i<temp_size;i++){
			tempInput = temp.get(i);
			winnerQueue.push(tempInput);
		}
	}

	//
	@Override
	public boolean hasNextMatch() {
		//If the winner queue has 2 elements and loser has none, the competition is over
		if(winnerQueue.length()==2 && loserQueue.length() ==0){
			return false;
		}
		
		//If either has more players to play, a next match must be possible
		if(winnerQueue.morePlayers()||loserQueue.morePlayers()){
			return true;
		}
		else{
			return false;
		}		

	}

	
	
	@Override
	public Match nextMatch() throws NoNextMatchException {

		if(winnerQueue.isEmpty()){
			throw new NoNextMatchException("No next match!");
		}
		else{
			/**
			 * Takes from the largest queue
			 * Except when queue lengths are the same
			 * In which case it checks if there's only one element left (ie: last round)
			 **/
			if(winnerQueue.length()>loserQueue.length()){
				competitor1 = winnerQueue.pop();
				competitor2 = winnerQueue.pop();
				loserOrWinnerMatch = true;
			}
			else if(winnerQueue.length()==loserQueue.length()){
				if(winnerQueue.length()==1){
					competitor1 = winnerQueue.pop();
					competitor2 = loserQueue.pop();
					loserOrWinnerMatch = true;
				}
				else{
					competitor1 = winnerQueue.pop();
					competitor2 = winnerQueue.pop();
					loserOrWinnerMatch = true;
				}
			}

			else{
				competitor1 = loserQueue.pop();
				competitor2 = loserQueue.pop();
				loserOrWinnerMatch = false;
			}
			
			Match match = new Match(competitor1, competitor2);
			
			return match;
		}
		
	}

	
	//Pushes data to queues depending on if winner or loser queue match
	@Override
	public void setMatchWinner(boolean player1) {

		if(loserOrWinnerMatch){
			if(player1){
				winnerQueue.push(competitor1);
				loserQueue.push(competitor2);
			}
			else {
				winnerQueue.push(competitor2);
				loserQueue.push(competitor1);
			}
		}
		else{
			if(player1){
				winnerQueue.push(competitor1);
			}
			else {
				winnerQueue.push(competitor2);
			}
		}
	}

	
	//Returns null if a next match is possible, else returns requested value
	@Override
	public String getPosition(int n) {
		if(hasNextMatch()){
			return null;
		}
		if(n==0){
			return winnerQueue.front();
		}
		else{
			return winnerQueue.getRunnerUp();
		}
	}
	
}
