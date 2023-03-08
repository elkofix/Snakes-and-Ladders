package model;

public class LinkedList {
    private Box head;
    private Box tail;
    private int size;
    private int rows;
    private int columns;

    public int getColumns() {
        return columns;
    }

    
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addNode(int value){
        Box node = new Box(value);
        if(head==null){
            head = node;
            tail = node;
        }else{
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
    }

    
	public Box search(int goal){
		return search(goal, this.head); 
	}

	private Box search(int goal, Box current){
		// Caso base 
		if(current == null){
			return null; 
		}

		// caso borde 
		if(goal == head.getValue() && current.equals(this.head)){
			return this.head; 
		}

		if(goal == tail.getValue() && current.equals(this.tail)){
			return this.tail; 
		}
		if(goal == current.getValue()){
			return current; 
		}

		return search(goal, current.getNext()); 
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