package entities;

public class Node<T> {
    private T value;
    private Node<T> right, left;
    private int height;

    public Node(T value){
        this.value = value;
        this.right = left = null;
        this.height = 0;
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

    public int getHeight(){
        return height;
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

    public void setHeight(int height){
        this.height = height;
    }

    //realiza o cálculo da altura da árvore
    private int calculateHeight(Node<T> root){
        if(root == null) {
            return -1;
        }
        
        //right height
        int rh = calculateHeight(root.getRight());
        //left height
        int lh = calculateHeight(root.getLeft());
        //verifica qual subárvore é maior e realiza a soma com a aresta da raiz
        if(rh > lh){
            return rh + 1;
        }else{
            return lh + 1;
        }
    }

    public int calculateHeight(){
        return calculateHeight(this);
    }

    //realiza o cálculo para o fator de balanceamento da árvore avl
    public int balancingFactor(){
        if(this.getRight() != null && this.getLeft() != null){
            return this.getRight().getHeight() - this.getLeft().getHeight();
        }else if(this.getRight() != null){
            return this.getRight().getHeight() + 1;
        }else if(this.getLeft() != null){
            return -1 - this.getLeft().getHeight();
        }else{
            return 0;
        }
    }
}
