package validaciones;

import mensajesPorConsola.Imprimir;

import java.util.Scanner;

public class Validar {

    /**
     * Recibe un entero que representa la cantidad de opciones entre las que puede elegir un usuario<br>
     * Valída si el valor ingresado es un entero comprendido entre 0 y el rango máximo indicado por cantOpciones
     *
     * @param cantOpciones int que representa la cantidad de opciones entre las que puede elegir el usuario
     * @param sc Scanner
     * @return int
     */
    public static int opcionDeMenu(int cantOpciones, Scanner sc) {

        int opcionElegida = numeroEntero(sc);

        while (opcionElegida < 0 || opcionElegida > (cantOpciones-1)) {
            Imprimir.errorOpcionExcedeRango(cantOpciones);
            opcionElegida = numeroEntero(sc);
        }
        return opcionElegida;
    };

    /**
     * Controla que el siguiente valor ingresado por teclado tenga al menos 2 caracteres y no sea solo espacios
     *
     * @param sc Scanner para leer entrada del usuario
     * @return String válido (mínimo 2 caracteres no vacíos)
     */
    public static String textoNoVacio2Caracteres(Scanner sc) {
        String input;

        do {
            input = sc.nextLine().trim();
            if (input.length() < 2) {
                Imprimir.errorTextoVacio2Caracteres(); // Debería decir algo como "El texto debe tener al menos 2 caracteres no vacíos."
            }
        } while (input.length() < 2);

        return input;
    }

    /**
     * Controla que el siguiente valor ingresado por teclado sea un número entero
     *
     * @param sc
     */
    public static int numeroEntero(Scanner sc){

        while (!sc.hasNextInt()) {
            Imprimir.errorNoEsEntero();
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * Controla que el siguiente valor ingresado por teclado sea un número real
     *
     * @param sc
     */
    public static double numeroReal(Scanner sc){

        while (!sc.hasNextDouble()) {
            Imprimir.errorNoEsReal();
            sc.next();
        }
        return sc.nextDouble();
    }
}
