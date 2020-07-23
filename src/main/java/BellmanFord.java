import utils.Currency;
import utils.graph.DiGraph;
import utils.graph.Vertex;
import web.HttpGet;

import java.io.IOException;
import java.util.ArrayList;

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


  public static void main(String[] args) throws IOException {
    BellmanFord test = new BellmanFord(HttpGet.fetchAllLatestCurrencies());
    System.out.println(test.graph);
  }
}
