/* Description of class:
 * The TeamBuilder class creates teams based on a sorted list of students that is read in 
 * from an input csv file. These new teams are then sent back to the Utility class to be
 * written to a file.
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class TeamBuilder {
	
	JavaKnowledgeComparator compare = new JavaKnowledgeComparator();  // UML
	Utility util = new Utility();  // UML
	
	// gets the data from readFile in the utility class
	Map<String, ArrayList<CSCE314Student>> students = util.getRoster();  

	// how the teams of CSCE314Students will be held - my take on what the UML lists
	Map<String, ArrayList<ArrayList<CSCE314Student>>> teams = new HashMap<String, ArrayList<ArrayList<CSCE314Student>>>();  
	
	
	public void outputResults() { util.sendResults(); }  // calls sendResults in order to create and write the output file
	
	
	public void outputErrors() { util.writeErrors(); }  // calls writeErrors in order to create and write the error file
	

	private void sortStudents() {
		
		// for loop goes through each section and uses the comparator in order to sort
		// the students based on java knowledge
		for (ArrayList<CSCE314Student> section : students.values()) {
			Collections.sort(section, compare);
		}
		
	}
	
	
	public void createTeams() {
		
		sortStudents();  // call sort function
		
		// nested for loop populates teams
		for (String key : students.keySet()) {
			
			ArrayList<CSCE314Student> section = students.get(key);  // get arraylist for each section by key
			teams.putIfAbsent(key, new ArrayList<ArrayList<CSCE314Student>>());  // create new section if needed
			int x = section.size()/2;
			
			// loops for half of the section size since we are creating teams of 2
			for (int i = 0; i < x; ++i) {
				teams.get(key).add(new ArrayList<CSCE314Student>(2));
				teams.get(key).get(i).add(section.get(i));
				teams.get(key).get(i).add(section.get(section.size()-i-1));
			}
			
			// checks for an odd amount of students and puts the middle student on their own team
			if (section.size() % 2 == 1) {
				teams.get(key).add(new ArrayList<CSCE314Student>(1));
				teams.get(key).get(x).add(section.get(x));
			}
			
		}
		
		util.importTeams(teams);  // send teams back to utility class to be used in write results
		
	}
	
	
	// used when testing program during development before file io was complete
	public void printStudents() {
		for(ArrayList<CSCE314Student> section : students.values()) {
			for(CSCE314Student kid : section) {
				System.out.println(kid.toString());
			}
		}
	}
	
	
	// used when testing program during development before file io was complete
	public void printTeams() {
		for(ArrayList<ArrayList<CSCE314Student>> section : teams.values()) {
			for(ArrayList<CSCE314Student> team : section) {
				for(CSCE314Student kid : team) {
					System.out.print(kid.toFile());
					System.out.print(" ");
				}
				System.out.println();
			}
		}
		
	}
	
	

	
	
}

