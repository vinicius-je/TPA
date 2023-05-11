package entities;

import java.util.ArrayList;

public class Vertex<T> {
    private T value;
    private ArrayList<Edge> destinations = new ArrayList<>();

    public Vertex(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public void addDestination(Edge edge){
        destinations.add(edge);
    }

    public ArrayList<Edge> getDestinations(){
        return destinations;
    }

    @Override
    public String toString() {
        return "Vertex [value=" + value + "]";
    }

    
}
