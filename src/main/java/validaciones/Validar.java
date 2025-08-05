package validaciones;

import clases.Ciudad;
import estructuras.conjuntistas.ArbolAVL;
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
     * Verifica si la opción elegida por el usuario se encuentra entre el rango de enteros indicado
     *
     * @param rangoInferior Límite inferior (inclusive)
     * @param rangoSuperior Límite superior (inclusive)
     * @param sc Scanner para leer entrada del usuario
     * @return Opción válida dentro del rango
     */
    public static int opcionEntreRango(int rangoInferior, int rangoSuperior, Scanner sc) {
        int opcionElegida;

        do {
            opcionElegida = numeroEntero(sc);
            if (opcionElegida < rangoInferior || opcionElegida > rangoSuperior) {
                Imprimir.errorOpcionFueraDeRango(rangoInferior, rangoSuperior);
            }
        } while (opcionElegida < rangoInferior || opcionElegida > rangoSuperior);

        return opcionElegida;
    }

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
                Imprimir.errorTextoVacio2Caracteres();
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
     * Controla que el siguiente valor ingresado por teclado sea un número entero mayor o igual a cero
     *
     * @param sc Scanner
     */
    public static int numeroEnteroNoNegativo(Scanner sc) {
        int input;

        do {

            if (sc.hasNextInt()) {
                input = sc.nextInt();
                Imprimir.errorNoEsReal();
            } else {
                sc.next();
                input = -1;
            }
            if (sc.hasNextInt() && input < 0) {
                Imprimir.errorNoEsMayorOIgualACero();
            }
        } while (input < 0);

        return input;
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

    /**
     * Controla que el siguiente valor ingresado por teclado sea un número real mayor o igual a cero
     *
     * @param sc Scanner
     */
    public static double numeroRealNoNegativo(Scanner sc) {
        double input = -1;

        do {
            if (sc.hasNextDouble()) {
                input = sc.nextDouble();
                if (input < 0) {
                    Imprimir.errorNoEsMayorOIgualACero();
                }
            } else {
                Imprimir.errorNoEsReal();  // Solo mostramos error si no es un número válido
                sc.next(); // Consumir valor inválido
            }
        } while (input < 0);

        return input;
    }

    /**
     * Busca una ciudad por nombre y la retorna
     *
     * @param arbolCiudades ArbolAVL
     * @param sc Scanner
     * @return Ciudad
     */
    public static Ciudad existeCiudad(ArbolAVL arbolCiudades, Scanner sc){
        Ciudad ciudadEncontrada;
        do {

            String nombreCiudad = Validar.textoNoVacio2Caracteres(sc);
            ciudadEncontrada = (Ciudad) arbolCiudades.getObjeto(nombreCiudad);
            if(ciudadEncontrada == null){
                Imprimir.errorCiudadNoEncontrada();
            }
        } while (ciudadEncontrada == null);

        return ciudadEncontrada;
    }

    /**
     * Solicita un año válido entre 2000 y 2025 inclusive.
     *
     * @param sc Scanner
     * @return Año válido
     */
    public static int anio(Scanner sc) {
        int input = -1;

        do {
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input < 2000 || input > 2025) {
                    Imprimir.errorAnioInvalido();
                    input = -1; // fuerza repetir si está fuera de rango
                }
            } else {
                Imprimir.errorNoEsEntero(); // solo mostramos error si no es un entero
                sc.next(); // consumir la entrada inválida
            }
        } while (input < 0);

        return input;
    }


}
