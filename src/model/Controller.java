package model;

public class Controller {
   private Player player_1;
   private Player player_2;
   private Player player_3;
   private LinkedList board;

   public Controller(){
      this.board = new LinkedList();
   }

   public String generateTable(int size){
      return generateTable(size, 1);
   }

   public String generateTable(int size, int counter){
      if(counter >= size+1){
         return "Se ha generado el tablero";
      }
      if(size<6){
         return "El tamaño del tablero debe ser de almenos 2*3";
      }
      board.addNode(counter);
      return generateTable(size, ++counter);
   }
}

