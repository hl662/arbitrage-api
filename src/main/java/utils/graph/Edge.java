package utils.graph;

public class Edge {
  Vertex source;
  Vertex dest;
  double weight;

  // Constructor
  public Edge(Vertex source, Vertex dest, double weight) {
    this.source = source;
    this.dest = dest;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return String.format("Source: %s, Dest: %s. Exchange Rate: %.6f", this.source, this.dest, this.weight);
  }
}
