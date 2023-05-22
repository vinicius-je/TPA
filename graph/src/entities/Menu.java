package entities;

import java.io.Console;
import java.util.List;

public class Menu {
    Graph graph;
    Console con;

    public Menu(Graph graph, Console con){
        this.graph = graph;
        this.con = con;
    }

    public void display(){
        System.out.println("\n\t[1] >> Cidades Vizinhas\n\t[2] >> Todos os caminhos por cidade\n\t[3] >> Sair");
    }

    public void readOption(Integer option){
        // if (option == 1){
        //     addNewVertex();
        // }else if (option == 2){
        //     addNewEdge();
        // }else if (option == 3){
        //     graph.breadthFirstSearch();
        // }else if (option == 4){
        //     System.out.println("OUT!");
        // }else{
        //     System.out.println("Opção Inválida!");
        // }

        if(option == 1){
            neighboringCities();
        }else if(option == 2){

        }else if(option == 3){

        }else{
            System.out.println("Opção Inválida!");
        }
    }

    public void neighboringCities(){
        Integer id = Integer.parseInt(con.readLine("Codigo da cidade: "));
        Vertex vertex = graph.getVertex(new City(id, null));

        List<Edge> edges = vertex.getDestinations();

        for(Edge edge : edges){
            System.out.println(edge.getDestination());
        }
    }

    public void addNewVertex(){
        String city = con.readLine("\n\tNome da cidade: ");
        Vertex vertex =  graph.addVertex(city);
        System.out.println("\n\tVertice: " + vertex + " adicionado ao grafo!");
    }

    public void addNewEdge(){
        String origin = con.readLine("\n\tOrigem: ");
        String destionation = con.readLine("\n\tDestino: ");
        Double weight = Double.parseDouble(con.readLine("\n\tPeso: "));
        graph.addEdge(origin, destionation, weight);
        System.out.println("\n\tAresta adicionado com sucesso!");
    }

}
