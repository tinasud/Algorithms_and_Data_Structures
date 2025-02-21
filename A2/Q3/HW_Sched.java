import java.util.*;

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}

	/**
	 *
	 * @return Array where output[i] corresponds to the assignment
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this

		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());

		// If homeworkPlan[i] has a value -1, it indicates that the
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];

		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}

		for (int i=0; i<=Assignments.size()-1; i++){
			//slot represents the deadline for the ith assignment
			int slot = Assignments.get(i).deadline;
			for (int j=slot-1; j>=0; j--){
				//find the greatest empty time slot in the homeworkPlan
				if (homeworkPlan[j] == -1){
					//replace the empty time slot with the ith assignment
					//since assignments is sorted by assignment with
					//the largest weight to the assignment with the smallest weight
					homeworkPlan[j] = Assignments.get(i).number;
					break;
				}
			}
		}
		//return the homeworkPlan which maximizes the sum of the weights
		//of completed assignments
		return homeworkPlan;
	}
}
