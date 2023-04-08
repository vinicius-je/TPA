import controller.MenuController;
import entities.BinaryTree;
import entities.CompareById;
import entities.CompareByName;
import entities.Student;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BinaryTree<Student> treeById = new BinaryTree<Student>(new CompareById());
        BinaryTree<Student> treeByName = new BinaryTree<Student>(new CompareByName());
        //caminho do arquivo
        String path = "src\\students.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //Leitura da quantidade de elementos da lista
            String line = br.readLine();
            //Leitura do primeiro aluno da lista
            line = br.readLine();
            while (line != null){
                String data[] = line.split(";");
                Student student = new Student(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
                treeById.insert(student);
                treeByName.insert(student);
                line = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }

        // Menu interativo
        Console con = System.console();
        MenuController menu = new MenuController(treeById, treeByName, con);
        int option = 0;

        do{
            menu.displayTreeOptions();
            int tree = Integer.parseInt(con.readLine("Tree Type: "));
            menu.displayOptions();
            option = Integer.parseInt(con.readLine("Option: "));
            MenuController.limparTela();
            menu.optionsSelected(option, tree);
        }while(option != 7);

    }
}