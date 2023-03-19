package model;
import java.lang.Math;

import exception.NotAllowedSizeException;
import exception.TooManySnakesNLaddersException;

public class Controller {
   private CircularLinkedList players;
   private LinkedList board;


    private BST scores;
   private String playerLists;
   private LinkedList copy;
   private Player currentPlayer;

   public Player getCurrentPlayer() {
      return currentPlayer;
   }

   public void setCurrentPlayer(Player currentPlayer) {
      this.currentPlayer = currentPlayer;
   }

   public LinkedList getBoard() {
      return board;
   }

   public void setBoard(LinkedList board) {
      this.board = board;
   }


   public Controller(){
      this.board = new LinkedList();
      this.copy = new LinkedList();
      this.playerLists = "!OX%$#+&";
      this.players = new CircularLinkedList();
      this.scores = new BST();
   }
   //Este metodo genera el numero de las casillas pedidas
   public String generateTable(int size, int rows, int colum){
      board.setColumns(colum);
      board.setSize(size);
      board.setRows(rows);
      return generateTable(size, 1);
   }

   public void addScore(int score){
       Node scoreNew = new Node(score);
       scores.insert(scoreNew);
   }

   public String printScores(){
       return scores.inOrderString();
   }

   public void resetGame(){
      this.board = new LinkedList();
      this.copy = new LinkedList();
      this.playerLists = "!OX%$#+&";
      this.players = new CircularLinkedList();
   }

   private String generateTable(int size, int counter){
      if(counter >= size+1){
         return "Se ha generado el tablero";
      }
      if(size<6){
         throw new NotAllowedSizeException("El tamaño del tablero debe ser de almenos 2*3");
      }
      board.addNode(counter);
      return generateTable(size, ++counter);
   }
    //Este metodo genera otra tabla con el numero de las casillas pedidas para generar las serpientes y escaleras
   public String generateTable1(int size, int rows, int colum){
      copy.setColumns(colum);
      copy.setSize(size);
      copy.setRows(rows);
      return generateTable1(size, 1);
   }

   private String generateTable1(int size, int counter){
      if(counter >= size+1){
         return "Se ha generado el tablero";
      }
      if(size<6){
         return "El tamaño del tablero debe ser de almenos 2*3";
      }
      copy.addNode(counter);
      return generateTable1(size, ++counter);
   }
   //Este metod genera las serpientes y escaleras segun la copia del tablero, y va borrando las casillas que ya estan ocupadas
   //Para que asi no se desborde la memoria
   public String addSnakenLadders(int snk, int ladd){
      if(snk==0 || ladd==0){
         throw new NotAllowedSizeException("Las escarleras y serpientes deben ser mayores a 0");
      }
      if((snk+ladd)*2 > board.getSize()){
         throw new TooManySnakesNLaddersException("El tablero no es capaz de contener tantas serpientes y esacaleras");
      }

      this.copy.delete(1);
      this.copy.delete(board.getSize());
      this.copy.decreaseSize();
      return adddSnakenLadders(snk, ladd, this.copy, 'A', 1);
   }

   private String adddSnakenLadders(int snk, int ladd, LinkedList copy, char symbol, int number){
      if(snk==0 && ladd==0){
         return "Se han agregado las serpientes y escaleras";
      }

      if(snk != 0){
         int boxstart = (int)((Math.random()* (copy.getSize()))); 
         Box boxStart = copy.searchIndex(boxstart);
         int boxsend = (int)((Math.random()* (copy.getSize())));
         Box boxEnd = copy.searchIndex(boxsend);
         double boxi = (double)(boxStart.getValue());
         Double x = (boxi/board.getColumns());
         Double row = Math.ceil(x);
         Double endColumn = row*board.getColumns();
         Double startColumn = (endColumn - board.getColumns())+1;

         if(boxEnd.getValue()<startColumn){
            if(!boxStart.isSnake() && !boxEnd.isSnake() && !boxStart.isLadder() && !boxEnd.isLadder()){
               boxStart = board.search(boxStart.getValue());
               boxEnd = board.search(boxEnd.getValue());
               boxStart.setSymbol(symbol+"");
               boxEnd.setSymbol(symbol+"");
               boxStart.setPointer(boxEnd);
               boxStart.setSnake(true);
               boxEnd.setSnake(true);
               copy.delete(boxStart.getValue());
               copy.delete(boxEnd.getValue());
               copy.decreaseSize();
               return adddSnakenLadders(--snk, ladd, copy, ++symbol, number);
            }
         }
      }

      if(ladd != 0){
         int boxstart = (int)((Math.random()* (copy.getSize()))); 
         Box boxStart = copy.searchIndex(boxstart);
         int boxsend = (int)((Math.random()* (copy.getSize())));
         Box boxEnd = copy.searchIndex(boxsend);
         double boxi = (double)(boxStart.getValue());
         Double x = (boxi/board.getColumns());
         Double row = Math.ceil(x);
         Double endColumn = row*board.getColumns();

         if(boxEnd.getValue()>endColumn){
            if(!boxStart.isSnake() && !boxEnd.isSnake() && !boxStart.isLadder() && !boxEnd.isLadder()){
               boxStart = board.search(boxStart.getValue());
               boxEnd = board.search(boxEnd.getValue());
               boxStart.setSymbol(number+"");
               boxEnd.setSymbol(number+"");
               boxStart.setPointer(boxEnd);
               boxStart.setLadder(true);
               boxEnd.setLadder(true);
               copy.delete(boxStart.getValue());
               copy.delete(boxEnd.getValue());
               copy.decreaseSize();
               return adddSnakenLadders(snk, --ladd, copy, symbol, ++number);
            }
         }
      }
      return adddSnakenLadders(snk, ladd, copy, symbol, number);
   }
   //Este metodo imprime el tablero dependiendo si las filas son impares o impares
   public String printBoard(){
      if(board.getRows()%2==0){
         return board.printBoard();
      }else{
         return board.printBoardOdd();
      }
   }

    public String printSnakes(){
        if(board.getRows()%2==0){
            return board.printSnakes();
        }else{
            return board.printSnakesOdd();
        }
    }
   //Este metodo imprime la lista de opciones de signos para usar como jugadore
   public String printPlayersList(){
      return printPlayersList(0, "");
   }

  public String printPlayersList(int counter, String msj){
      if(counter==playerLists.length()-1){
          return msj;
      }
      msj+=(counter+1)+". "+this.playerLists.charAt(counter)+"\n";
      return printPlayersList(++counter, msj);
      
  }
  //Este metodo agrega los jugadores a la lista de jugadores
  public String updatePlayers(String character){
      return updatePlayers(character, 0);
  }

  public String updatePlayers(String character, int counter){
      if(counter==character.length()){
         return "Se agregaron los jugadores";
      }
      Player player = new Player(character.charAt(counter)+"");
      System.out.println(player.getId());
      if(counter == 0){
         currentPlayer = player;
     }
      player.setCurrent_box(board.getHead());
      players.addLast(player);
      return updatePlayers(character, ++counter);
   }
   //Este metodo recibe la figura que escogieron pa un jugador y la borra para que no la puedan escoger mas
   public String getFigure(int pos){
      String chars = this.playerLists.charAt(pos-1)+"";
      this.playerLists = this.playerLists.replaceFirst(chars, "");
		return chars;
	}

   public void asingPosition(String c){
      board.getHead().setPlayers(c);
   }

   public void passTurn(){
      this.currentPlayer=this.currentPlayer.getNext();
    }

    public  int trowDice(){
      return   ((int)(Math.random()*6)+1);
    }

    public boolean movePlayer(int steps){
      int nextBox = this.currentPlayer.getCurrent_box().getValue()+steps;
      if(nextBox<=board.getSize()) {
         this.currentPlayer.getCurrent_box().removePlayer(this.currentPlayer.getId());
         Box box = board.search(nextBox);

         if(box.getPointer()!=null){
             box.getPointer().addPlayers(this.currentPlayer.getId());
             this.currentPlayer.setCurrent_box(box.getPointer());
         }else{
             box.addPlayers(this.currentPlayer.getId());
             this.currentPlayer.setCurrent_box(box);
         }
         return true;
      }
      return false;
    }

    public boolean isEnd(){
       return !board.getTail().getPlayers().equals("");
    }

    public String printScoreList(){
      return printScoreList(1, "Lista de puntajes \n", scores.inOrderString());
    }

    public String printScoreList(int counter, String msj, String scores){
         if(scores.length()==0){
            return msj;
         }
         String current;
         int i;
         i = scores.indexOf(",");
         current = scores.substring(0, i);
         scores = scores.substring(i+1, scores.length());
         msj += counter+": "+current+"\n";
         return printScoreList(++counter, msj, scores);

    }
    public BST getScores() {
      return scores;
 }

}