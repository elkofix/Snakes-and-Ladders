package model;

public class Game {
   private Player player_1 = new Player("O");
   private Player player_2 = new Player("X");
   private Player player_3 = new Player("#");

   public Game(int rows, int cols){
      Board board = new Board(rows*cols);
      //board.printBoardRange(1, rows*cols);

      System.out.println(board.printRange(rows, cols));
   }
}
