package ui;

import java.util.Scanner;
import model.Controller;

public class Main{

    //Scanner del sistema
    private Scanner sc;
    //Clase Controladora
    private Controller control;
    //Tiempo de inicio del juego, es una variable global porque al usar recursividad, llegaba a un punto donde se acumulaban
    //los metodos y este se reiniciaba de manera erronea, entonces es global para usarlo solo despues de que se asignen los jugadores
    //en el metodo necesario. 
    private long startTime;

    //Clase main
    public Main(){
        //Inicializo en scanner
        this.sc = new Scanner(System.in);
        //Inicializo en controller
        this.control = new Controller();
    }
    //Nuestra clase main
    public static void main(String args[]) {
        //Instancio un objeto main
        Main main = new Main();
        //Llamo al metodo que será el hilo principal de la ejecución de nuestro juego, el cual llamara a todos los metodos necesarios
        //de manera consecutiva para el correcto funcionamiento del juego.
        main.startMenu();
    }

    //Es el metodo que llama a los demas metodos necesarios para poder jugar
    public void startMenu(){
        //Imprime el menu con las primeras dos opciones (1) Jugar y (2) Salir
        System.out.println("\n Bienvenido al juego Escaleras y Serpientes... \n\n Seleccione una opcion: \n [1] Jugar \n [2] Salir");
        int option = sc.nextInt();
        if(option==1){
            //Si el usuario decide jugar llamo al primer metodo de la construccion de nuestro juego, que va a generar el tablero
            generateTable();
        }else if(option==2){
            //Muestro un mensaje de salida del programa
            System.out.println("Salida del programa");    
            //Cierro el scanner para evitar que capte mas informacion despues de haber cerrado el juego 
            sc.close();       
        // Si lo ingresado por el usuario no corresponde a ninguna de las dos opciones (1) Jugar (2) Salir
        }else{ 
            //Imprimo un mensaje de error
            System.out.println("Opcion invalida");
            //Llamo a este mismo metodo recursivamente para que se vuelva a imprimir el menu
            startMenu();
        }
        
    }
    //Este metodo obtendra la informacion para generar el tablero
    public void generateTable(){
        //Imprimo la cuestion al usuario
        System.out.println("\n Digite el numero de filas del tablero: ");
        //Obtengo las filas
        int rows = sc.nextInt();

        System.out.println("\n Digite el numero de columnas del tablero: ");
        //Obtengo las columnas
        int cols = sc.nextInt();
        //Como el metodo generateTable y generateTable1 son propensos a lanzar una excepcion en tiempo de ejecucion
        //NotAllowedSizeException uso un try para controlar la excepcion
        try{
            //Imprimo el resultado del metodo al generar la tabla
            System.out.println(control.generateTable(rows*cols, rows, cols));
            control.generateTable1(rows*cols, rows, cols);
            //Paso al siguiente metodo para generar las serpientes y escaleras
            generateSnakeNLadders();
        }catch(RuntimeException e){
            //Si encuentra una excepcion la capturo e imprimo el mensaje de la misma
            System.out.println(e);
            //Reseteo el juego para que no guarde los datos obtenidos hasta el momento
            control.resetGame();
            //llamo al metodo principal para que vuelva al hilo normal de jecucion
            startMenu();
        }
    }
    //Obtiene la informacion para generar las serpiente s yescaleras    
    public void generateSnakeNLadders(){
        System.out.println("\n Digite el numero de serpientes: ");
        //Obtengo escaleras 
        int snakes = sc.nextInt();
        
        System.out.println("\n Digite el numero de escaleras: ");
        //Obtengo las escaleras
        int stairs = sc.nextInt();
        //Como el metodo addSnakesNLadders son propensos a lanzar una excepcion en tiempo de ejecucion
        //TooManySnakesNLadders uso un try para controlar la excepcion
        try{
            System.out.println(control.addSnakenLadders(snakes, stairs));
            //Si no se lanza ninguna excepcion se pasa a seleccionar los personajes para lo jugadores
            selectPlayers();
        }catch(RuntimeException e){
            //Si se captura una excepcion la imprimo y reseteo el juego, luego vuelvo al menu principal
            System.out.println(e);
            control.resetGame();
            startMenu();
        }
    }

    public void selectPlayers(){
        //Cadena contenedora de las figuras
        String figures = "";
        System.out.println("Escoja un simbolo para el primer jugador:");
        //Este metodo imprime la lista de caracteres disponibles
        System.out.println(control.printPlayersList());
        //Esta es la opcion del caracter representado como entero
        int character = sc.nextInt();  
        //Aqui acumulo las figuras para los tres jugadores y voy borrando la elegida para que el 
        //Siguiente no pueda escoger la misma
        figures += control.getFigure(character);
        System.out.println("Escoja un simbolo para el segundo jugador:");
        System.out.println(control.printPlayersList());
        character = sc.nextInt();
        figures += control.getFigure(character);
        System.out.println("Escoja un simbolo para el tercer jugador:");
        System.out.println(control.printPlayersList());
        character = sc.nextInt();
        figures += control.getFigure(character);
        //Cuando ya tengo los tres carateres, el siguiente metodo crea los jugadores y los agrega a un lista circular
        //Para asi iterar sobre los turnos, cuando llega al ultimo vuelve al primero
        control.updatePlayers(figures);   
        //El siguiente metodo pone a los caracters de los jugadores en la primera casilla
        control.asingPosition(figures);
        //Desde aqui se empieza a contar el tiempo
        startTime = System.nanoTime();
        //Llamo al siguiente metodo para que empiecen los turno
        takeTurn();
    }

    public void takeTurn(){
        //Antes de cada turno imprimo el tablero, para que vean donde estan posicionados los jugadores y los cambios respexcto al turno anterior
        System.out.println(control.printBoard());
        //Muestro el menu con el simbolo del jugador que va a tira y le muestro las opciones
        System.out.println("Jugador "+control.getCurrentPlayer().getId()+", es tu turno \n"+
                "1. Tirar dado\n"+
                "2. Ver escaleras y serpientes");
        int option = sc.nextInt();
        if(option == 1){
            //Este metodo me devuelve un numero aleatoria de 1 a 6
            int steps = control.trowDice();
            System.out.println("Has sacado: "+steps);
            //Este metodo mueve al jugador, pero valida que no se salga del tablero,
            if(control.movePlayer(steps)){
                //Aqui valido si algun jugador ha llegado a la meta para terminar el juego
                if(control.isEnd()){
                    //Si es asi imprimo cual jugador llegó
                    System.out.println(control.getCurrentPlayer().getId()+" Ha llegado a la meta");
                    //Resetri el juego para la proxima ronda
                    control.resetGame();
                    //Llamo al metodo para calcular el puntaje
                    printScores();
                    return;
                }
                //Si no ha llegado nadie al final, paso el turno y vuelvo a llamar al mismo metodo recursivamente
                control.passTurn();
                takeTurn();
            }else{
                //Si se sale del tablero, le digo que se ha pasado y no lo muevo, luego paso el turno al sgte jugador
                System.out.println("Te has pasado!");
                control.passTurn();
                //Llamo recursivamente al metodo
                takeTurn();
            }


        }else if(option==2){
            //Si selecciona la opcion de ver las serpientes y escaleras llamo al metodo que las imprime
            System.out.println(control.printSnakes());
            //Y llamo recursivamente sin pasar el turno
            takeTurn();
        }else{
            //Si ingresa otro numero repito el turno
            System.out.println("Opcion invalida");
            takeTurn();
        }
    }
    //Este metodo calcula el puntaje del jugador que llegó a la meta, lo agrega al arbol e imprime la lista
    public void printScores(){
        //Captura el tiempor actual
        long endTime = System.nanoTime();
        //Este es el factor de division, para pasar de nanosegundos a minutos
        long division = (long)600000000*100;
        //Calculo del tiempo que pasó en minutos
        int elapsedTime= ((int)((endTime-startTime)/division));
        //Calculo del puntaje
        int score = (600-elapsedTime)/6;
        //Agrego el puntaje al arbol
        control.addScore(score);
        //Imprimo la lista
        System.out.println(control.printScoreList());
        //Vuelvo al menu para otra ronda
        startMenu();
    }
