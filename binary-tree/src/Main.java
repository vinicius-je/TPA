import entities.BinaryTree;
import entities.CompareById;
import entities.CompareByName;
import entities.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Student> treeById = new BinaryTree<Student>(new CompareById());
        BinaryTree<Student> treeByName = new BinaryTree<Student>(new CompareByName());

        String path = "src\\teste.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //Read the file index
            String line = br.readLine();
            //Read the first student data
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

//        treeById.displayInOrder();
//        System.out.println("Height of the tree: " + treeById.height());
//        System.out.println("Number of elements in tree: " + treeById.numberOfElements());
//        System.out.println("Smallest elements in tree: " + treeById.smallest().getValue());
//        System.out.println("Biggest elements in tree: " + treeById.biggest().getValue());
        treeById.searchById(8);
        treeById.searchById(29);
        treeByName.searchByName("Carlos Santos");
        treeByName.searchByName("Vinicius");
    }
}