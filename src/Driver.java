/* Description of class:
 * The driver class holds the main. The sole purpose for this class is to create a 
 * TeamBuilder object which runs the majority of the code.
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */


public class Driver {
	
	// create a new instance of the team builder class
	TeamBuilder teamMaker = new TeamBuilder();
	
	// only two functions needed from TeamBuilder
	public void run() {
		teamMaker.createTeams();
		teamMaker.outputResults();	
		teamMaker.outputErrors();
	}

	public static void main(String[] args) {
		
		Driver program = new Driver();
		
		program.run();  // runs the program
		
	}
}