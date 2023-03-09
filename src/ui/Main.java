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

                System.out.println("\n Digite el numero de filas del tablero: ");
                int rows = sc.nextInt();

                System.out.println("\n Digite el numero de columnas del tablero: ");
                int cols = sc.nextInt();

                System.out.println(control.generateTable(rows*cols, rows, cols));
                control.generateTable1(rows*cols, rows, cols);

                System.out.println("\n Digite el numero de serpientes: ");
                int snakes = sc.nextInt();

                System.out.println("\n Digite el numero de escaleras: ");
                int stairs = sc.nextInt();
                System.out.println(control.addSnakenLadders(snakes, stairs));
                System.out.println("Escoja un simbolo para el primer jugador:");
                System.out.println(control.printPlayersList());
                int character = sc.nextInt();
                System.out.println(control.updatePlayer1(character));    
                System.out.println("Escoja un simbolo para el segundo jugador:");
                System.out.println(control.printPlayersList());
                character = sc.nextInt();
                System.out.println(control.updatePlayer2(character));    
                System.out.println("Escoja un simbolo para el tercer jugador:");
                System.out.println(control.printPlayersList());
                character = sc.nextInt();
                System.out.println(control.updatePlayer3(character)); 
                control.addPlayers();   
                System.out.println(control.printBoard());

            }else{
                if(option==0){
                    System.out.println("Salida del programa");
                }else{
                    System.out.println("Opcion invalida");
                    startMenu();
                }
            }
            
        
        return;
    }

    public void printPlayersList(String list){
        printPlayersList(0, list);

    }

    public void printPlayersList(int counter, String list){
        if(counter==list.length()-1){
            return;
        }
        System.out.println(counter+1+". "+list.charAt(counter));
        printPlayersList(++counter, list);
        
    }
}