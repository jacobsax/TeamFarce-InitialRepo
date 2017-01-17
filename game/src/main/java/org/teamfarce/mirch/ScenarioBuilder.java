package org.teamfarce.mirch;

import java.util.ArrayList;

import org.junit.Test;

public class ScenarioBuilder {
	/**
	 * 
	 * @return ArrayList of Suspects
	 * 
	 * Generates an ArrayList of suspects and returns the ArrayList
	 */
	public ArrayList<Suspect> generateSuspects(){
		//Load suspects from database
		//Store list of suspects in ArrayList<Suspect>
		
		return null; //return new array list
	}
	
	/**
	 * 
	 * @param suspectList
	 * @return Suspect
	 * 
	 * Chooses a suspect from the suspect list and returns the Suspect Object
	 */
	public Suspect chooseVictim(ArrayList<Suspect> suspectList){
		//randomly choose a victim from the suspect list
		//return the suspect
		return null;
	}
	
	/**
	 * 
	 * @param suspectList
	 * @return updates suspectList with murderer marked
	 * 
	 * Randomly selects a murderer from the suspect list, and marks that suspect
	 * Then returns the edited list
	 */
	public ArrayList<Suspect> chooseMurderer(ArrayList<Suspect> suspectList){
		//randomly select a murderer from the list
		//mark the murderer by changing boolean
		return suspectList;
	}
	
	/**
	 * 
	 * @param size
	 * @return ArrayList of rooms
	 * 
	 * Generates an array list of rooms of size 'size' and returns the list
	 */
	public ArrayList<Room> generateRooms(int size){
		return null;
	}
	
	/**
	 * 
	 * @param props
	 * @param roomID
	 * @return ArrayList of Props
	 * 
	 * Takes an ArrayList of props and a room ID, adds additional props to the list and returns the extended list
	 */
	public ArrayList<Prop> generateProps(ArrayList<Prop> props, int roomID){
		//load props from prop list into array list
		return null; //return new prop list
	}
	
	/**
	 * 
	 * @return Clue
	 * 
	 * Generates a clue object and returns it
	 */
	public Clue generateClue(Prop prop){
		//generate clue from prop and return
		return null;
	}
	
	
	public GameSnapshot generateGame(int roomNumber/*Pass character traits here*/){
		//Generate suspects
		ArrayList<Suspect> suspects = this.generateSuspects();
		
		//Choose Victim
		Suspect victim = this.chooseVictim(suspects);
		
		//Remove victim from suspect list
		boolean success = suspects.remove(victim);
		if (success){
			System.out.println("Suspect removal successful");
		} else {
			System.out.println("Suspect removal failed");
		}
		
		//Choose Murderer
		suspects = this.chooseMurderer(suspects);
		
		//Choose Murder Weapon??!
		
		//Generate Rooms
		ArrayList<Room> rooms = this.generateRooms(roomNumber);
		
		//For Each Room, generate Props
		ArrayList<Prop> props = null; //create an empty list of props
		
		//Iterate through each room in the rooms ArrayList
		for (Room room: rooms){
			props = this.generateProps(props, room.id);
		}
		
		//For Each Prop, generate Clues
		//Iterate through each prop in the props ArrayList
		for (Prop prop : props){
			prop.clue.add(this.generateClue(prop)); //add a clue to the prop
		}
		
		//Generate Detective - using character traits
		
		//Generate Dialogue Tree
		//Iterate through each suspect in the suspect list
		for (Suspect suspect : suspects){
			
			//load dialogue text screens
			//search for next text screen from follow up question linker
			//load next dialogue text screen
			
			//store dialogue tree in suspect
		}
		
		//Produce Game Snapshot
		
		//Return Game Snapshot
		return null;
		
	}
	
	@Test
	public void testGenerateGame(){
		this.generateGame(10);
		//temporary output until test is fully built
		System.out.println("Generate Game Successful");
	} 

}
