import java.util.*;
import java.io.File;

public class FordFulkerson {
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> stack = new ArrayList<Integer>();
		ArrayList<Integer> visited = new ArrayList<Integer>();

		visited.add(source);
		stack.add(source);

		while (!stack.isEmpty()) {
			// find last element in stack
			int stackSize = stack.size() - 1;
			int last1 = stack.get(stackSize);
			// remove last element from stack and add it to visited list
			stack.remove(stackSize);
			visited.add(last1);
			while (!path.isEmpty()) {
				// find last element in path
				int pathSize = path.size() - 1;
				int last2 = path.get(pathSize);
				// find edge that connects both elements
				Edge e = graph.getEdge(last2, last1);
				// remove top element if the edge is null or the weight of the edge is 0
				if (e == null || e.weight == 0) {
					path.remove(pathSize);
				} else {
					break;
				}
			}
			// add last element (last1) in stack to path
			path.add(last1);
			// go through every edge in the graph
			for (Edge e: graph.getEdges()) {
				// add the nodes that are valid to the stack
				if ((e.nodes[0] == last1 && e.weight > 0) && (!visited.contains(e.nodes[1]))) {
					// check if the end node is destination
					if (e.nodes[1] == destination) {
						// if it is, add it to the path
						path.add(destination);
						// clear the stack, because dfs is done
						stack.clear();
					} else {
						// otherwise, add the end node to the stack and continue
						stack.add(e.nodes[1]);
					}
				}
			}
		}
		return path;
	}

	public static String fordfulkerson(WGraph graph){
		String answer="";
		int maxFlow = 0;

		// create residual and capacity graphs
		WGraph residual = new WGraph(graph);
		WGraph capacity = new WGraph(graph);

		// initialize all the weights of the edges to 0
		for (Edge e: graph.getEdges()) {
			graph.setEdge(e.nodes[0], e.nodes[1], 0);
		}

		// store source and destination nodes
		int source = graph.getSource();;
		int destination = graph.getDestination();

		// if source and destination are both in pathDFS, then pathDFS is successful
		while (pathDFS(source, destination, residual).contains(source) && pathDFS(source, residual.getDestination(), residual).contains(destination)) {
			ArrayList<Integer> dfs = pathDFS(source, destination, residual);
			// initialize bottleneck to infinity
			int bottleneck = Integer.MAX_VALUE;
			// compute the bottleneck flow
			for (int i = 0; i < dfs.size()-1; i++) {
				Edge e = residual.getEdge(dfs.get(i), dfs.get(i+1));
				if (e != null && e.weight < bottleneck) {
					bottleneck = e.weight;
				}
			}
			// augment the max flow
			for (int i = 0; i < dfs.size()-1; i++) {
				Integer u = dfs.get(i);
				Integer v = dfs.get(i+1);
				Edge e = graph.getEdge(u, v);
				// check if e is a forward edge, if so, increase e by bottleneck
				if (e != null) {
					graph.setEdge(u, v, e.weight + bottleneck);
				}
				// otherwise it is a backward edge, so decrease e by bottleneck
				else {
					graph.setEdge(u, v, e.weight - bottleneck);
				}
			}
			// update the residual graph
			for (int i = 0; i < dfs.size()-1; i++) {
				Integer u = dfs.get(i);
				Integer v = dfs.get(i+1);
				Edge e = graph.getEdge(u, v);
				Edge capE = capacity.getEdge(u, v);
				// check if edge weight is smaller/equal to edge weight in capacity graph
				if (e.weight <= capE.weight) {
					residual.setEdge(u, v, capE.weight - e.weight); // if so update edge in residual graph to the difference
				} else if (e.weight > 0) {
					Edge residualE = residual.getEdge(u, v);
					// check if residual edge is null
					if (residualE == null) {
						Edge backEdge = new Edge(u, v, e.weight);
						// add backEdge to the residual graph
						residual.addEdge(backEdge);
					}
					// otherwise, set backward edge in residual graph
					else {
						residual.setEdge(v, u, e.weight);
					}
				}
			}
			maxFlow += bottleneck; // add bottleneck to the maxFlow
			bottleneck = Integer.MAX_VALUE; // set bottleneck to infinity
		}
		answer += maxFlow + "\n" + graph.toString();
		return answer;
	}

	public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
		System.out.println(fordfulkerson(g));
	}
}
