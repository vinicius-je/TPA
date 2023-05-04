package entities;

import java.io.*;

public class MenuController<T> {
    private BinaryTree<Student> treeById;
    private BinaryTree<Student> treeByName;
    private AvlTree<Student> avlById;
    private AvlTree<Student> avlByName;
    public Console con;

    public MenuController(BinaryTree<Student> treeById, BinaryTree<Student> treeByName,
                            AvlTree<Student> avlById, AvlTree<Student> avlByName, Console con){
        this.treeById = treeById;
        this.treeByName = treeByName;
        this.avlById = avlById;
        this.avlByName = avlByName;
        this.con = con;
    }

    public void displayOptions() {
        System.out.println("\n\tSeleciona uma operacao:" +
                "\n\t[1] - Inserir\n\t[2] - Buscar\n\t[3] - Deletar" +
                "\n\t[4] - Estatisticas\n\t[5] - Exibir em ordem" + 
                "\n\t[6] - Exibir em nivel\n\t[7] - sair\n");
    }

    public void displayTreeOptions(){

        System.out.println("\n\n\n" + "\t██████╗ ██╗   ██╗██╗         ████████╗██████╗ ███████╗███████╗");
        System.out.println("\t██╔══██╗██║   ██║██║         ╚══██╔══╝██╔══██╗██╔════╝██╔════╝");
        System.out.println("\t███████║██║   ██║██║            ██║   ██████╔╝█████╗  █████╗  ");
        System.out.println("\t██╔══██║╚██╗ ██╔╝██║            ██║   ██╔══██╗██╔══╝  ██╔══╝  ");
        System.out.println("\t██║  ██║ ╚████╔╝ ███████╗       ██║   ██║  ██║███████╗███████╗");
        System.out.println("\t╚═╝  ╚═╝  ╚═══╝  ╚══════╝       ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝\n\n");
    
        System.out.println("\n\tSeleciona o modo de organização da arvore:" +
                "\n\t[1] - Ordenacao por nome de aluno\n\t[2] - Ordenacao por matricula de aluno\n");
    }

    public void optionsSelected(int option, int tree){
        if(tree > 2 || tree < 1){
            System.out.println("\tTipo de arvore invalido");
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
            System.out.println("\tOpcao invalida");
        }
    }

    public void insertStudent(){
        boolean condition = false;

        while(!condition){
            try {
                Integer id = Integer.parseInt(con.readLine("\tMatricula do aluno: "));
                String name = con.readLine("\tNome do aluno: ");
                Double score = Double.parseDouble(con.readLine("\tNota do aluno: "));
                
                condition = !condition;

                Student std = new Student(id, name, score);

                treeByName.insert(std);
                treeById.insert(std);

                avlByName.insert(std);
                avlById.insert(std);

                System.out.println("\tAluno inserido: " + std);
                System.out.println("\t___________________________________________________________________________________\n");
            } catch (Exception e) {
                System.out.println("\tDados invalidos!");
            }
        }
    }

    public void searchStudent(int tree){
        if(tree == 1){
            String name = con.readLine("\tInsira o nome do aluno a ser buscado: ");
            System.out.println("\t___________________________________________________________________________________\n");
            System.out.println("\tArvore binaria");
            treeByName.search(new Student(name));
            System.out.println("\t___________________________________________________________________________________\n");
            System.out.println("\tArvore AVL");
            avlByName.search(new Student(name));
            System.out.println("\t___________________________________________________________________________________\n");
        }else{
            Integer id = Integer.parseInt(con.readLine("\tInsira a matricula do aluno a ser buscado: "));
            System.out.println("\t___________________________________________________________________________________\n");
            System.out.println("\tArvore binaria");
            treeById.search(new Student(id));
            System.out.println("\t___________________________________________________________________________________\n");
            System.out.println("\tArvore AVL");
            avlById.search(new Student(id));
            System.out.println("\t___________________________________________________________________________________\n");
        }
    }

    public void removeStudent(int tree){
        if(tree == 1){
            String name = con.readLine("\tInsira o nome do aluno a ser removido: ");
            Student std = treeByName.remove(new Student(name));
            if(std != null){
                System.out.println("\tAluno removido: " + std);
                System.out.println("\t___________________________________________________________________________________\n");
                treeById.remove(std);
                avlById.remove(std);
            }
        }else{
            Integer id = Integer.parseInt(con.readLine("\tInsira a matricula do aluno a ser removido: "));
            Student std = treeById.remove(new Student(id));
            if(std != null){
                System.out.println("\tAluno removido: " + std);
                System.out.println("\t___________________________________________________________________________________\n");
                treeByName.remove(std);
                avlByName.remove(std);
            }
        }
    }

    public void treeStatistics(int tree){
        BinaryTree<Student> binaryAux;
        AvlTree<Student> avlAux;

        if(tree == 1){
            binaryAux = treeByName;
            avlAux = avlByName;
        }else{
            binaryAux = treeById;
            avlAux = avlById;
        }

        System.out.println("\t___________________________________________________________________________________\n");
        System.out.println("\tEstatisticas Arvore Binaria");
        binaryAux.statistic();
        System.out.println("\t___________________________________________________________________________________\n");
        System.out.println("\tEstatisticas Arvore AVL");
        avlAux.statistic();
        System.out.println("\t___________________________________________________________________________________\n");
    }

    public void displayOrder(int tree){
        BinaryTree<Student> binaryAux;
        AvlTree<Student> avlAux;

        if(tree == 1){
            binaryAux = treeByName;
            avlAux = avlByName;
        }else{
            binaryAux = treeById;
            avlAux = avlById;
        }

        System.out.println("\t___________________________________________________________________________________\n");
        System.out.println("\tArvore Binaria em ordem");
        binaryAux.displayInOrder();
        System.out.println("\t___________________________________________________________________________________\n");
        System.out.println("\tArvore AVL em ordem");
        avlAux.displayInOrder();
        System.out.println("\t___________________________________________________________________________________\n");
    }

    public void displayLevel(int tree){
        BinaryTree<Student> binaryAux;
        AvlTree<Student> avlAux;

        if(tree == 1){
            binaryAux = treeByName;
            avlAux = avlByName;
        }else{
            binaryAux = treeById;
            avlAux = avlById;
        }

        System.out.println("\t___________________________________________________________________________________\n");
        System.out.println("\tArvore Binaria em level");
        binaryAux.displayByLevel();
        System.out.println("\t___________________________________________________________________________________\n");
        System.out.println("\tArvore AVL em level");
        avlAux.displayByLevel();
        System.out.println("\t___________________________________________________________________________________\n");
    }

    public void generateFile(){
        File file = new File("src\\arquivoSaida.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            treeById.writeFileInOrder(bw);
        } catch (IOException e){
            System.out.println("Erro ao gerar arquivo de saída: " + e.getMessage());
        }

        System.out.println("\tArquivo 'arquivoSaida.txt' gerado com sucesso!");
    }

   public static void cleanConsole() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }
}
