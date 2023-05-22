package entities;

import java.util.ArrayList;

//Modelo: Listas de adjacência

public class Graph<T> {
    private ArrayList<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> addVertex(T valor){
        Vertex<T> newVertex = new Vertex<T>(valor);
        vertices.add(newVertex);
        return newVertex;
    }

    public Vertex getVertex(T value){
        //Percorre a lista de vertice até encontrar o valor desejado, caso contrário toda a lista
        //será percorrida e então será retornado null
        for(Vertex current : vertices){
            if(current.getValue().equals(value)){
                return current;
            }
        }
        return null;
    }

    public void addEdge(T origin, T destination, Double weight){
        Vertex originAux, destinationAux;

        //Busca pelo vertice de origem
        originAux = getVertex(origin);
        //Caso o vertice de origem não exista, o mesmo será criado
        if(originAux == null){
            originAux = addVertex(origin);
        }
        //Busca pelo vertice de destino
        destinationAux = getVertex(destination);
        //Caso o vertice de destino não exista, o mesmo será criado
        if(destinationAux == null){
            destinationAux = addVertex(destination);
        }
        //Adiciona a aresta na lista de adjacência do vertice de origem
        originAux.addDestination(new Edge(destinationAux, weight));
    }

    public void breadthFirstSearch(){
        ArrayList<Vertex> marked = new ArrayList<>();
        ArrayList<Vertex> queue = new ArrayList<>();
        //Seleciono o primeiro vertice como ponto de partida e então o mesmo é
        //adicionado na fila
        Vertex current = vertices.get(0);
        queue.add(current);

        while (queue.size() > 0){
            current = queue.get(0);
            //Remoção do vertice da vez da fila
            queue.remove(0);
            //O vertice da vez é adicionado na lista de marcados
            marked.add(current);
            System.out.println(current.getValue());

            //Seleciona os vertices adjacente
            ArrayList<Edge> destinations = current.getDestinations();
            
            Vertex nextVertex;
            for(int i = 0; i < destinations.size(); i++){
                nextVertex = destinations.get(i).getDestination();
                if(!marked.contains(nextVertex)){
                    queue.add(nextVertex);
                }
            }
        }
    }

    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }

    
}
