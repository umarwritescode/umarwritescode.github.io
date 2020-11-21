
/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
*/
public class AdjListNode {

	private int vertexIndex;
	private int distance;

	public AdjListNode(int n, int d){
		vertexIndex = n;
		distance = d;
	}
	
	public int getVertexIndex(){
		return vertexIndex;
	}


	public int getDistance() { return distance; }


}
