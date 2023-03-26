package entities;

import java.util.Comparator;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private Comparator<T> comp;

    public BinaryTree(Comparator<T> comp){
        this.root = null;
        this.comp = comp;
    }

    private Node<T> insert(Node<T> current, Node<T> newNode){
        if(current == null){
            return newNode;
        }else if(comp.compare(current.getValue(), newNode.getValue()) < 0){
            current.setRight(insert(current.getRight(), newNode));
        }else{
            current.setLeft(insert(current.getLeft(), newNode));
        }
        return current;
    }

    public void insert(T value){
        Node<T> newNode = new Node<T>(value);
        root = insert(root, newNode);
    }

    //delete


    public void searchById(Integer id){
        Node<T> searchNode = new Node<T>((T) new Student(id, null, null));
        Node<T> current = root;
        int elements = 0;

        while(current != null){
            if(comp.compare(current.getValue(), searchNode.getValue()) == 0){
                System.out.println(current.getValue());
                System.out.println("Number of elements traversed: " + elements);
                return;
            }else if(comp.compare(current.getValue(), searchNode.getValue()) > 0){
                current = current.getLeft();
            }else{
                current = current.getRight();
            }
            elements++;
        }
        if(current == null){
            System.out.println("Element not found in tree");
            System.out.println("Number of elements traversed: " + elements);
        }
    }

    private void displayInOrder(Node<T> current){
        if(current != null){
            displayInOrder(current.getLeft());
            System.out.println(current.getValue() + " - ");
            displayInOrder(current.getRight());
        }
    }

    public void displayInOrder(){
        displayInOrder(this.root);
    }

    private int height(Node<T> current){
        if(current == null){
            return 0;
        }else if (current.getLeft() == null && current.getRight() == null) {
            return 0;
        }
        int left = height(current.getLeft());
        int right = height(current.getRight());
        return Math.max(left, right) + 1;
    }

    public int height(){
        return height(root);
    }

    private int numberOfElements(Node<T> current){
        if(current == null){
            return 0;
        }
        int left = numberOfElements(current.getLeft());
        int right = numberOfElements(current.getRight());
        return left + right + 1;
    }

    public int numberOfElements(){
        return numberOfElements(root);
    }

    public Node<T> smallest(){
        Node<T> aux = root;
        while(aux.getLeft() != null)
            aux = aux.getLeft();
        return aux;
    }

    public Node<T> biggest(){
        Node<T> aux = root;
        while(aux.getRight() != null)
            aux = aux.getRight();
        return aux;
    }
}
