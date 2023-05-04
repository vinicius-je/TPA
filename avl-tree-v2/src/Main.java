import entities.AvlTree;
import entities.BinaryTree;
import entities.CompareById;
import entities.CompareByName;
import entities.MenuController;
import entities.Student;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Comparatos
        Comparator<Student> compByID = new CompareById();
        Comparator<Student> compByName = new CompareByName();
        //Binary Tree
        BinaryTree<Student> treeById = new BinaryTree<Student>(compByID);
        BinaryTree<Student> treeByName = new BinaryTree<Student>(compByName);
        //Avl Tree
        AvlTree<Student> avlById = new AvlTree<>(compByID);
        AvlTree<Student> avlByName = new AvlTree<>(compByName);
        
        //caminho do arquivo
        String path = "src\\files\\entradaOrdenada100.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //leitura da quantidade de elementos da lista
            String line = br.readLine();
            //leitura do primeiro aluno da lista
            line = br.readLine();
            while (line != null){
                String data[] = line.split(";");
                Student student = new Student(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
                //Binary Tree
                treeById.insert(student);
                treeByName.insert(student);
                //Avl Tree
                avlById.insert(student);
                avlByName.insert(student);
                line = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }

        // Menu interativo
        Console con = System.console();
        MenuController menu = new MenuController(treeById, treeByName, avlById, avlByName, con);
        int option = 0;

        do{
            menu.displayTreeOptions();
            int tree = Integer.parseInt(con.readLine("Tipo da arvore: "));
            menu.displayOptions();
            option = Integer.parseInt(con.readLine("Opcao: "));
            MenuController.cleanConsole();
            menu.optionsSelected(option, tree);
        }while(option != 7);

    }
}