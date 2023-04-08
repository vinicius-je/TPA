package entities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private Comparator<T> comp;

    public BinaryTree(Comparator<T> comp){
        this.root = null;
        this.comp = comp;
    }

    private Node<T> insert(Node<T> current, Node<T> newNode){
        //caso o nó atual for nulo, o novo nó será retornado e inserido
        if(current == null){
            return newNode;
        }else if(comp.compare(current.getValue(), newNode.getValue()) < 0){
            //segue recursivamente para a sub árvore da direita
            current.setRight(insert(current.getRight(), newNode));
        }else{
            //segue recursivamente para a sub árvore da esquerda
            //caso o nó a ser inserido tiver o mesmo valor do nó atual, esse novo nó ficará a esquerda do atual
            current.setLeft(insert(current.getLeft(), newNode));
        }
        //retorna o nó atual para sua devida posição conforme o retorno recursivo
        return current;
    }

    public void insert(T value){
        Node<T> newNode = new Node<T>(value);
        root = insert(root, newNode);
    }

    private void remove(Node<T> current, Node<T> target){
        Node<T> father = null;

        while(current != null && comp.compare(current.getValue(), target.getValue()) != 0){
            father = current;
            if(comp.compare(current.getValue(), target.getValue()) > 0){
                current = current.getLeft();
            }else{
                current = current.getRight();
            }
        }

        if (current == null){
            System.out.println("Element not found!");
            return;
        }

        //sheet
        if(current.getLeft() == null && current.getRight()== null){
            if(father != null){
                if(comp.compare(current.getValue(), father.getValue()) < 0){
                    father.setLeft(null);
                }else{
                    father.setRight(null);
                }
            }else{
                root.setValue(null);
            }
        }else if(current.getLeft() == null || current.getRight()== null){ //one child
            Node<T> child = null;
            if(current.getLeft() != null){
                child = current.getLeft();
            }else{
                child = current.getRight();
            }

            if(father != null){
                if(comp.compare(current.getValue(), father.getValue()) < 0){
                    father.setLeft(child);
                }else{
                    father.setRight(child);
                }
            }else{
                root = child;
            }
        }else{ //two children
            Node<T>minFather = current;
            //smallest node at right side of the tree
            Node<T> smallestNode = this.smallest(current.getRight());
            current.setValue(smallestNode.getValue());

            if(comp.compare(minFather.getLeft().getValue(), smallestNode.getValue()) == 0){
                minFather.setLeft(smallestNode.getRight());
            }else{
                minFather.setRight(smallestNode.getRight());
            }
        }
        System.out.println("Element removed: " + current.getValue());
    }

    public void remove(T value){
        Node<T> target = new Node<T>(value);
        remove(root, target);
    }

    private void searchElement(Node<T> target){
        Node<T> current = root;
        int count = 0;
        //loop para varrer a árvore.
        while(current != null){
            //elemento encontrado.
            if(comp.compare(current.getValue(), target.getValue()) == 0){
                System.out.println(current.getValue());
                System.out.println("Number of elements traversed: " + count);
                return;
            }else if(comp.compare(current.getValue(), target.getValue()) > 0){
                //se o valor alvo for menor que o atual, segue pela esquerda do nó atual.
                current = current.getLeft();
            }else{
                //se não, segue pela direita do nó atual.
                current = current.getRight();
            }
            count++;
        }
        if(current == null){
            System.out.println("Element not found in tree");
            System.out.println("Number of elements traversed: " + count);
        }
    }

    public void search(T value){
        Node<T> target = new Node<T>(value);
        searchElement(target);
    }

    //método recursivo para imprimir a árvore em ordem,
    //primeiramente acessa os elementos da sub árvore à esquerda,
    //então segue acessando os elementos da sub árvore à direita,
    //o método varre os nós da direita e esquerda de cada nó independente da sub árvore.
    private void displayInOrder(Node<T> current){
        if(current != null){
            displayInOrder(current.getLeft());
            System.out.println(current.getValue());
            displayInOrder(current.getRight());
        }
    }

    public void displayInOrder(){
        displayInOrder(this.root);
    }

    //método para imprimir árvore em nível.
    public void displayByLevel(){
        //lista encadeada para armazenar os nós da árvore por nível, inicia-se com a raiz.
        LinkedList<Node<T>> students = new LinkedList<>();
        students.add(root);

        //lógica: a lista armazena o nó que deve ser impresso e então o remove
        //em seguida verifica se existe um nó a esquerda ou a direita do nó atual
        //conforme a presença de nós periféricos eles serão adicionados na lista
        //e o processo se repete até a lista estar vázia.
    
        while(!students.isEmpty()){
            Node<T> current = students.getFirst();
            System.out.println(current.getValue());
            students.removeFirst();

            if(current.getLeft() != null){
                students.addLast(current.getLeft());
            }
            if(current.getRight() != null){
                students.addLast(current.getRight());
            }
        }
    }

    //método recursivo para retorna a altura da árvore.
    private int height(Node<T> current){
        //caso o nó atual ou seus filhos forem nulo, será retornado zero.
        if(current == null){
            return 0;
        }else if (current.getLeft() == null && current.getRight() == null) {
            return 0;
        }
        //a chamada do método se dá de forma recursiva para a sub árvore à direita e esquerda
        //com o objetivo de identificar qual é a maior sub árvore.
        int left = height(current.getLeft());
        int right = height(current.getRight());
        //a altura da árvore se dá pela soma da maior sub árvore com a raiz(1 unidade).
        return Math.max(left, right) + 1;
    }

    public int height(){
        return height(root);
    }

    //método recursivo para econtrar a quantidade de elementos na árvore
    private int numberOfElements(Node<T> current){
        if(current == null){
            return 0;
        }
        //os nós na sub árvore esquerda e direita serão contabilizados de forma recursiva 
        //ao final será somado a quantidade de nós dessas sub árvores com a raiz.
        int left = numberOfElements(current.getLeft());
        int right = numberOfElements(current.getRight());
        return left + right + 1;
    }

    public int numberOfElements(){
        return numberOfElements(root);
    }

    //método para encontrar o menor nó na árvore
    private Node<T> smallest(Node<T> current){
        //loop para varrer a sub árvore da esquerda até econtrar o último nó que será o menor
        while(current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public Node<T> smallest(){
        return smallest(root);
    }

    //método para encontrar o maior nó na árvore
    private Node<T> biggest(Node<T> current){
        //loop para varrer a sub árvore da direita até econtrar o último nó que será o maior
        while(current.getRight() != null)
            current = current.getRight();
        return current;
    }

    public Node<T> biggest(){
        return biggest(root);
    }

    //método para exibir às estatísticas da árvore
    public void statistic(){
        System.out.println("Number of elements in tree: " + this.numberOfElements());
        System.out.println("Height of the tree: " + this.height());
        System.out.println("Biggest elements in tree: " + this.biggest().getValue());
        System.out.println("Smallest elements in tree: " + this.smallest().getValue());
    }

    //método para escrever no arquivo de saída de forma ordenada
    private void writeFileInOrder(Node<T> current, BufferedWriter bw) throws IOException {
        if(current != null){
            writeFileInOrder(current.getLeft(), bw);
            Student std = (Student) current.getValue();
            bw.write(std.getId() + ";" + std.getName() + ";" + std.getTestScore());
            bw.newLine();
            writeFileInOrder(current.getRight(), bw);
        }
    }

    public void writeFileInOrder(BufferedWriter bw) throws IOException {
        writeFileInOrder(root, bw);
    }
}
