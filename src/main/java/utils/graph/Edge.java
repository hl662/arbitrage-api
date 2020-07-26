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

  public Vertex getSource() {
    return this.source;
  }

  public Vertex getDest() {
    return this.dest;
  }

  public double getWeight() {
    return weight;
  }

  public void preprocessWeight() {
    this.weight = -1 * Math.log10(this.weight);
  }
  @Override
  public String toString() {
    return String.format("Source: %s, Dest: %s. Exchange Rate: %.6f", this.source, this.dest, this.weight);
  }
}
