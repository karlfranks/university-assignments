package uk.ac.aber.dcs.kpf.cs21120.assignment1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DoubleEliminationTest {
	IManager managerOne, managerTwo, managerThree, managerFour;
	Match matchOne, matchTwo, matchThree, matchFour;
	ArrayList<String> testData = new ArrayList<String>();
	int amountOfPlayers;
	@Before
	public void setup(){
		//Set up separate IManagers for separate tests - so no interference possible
        managerOne = IManagerFactory.getManager("uk.ac.aber.dcs.kpf.cs21120.assignment1.DoubleElimination");
        managerTwo = IManagerFactory.getManager("uk.ac.aber.dcs.kpf.cs21120.assignment1.DoubleElimination");
        managerThree = IManagerFactory.getManager("uk.ac.aber.dcs.kpf.cs21120.assignment1.DoubleElimination");
        managerFour = IManagerFactory.getManager("uk.ac.aber.dcs.kpf.cs21120.assignment1.DoubleElimination");
        
        amountOfPlayers = 6;
       
        //Adds some test data
        for(int i=0;i<(amountOfPlayers-1);i++){
        	testData.add(i, ("Competitor " + i));
        }
        
        managerOne.setPlayers(testData);
        managerTwo.setPlayers(testData);
        managerThree.setPlayers(testData);
        managerFour.setPlayers(testData);
	}
	
	//Tests if the players have been set by setPlayers
	@Test
	public void setPlayers(){
		matchOne = managerOne.nextMatch();
		
		String compone = "Competitor 0";
		String comptwo = "Competitor 1";

		managerOne.setMatchWinner(true);
		assertEquals("Items not added", compone, matchOne.getPlayer1());
		assertEquals("Items not added", comptwo, matchOne.getPlayer2());
		
	}
	
	//Tests if a next match is possible, which it should be
	@Test
	public void hasNextMatch(){
		assertEquals("Error: there should be another match", true, managerOne.hasNextMatch());
	}
	
	//Tests if it can get the next match correctly
	@Test
	public void nextMatch(){
		matchTwo = managerTwo.nextMatch();

		String playerone = "Competitor 0";
		String playertwo = "Competitor 1";
		
		assertEquals("Match not found", playerone, matchTwo.getPlayer1());
		assertEquals("Match not found", playertwo, matchTwo.getPlayer2());
	}
	
	//Cycles through a few matches, to test if the winners have been set correctly
	@Test
	public void setMatchWinner(){	
		//With 6 competitors, this should mean the front two players are Competitor 0 and Competitor 2
		matchThree = managerThree.nextMatch(); //match one
		managerThree.setMatchWinner(true);
		matchThree = managerThree.nextMatch(); //match two
		managerThree.setMatchWinner(true);
		matchThree = managerThree.nextMatch(); //match three
		managerThree.setMatchWinner(true);
		
		String playerone = "Competitor 0";
		String playertwo = "Competitor 2";
		
		assertEquals("Match results not set correctly", playerone, matchThree.getPlayer1());
		assertEquals("Match results not set correctly", playertwo, matchThree.getPlayer2());
	}
	
	//Tests if the position is returned correctly
	@Test
	public void getPosition(){
		String temp = managerFour.getPosition(0);
		assertEquals("Error with getting position", null, temp);
	}

}
