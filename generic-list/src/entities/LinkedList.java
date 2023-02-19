package entities;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> first, last; 
    private int size;

    public LinkedList(){
        this.first = this.last = null;
        this.size = 0;
    }

    public boolean add(T element){
        Node<T> newNode = new Node<T>(element);
        Node<T> current, previous;
        current = this.first;
        previous = null;

        if(this.first == null){
            this.first = this.last = newNode;
        }else{
            while(current != null && current.getValue().compareTo(newNode.getValue()) < 0){
                previous = current;
                current = current.getNext();
            }
            
            if(previous == null){
                newNode.setNext(current);
                this.first = newNode;
            }
            else if(current == null){
                this.last.setNext(newNode);
                this.last = newNode;
            }
            else{
                newNode.setNext(current);
                previous.setNext(newNode);
            }
            this.size++;
        }   
        return true;
    }

    public boolean remove(T element){
        Node<T> current, previous;
        Node<T> node = new Node<T>(element);

        current = this.first;
        previous = null;

        while(current != null){
            if(current.getValue().equals(node.getValue())){
                if(current == this.first){
                    this.first = current.getNext();

                    if(current == this.last){
                        this.last = null;
                    }
                }
                else{
                    previous.setNext(current.getNext());
                    if(current == this.last){
                        this.last = previous;
                    }
                }
                this.size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public boolean hasElement(T element){
        Node<T> current = this.first;
        Node<T> node = new Node<T>(element);

        while(current != null){
            if(current.getValue().equals(node.getValue())){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "";
        Node<T> current = this.first;
        while(current != null){
            str += current.getValue() + "\n";
            current = current.getNext();
        }
        return str;
    }    
}
