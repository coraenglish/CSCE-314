/* Description of class:
 * This class holds the comparator which will compare the java knowledge of student a and b 
 * and return 1,-1, or 0 accordingly.
 * Cora English
 * 127007634
 * coraenglish@tamu.edu
 */


import java.util.Comparator;

public class JavaKnowledgeComparator implements Comparator<CSCE314Student> {
	
	// basic design of a compare function
	public int compare(CSCE314Student a, CSCE314Student b) {
		if (a.getJavaKnowledge() > b.getJavaKnowledge()) return 1;
		else if (a.getJavaKnowledge() < b.getJavaKnowledge()) return -1;
		else return 0;
	}
	
}