/* Description of class:
 * The Students class inherits from Person and includes new variables such as UIN and Rank
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */


public class Student extends Person {
	
	// private members
	private String UIN;
	private int Rank;
	protected enum Ranks {Freshman, Sophomore, Junior, Senior};
	
	// constructor
	Student(String f, String l, String uin, int r) {
		this.setFirstName(f);
		this.setLastName(l);
		UIN = uin;
		Rank = r;
	}
	
	// public getters, setters, and toString
	public void setUIN(String uin) { UIN = uin; }
	public String getUIN() { return UIN; }
	public void setRank(int r) { Rank = r; }
	public int getRank() { return Rank; }
	
	public String toString() { return this.getFirstName() + " " + this.getLastName() + " " + UIN + " " + Ranks.values()[Rank-1]; }
	
}