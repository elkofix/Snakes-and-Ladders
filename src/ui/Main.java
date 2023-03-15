package ui;

import java.util.Scanner;
import model.Controller;

public class Main{

    private Scanner sc;
    private Controller control;

    public Main(){
        this.sc = new Scanner(System.in);
        this.control = new Controller();
    }
    public static void main(String args[]) {
        Main main = new Main();
        main.startMenu();
    }

    public void startMenu(){
        System.out.println("\n Bienvenido al juego Escaleras y Serpientes... \n\n Seleccione una opcion: \n [1] Jugar \n [2] Salir");
        int option = sc.nextInt();
        if(option==1){
            generateTable();
            generateSnakeNLadders();
            selectPlayers();
            long startTime = System.nanoTime();
            takeTurn();
            printScores(startTime);
        }else{
            if(option==0){
                System.out.println("Salida del programa");
            }else{
                System.out.println("Opcion invalida");
                startMenu();
            }
        } 
    }

    public void generateTable(){
        System.out.println("\n Digite el numero de filas del tablero: ");
        int rows = sc.nextInt();

        System.out.println("\n Digite el numero de columnas del tablero: ");
        int cols = sc.nextInt();

        System.out.println(control.generateTable(rows*cols, rows, cols));
        control.generateTable1(rows*cols, rows, cols);
    }

    public void generateSnakeNLadders(){
        System.out.println("\n Digite el numero de serpientes: ");
        int snakes = sc.nextInt();
        System.out.println("\n Digite el numero de escaleras: ");
        int stairs = sc.nextInt();
        System.out.println(control.addSnakenLadders(snakes, stairs));
    }

    public void selectPlayers(){
        String figures = "";
        System.out.println("Escoja un simbolo para el primer jugador:");
        System.out.println(control.printPlayersList());
        int character = sc.nextInt();  
        figures += control.getFigure(character);
        System.out.println("Escoja un simbolo para el segundo jugador:");
        System.out.println(control.printPlayersList());
        character = sc.nextInt();
        figures += control.getFigure(character);
        System.out.println("Escoja un simbolo para el tercer jugador:");
        System.out.println(control.printPlayersList());
        character = sc.nextInt();
        figures += control.getFigure(character);
        control.updatePlayers(figures);   
        control.asingPosition(figures);
    }

    public void takeTurn(){
        System.out.println(control.printBoard());
        System.out.println("Jugador "+control.getCurrentPlayer().getId()+", es tu turno \n"+
                "1. Tirar dado\n"+
                "2. Ver escaleras y serpientes");
        int option = sc.nextInt();
        if(option == 1){
            int steps = control.trowDice();
            System.out.println("Has sacado: "+steps);
            if(control.movePlayer(steps)){
                if(control.isEnd()){
                    System.out.println(control.getCurrentPlayer().getId()+" Ha llegado a la meta");
                    return;
                }
                control.passTurn();
                takeTurn();

            }else{
                System.out.println("Te has pasado!");
                control.passTurn();
                takeTurn();

            }


        }else if(option==2){
            System.out.println(control.printSnakes());
            takeTurn();
        }
    }

    public void printScores(long startTime){
        long endTime = System.nanoTime();
        long division = (long)600000000*100;
        int elapsedTime= ((int)((endTime-startTime)/division));
        System.out.println(elapsedTime) ;
        int score = (600-elapsedTime)/6;
        control.addScore(score);
        System.out.println(control.printScores());
    }
}