package entities;

import java.io.Console;

public class Menu {
    Graph graph;
    Console con;

    public Menu(Graph graph, Console con){
        this.graph = graph;
        this.con = con;
    }

    public void display(){
        System.out.println("\n\t[1] >> Adicionar Vertice\n\t[2] >> Adicionar Aresta" +
                "\n\t[3] >> Buscar em Largura\n\t[4] >> Sair");
    }

    public void readOption(Integer option){
        if (option == 1){
            addNewVertex();
        }else if (option == 2){
            addNewEdge();
        }else if (option == 3){
            graph.breadthFirstSearch();
        }else if (option == 4){
            System.out.println("OUT!");
        }else{
            System.out.println("Opção Inválida!");
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
