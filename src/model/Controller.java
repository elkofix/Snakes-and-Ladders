package model;
import java.lang.Math;

public class Controller {
   private CircularLinkedList players;
   private LinkedList board;
   private String playerLists;
   private LinkedList copy;
   private String turns;

   public String getTurns() {
      return turns;
   }

   public void setTurns(String turns) {
      this.turns = turns;
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
   }
   //Este metodo genera el numero de las casillas pedidas
   public String generateTable(int size, int rows, int colum){
      board.setColumns(colum);
      board.setSize(size);
      board.setRows(rows);
      return generateTable(size, 1);
   }

   private String generateTable(int size, int counter){
      if(counter >= size+1){
         return "Se ha generado el tablero";
      }
      if(size<6){
         return "El tamaño del tablero debe ser de almenos 2*3";
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
         return "Las escarleras y serpientes deben ser mayores a 0";
      }

      this.copy.delete(1);
      this.copy.delete(board.getSize());
      this.copy.decreaseSize();
      return adddSnakenLadders(snk, ladd, this.copy);
   }

   private String adddSnakenLadders(int snk, int ladd, LinkedList copy){
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
               boxStart.setPointer(boxEnd);
               boxStart.setSnake(true);
               boxEnd.setSnake(true);
               copy.delete(boxStart.getValue());
               copy.delete(boxEnd.getValue());
               copy.decreaseSize();
               return adddSnakenLadders(--snk, ladd, copy);
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
               boxStart.setPointer(boxEnd);
               boxStart.setLadder(true);
               boxEnd.setLadder(true);
               copy.delete(boxStart.getValue());
               copy.delete(boxEnd.getValue());
               copy.decreaseSize();
               return adddSnakenLadders(snk, --ladd, copy);
            }
         }
      }
      return adddSnakenLadders(snk, ladd, copy);
   }
   //Este metodo imprime el tablero dependiendo si las filas son impares o impares
   public String printBoard(){
      if(board.getRows()%2==0){
         return board.printBoard();
      }else{
         return board.printBoardOdd();
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
      if(character.length()==counter+1){
         return "Se agregaron los jugadores";
      }
      Player player = new Player(character.charAt(counter)+"");
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
}
