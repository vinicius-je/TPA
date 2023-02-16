package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.LinkedList;
import entities.Student;

public class App {
    public static void main(String[] args) throws Exception {
        String file = "src\\file\\students.txt";
        LinkedList<Student> list = new LinkedList<Student>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split(",");
                Student student = new Student(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2]));
                list.add(student);
                line = br.readLine();
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(list);
    }
}
