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
            
            if(sc.nextInt() == 1){

                System.out.println("\n Digite el numero de filas del tablero: ");
                int rows = sc.nextInt();

                System.out.println("\n Digite el numero de columnas del tablero: ");
                int cols = sc.nextInt();

                System.out.println(control.generateTable(rows*cols, rows, cols));

                System.out.println("\n Digite el numero de serpientes: ");
                int snakes = sc.nextInt();

                System.out.println("\n Digite el numero de escaleras: ");
                int stairs = sc.nextInt();
                System.out.println(control.addSnakenLadders(snakes, stairs));
                startMenu();
            }
            
        
        return;
    }
}