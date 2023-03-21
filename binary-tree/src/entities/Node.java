package entities;

public class Node<T> {
    private T value;
    private Node<T> right, left;

    public Node(T value){
        this.value = value;
        this.right = left = null;
    }
    //Getters
    public T getValue() {
        return value;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getLeft() {
        return left;
    }
    //Setters
    public void setValue(T value) {
        this.value = value;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }
}
