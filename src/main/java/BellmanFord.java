import utils.Currency;
import utils.graph.DiGraph;
import utils.graph.Edge;
import utils.graph.Vertex;
import web.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
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

  public static void relax(DiGraph graph, Edge edge) {
      if (edge.getSource().getMinimumWeight() > edge.getDest().getMinimumWeight() + edge.getWeight()) {
        edge.getSource().setMinimumWeight(edge.getDest().getMinimumWeight() + edge.getWeight()); // TODO: Review, might not be done yet.
      }
  }
  // TODO: Implement checkNegativeCycle()
  public void bellmanFordAlgorithm(Vertex source, DiGraph graph) {
    // First, turn all edge weights into log(weight), when we want to get the result, we will get the exponential form of log(weight)
    // When we get the shortest path, aka a path that is negative, then in reality, when we exponentiate it, we will get the positive value.
    // TODO: Preprocess the edge weights.
    // First, lets initialize a graph.
    // Edge to source is 0, the rest is infinity.
    // TODO: Explain on README that this intends to find arbitrage opportunities on the foreign exchange market (FOREX)
    DiGraph newGraph = new DiGraph();
    newGraph.addEdge(source, source, 0);

  }

  public static void main(String[] args) throws IOException {
    BellmanFord test = new BellmanFord(HttpGet.fetchAllLatestCurrencies());
    System.out.println(test.graph);
  }
}
