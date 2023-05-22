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
        if(option == 1){
            neighboringCities();
        }else if(option == 2){
            allPathPerCity();
        }else if(option == 3){
            System.out.println("\n\tOUT!");
        }else{
            System.out.println("\n\tOpção Inválida!");
        }
    }

    public void neighboringCities(){
        Integer id = Integer.parseInt(con.readLine("\n\tCodigo da cidade: "));
        Vertex vertex = graph.getVertex(new City(id));

        List<Edge> edges = vertex.getDestinations();

        for(Edge edge : edges){
            System.out.println("\t" + edge.getDestination() + ", weight=" + edge.getWeight());
        }
    }

    public void allPathPerCity(){
        Integer id = Integer.parseInt(con.readLine("\n\tCodigo da cidade: "));
        graph.breadthFirstSearch(new City(id));
    }

}
