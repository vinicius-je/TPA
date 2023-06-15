package entities;

public class Edge<T> implements Comparable<Edge<T>> {
    private Vertex<T> destination;
    private double weight;

    public Edge(Vertex<T> destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(double weight) {
        this.weight = weight;
    }

    public Vertex<T> getDestination(){
        return destination;
    }

    public double getWeight(){
        return weight;  
    }

    @Override
    public int compareTo(Edge<T> edge){
        return Double.compare(this.weight, edge.getWeight());
    }

    @Override
    public String toString() {
        return " ---> " + destination + " | " + weight;
    }
}
