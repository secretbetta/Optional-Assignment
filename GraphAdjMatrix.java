import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Graph Adjacent Matrix
 * @author Andrew
 *
 */
public class GraphAdjMatrix implements Graph {
	
	/**
	 * Edge Class storing weight, source, and destination
	 * @author Andrew
	 *
	 */
	class Edge implements Comparable<Edge> {
		
		int weight;
		int src;
		int dest;
		
		public Edge(int v1, int v2, int weight) {
			this.src = v1;
			this.dest = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
		
	}
	
	/**
	 * Disjoint Set Class
	 * @author Modified by Andrew, from Sleek
	 *
	 */
	class DisjointSet{
		private int[] set;
		
		public DisjointSet(int numElements) {
			set = new int[numElements];
			for (int i = 0; i < set.length; i++) {
				set[i] = -1;
			}
		}
		
		public void union(int root1, int root2) {
			if (set[root2] < set[root1]) {
				set[root1] = root2;
			} else {
				if (set[root1] == set[root2]) {
					set[root1]--;
				}
				set[root2] = root1;
			}
		}
		
		public int find(int x) {
			if (set[x] < 0) {
				return x;
			}
			int next = x;		
			while (set[next] > 0) {
				next = set[next];
			}
			
			return next;
		}
	}
	
	int vertices;
	TreeSet<Edge> edges;
	
	public GraphAdjMatrix(int vertices) {
		edges = new TreeSet<Edge>();
		this.vertices = vertices;
	}

	/**
	 * Adds edge to source and destination
	 */
	@Override
	public void addEdge(int v1, int v2, int weight) {
		Edge edge = new Edge(v1, v2, weight);
		edges.add(edge);
	}

	/**
	 * Gets the weight of a specified edge
	 */
	@Override
	public int getEdge(int v1, int v2) {
		for (Edge edge : edges) {
			if (edge.src == v1 && edge.dest == v2) {
				return edge.weight;
			}
		}
		return 0;
	}

	/**
	 * Creates a Spanning tree and returns total weight of edges
	 */
	@Override
	public int createSpanningTree() {
		ArrayList<Edge> tempEdges = new ArrayList<Edge>(); 
		DisjointSet nodes = new DisjointSet(this.vertices + 1);
		int totalweight = 0;
		
		for (Edge curr : this.edges) {
			if (tempEdges.size() >= this.vertices - 1) {
				break;
			}
			
			int root1 = nodes.find(curr.src);
			int root2 = nodes.find(curr.dest);
			
			if (root1 != root2) {
				totalweight += curr.weight;
				nodes.union(root1, root2);
			}
		}
		
		return totalweight;
	}

	@Override
	public void addEdge(int v1, int v2) {
	}

	@Override
	public void topologicalSort() {
	}
}
