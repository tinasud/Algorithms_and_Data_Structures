import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// TODO Implement this
		//compare only the weight of the assignments
		//if weight is the same return 0
		if (a1.weight == a2.weight){
			return 0;
		}
		//if weight of a1 is less than the weight of a2, then a1 should appear
		//after a2 (since its less important) so return 1
		else if (a1.weight < a2.weight){
			return 1;
		}
		//otherwise, a2 should appear after a1
		//so return -1
		return -1;
	}
}
