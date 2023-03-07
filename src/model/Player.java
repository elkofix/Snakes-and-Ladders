package model;

public class Player {
    private String id;

    //Establecer como base la primera casilla
    private Box current_box;

    
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
