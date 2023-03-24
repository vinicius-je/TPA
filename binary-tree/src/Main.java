import entities.BinaryTree;
import entities.CompareById;
import entities.CompareByName;
import entities.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Student> tree = new BinaryTree<Student>(new CompareById());

        String path = "src\\teste.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null){
                String data[] = line.split(";");
                Student student = new Student(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
                tree.insert(student);
                line = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }

        tree.displayInOrder();
        System.out.println("Height of the tree: " + tree.height());
        System.out.println("Number of elements in tree: " + tree.numberOfElements());
    }
}