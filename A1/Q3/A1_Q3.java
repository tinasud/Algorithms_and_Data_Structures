import java.util.*;

public class A1_Q3 {

	public static int elements(int[] sizes) {
		int max = 0;
		//use sliding window technique: initialize a hash set, legos, with size 1 million
		//the hash set stores legos from the current window
		int[] legos = new int[(int) Math.pow(10, 6)];
		//create integers i and j to keep track of unique legos for each window
		int i = 0; int j =0;

		//move j towards end of array
		while (j < sizes.length){
			int a = sizes[j];
			//if lego is not present in current hashset, add it
			legos[a]++;

			//move i towards beginning of array
			while (legos[a] > 1){
				int b = sizes[i];
				//if lego is already in current hashset, remove it
				legos[b]--;
				//continue until i is at the beginning of array
				i++;
			}

			//update the maximum number of unique legos
			max = Math.max(max, j-i+1);
			//continue until j reaches the end of array
			j++;
		}
		return max;
	}

}
