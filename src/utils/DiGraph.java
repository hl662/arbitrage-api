import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiGraph {
    // We want the weighted edge between two vertices. Use an adjacency list.
    private Map<Vertex, List<Vertex>> neighbors = new HashMap<Vertex, List<Vertex>>();

    public String toString() {

        for (Vertex v : neighbors.keySet()) {
            System.out.println(v); // Will later use the neighbors.get(v) to get the items neighbors.
        }
    }
    public static void main(String[] args) {

    }
}

// Initialize a weight between two vertices as infinity first, then assign the weight accordingly
class Vertex {
    // int weight;
    String currency;

    @Override
    public String toString() {
        return String.format("Currency at this vertex: "+ currency + "\n");
    }
}