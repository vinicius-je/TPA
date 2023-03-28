package entities;

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

    public void removeById(Integer id){
        Node<T> target = new Node<T>((T) new Student(id));
        remove(root, target);
    }

    public void removeByName(String name){
        Node<T> target = new Node<T>((T) new Student(name));
        remove(root, target);
    }

    private Node<T> remove(Node<T> current, Node<T> target){
        if(current == null){
            return current;
        }

        if(comp.compare(current.getValue(), target.getValue()) < 0){
            current.setRight(remove(current.getRight(), target));
        } else if (comp.compare(current.getValue(), target.getValue()) > 0){
            current.setLeft(remove(current.getLeft(), target));
        } else {
            System.out.println("Element removed: " + current.getValue());
            if (current.getLeft() == null){
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                Node<T> aux = this.smallest(current.getRight());
                current.setValue(aux.getValue());
                current.setRight(remove(current.getRight(), aux));
            }
        }
        return current;
    }
    public void searchElement(Node<T> target){
        Node<T> current = root;
        int elements = 0;

        while(current != null){
            if(comp.compare(current.getValue(), target.getValue()) == 0){
                System.out.println(current.getValue());
                System.out.println("Number of elements traversed: " + elements);
                return;
            }else if(comp.compare(current.getValue(), target.getValue()) > 0){
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

    public void searchById(Integer id){
        Node<T> target = new Node<T>((T) new Student(id));
        searchElement(target);
    }

    public void searchByName(String name){
        Node<T> target = new Node<T>((T) new Student(name));
        searchElement(target);
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

//    public void displayInLevel(){
//        Node<T> current = root;
//        while (current != null){
//            System.out.println(current.getValue());
//            if(current.getLeft() != null){
//                System.out.println(current.getLeft().getValue());
//            }
//            if(current.getRight() != null){
//                System.out.println(current.getRight().getValue());
//            }
//
//            if(current.getLeft().getLeft() != null) {
//                current = current.getLeft().getLeft();
//            }else if (current.getLeft().getRight() != null){
//                current = current.getLeft().getRight();
//            }else if(current.getRight().getLeft() != null){
//                current = current.getRight().getLeft();
//            }else{
//                current = current.getRight().getRight();
//            }
//        }
//    }

    public void displayByLevel(){
        LinkedList<Node<T>> students = new LinkedList<>();
        students.add(root);

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

    private Node<T> smallest(Node<T> current){
        while(current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public Node<T> smallest(){
        return smallest(root);
    }

    private Node<T> biggest(Node<T> current){
        while(current.getRight() != null)
            current = current.getRight();
        return current;
    }

    public Node<T> biggest(){
        return biggest(root);
    }

    public void statistic(){
        System.out.println("Number of elements in tree: " + this.numberOfElements());
        System.out.println("Height of the tree: " + this.height());
        System.out.println("Biggest elements in tree: " + this.biggest().getValue());
        System.out.println("Smallest elements in tree: " + this.smallest().getValue());
    }
}
