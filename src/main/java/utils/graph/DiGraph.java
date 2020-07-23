// https://www.cs.cornell.edu/courses/cs211/2006fa/Lectures/L22-More%20Graphs/Digraph.java
package utils.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Initialize a weight between two vertices as infinity first, then assign the
// weight accordingly
// Credits to Cornells' CS211 DiGraph Implementation, which I used for reference.
public class DiGraph {
  // We want the weighted edge between two vertices. Use an adjacency list.
  // TODO: We don't need vertex, we can just use String. Same for Edge, Go inside of Edge class and set source and dest to be of type String instead.
  private Map<Vertex, List<Edge>> neighbors = new HashMap<Vertex, List<Edge>>();

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    for (Vertex v : this.neighbors.keySet()) {
      result.append(String.format("\n%s\n", v)); // Will later use the neighbors.get(v) to get the items neighbors.
      List<Edge> edges = this.neighbors.get(v);
      for (Edge edge : edges) {
        result.append(String.format("\t%s\n", edge));
      }
      result.append("\n");
    }
    return result.toString();
  }

  public void add(Vertex newVertex) {
    if (neighbors.containsKey(newVertex)) {
      return;
    }
    neighbors.put(newVertex, new ArrayList<Edge>());
  }

  public void addEdge(Vertex source, Vertex dest, double weight) {
    this.add(source);
    this.add(dest);
    this.neighbors.get(source).add(new Edge(source, dest, weight));
  }
  public static void main(String[] args) {
    // Testing.
    DiGraph testGraph = new DiGraph();
    Vertex usd = new Vertex("USD");
    Vertex yen = new Vertex("YEN");
    Vertex gbp = new Vertex("GBP");
    Vertex rand = new Vertex("RND");
    Vertex eur = new Vertex("EUR");
    testGraph.add(usd);
    testGraph.add(yen);
    testGraph.add(gbp);
    testGraph.add(rand);
    testGraph.add(eur);

    // Test
    testGraph.addEdge(eur, usd, 1.2234);
    System.out.print(testGraph);
  }
}
