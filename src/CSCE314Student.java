/* Description of class:
 * This class inherits from Student and holds info specific to CSCE314 such as java knowledge and section number.
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */


public class CSCE314Student extends Student {
	
	// private members
	private int JavaKnowledge;
	private String Section;
	
	CSCE314Student(String f, String l, String uin, int r, int jk, String sec) {
		super(f, l, uin, r);
		JavaKnowledge = jk;
		Section = sec;
	}
	
	// public getters, setters, and toString
	public void setJavaKnowledge(int jKnow) { JavaKnowledge = jKnow; }
	public int getJavaKnowledge() { return JavaKnowledge; }
	public void setSectionNum(String s) { Section = s; }
	public String getSectionNum() { return Section; }
	
	public String toString() { return this.getFirstName() + " " + this.getLastName() + " " + this.getUIN() + " " + Ranks.values()[this.getRank()] + " " + Section + " " + JavaKnowledge; }
	
	// Created an extra toString function to make printing to file easier
	public String toFile() { return this.getFirstName() + " " + this.getLastName() + " (" + JavaKnowledge + ")"; }

}
