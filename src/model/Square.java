package model;

public class Square {
    private int number;
    private Square next;
    private Square previous;
    private Square snakeTail;
    private Square ladderEnd;
    
    public Square getLadderEnd() {
        return ladderEnd;
    }

    public void setLadderEnd(Square ladderEnd) {
        this.ladderEnd = ladderEnd;
    }

    public Square getSnakeTail() {
        return snakeTail;
    }

    public void setSnakeTail(Square snakeTail) {
        this.snakeTail = snakeTail;
    }

    public Square(int number) {
        this.number = number;
        this.next = null;
        this.previous = null;
    }

    public int getNumber() {
        return number;
    }

    public Square getNext() {
        return next;
    }

    public void setNext(Square next) {
        this.next = next;
    }

    public Square getPrevious() {
        return previous;
    }

    public void setPrevious(Square previous) {
        this.previous = previous;
    }
}