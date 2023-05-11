package entities;

public class Edge {
    private Vertex destination;
    private Double weight;

    public Edge(Vertex destination, Double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getDestination(){
        return destination;
    }
}
