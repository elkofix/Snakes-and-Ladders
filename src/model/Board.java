package model;

public class Board {
    private Square head;
    public Square getHead() {
        return head;
    }

    public void setHead(Square head) {
        this.head = head;
    }

    private Square tail;

    public Square getTail() {
        return tail;
    }

    public void setTail(Square tail) {
        this.tail = tail;
    }

    private int size;

    // Constructor
    public Board(int size) {
        this.size = size;

        createList(size, 1);
    }

    private void createList(int size, int i){
        add(i);

        if(i < size){
            createList(size, i+1);
        }
    }

    private void addRecursive(int value, Square current) {
        if (current.getNext() == null) {
            Square newNode = new Square(value);
            newNode.setPrevious(current);
            current.setNext(newNode);
            tail = newNode;
        } else {
            addRecursive(value, current.getNext());
        }
    }
    
    private void add(int value) {
        if (head == null) {
            head = new Square(value);
            tail = head;
        } else {
            addRecursive(value, tail);
        }
    }

    public boolean search(int value) {
        return searchRecursive(head, value);
    }
    
    private boolean searchRecursive(Square current, int value) {
        if (current == null) {
            return false; // Llegamos al final de la lista y no encontramos el valor
        } else if (current.getNumber() == value) {
            return true; // Encontramos el valor en el nodo actual
        } else {
            return searchRecursive(current.getNext(), value); // Buscamos en el siguiente nodo de la lista
        }
    }

    public String printRange(int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        printRangeRecursive(head, sb, startIndex, endIndex);
        sb.append("]");
        return sb.toString();
    }
    
    private void printRangeRecursive(Square current, StringBuilder sb, int startIndex, int endIndex) {
        if (current == null) {
            return;
        }
        if (startIndex <= 0 && endIndex >= 0) {
            sb.append(current.getNumber());
            if (current.getNext() != null && endIndex > 0) {
                sb.append(", ");
            }
        }
        printRangeRecursive(current.getNext(), sb, startIndex - 1, endIndex - 1);
    }
    
    

    /*
    // Add a snake to the board
    public void addSnake(int headPosition, int tailPosition) {
        Square head = getSquare(headPosition);
        Square tail = getSquare(tailPosition);

        if (head != null && tail != null) {
            head.setSnakeTail(tail);
        }
    }
    
    // Add a ladder to the board
    public void addLadder(int start, int end) {
        Square ladderStart = getSquare(start);
        Square ladderEnd = getSquare(end);
        if (ladderStart != null && ladderEnd != null) {
            ladderStart.setLadderEnd(ladderEnd);
        }
    }*/

    //Pintar la tabla segun un rango:


//Pintar todo el tablero

/*
public void printBoard() {

    System.out.println(getSquare(10));
    //printBoardRecursive(start, size);
}

/*private void printBoardRecursive(Square current, int remainingSquares) {
    if (current == null || remainingSquares <= 0) {
        return;
    }
    
    System.out.print("[" + current.getNumber() + "]");
    
    if (current.getSnakeTail() != null) {
        System.out.print("<" + current.getSnakeTail().getNumber() + ">");
    } else if (current.getLadderEnd() != null) {
        System.out.print("[" + current.getLadderEnd().getNumber() + "]");
    }
    
    System.out.print(" ");
    
    printBoardRecursive(current.getNext(), remainingSquares - 1);

}*/

}