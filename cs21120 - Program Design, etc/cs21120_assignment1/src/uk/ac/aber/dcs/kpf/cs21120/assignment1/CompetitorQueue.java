package uk.ac.aber.dcs.kpf.cs21120.assignment1;

import java.util.ArrayList;
/**
 * 
 * @author karlfranks
 * Code based on Queue example provided in lectures
 *
 */
public class CompetitorQueue implements MyQueue {
	ArrayList<String> competitors = new ArrayList<String>();
	String runnerUp;
	int tempQueueLength;
	String[] listOfPlayers;
	int queueLength;
	
	public CompetitorQueue(int queueSize){
		//Sets up the array when initialised, so it should always have enough room for data
		listOfPlayers = new String[queueSize];
	}
	
	@Override
	public void push(String input) {
		//Checks for the next null element to add the data to, before setting it
		queueLength = listOfPlayers.length;
		int z = 0;
		boolean nullPlayer = false;
		while(nullPlayer==false && z<(queueLength - 2)){
			if(listOfPlayers[z]==null){
				nullPlayer = true;
			}
			else{
				
				nullPlayer = false;
				z++;
			}
		}
		
		listOfPlayers[z] = input;
	}

	@Override
	public String pop() {
		//Copies data from queue, then shuffles the elements down
		String player = listOfPlayers[0];
		shuffleDown();
		
		return player;
	}

	@Override
	public String front() {
		//Simply returns the first element in the queue
		if(listOfPlayers[0]==null) throw new QueueEmptyException();
		return listOfPlayers[0];
	}

	@Override
	public int length() {
		//Returns the length of the part of the array with data in
		int z = 0;
		boolean nullPlayer = false;
		while(nullPlayer==false && z<(queueLength - 2)){
			if(listOfPlayers[z]==null){
				nullPlayer = true;
			}
			else{
				nullPlayer = false;
				z++;
			}
		}
		
		return z;
	}

	@Override
	public boolean isEmpty() {
		//With the "shuffling down", the first element will always have data unless empty
		if(listOfPlayers[0]==null){
			return true;
		}
		else{
			return false;
		}
		
	}

	@Override
	public void clear() {
		//Sets all elements to null
		for(int i=0;i<listOfPlayers.length;i++){
				listOfPlayers[i]=null;
		}
	}
	
	public void shuffleDown(){
		//An inefficient solution for keeping the queue working
		for(int i=0;i<(queueLength-1);i++){
			String temp;
			int y = i +1;
			temp = listOfPlayers[y];
			listOfPlayers[i] = temp;
		}
	}

	public boolean morePlayers(){
		//Returns true when there are enough players to play more matches
		//Also stores the runner up value in the case of the third value being null
		if(listOfPlayers[2]==null){
			runnerUp = listOfPlayers[1];
		}
		
		if(listOfPlayers[1]==null){
			return false;
		}
		else{
			return true;
		}

	}
	
	public String getRunnerUp(){
		//Used to get the runner up when displaying final results
		return listOfPlayers[1];
	}
	
	
}
