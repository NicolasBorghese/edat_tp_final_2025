package main;

import java.util.Scanner;
import mensajesPorConsola.Imprimir;

public class TransporteAgua {

    /**
     * 1 - Carga los datos con los que va a trabajar el usuario según su elección <br>
     * 2 - Muestra las operaciones que puede realizar el usuario en un menú dinámico <br>
     * 3 - Se encarga de ejecutar las operaciones que el usuario indique y las funciones adicionales que eso implique
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        int cantOpciones;

        do {
            cantOpciones = Imprimir.menuPrincipal("menuPrincipal");

            while (!sc.hasNextInt()) {
                Imprimir.errorOpcionNoEsEntero();
                sc.next();
            }

            opcion = sc.nextInt();

            while (opcion < 0 || opcion > cantOpciones) {
                Imprimir.errorOpcionExcedeRango(cantOpciones);
                while (!sc.hasNextInt()) {
                    Imprimir.errorOpcionNoEsEntero();
                    sc.next();
                }
                opcion = sc.nextInt();
            }

            //ACA VA UN SWITCH PARA CONTROLAR LA OPERACIÓN ELEGIDA POR EL USUARIO

        } while (opcion != 0);
        Imprimir.finDeEjecucion();
        sc.close();
    }
}

