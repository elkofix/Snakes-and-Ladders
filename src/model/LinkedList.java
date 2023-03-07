package model;

public class LinkedList {
    private Box head;
    private Box tail;

    private LinkedList previous;
    
    public LinkedList getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedList previous) {
        this.previous = previous;
    }

    private LinkedList next;

    public LinkedList getNext() {
        return next;
    }

    public void setNext(LinkedList next) {
        this.next = next;
    }

    public void addNode(Box node){
        if(head==null){
            head = node;
            tail = node;
        }else{
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
    }

    public void addFirst(Box node){

        if(head==null){
            head = node;
            tail = node;
        }else{
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        
    }

    //Activacion
    public void print(){
        print(head);
    }

    //Recursivo
    private void print(Box current){
        if(current == null){
            return;
        }
        System.out.println(current.getValue());
        print(current.getNext());
    }

    //Eliminacion
    public void delete(int goal){
        delete(head, goal);
    }
    private void delete(Box current, int goal){
        if(current == null){
            return;
        }
        if(current.getValue() == goal){
            if(current == head){
                Box next = head.getNext();
                next.setPrevious(null);
                head = next;
                return;
            }
            if(current == tail){
                Box prev = tail.getPrevious();
                prev.setNext(null);
                tail = prev;
                return;
            }
            //Eliminar
            Box prev = current.getPrevious();
            Box next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            return;
        }
        delete(current.getNext(), goal);

    }

}