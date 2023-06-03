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

    public Graph<T> minimumSpanningTree(){
        Graph<T> graph = new Graph<>();
        ArrayList<Vertex<T>> marked = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        //Seleciona o primeior nó do grafo
        Vertex<T> current = this.getVertices().get(0);
        //Adiciona o primeiro vértice do grafo na fila
        queue.add(current);
        //Adiciona a primeira cidade como um novo vértice no grafo
        graph.addVertex(current.getValue());
        Edge<T> smaller;

        while(!queue.isEmpty()){
            //Remove o vértice atual da fila
            current = queue.poll();
            //Marca o vértice atual como visitada
            marked.add(current);
            //Recebe as arestas do vértice atual
            ArrayList<Edge> edges = current.getDestinations();

            if(edges.size() > 0){
                //Seleciona a primeira aresta como a menor
                smaller = edges.get(0);
                for(int i = 1; i < edges.size(); i++){
                    //Compara se a distância atual é maior do que a distância corrente segundo o index
                    if(!marked.contains(edges.get(i).getDestination()) && smaller.compareTo(edges.get(i)) > 0){
                        smaller = edges.get(i);
                    }
                }    

                if(!marked.contains(smaller.getDestination())){
                    //Adiciona o vértice mais próxima do vértice atual na fila   
                    queue.add(smaller.getDestination());
                    //Adiciona a cidade vizinha do vértice atual como um novo vértice no grafo
                    graph.addVertex(smaller.getDestination().getValue());
                    //Adicionar uma nova aresta
                    graph.addEdge(current.getValue(), smaller.getDestination().getValue(), smaller.getWeight());
                    System.out.println("\t" + current.getValue() + " ---> " + smaller.getDestination().getValue() + " Peso=" + smaller.getWeight());
                }
            }
        }
        System.out.println("\n\tSoma total dos pesos da aresta: " + graph.edgesSum());
        return graph;
    }

    private Double edgesSum(){
        Double total = 0.0;
        //Lista de arestas
        ArrayList<Edge> edgeList = new ArrayList<>();

        for(Vertex vertex : this.vertices){
            //Recebe as arestas do vértice atual
            ArrayList<Edge> edges = vertex.getDestinations();
            for(Edge edge : edges){
                //Verifica se a aresta já consta na lista, caso contrário a mesma é adicionada
                if(!edgeList.contains(edge)){
                    edgeList.add(edge);
                }
            }
        }
        //Soma dos pesos das arestas
        for(Edge edge : edgeList){
            total += edge.getWeight();
        }
        return total;
    }
}
