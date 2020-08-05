/* Description of class:
 * This is an abstract base class of a person which holds a name and unused gender.
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */

public abstract class Person {
	
	// private members
	private String firstName;
	private String lastName;
	private enum gender { male, female };
	
	
	// public getters, setters, and toString
	public void setFirstName(String fName) { firstName = fName; }
	public String getFirstName() { return firstName; }
	public void setLastName(String lName) { lastName = lName; }
	public String getLastName() { return lastName; }

	public String toString() { return this.getFirstName() + " " + this.getLastName(); }
	
}