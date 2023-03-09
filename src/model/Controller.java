package model;
import java.lang.Math;

public class Controller {
   private Player player_1;
   private Player player_2;
   private Player player_3;
   private LinkedList board;
   private String playerLists;
   private LinkedList copy;

   public LinkedList getBoard() {
      return board;
   }

   public void setBoard(LinkedList board) {
      this.board = board;
   }


   public Controller(){
      this.board = new LinkedList();
      this.copy = new LinkedList();
      this.playerLists = "*!OX%$#+&";
   }

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

   public String printBoard(){
      if(board.getRows()%2==0){
         return board.printBoard();
      }else{
         return board.printBoardOdd();
      }
   }

   public String printPlayersList(){
      return printPlayersList(0, playerLists, "");
   }

  public String printPlayersList(int counter, String list, String msj){
      if(counter==list.length()-1){
          return msj;
      }
      msj+=counter+1+". "+list.charAt(counter)+"\n";
      return printPlayersList(++counter, list, msj);
      
  }

  public String updatePlayer1(int character){
      if(character<this.playerLists.length()){
         String charac = playerLists.charAt(character-1)+"";
         this.player_1 = new Player(charac);
         int len = this.playerLists.length();
         this.playerLists = this.playerLists.replace(charac, "");
         int len2 = this.playerLists.length();
         if(len-len2==1){
            return "Personaje seleccionado";
         }else{
            return "No se selecciono el personaje";
         }
      }else{
         return "Opcion invalida";
      }
  }

  public String updatePlayer2(int character){
      if(character<this.playerLists.length()){
         String charac = playerLists.charAt(character-1)+"";
         this.player_2 = new Player(charac);
         int len = this.playerLists.length();
         this.playerLists = this.playerLists.replace(charac, "");
         int len2 = this.playerLists.length();
         if(len-len2==1){
            return "Personaje seleccionado";
         }else{
            return "No se selecciono el personaje";
         }
      }else{
         return "Opcion invalida";
      }
   }

   public String updatePlayer3(int character){
      if(character<this.playerLists.length()){
         String charac = playerLists.charAt(character-1)+"";
         this.player_3 = new Player(charac);
         int len = this.playerLists.length();
         this.playerLists = this.playerLists.replace(charac, "");
         int len2 = this.playerLists.length();
         if(len-len2==1){
            return "Personaje seleccionado";
         }else{
            return "No se selecciono el personaje";
         }
      }else{
         return "Opcion invalida";
      }
   }

   public void addPlayers(){
      board.addPlayers(player_1.getId());
      player_1.setCurrent_box(board.getHead());
      board.addPlayers(player_3.getId());
      player_2.setCurrent_box(board.getHead());
      board.addPlayers(player_2.getId());
      player_3.setCurrent_box(board.getHead());
   }
}
