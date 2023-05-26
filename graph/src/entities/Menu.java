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
        String asciiText = " \n\n\t██████╗ ██████╗  █████╗ ███████╗ ██████╗ ███████╗\n"
                         + "\t██╔════╝ ██╔══██╗██╔══██╗██╔════╝██╔═══██╗██╔════╝\n"
                         + "\t██║  ███╗██████╔╝███████║█████╗  ██║   ██║███████╗\n"
                         + "\t██║   ██║██╔══██╗██╔══██║██╔══╝  ██║   ██║╚════██║\n"
                         + "\t╚██████╔╝██║  ██║██║  ██║██║     ╚██████╔╝███████║\n"
                         + " \t╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝      ╚═════╝ ╚══════╝";

        System.out.println(asciiText);
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
        boolean loop = true;
  
        while(loop){
            try {
                Integer id = Integer.parseInt(con.readLine("\n\tCodigo da cidade: "));
                Vertex vertex = graph.getVertex(new City(id));

                //conversão do object para cidade, somente para exibir o nome
                City city = (City) vertex.getValue();
                //recupera os destinos a partir da cidade selecionada
                List<Edge> edges = vertex.getDestinations();

                System.out.println("\tCidades vizinhas de: " + city.getName());
                System.out.println("\n\tCódigo\t\t | Cidade\t\t| Distância");
                for(Edge edge : edges){
                    System.out.println("\t" + edge.getDestination() + " weight=" + edge.getWeight());
                }

                loop = !loop;
            } catch (Exception e) {
                System.out.println("\tCidade não econtrada, tente outra!");
            }
        }
    }

    public void allPathPerCity(){
        boolean loop = true;

        while(loop){
            try {
                Integer id = Integer.parseInt(con.readLine("\n\tCodigo da cidade: "));
                graph.breadthFirstSearch(new City(id));
            } catch (Exception e) {
                System.out.println("\tCidade não econtrada, tente outra!");
            }
        }
    }

}
