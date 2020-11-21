import java.util.*;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 * creates a new instance of Graph with n vertices
	 */
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	
	public Vertex find_neighbour(Set<Vertex> unvisited_q){
		Vertex current = unvisited_q.iterator().next(); int lowestDistance = -1;
		for (Vertex v : unvisited_q){
			if (v.getSourceDistance() < lowestDistance || lowestDistance < 0){
				current = v; lowestDistance = v.getSourceDistance();
			}
		}
		return current;
	};

	/**
	 * Perform Dijkstra's algorithm to find shortest path from the start word to end word
	 */
	public void dijkstras(int start_index, int end_index, List<String> wordList) {

		Vertex start = vertices[start_index];

		vertices[start_index].setPredecessor(-1); vertices[start_index].setSourceDistance(0);
		// Since s is the start word (origin) set pred to -1, dist to 0.

		Set<Vertex> unvisited_q = new HashSet<>();

		vertices[start_index].setVisited(true);
		
		unvisited_q.add(start);

		while (!unvisited_q.isEmpty()) {
			start = find_neighbour(unvisited_q);

			for (AdjListNode node : start.getAdjList()) { 
				if (vertices[node.getVertexIndex()].getVisited() == false){
					/*
					* If adj node is not visited
					* add it to the unvisited queue
					*/
					unvisited_q.add(vertices[node.getVertexIndex()]);
				}
				if (( start.sourceDistance + node.getDistance()) < vertices[node.getVertexIndex()].getSourceDistance()) {   
					vertices[node.getVertexIndex()].setPredecessor(start.getIndex()); 
					vertices[node.getVertexIndex()].setSourceDistance(start.getSourceDistance() + node.getDistance()); 
				}

				if (vertices[end_index].getSourceDistance() != Integer.MAX_VALUE && node.getVertexIndex() == end_index) {

					Vertex curr_index = vertices[end_index];
					int predecessor_idx = curr_index.getPredecessor();
		
					System.out.println(wordList.get(end_index));
					while (predecessor_idx > -1) {
						curr_index = vertices[predecessor_idx];
						System.out.println(wordList.get(curr_index.getIndex()));
						predecessor_idx = curr_index.getPredecessor();
					}
		
					System.out.println("\nTotal weight of path: " +vertices[end_index].getSourceDistance());
					return;
				}

			}
			vertices[start.getIndex()].setVisited(true);
			// Remove from unvisited queue as it is now visited
			unvisited_q.remove(vertices[start.getIndex()]); 
		}

		System.out.println("Given words do not have a word ladder.");
	}
}
