import entities.City;
import entities.Graph;
import entities.Menu;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Console con = System.console();

        Graph graph = new Graph<>();
        String path = "./entrada.txt";
    
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            //ler a primeira linha (quantidade de elementos)
            Integer qtd = Integer.parseInt(br.readLine());
            //Leituras dos vértices
            for(int i = 0; i < qtd; i++){
                String cities[] = br.readLine().split(",");
                City city = new City(Integer.parseInt(cities[0]), cities[1]);
                graph.addVertex(city);
            }
            //Leitura das arestas
            for(int i = 1; i <= qtd; i++){
                String edgeList[] = br.readLine().split(",");
                for(int j = 1; j <= qtd; j++){
                    Double weight =  Double.parseDouble(edgeList[j-1]);
                    if(weight != 0.00){
                        graph.addEdge(new City(i), new City(j), weight);
                    }
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Erro de leitura do arquivo!");
        }

        Menu menu = new Menu(graph, con);

        int option;

        do{
            menu.display();
            option = Integer.parseInt(con.readLine("\n\tDigite a opção: "));
            menu.readOption(option);
        }while(option != 3);
    }

}