import utils.Currency;
import utils.graph.DiGraph;
import utils.graph.Edge;
import utils.graph.Vertex;
import web.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BellmanFord {
  public DiGraph graph;
  public void initializeGraph(ArrayList<Currency> currencies) {
    for (Currency currency : currencies) {
      for (String rate : currency.rates.keySet()) {
        this.graph.addEdge(new Vertex(currency.base), new Vertex(rate), currency.rates.get(rate));
      }
    }
  }

  public BellmanFord(ArrayList<Currency> currencies) {
    this.graph = new DiGraph();
    this.initializeGraph(currencies);
  }

  public static void preProcessGraph(DiGraph graph) {
    for (Vertex base : graph.neighbors.keySet()) {
      for (Edge edge : graph.neighbors.get(base)) {
        edge.preprocessWeight(); // converts weight to -log10(weight)
      }
    }
  }

  public static boolean canRelax(Edge edge) {
      return edge.getDest().getMinimumWeight() > edge.getSource().getMinimumWeight() + edge.getWeight();
  }
  // TODO: Implement checkNegativeCycle()
  public static void bellmanFordAlgorithm() throws IOException {
    // First, turn all edge weights into log(weight), when we want to get the result, we will get the exponential form of log(weight)
    // When we get the shortest path, aka a path that is negative, then in reality, when we exponentiate it, we will get the positive value.

    // TODO: Explain on README that this intends to find arbitrage opportunities on the foreign exchange market (FOREX)
    HashMap<Vertex, Vertex> predecessor = new HashMap<Vertex, Vertex>();
    BellmanFord test = new BellmanFord(HttpGet.fetchAllLatestCurrencies());

    BellmanFord.preProcessGraph(test.graph);

    int numOfVertices = test.graph.neighbors.keySet().size();
    // Let's let source vertex to be "USD", we just need one vertex to be 0.
    for (Vertex v : test.graph.neighbors.keySet()) {
      if (v.getCurrency().equals("USD")) {
        v.setMinimumWeight(0);
      }
    }

    // We relax all edges v-1 times
    for (int i = 0; i < numOfVertices - 1; i++) {
      for (Vertex v : test.graph.neighbors.keySet()) {
        for (Edge edge : test.graph.neighbors.get(v)) {
          if (BellmanFord.canRelax(edge)) {
            edge.getDest().setMinimumWeight(edge.getSource().getMinimumWeight() + edge.getWeight());
            if (predecessor.containsKey(edge.getDest())) {
              predecessor.replace(edge.getDest(), edge.getSource());
            } else {
              predecessor.put(edge.getDest(), edge.getSource());
            }
          }
        }
      }
    }

    // Now we check if any edges can be further relaxed, then it means we have a negative cycle.
    for (Vertex v : test.graph.neighbors.keySet()) {
      for (Edge edge : test.graph.neighbors.get(v)) {
        if (BellmanFord.canRelax(edge)) {
          System.out.println("ho");
          StringBuilder printCycle = new StringBuilder();
          // Go through predecessor list of the edge's source.
          printCycle.insert(0, String.format(" <<< %s <<< %s", edge.getSource(), edge.getDest()));
          HashSet<Vertex> found = new HashSet<Vertex>();
          found.add(edge.getSource());
          found.add(edge.getDest());
          Vertex current = predecessor.get(edge.getSource());
          while (!found.contains(current)) {
            printCycle.insert(0, String.format(" <<< %s",current));
            found.add(current);
            current = predecessor.get(current);
          }
          System.out.println(printCycle.toString());
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BellmanFord.bellmanFordAlgorithm();
  }
}
