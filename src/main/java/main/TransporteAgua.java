package main;

import java.util.Scanner;

import clases.ClaveTuberia;
import clases.Tuberia;
import funciones.VisualizaEstructuras;
import validaciones.Validar;
import funciones.CargaEstructuras;
import mensajesPorConsola.Imprimir;
import estructuras.conjuntistas.ArbolAVL;
import java.util.HashMap;

public class TransporteAgua {

    /**
     * 1 - Carga los datos con los que va a trabajar el usuario según su elección <br>
     * 2 - Muestra las operaciones que puede realizar el usuario en un menú dinámico <br>
     * 3 - Se encarga de ejecutar las operaciones que el usuario indique y las funciones adicionales que eso implique
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcionElegida;
        ArbolAVL arbolCiudades = new ArbolAVL();
        HashMap<ClaveTuberia, Tuberia> hashTuberias = new HashMap<>();

        Imprimir.portada();

        // Controlar carga inicial de datos
        opcionElegida = mostrarMenuDeOpciones("cargaDeDatosInicial", sc);
        controlCargaDeDatosInicial(opcionElegida, arbolCiudades, hashTuberias);

        // Mostrar menú principal
        while (opcionElegida != 0) {
            opcionElegida = mostrarMenuDeOpciones("menuPrincipal", sc);
            controlOperacionesMenuPrincipal(opcionElegida, arbolCiudades, hashTuberias);
        }

        Imprimir.finDeEjecucion();
        sc.close();
    }

    /**
     * Recibe un nombre de menú, carga sus opciones y devuelve la opción elegida si es válida
     * @param nombreMenu - nombre del menú que hay que cargar
     * @return int
     */
    public static int mostrarMenuDeOpciones(String nombreMenu, Scanner sc) {

        int opcionElegida;
        int cantOpciones;

        cantOpciones = Imprimir.menuDeOpciones(nombreMenu);
        opcionElegida = Validar.opcionDeMenu(cantOpciones, sc);

        return opcionElegida;
    };

    /**
     * Controla las operaciones de la carga de datos inicial
     * @param opcion
     */
    public static void  controlCargaDeDatosInicial(int opcion,  ArbolAVL arbolCiudades,  HashMap<ClaveTuberia, Tuberia> hashTuberias) {
        String rutaCiudades = "";
        String rutaCiudadHabitantes = "";
        String rutaTuberias = "";

        switch(opcion) {
            case 0:// 0. Salir.
                break;
            case 1:// 1. Utilizar los últimos registros de datos
                rutaCiudades = "src/main/java/datos/seed_ciudad.txt";
                rutaCiudadHabitantes = "src/main/java/datos/seed_ciudad_habitantes.txt";
                rutaTuberias = "src/main/java/datos/registro_tuberia.txt";
                CargaEstructuras.cargarEstructurasCompleto(arbolCiudades, rutaCiudades,  rutaCiudadHabitantes, hashTuberias, rutaTuberias);
                break;
            case 2:// 2. Utilizar la carga inicial de datos
                rutaCiudades = "src/main/java/datos/registro_ciudad.txt";
                rutaCiudadHabitantes = "src/main/java/datos/registro_ciudad_habitantes.txt";
                rutaTuberias = "src/main/java/datos/registro_tuberia.txt";
                CargaEstructuras.cargarEstructurasCompleto(arbolCiudades, rutaCiudades,  rutaCiudadHabitantes, hashTuberias, rutaTuberias);
                break;
            case 3:// 3. Iniciar programa sin cargar datos
                break;
            default:
                break;
        }
    }

    /**
     * Controla las operaciones del menú principal
     * @param opcion
     */
    public static void  controlOperacionesMenuPrincipal(int opcion, ArbolAVL arbolCiudades,  HashMap<ClaveTuberia, Tuberia> hashTuberias) {

        String rutaCiudades = "";
        String rutaCiudadHabitantes = "";
        String rutaTuberias = "";
        String rutaContadorCiudad = "src/main/java/datos/contador_ciudad.txt";

        switch(opcion) {
            case 0:// 0. Salir.
                break;
            case 1:// 1. Agregar una ciudad.
                break;
            case 2:// 2. Dar de baja una ciudad.
                break;
            case 3:// 3. Modificar una ciudad.
                break;
            case 4:// 4. Agregar una tubería.
                break;
            case 5:// 5. Dar de baja una tubería.
                break;
            case 6:// 6. Modificar una tubería.
                break;
            case 7:// 7. Ver el Ranking de ciudades.
                break;
            case 8:// 8. Ver el árbol AVL.
                VisualizaEstructuras.visualizarArbolAVLCiudades(arbolCiudades);
                break;
            case 9:// 9. Ver el Grafo Etiquetado.
                break;
            case 10:// 10. Ver el HashMap.
                VisualizaEstructuras.visualizarHashMapTuberias(hashTuberias);
                break;
            default:
                break;
        }
    }
}

