package entities;

import java.io.*;

public class MenuController<T> {

    private BinaryTree<Student> treeById;
    private BinaryTree<Student> treeByName;
    public Console con;

    public MenuController(BinaryTree<Student> treeById, BinaryTree<Student> treeByName, Console con){
        this.treeById = treeById;
        this.treeByName = treeByName;
        this.con = con;
    }

    public void displayOptions() {
        System.out.println("\n\tSeleciona uma operacao:" +
                "\n\t[1] - Inserir\n\t[2] - Buscar\n\t[3] - Deletar" +
                "\n\t[4] - Estatisticas\n\t[5] - Exibir em ordem" + 
                "\n\t[6] - Exibir em nivel\n\t[7] - sair\n");
    }

    public void displayTreeOptions(){
        System.out.println("\n\n\n" + "\t██████╗ ██╗███╗   ██╗ █████╗ ██████╗ ██╗   ██╗    ████████╗██████╗ ███████╗███████╗");
        System.out.println("\t██╔══██╗██║████╗  ██║██╔══██╗██╔══██╗╚██╗ ██╔╝    ╚══██╔══╝██╔══██╗██╔════╝██╔════╝");
        System.out.println("\t██████╔╝██║██╔██╗ ██║███████║██████╔╝ ╚████╔╝        ██║   ██████╔╝█████╗  █████╗  ");
        System.out.println("\t██╔══██╗██║██║╚██╗██║██╔══██║██╔══██╗  ╚██╔╝         ██║   ██╔══██╗██╔══╝  ██╔══╝  ");
        System.out.println("\t██████╔╝██║██║ ╚████║██║  ██║██║  ██║   ██║          ██║   ██║  ██║███████╗███████╗");
        System.out.println("\t╚═════╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝          ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝\n\n");

        System.out.println("\n\tSeleciona o modo de organização da arvore:" +
                "\n\t[1] - Arvore por nome de aluno\n\t[2] - Arvore por matricula de aluno\n");
    }

    public void optionsSelected(int option, int tree){
        if(tree > 2 || tree < 1){
            System.out.println("Tipo de arvore invalido");
            return;
        }

        if(option == 1){
            insertStudent();
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
        }else if(option == 7){
            generateFile();
        }else{
            System.out.println("Opcao invalida");
        }
    }

    public void insertStudent(){
        boolean condition = false;

        while(!condition){
            try {
                Integer id = Integer.parseInt(con.readLine("Insira a matricula do aluno: "));
                String name = con.readLine("Insira o nome do aluno: ");
                Double score = Double.parseDouble(con.readLine("Insira a nota do aluno: "));
                
                condition = !condition;

                Student std = new Student(id, name, score);

                treeByName.insert(std);
                treeById.insert(std);
            } catch (Exception e) {
                System.out.println("Dados invalidos!");
            }
        }
    }

    public void searchStudent(int tree){
        if(tree == 1){
            String name = con.readLine("Insira o nome do aluno a ser buscado: ");
            treeByName.search(new Student(name));
        }else{
            Integer id = Integer.parseInt(con.readLine("Insira a matricula do aluno a ser buscado: "));
            treeById.search(new Student(id));
        }
    }

    public void removeStudent(int tree){
        if(tree == 1){
            String name = con.readLine("Insira o nome do aluno a ser removido: ");
            Student std = treeByName.remove(new Student(name));
            if(std != null){
                System.out.println("Elemento removido: " + std);
                treeById.remove(std);
            }
        }else{
            Integer id = Integer.parseInt(con.readLine("Insira a matricula do aluno a ser removido: "));
            Student std = treeById.remove(new Student(id));
            if(std != null){
                System.out.println("Elemento removido: " + std);
                treeByName.remove(std);
            }
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

    public void generateFile(){
        File file = new File("src\\arquivoSaida.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            treeById.writeFileInOrder(bw);
        } catch (IOException e){
            System.out.println("Erro ao gerar arquivo de saída: " + e.getMessage());
        }

        System.out.println("Arquivo 'arquivoSaida.txt' gerado com sucesso!");
    }

   public static void limparTela() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }
}
