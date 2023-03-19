package model;
import java.lang.Math;

import exception.NotAllowedSizeException;
import exception.TooManySnakesNLaddersException;
//Clase controladora
//Cosas por hacer: 
//1. Testear
//2. Arreglar error al escoger metacaracteres + y *
public class Controller {
   //Lista circular de jugadores
   private CircularLinkedList players;
   //Lista enlazada simple que representa el tablero
   private LinkedList board;
   //Arbol binario para almacenar los puntajes
   private BST scores;
   //Lista de los caracteres que disponibles para ser elegidos como personajes por los jugadores
   private String playerLists;
   //Copia de la tabla para generar las serpientes y escaleras
   private LinkedList copy;
   //El jugador que tiene el turno actual
   private Player currentPlayer;

   public Controller(){
      this.board = new LinkedList();
      this.copy = new LinkedList();
      this.playerLists = "*!OX%$#+&";
      this.players = new CircularLinkedList();
      this.scores = new BST();
   }
   //Este metodo genera el numero de las casillas pedidas
   public String generateTable(int size, int rows, int colum){
      //Le asigna al tablero las columnas
      board.setColumns(colum);
      //Le asigna al tablero el tamaño
      board.setSize(size);
      //Le asigna el tablero las filas
      board.setRows(rows);
      //Llama al metodo recursivo para agregar las cajas
      return generateTable(size, 1);
   }

   private String generateTable(int size, int counter){
      //Caso base: Si el contador de las cajas iguala al tamaño + 1 significa que se han agregado todas las cajas
      if(counter >= size+1){
         return "Se ha generado el tablero";
      }
      //Si el tablero es menor que 6 casillas, se lanza una excepcion, porque este tablero no puede contener mas que 1 serpiente o 1 escalera
      //y establecimos como minimo que debe haber 1 serpiente y una escalera
      if(size<6){
         throw new NotAllowedSizeException("El tamaño del tablero debe ser de almenos 2*3");
      }
      //Si no hat excepcio se llama al metodo para añadir la caja
      board.addNode(counter);
      //Y se llama recursivamente sumando al contador 1
      return generateTable(size, ++counter);
   }
    //Este metodo genera otra tabla con el numero de las casillas pedidas para generar las serpientes y escaleras
   public String generateTable1(int size, int rows, int colum){
      //Hace exactamente lo mismo que el anterios
      copy.setColumns(colum);
      copy.setSize(size);
      copy.setRows(rows);
      return generateTable1(size, 1);
   }
   //De verdad, hace exactamente lo mismo, solo no lanza las excepciones porque si no se lanzarian dos veces
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

   //Este metodo genera las serpientes y escaleras segun la copia del tablero, y va borrando las casillas que ya estan ocupadas
   //Para que asi no se desborde la memoria y solo escoja entre cajas que estan desocupadas
   public String addSnakenLadders(int snk, int ladd){
      //Si el usuario pide cero escaleras o 0 serpientes, se lanza una excepcion, pues debe haber almenos 1 de cada 1
      if(snk==0 || ladd==0){
         throw new NotAllowedSizeException("Las escarleras y serpientes deben ser mayores a 0");
      }
      //Si la suma de las serpientes y escaleras es mayor al tamaño del tablero menos 2, lanza una excepcion, porque significa
      //que no hay suficiente espacio para agregar las serpientes y escaleras pedidas en el tablero
      if((snk+ladd)*2 > board.getSize()-2){
         throw new TooManySnakesNLaddersException("El tablero no es capaz de contener tantas serpientes y esacaleras");
      }
      //Borro la casilla del inicio porque esta no puede ser usada para escalera o serpiente
      this.copy.delete(1);
      //Borro la casilla del final por la misma razon
      this.copy.delete(board.getSize());
      //Llamo este metodo que decrementa el atributo del tamaño del tablero en 2 unidades
      this.copy.decreaseSize();
      //Llamo al metodo recursivo, pasandole las serpientes, escaleras, la copia del tablero y un simbolo que va a representar las serpientes
      //empezando desde A y un numero para representar las escaleras empezando desde 1
      return adddSnakenLadders(snk, ladd, this.copy, 'A', 1);
   }
   //Tiene la logica para añadir las serpientes y escaleras
   private String adddSnakenLadders(int snk, int ladd, LinkedList copy, char symbol, int number){
      //Caso base: Si ya se han agregado todas las serpientes y escaleras, se regresa el mensaje de exito
      if(snk==0 && ladd==0){
         return "Se han agregado las serpientes y escaleras";
      }
      //Si falta alguna serpiente por agregar
      if(snk != 0){
         //Esta variable es un valor aleatorio para la parte donde comienza la escalera
         int boxstart = (int)((Math.random()* (copy.getSize()))); 
         //Este metodo busca el numero aleatorio obtenido anteriormente, pero no por su valor, sino por su indice, pues como cada que se agrega
         //una serpiente o escalera, esta caja se borra de la lista, no se puede buscar por valor, entonces busco la caja que se encuentre en
         //la posicion aleatoria
         Box boxStart = copy.searchIndex(boxstart);
         //Hago esto mismo para la parte en donde termina la serpiente
         int boxsend = (int)((Math.random()* (copy.getSize())));
         //Igual busco por index
         Box boxEnd = copy.searchIndex(boxsend);
         //Este es el valor de la caja que encontre por index, este lo paso a double para un calculo que hare en las siguientes lineas
         double boxi = (double)(boxStart.getValue());
         //Este es una variable holder, que divide el valor de la caja por el numero de columnas
         Double x = (boxi/board.getColumns());
         //Al aplicar la funcion techo a la division del valor de una caja por la cantidad de columnas del tablero, obtengo la fila en 
         //que se encuentra ese valor
         Double row = Math.ceil(x);
         //Si multiplico el valor que obtuve como fila con la cantidad de columnas, obtengo el valor final de esa fila,
         //Es decir, el valor que se encuentra antes de subir a la siguiente fila
         Double endColumn = row*board.getColumns();
         //Por ultimo, si resto el valor final de la columna, por el numero de columnas y luego le sumo 1, obtengo el valor inicial de la fina
         //es decir, el primer valor con el que iniciai esa fila en la que se encuentra la caja que encontré
         Double startColumn = (endColumn - board.getColumns())+1;
         //Aqui valida que el valor que encontre como final de mi serpiente, este por lo menos una fila mas abajp que el valor inicial
         if(boxEnd.getValue()<startColumn){
            //Aqui valido que no ninguno de las cajas que encontre esta ya ocupada, lo cual es innecesario porque cuando las ocupo, las borro
            //Pero tampoco esta demas las validacion
            if(!boxStart.isSnake() && !boxEnd.isSnake() && !boxStart.isLadder() && !boxEnd.isLadder()){
               //Aqui traigo la referencia a la caja original, que va a ser la que va a ser usada durante el juego
               boxStart = board.search(boxStart.getValue());
               //Esto tambien para la caja final
               boxEnd = board.search(boxEnd.getValue());
               //Le pongo un simbolo que la va a representar a ambas
               boxStart.setSymbol(symbol+"");
               boxEnd.setSymbol(symbol+"");
               //Pongo una referencia en la caja de la cabeza de la serpiente apuntando a la cola
               boxStart.setPointer(boxEnd);
               //Pongo los booleanos de que ambos son serpientes en true
               boxStart.setSnake(true);
               boxEnd.setSnake(true);
               //Borro esta cajas del tablero copia
               copy.delete(boxStart.getValue());
               copy.delete(boxEnd.getValue());
               //Disminuyo el tamaño de la copia en dos por los dos que borre
               copy.decreaseSize();
               //Vuelvo a llamar el metodo recursivamente disminuyendo las serpientes por agregar, y pasando al siguiente simbolo para la sgte serpiente
               return adddSnakenLadders(--snk, ladd, copy, ++symbol, number);
            }
         }
      }
      //Esto es exactamente lo mismo para las escalares, pero en vez de validar que sea una final menor, ahora sera a una fila mayor
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
   //Este metodo imprime el tablero con las serpiente y escalres dependiendo si son pares o impares
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

   //Este metodo imprime la lista de caracteres disponibles para elegir como personajes por los jugadores
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
  //Crea a los jugadores y los agrega a la lista circular
  public String updatePlayers(String character, int counter){
      if(counter==character.length()){
         return "Se agregaron los jugadores";
      }
      Player player = new Player(character.charAt(counter)+"");
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
   //Pone a los jugadores en la caja del principio
   public void asingPosition(String c){
      board.getHead().setPlayers(c);
   }
   //Pasa el turno al sgte jugador
   public void passTurn(){
      this.currentPlayer=this.currentPlayer.getNext();
    }
    //Tira el dado y devuelve un valor del 1 al 6
    public  int trowDice(){
      return   ((int)(Math.random()*6)+1);
    }
    //Mueve al jugador y verifica si ha caido en una serpiente o escalera y lo mueve a traves de esta
    public boolean movePlayer(int steps){
      //La caja a la que se debe mover
      int nextBox = this.currentPlayer.getCurrent_box().getValue()+steps;
      //Valida que no se haya salido del tablero
      if(nextBox<=board.getSize()) {
         //Lo borra de la caja actual
         this.currentPlayer.getCurrent_box().removePlayer(this.currentPlayer.getId());
         //Busca la caja en la que cayo
         Box box = board.search(nextBox);
         //Valida si esta apunta a otra caja para moverlo a esta
         if(box.getPointer()!=null){
            //Lo manda a la caja si esta apunta a otro
             box.getPointer().addPlayers(this.currentPlayer.getId());
             this.currentPlayer.setCurrent_box(box.getPointer());
         }else{
            //Si no solo lo agrega a la caja en que cayó
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

   public void addScore(int score){
      Node scoreNew = new Node(score);
      scores.insert(scoreNew);
  }

  public void resetGame(){
     this.board = new LinkedList();
     this.copy = new LinkedList();
     this.playerLists = "*!OX%$#+&";
     this.players = new CircularLinkedList();
  }


}