package model;
import java.lang.Math;

import javax.print.attribute.standard.Sides;

public class Controller {
   private Player player_1;
   private Player player_2;
   private Player player_3;
   private LinkedList board;
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
}
