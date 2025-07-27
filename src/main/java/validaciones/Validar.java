package validaciones;

import mensajesPorConsola.Imprimir;

import java.util.Scanner;

public class Validar {

    /**
     * Recibe un entero que representa la cantidad de opciones entre las que puede elegir un usuario<br>
     * Valída si el valor ingresado es un entero comprendido entre 0 y el rango máximo indicado por cantOpciones
     * @param cantOpciones int que representa la cantidad de opciones entre las que puede elegir el usuario
     * @param sc Scanner
     * @return int
     */
    public static int opcionDeMenu(int cantOpciones, Scanner sc) {

        int opcionElegida;

        while (!sc.hasNextInt()) {
            Imprimir.errorOpcionNoEsEntero();
            sc.next();
        }

        opcionElegida = sc.nextInt();

        while (opcionElegida < 0 || opcionElegida > (cantOpciones-1)) {
            Imprimir.errorOpcionExcedeRango(cantOpciones);
            while (!sc.hasNextInt()) {
                Imprimir.errorOpcionNoEsEntero();
                sc.next();
            }
            opcionElegida = sc.nextInt();
        }
        return opcionElegida;
    };

}
