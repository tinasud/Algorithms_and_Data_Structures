import java.util.*;

public class A2_Q4 {
	public static double swaps(int[] passengers) {
		int n = passengers.length;
		return mergesort(passengers, 0, n-1);
	}

	//own helper function mergesort
	private static double mergesort(int[] list, int p, int r){
		double numOfSwaps = 0.0;
		//followed pseudocode from slides
		if (p<r){
			int q = (p+r)/2;
			numOfSwaps += mergesort(list,p,q);
			numOfSwaps += mergesort(list,q+1,r);
			numOfSwaps += merge(list, p, q+1, r);
		}
		return numOfSwaps;
	}

	//own helper function merge
	private static double merge(int[] list, int p, int q, int r) {
		int i=p, j=q, k=0;
		double numOfSwaps = 0.0;
		//create temp array that will have the correct order of elements
		int[] temp = new int[(r-p+1)];
		//mostly followed pseudocode from slides
		//select elements to add until one of the indexes reaches the maximum
		while (i<q && j<=r){
			//check if element on left is smaller than elements on right
			if (list[i] <= list[j]){
				temp[k] = list[i];
				k++;
				i++;
			}
			//check if element on right is smaller than element on left
			else {
				temp[k] = list[j];
				//if so, need to swap elements, so update numOfSwaps
				numOfSwaps += (q-i);
				k++;
				j++;
			}
		}
		//add remaining elements to temp array
		while (i<q){
			temp[k] = list[i];
			k++;
			i++;
		}
		//add remaining elements to temp array
		while (j<=r){
			temp[k] = list[j];
			k++;
			j++;
		}
		//modify input array so that it contains the correct order
		for (i=p, k=0; i<=r; i++, k++){
			list[i] = temp[k];
		}
		return numOfSwaps;
	}
}
