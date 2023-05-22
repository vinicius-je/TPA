package entities;

public class Edge<T> {
    private Vertex<T> destination;
    private Double weight;

    public Edge(Vertex<T> destination, Double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getDestination(){
        return destination;
    }

    public Double getWeight(){
        return weight;
    }

}
