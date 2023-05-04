package entities;

import java.util.Comparator;

/***********************************
 * @Autor: Vinícius Estevam
 ***********************************/

public class AvlTree<T extends Comparable<T>> extends BinaryTree<T> {
    
    public AvlTree(Comparator<T> comp) {
        super(comp);
    }

    @Override
    protected Node<T> insert(Node<T> current, Node<T> newNode){
        //adiciona o nó normalmente na árvore
        current = super.insert(current, newNode);
        //calculo do fator de balanceamento do nó atual
        int bf = current.balancingFactor();
        //deve-se balancear a árvore sempre que o FB for > 1 ou < -1
        if(bf > 1){
            //verificação para retoção à esquerda
            if(current.getRight().balancingFactor() > 0){
                current = this.rotateLeft(current);
            }else{
                current = this.rotateRightLeft(current);
            }
        }else if(bf < -1){
            //verificação para retoção à direita
            if(current.getLeft().balancingFactor() < 0){
                current = this.rotateRight(current);
            }else{
                current = this.rotateLeftRight(current);
            }
        }
        return current;
    }

    public Node<T> rotateLeft(Node<T> node){
        Node<T> child = node.getRight();
        node.setRight(child.getLeft());
        child.setLeft(node);
        //altera os valores da altura dos nós
        node.setHeight(node.calculateHeight());
        child.setHeight(child.calculateHeight());
        return child;
    }

    public Node<T> rotateRight(Node<T> node){
        Node<T> child = node.getLeft();
        node.setLeft(child.getRight());
        child.setRight(node);
        //altera os valores da altura dos nós
        node.setHeight(node.calculateHeight());
        child.setHeight(child.calculateHeight());
        return child;
    }

    public Node<T> rotateLeftRight(Node<T> node){
        //rotaciona a esquerda o filho à esquerda do nó
        node.setLeft(rotateLeft(node.getLeft()));
        //rotaciona o nó à direita
        return rotateRight(node);
    }

    public Node<T> rotateRightLeft(Node<T> node){
        //rotaciona a direita o filho à direita do nó
        node.setRight(rotateRight(node.getRight()));
        //rotaciona o nó à esquerda
        return rotateLeft(node);
    }
}
