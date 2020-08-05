/* Description of class:
 * The Utility class holds the private readFile and writeResults functions according to the UML.
 * Other functions were added since readFile and writeResults were both private and since the 
 * Utility class could only be instantiated in the team builder class and not called directly
 * from the Driver.
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.*; // Import regex to parse data in .csv
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Utility {
	
	// roster of students held in a <section number, list of students> map
	private Map<String, ArrayList<CSCE314Student>> roster = new HashMap<String, ArrayList<CSCE314Student>>();

	
	// calls readFile and sends the resultant roster to team builder
	public Map<String, ArrayList<CSCE314Student>> getRoster() {
		readFile();
		return roster;
	}
	
	
	// create local private teams to use in write results
	private Map<String, ArrayList<ArrayList<CSCE314Student>>> teams = new HashMap<String, ArrayList<ArrayList<CSCE314Student>>>();
	
	
	// imports the teams created in team builder to write results in file in this class
	public void importTeams(Map<String, ArrayList<ArrayList<CSCE314Student>>> t) { 
		teams = t;
	}
	
	
	public void sendResults() { writeResults(); }  // public function to write results which will be calles in the team builder class

	
	private ArrayList<String> errors = new ArrayList<String>();  // error list that will
	
	// uses errors arraylist to write error log text file
	public void writeErrors() {
		
		try {
			
			PrintWriter outfile = new PrintWriter("ErrorLog.txt");  // create new ErrorLog file
			for (String message : errors) {
				outfile.println(message);  // prints all errors to text file
			}
			outfile.close();
			
		} catch (FileNotFoundException e) {

			// Exits program if the program cannot write to the file
			e.printStackTrace();
			System.exit(0);
		
		}
	}
	

	// made this private under the assumption that's what the "-" in the UML designated...
	private boolean readFile() {    

		try {

			File f = new File("CSCE 314 Spring 2020 Project 1 Survey Data  - Form Responses 1.csv");
			Scanner infile = new Scanner(f);

			// Using regex in order to match the pattern allows for the groups such as first
			// name, UIN, section to already be separated out. Groups are as follows:
			// 0 - Entire line || 1 - Timestamp || 2* - Skill level || 3 - Last time programming in java || 4 - Keep partner?
			// 5* - Firstname || 6* - Lastname || 7* - Section number w/o time || 8* - UIN w/o @tamu.edu || 9* - Rank
			// #* indicates a group that will be used in the CSCE314Student constructor
			
			String pattern = "(.*?),([1-5]),(.*?),\"?([^,]{2,3}),?.*?,(.*) (.*),(\\d\\d\\d).*,(.{10}).*,(.*)";
			Pattern p = Pattern.compile(pattern);
			String ignore = infile.nextLine(); // remove header from pattern matching

			while (infile.hasNextLine()) {
				
				String line = infile.nextLine();  // get next line
				Matcher m = p.matcher(line);  // match the line according to regex pattern
				m.matches();

				// if statement accounts for errors in rank that are not accounted for in enum
				if (!m.group(9).contentEquals("1") && !m.group(9).contentEquals("2")
						&& !m.group(9).contentEquals("3") && !m.group(9).contentEquals("4")) { 
					errors.add("[Utility-readFile()] RANK ERROR - Invalid rank found in csv file: " + m.group(9) + ".");  // add to list of errors for error log
					continue;  // skip rest of while loop
				}

				// if statement accounts for errors in java knowledge that are not valid numbers (1-5 only)
				if (!m.group(2).contentEquals("1") && !m.group(2).contentEquals("2")
						&& !m.group(2).contentEquals("3") && !m.group(2).contentEquals("4") 
						&& !m.group(2).contentEquals("5")) { 
					errors.add("[Utility-readFile()] JAVA KNOWLEDGE ERROR - Invalid java knowledge found in csv file: " + m.group(2) + ".");  // add to list of errors for error log
					continue;  // skip rest of while loop 
				}
				
				
				// create a new kid from line that has been read in
				CSCE314Student kid = new CSCE314Student(m.group(5), m.group(6), m.group(8),
						(Integer.parseInt(m.group(9)) - 1), Integer.parseInt(m.group(2)), m.group(7));
				
				String key = m.group(7); // section number is the key for the hash map in order to keep sections separate
				
				// accounts for new sections in the hash map and creates a new array list
				roster.putIfAbsent(key, new ArrayList<CSCE314Student>());
				roster.get(key).add(kid); // add kid to appropriate section
			}

			// exit while loop - close file and return true
			infile.close();
			return true;
			
		} catch (FileNotFoundException e) {
			
			// Exits program if the program cannot write to the file
			errors.add("[Utility-readFile()] FILE ERROR - Could not find input file.");  // add to list of errors for error log
			e.printStackTrace();
			System.exit(0);
			return false;  // failed ot read so return false
		
		}
			 
	}

	
	
	// made this private under the assumption that's what the "-" in the UML designated...
	private boolean writeResults() {
		PrintWriter outfile = null;
		try {
			
			outfile = new PrintWriter("results.txt");
			
			// loop through keys
			for (String key : teams.keySet()) {
				
				ArrayList<ArrayList<CSCE314Student>> section = teams.get(key);  // get arraylist for each section by key
				outfile.println("--- Section #" + key + " ---");  // section header
				
				for(ArrayList<CSCE314Student> team : section) {  // loop through teams
					
					outfile.print(key + " - ");
					if (team.size() == 1) {
						outfile.print("[EXTRA] ");  // case for odd number of students
					}
					
					for(CSCE314Student kid : team) {
						outfile.print(kid.toFile());  // special version of a toString
						outfile.print(" ");
					}
					
					outfile.println();  // newline in results file
				}
			}
			
			// close file and return true
			outfile.close();
			return true;
			
		} catch (FileNotFoundException e) {

			// Exits program if the program cannot write to the file
			errors.add("[Utility-writeResults()] FILE ERROR - Could not open output file.");
			e.printStackTrace();
			System.exit(0);
			return false;
		
		}
	}

}

