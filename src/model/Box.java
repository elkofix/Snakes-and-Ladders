package model;

public class Box {

    private int value;
    private Box next;
    private Box previous;
    //Este atributo es la caja a la que apunta otra, por ejemplos si este fuera la casilla 2, y es una escalera, este apuntaria al 7
    private Box pointer;
    private boolean snake;
    private String players;
    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

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
        this.ladder = false;
        this.snake = false;
        this.players = "";
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

    public void deletePlayer(String player){
        players = players.replaceAll(player, "");
    }

    public void addPlayers(String player){
        players+=player;
    }

    public void setPrevious(Box previous) {
        this.previous = previous;
    }
    //Este metodo imprime una casilla, y calcula los espacios necesarios para que contenga jugadores y no corra las demas casillas
    @Override
    public String toString(){
        if(!players.equals("")){
            
            if(value>=10){
                String box = "["+ value+players+"]   ";
                return "  "+recalculateSpaces(players.length(), box);
            }
            String box = "["+ value+players+"]    ";
            return "  "+recalculateSpaces(players.length(), box);
        }else{
            if(value>=10){
                return "  ["+ value+"]   ";
            }
            return "  ["+ value+"]    "; 
        }
    }

    public String toString2(){
        return "  ["+ snake+"]  ";
    }

    public String recalculateSpaces(int players, String box){
        if(players==0){
            return box;
        }        
        box = box.replaceFirst(" ", "");
        return recalculateSpaces(--players, box);
    }

    public void removePlayer(String character){
        this.players = this.players.replaceAll(character, "");
    }

    
}