package controller;

import entities.BinaryTree;
import entities.CompareById;
import entities.CompareByName;
import entities.Student;

import java.io.Console;

public class MenuController {

    BinaryTree<Student> treeById = new BinaryTree<Student>(new CompareById());
    BinaryTree<Student> treeByName = new BinaryTree<Student>(new CompareByName());
    Console con;

    public MenuController(Console con){
        this.con = con;
        this.treeById = new BinaryTree<Student>(new CompareById());
        this.treeByName = new BinaryTree<Student>(new CompareByName());
    }

    public void displayOptions() {
        System.out.println("\n\tSeleciona uma operacao:" +
                "\n\t[1] - Inserir\n\t[2] - Buscar\n\t[3] - Deletar" +
                "\n\t[4] - Estatisticas\n\t[5] - Exibir em ordem" + 
                "\n\t[6] - Exibir em nivel\n\t[7] - sair\n");

    }

    public void displayTreeOptions(){
        System.out.println("\n\tSeleciona o modo de organização da arvore:" +
                "\n\t[1] - Arvore por nome de aluno\n\t[2] - Arvore por matricula de aluno\n");
    }

    public void optionsSelected(int option, int tree){
        if(tree > 2 || tree < 1){
            System.out.println("Tipo de arvore invalido");
            return;
        }

        if(option == 1){
            insertStudent(tree);
        }else if(option == 2){
            searchStudent(tree);
        }else if(option == 3){
            removeStudent(tree);
        }else if(option == 4){
            treeStatistics(tree);
        }else if(option == 5){
            displayOrder(tree);
        }else if(option == 6){
            displayLevel(tree);
        }else{
            System.out.println("Opcao invalida");
        }
    }

    public void insertStudent(int tree){
        Integer id = Integer.parseInt(con.readLine("Insira a matricula do aluno: "));
        String name = con.readLine("Insira o nome do aluno: ");
        Double score = Double.parseDouble(con.readLine("Insira a nota do aluno: "));

        Student std = new Student(id, name, score);

        if(tree == 1)
            treeByName.insert(std);
        else
            treeById.insert(std);
    }

    public void searchStudent(int tree){
        if(tree == 1){
            String name = con.readLine("Insira o nome do aluno a ser buscado: ");
            treeByName.searchByName(name);
        }else{
            Integer id = Integer.parseInt(con.readLine("Insira a matricula do aluno a ser buscado: "));
            treeById.searchById(id);
        }
    }

    public void removeStudent(int tree){
        if(tree == 1){
            String name = con.readLine("Insira o nome do aluno a ser removido: ");
            treeByName.removeByName(name);
        }else{
            Integer id = Integer.parseInt(con.readLine("Insira a matricula do aluno a ser removido: "));
            treeById.removeById(id);
        }
    }

    public void treeStatistics(int tree){
        if(tree == 1)
            treeByName.statistic();
        else
            treeById.statistic();
    }

    public void displayOrder(int tree){
        if(tree == 1)
            treeByName.displayInOrder();
        else
            treeById.displayInOrder();
    }

    public void displayLevel(int tree){
        if(tree == 1)
            treeByName.displayByLevel();
        else
            treeById.displayByLevel();
    }

//    public void limparTela(){
//        try {
//            if (System.getProperty("os.name").contains("Windows"))
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            else
//                Runtime.getRuntime().exec("clear");
//        } catch (IOException e) {
//            System.out.println("Error ao limpar a tela");
//        } catch (InterruptedException e) {
//            System.out.println("Error ao limpar a tela");
//        }
//    }
}
