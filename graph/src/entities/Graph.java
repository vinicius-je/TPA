package entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        //O vertice atual é adicionado na lista de marcados
        marked.add(current);

        while (queue.size() > 0){
            //Remoção do vertice da vez da fila
            current = queue.poll();
            
            System.out.println("\t" + current.getValue());
            //Seleciona os vertices adjacente
            ArrayList<Edge> destinations = current.getDestinations();
            
            //Vertex nextVertex;
            for(int i = 0; i < destinations.size(); i++){
                Vertex nextVertex = destinations.get(i).getDestination();
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

    public Graph<T> prim(){
        Graph<T> graph = new Graph<>();
        ArrayList<Vertex<T>> marked = new ArrayList<>();
        //Seleciona o primeior nó do grafo
        Vertex<T> current = vertices.get(0);
        //Adiciona a primeira cidade como um novo vértice no grafo
        graph.addVertex(current.getValue());
        //Marca o vértice atual como visitada
        marked.add(current);
        //usar o marked para loop
        while(marked.size() < vertices.size()){
            //Define uma aresta como referência para encontrar a menor aresta
            Edge<T> smaller = new Edge<T>(Double.MAX_VALUE);
            //Loop para percorrer os vértices marcados
            for(int i = 0; i < marked.size(); i++){
                //Recebe as arestas do vértice
                ArrayList<Edge> edges = marked.get(i).getDestinations();
                //Verifica se existe arestas no vértice atual
                if(edges.size() > 0){
                    for(int j = 0; j < edges.size(); j++){
                        if(!marked.contains(edges.get(j).getDestination()) && smaller.getWeight() > edges.get(j).getWeight()){
                            smaller = edges.get(j);
                            current = marked.get(i);
                        }
                    }   
                }
            }

            if(!marked.contains(smaller.getDestination())){
                //Marca o vértice atual como visitada
                marked.add(smaller.getDestination());
                //Adiciona a cidade vizinha do vértice atual como um novo vértice no grafo
                graph.addVertex(smaller.getDestination().getValue());
                //Adicionar uma nova aresta
                graph.addEdge(current.getValue(), smaller.getDestination().getValue(), smaller.getWeight());
                graph.addEdge(smaller.getDestination().getValue(), current.getValue(), smaller.getWeight());
                System.out.println("\t" + current.getValue() + " <---> " + smaller.getDestination().getValue() + " Peso=" + smaller.getWeight());
            }
        }
        System.out.println("\n\tSoma total dos pesos da aresta: " + String.format("%.2f", graph.edgesSum()));
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
        return total / 2;
    }

    public void dijkstra(T origin, T destination){
        //Lista de distâncias
        List<Double> distances = new ArrayList<>();
        //Lista de predecessores
        List<Integer> previous = new ArrayList<>();
        //Lista de vértices visitados
        List<Vertex> marked = new LinkedList<>();

        //Preenche a lista de distâncias com o maior valor do inteiro
        for(int i = 0; i <= vertices.size() - 1; i++){
            distances.add(Double.MAX_VALUE);
            previous.add(-1);
        }

        //Recupera o vertice de origem
        Vertex current = getVertex(origin);
        Integer originIndex = vertices.indexOf(current);
        distances.set(originIndex, 0.0);

        while(marked.size() < vertices.size() - 1){
            marked.add(current);

            ArrayList<Edge> listOfEdges = current.getDestinations();
            for (Edge edge : listOfEdges) {
                int edgeIndex = vertices.indexOf(edge.getDestination());
                //Soma das arestas
                Double sumOfEdges = distances.get(originIndex) + edge.getWeight();
                //Compara se o valor da aresta é menor do que o valor estimado
                if(!marked.contains(edge.getDestination()) && distances.get(edgeIndex) > sumOfEdges){
                    //Atualiza os valores na lista de distância e de predecessores
                    distances.set(edgeIndex, sumOfEdges);
                    previous.set(edgeIndex, originIndex);
                }
            }
            //Selecionar a menor aresta
            Double shorterdistances = Double.MAX_VALUE;
            for(int i = 0; i < distances.size(); i++){
                //Verifica se o vétice já foi visitado
                if(!marked.contains(vertices.get(i))){
                    if(shorterdistances >= distances.get(i)){
                        shorterdistances = distances.get(i);
                        originIndex = i;
                    }
                }
            }
            //Seleciona o vértice de menor valor de aresta
            current = vertices.get(distances.indexOf(shorterdistances));
        }

        for(int i = 0; i < vertices.size(); i++){
            City city = ((City)vertices.get(i).getValue());
            System.out.println("\n\tindex: " + i + " | código-cidade: (" + city.getId() + "," + city.getName() + 
                ") | distância: " + distances.get(i) + " | predecessor: " + previous.get(i));
        }
    }
}
