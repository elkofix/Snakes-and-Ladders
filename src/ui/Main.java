package ui;

import java.util.Scanner;

public class Main{

    private static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        startMenu();
    }

    public static void startMenu(){

            System.out.println("\n Bienvenido al juego  y Serpientes... \n\n Seleccione una opcion: \n [1] Jugar \n [2] Salir");
            
            if(sc.nextInt() == 1){

                System.out.println("\n Digite el numero de filas del tablero: ");
                int rows = sc.nextInt();

                System.out.println("\n Digite el numero de columnas del tablero: ");
                int cols = sc.nextInt();

                System.out.println("\n Digite el numero de serpientes: ");
                int snakes = sc.nextInt();

                System.out.println("\n Digite el numero de escaleras: ");
                int stairs = sc.nextInt();

                
                startMenu();
            }
            
        
        return;
    }
}