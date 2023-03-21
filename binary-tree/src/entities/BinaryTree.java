package entities;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private Object comp;

    public BinaryTree(Object comp){
        this.root = null;
        this.comp = comp;
    }
}
