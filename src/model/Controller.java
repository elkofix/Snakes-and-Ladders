package model;
import java.lang.Math;

public class Controller {
   private Player player_1;
   private Player player_2;
   private Player player_3;
   private LinkedList board;

   public LinkedList getBoard() {
      return board;
   }

   public void setBoard(LinkedList board) {
      this.board = board;
   }

   public Controller(){
      this.board = new LinkedList();
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

   public String addSnakenLadders(int snk, int ladd){
      if(snk==0 || ladd==0){
         return "Las escarleras y serpientes deben ser mayores a 0";
      }
      if(snk+ladd > (board.getSize()-2)/2){
         return "El tablero no puede tener tantas escaleras y serpientes";
      }
      return adddSnakenLadders(snk, ladd);
   }

   private String adddSnakenLadders(int snk, int ladd){
      if(snk==0 && ladd==0){
         return "Se han agregado las serpientes y escaleras";
      }

      if(snk != 0){
         int boxstart = ((int)(Math.random()* (board.getSize() - 2 + 1) + 2));
         int boxsend = ((int)(Math.random()* (board.getSize() - 2 + 1) + 2));
         int row = (int)(Math.ceil(boxstart/board.getColumns()));
         int endColumn = row*board.getColumns();
         int startColumn = (endColumn - board.getColumns())+1;
         if(boxsend<startColumn && endColumn != 1){
            Box boxStart = board.search(boxstart);
            Box boxEnd = board.search(boxsend);
            if(!boxStart.isSnake() && !boxEnd.isSnake() && !boxStart.isLadder() && !boxEnd.isLadder()){
               boxStart.setPointer(boxEnd);
               boxStart.setSnake(true);
               boxEnd.setSnake(true);
               return adddSnakenLadders(--snk, ladd);
            }
         }
      }
      if(ladd != 0){
         int boxstart = ((int)(Math.random()* (board.getSize() - 2 + 1) + 2));
         int boxsend = ((int)(Math.random()* (board.getSize() - 2 + 1) + 2));
         int row = (int)(Math.ceil(boxstart/board.getColumns()));
         int endColumn = row*board.getColumns();
         if(boxsend>endColumn && endColumn != board.getSize()){
            Box boxStart = board.search(boxstart);
            Box boxEnd = board.search(boxsend);
            if(!boxStart.isSnake() && !boxEnd.isSnake() && !boxStart.isLadder() && !boxEnd.isLadder()){
               boxStart.setPointer(boxEnd);
               boxStart.setLadder(true);
               boxEnd.setLadder(true);
               return adddSnakenLadders(snk, --ladd);
            }
         }
      }
      return adddSnakenLadders(snk, ladd);
   }

   public String printBoard(){
      if(board.getRows()%2==0){
         return board.printBoard();
      }else{
         return board.printBoardOdd();
      }
   }
}
