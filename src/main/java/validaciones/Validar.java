package validaciones;

import clases.Ciudad;
import estructuras.conjuntistas.ArbolAVL;
import mensajesPorConsola.Imprimir;
import utiles.TecladoIn;

import java.util.Scanner;

public class Validar {

    /**
     * Recibe un entero que representa la cantidad de opciones entre las que puede elegir un usuario<br>
     * Valída si el valor ingresado es un entero comprendido entre 0 y el rango máximo indicado por cantOpciones
     *
     * @param cantOpciones int que representa la cantidad de opciones entre las que puede elegir el usuario
     * @return int
     */
    public static int opcionDeMenu(int cantOpciones) {

        int opcionElegida = utiles.TecladoIn.readLineInt();

        while (opcionElegida < 0 || opcionElegida > (cantOpciones-1)) {
            Imprimir.errorOpcionExcedeRango(cantOpciones);
            opcionElegida = utiles.TecladoIn.readLineInt();
        }
        return opcionElegida;
    };


    /**
     * Verifica si la opción elegida por el usuario se encuentra entre el rango de enteros indicado
     *
     * @param rangoInferior Límite inferior (inclusive)
     * @param rangoSuperior Límite superior (inclusive)
     * @return Opción válida dentro del rango
     */
    public static int opcionEntreRango(int rangoInferior, int rangoSuperior) {
        int opcionElegida;

        do {
            opcionElegida = utiles.TecladoIn.readLineInt();
            if (opcionElegida < rangoInferior || opcionElegida > rangoSuperior) {
                Imprimir.errorOpcionFueraDeRango(rangoInferior, rangoSuperior);
            }
        } while (opcionElegida < rangoInferior || opcionElegida > rangoSuperior);

        return opcionElegida;
    }

    /**
     * Controla que el siguiente valor ingresado por teclado tenga al menos 2 caracteres y no sea solo espacios
     *
     * @return String válido (mínimo 2 caracteres no vacíos)
     */
    public static String textoNoVacio2Caracteres() {
        String input;
        do {
            input = utiles.TecladoIn.readLine();
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
     */
    public static int numeroEnteroNoNegativo() {
        int numeroEntero;

        do {
            numeroEntero = utiles.TecladoIn.readLineInt();
            if (numeroEntero < 0) {
                Imprimir.errorNoEsMayorOIgualACero();
            }
        } while (numeroEntero < 0);

        return numeroEntero;
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
     */
    public static double numeroRealNoNegativo() {
        double numeroReal;

        do {
            numeroReal = utiles.TecladoIn.readDouble();
            if (numeroReal < 0) {
                Imprimir.errorNoEsMayorOIgualACero();
            }
        } while (numeroReal < 0);

        return numeroReal;
    }

    /**
     * Busca una ciudad por nombre y la retorna
     *
     * @param arbolCiudades ArbolAVL
     * @return Ciudad
     */
    public static Ciudad existeCiudad(ArbolAVL arbolCiudades){
        Ciudad ciudadEncontrada;
        do {
            String nombreCiudad = Validar.textoNoVacio2Caracteres();
            ciudadEncontrada = (Ciudad) arbolCiudades.getObjeto(nombreCiudad);
            if (ciudadEncontrada == null) {
                Imprimir.errorCiudadNoEncontrada();
            }
        } while (ciudadEncontrada == null);

        return ciudadEncontrada;
    }

    /**
     * Solicita un año válido entre 2000 y 2025 inclusive.
     *
     * @return Año válido
     */
    public static int anio() {
        int anio;

        do {
            anio = utiles.TecladoIn.readInt();
            if (anio < 2000 || anio > 2025) {
                Imprimir.errorAnioInvalido();
            }
        } while (anio < 2000 || anio > 2025);

        return anio;
    }

}
