package model;
public class Player {
    private String id;
    //Establecer como base la primera casilla
    private Box current_box;
    private Player next;
    public Player getNext() {
        return next;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    private Player previous;

    
    public Player getPrevious() {
        return previous;
    }

    public void setPrevious(Player previous) {
        this.previous = previous;
    }

    public Player(String id) {
        this.id = id;
        this.current_box = null;
    }

    public Box getCurrent_box() {
        return current_box;
    }

    public void setCurrent_box(Box current_box) {
        this.current_box = current_box;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
