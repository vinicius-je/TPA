package entities;

public class Edge<T> implements Comparable<Edge<T>> {
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

    @Override
    public int compareTo(Edge<T> edge){
        return Double.compare(this.weight, edge.getWeight());
    }

}
