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
        int opcion;

        Imprimir.portada();

        // Controlar carga inicial de datos
        opcion = controlarOpcionDeMenu("cargaDeDatosInicial", sc);
        controlCargaDeDatosInicial(opcion);

        // Mostrar menú principal
        while (opcion != 0) {
            opcion = controlarOpcionDeMenu("menuPrincipal", sc);
            controlOperacionesMenuPrincipal(opcion);
        }

        Imprimir.finDeEjecucion();
        sc.close();
    }

    /**
     * Recibe un nombre de menú, carga sus opciones y devuelve la opción elegida si es válida
     * @param nombreMenu - nombre del menú que hay que cargar
     * @return int
     */
    public static int controlarOpcionDeMenu(String nombreMenu, Scanner sc) {

        int opcion;
        int cantOpciones;

        cantOpciones = Imprimir.menuDeOpciones(nombreMenu);

        while (!sc.hasNextInt()) {
            Imprimir.errorOpcionNoEsEntero();
            sc.next();
        }

        opcion = sc.nextInt();

        while (opcion < 0 || opcion > (cantOpciones-1)) {
            Imprimir.errorOpcionExcedeRango(cantOpciones);
            while (!sc.hasNextInt()) {
                Imprimir.errorOpcionNoEsEntero();
                sc.next();
            }
            opcion = sc.nextInt();
        }
        return opcion;
    };

    /**
     * Controla las operaciones de la carga de datos inicial
     * @param opcion
     */
    public static void  controlCargaDeDatosInicial(int opcion) {
        switch(opcion) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    /**
     * Controla las operaciones del menú principal
     * @param opcion
     */
    public static void  controlOperacionesMenuPrincipal(int opcion) {
        switch(opcion) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 0:
                break;
            default:
                break;
        }
    }
}

