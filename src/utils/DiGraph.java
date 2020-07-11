// https://www.cs.cornell.edu/courses/cs211/2006fa/Lectures/L22-More%20Graphs/Digraph.java
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Credits to Cornells' CS211 DiGraph Implementation, where I used it for reference.
public class DiGraph {
	// We want the weighted edge between two vertices. Use an adjacency list.
	private Map<Vertex, List<Vertex>> neighbors = new HashMap<Vertex, List<Vertex>>();

	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Vertex v : neighbors.keySet()) {
			result.append(String.format("\n%s\n", v)); // Will later use the neighbors.get(v) to get the items neighbors.
		}
		return result.toString();
	}

	public void add(Vertex newVertex) {
		// Initialize a new adjacent list;
		neighbors.put(newVertex, new ArrayList<Vertex>());
	}

	public static void main(String[] args) {
      DiGraph testGraph = new DiGraph();
			Vertex usd = new Vertex("USD");
			Vertex yen = new Vertex("YEN");
			Vertex gbp = new Vertex("GBP");
			Vertex rand = new Vertex("RND");
			testGraph.add(usd);
			testGraph.add(yen);
			testGraph.add(gbp);
			testGraph.add(rand);
			System.out.print(testGraph);
    }
}

// Initialize a weight between two vertices as infinity first, then assign the
// weight accordingly
class Vertex {
	// int weight;
	String currency;

	public Vertex(String newCurrency) {
		currency = newCurrency;
	}

	@Override
	public String toString() {
		return String.format("Currency at this vertex: " + currency);
	}
}