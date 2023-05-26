package entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/***********************************
 * @Autor: Vinícius Estevam
 * Modelo: Listas de adjacência
 ***********************************/

public class Graph<T> {
    private ArrayList<Vertex<T>> vertices = new ArrayList<>();
    
    public Vertex<T> addVertex(T valor){
        Vertex<T> newVertex = new Vertex<T>(valor);
        vertices.add(newVertex);
        return newVertex;
    }

    public Vertex getVertex(T value){
        //Percorre a lista de vertice até encontrar o valor desejado, caso contrário será retornado null
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

    public void breadthFirstSearch(T value){
        ArrayList<Vertex> marked = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        //Seleciono vertice informado pelo usuário como ponto de partida
        //e então o mesmo é adicionado na fila
        Vertex current = this.getVertex(value);
        queue.add(current);

        while (queue.size() > 0){
            //Remoção do vertice da vez da fila
            current = queue.poll();
            //O vertice da vez é adicionado na lista de marcados
            marked.add(current);
            
            System.out.println("\t" + current.getValue());
            //Seleciona os vertices adjacente
            ArrayList<Edge> destinations = current.getDestinations();
            
            Vertex nextVertex;
            for(int i = 0; i < destinations.size(); i++){
                nextVertex = destinations.get(i).getDestination();
                if(!marked.contains(nextVertex)){
                    //Adiciona o proximo vertice na fila
                    queue.add(nextVertex);
                    //Marca o vertice como visitado
                    marked.add(nextVertex);
                }
            }
        }
    }

    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }
}
