import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 creates a new instance of Graph with n vertices
	*/
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	 */
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexIndex();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	 */
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}


	/**
	 carry out a breadth first search/traversal of the graph
	 */
	public void bfs(int start, int end, List<String> wordList) {

		// Queue will hold the vertices to process
  		LinkedList<Vertex> queue = new LinkedList<Vertex>(); 

		  	// Initially we just have the vertex corresponding to the start word
			Vertex vtx = vertices[start]; vtx.setVisited(true); vtx.setPredecessor(vtx.getIndex()); queue.add(vtx); 

			while (!queue.isEmpty()) {
				Vertex start_vertex = queue.remove(); // get next vertex to process


					for (AdjListNode node : start_vertex.getAdjList()) {
						Vertex corr_n_vertex = vertices[node.getVertexIndex()];

						if (corr_n_vertex.getVisited() == false) { 
							corr_n_vertex.setVisited(true);
							corr_n_vertex.setPredecessor(start_vertex.getIndex()); 
							queue.add(corr_n_vertex); // add to queue for processing since it was unvisited just prior

							int no_of_words = 0;

							if (corr_n_vertex.getIndex() == end) { // reached the target word
								System.out.println(wordList.get(corr_n_vertex.getIndex()));
								int predecessor_i = corr_n_vertex.getPredecessor();
								no_of_words++;
							

								while (predecessor_i != start) {
									System.out.println(wordList.get(predecessor_i));
									predecessor_i = vertices[predecessor_i].getPredecessor();
									no_of_words++;
								}
								System.out.println(wordList.get(start));
								no_of_words++;

								System.out.println("Word count: " + no_of_words);
								return;
							}

						}
					}
				}
				System.out.println("Word ladder not possible for " + wordList.get(start) + " and " + wordList.get(end));
			}

			
		}



