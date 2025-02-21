import java.util.*;

public class A3Q1 {
	public static int find_exit(int time, String[][] board) {
		// N = number of rows, M = number of columns of the N by M board
		int N = board.length;
		int M = board[0].length;

		// create a 2D array times which stores the smallest time to reach a building
		// and a 2D array directions which stores the possible directions
		int[][] times = new int[N][M];
		int [][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

		// create a queue needed for the BFS traversal
		Queue<int[]> queue = new LinkedList<>();

		// set spider-man's initial position (x0, y0) to (-1, -1)
		int x0 = -1;
		int y0 = -1;

		for (int i = 0; i <= N-1; i++){
			for (int j = 0; j <= M-1; j++){
				// found spider-man's initial position, so store it
				if (board[i][j].equals("S")){
					x0 = i;
					y0 = j;
					break;
				}
			}
			if (x0 != -1){
				break;
			}
		}

		times[x0][y0] = 0; // set time to get to initial position to 0
		queue.add(new int[]{x0,y0,0}); // add this start position to the queue

		while (!queue.isEmpty()){
			// go through each element of the queue
			int[] currentPosition = queue.poll();
			// store the information of the current building
			int currentX = currentPosition[0];
			int currentY = currentPosition[1];
			int currentTime = currentPosition[2];

			// check if currentTime has exceeding the maximum amount of time allowed
			if (currentTime > time){
				continue;
			}
			// check if spider-man reached a boarder element within the given time frame
			if (currentX ==0 || currentX == N-1 || currentY == 0 || currentY == M-1){
				return currentTime;
			}
			for (int[] d: directions){
				int x = currentX + d[0];
				int y = currentY + d[1];
				// check if spider-man already reached the building with a better time
				if (times[x][y] != 0 && times[x][y] <= currentTime + 1){
					continue;
				}
				// check if next building is not passable
				if (board[x][y].equals("1")){
					continue;
				}
				// check if spider-man cannot access the upper building
				else if (board[x][y].equals("U") && d[0] != 1){
					continue;
				}
				// check if spider-man cannot access the lower building
				else if (board[x][y].equals("D") && d[0] != -1){
					continue;
				}
				// check if spider-man cannot access the right building
				else if (board[x][y].equals("R") && d[1] != -1){
					continue;
				}
				// check if spider-man cannot access the left building
				else if (board[x][y].equals("L") && d[1] != 1){
					continue;
				}
				// compute the new time to reach the current building
				times[x][y] = currentTime + 1;
				// add this current building to the queue
				queue.add(new int[]{x, y, times[x][y]});
			}
		}
		// return -1 if spider-man is unable to exit the city
		return -1;
	}
}
