package model;

public class Box {

    private int value;
    private Box next;
    private Box previous;
    private Box pointer;
    private boolean snake;
    public boolean isSnake() {
        return snake;
    }

    public void setSnake(boolean snake) {
        this.snake = snake;
    }

    private boolean ladder;
    

    public boolean isLadder() {
        return ladder;
    }

    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    
    public Box getPointer() {
        return pointer;
    }

    public void setPointer(Box pointer) {
        this.pointer = pointer;
    }



    public Box(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Box getNext() {
        return next;
    }

    public void setNext(Box next) {
        this.next = next;
    }

    public Box getPrevious() {
        return previous;
    }

    public void setPrevious(Box previous) {
        this.previous = previous;
    }
}