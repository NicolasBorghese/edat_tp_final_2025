package main;

import java.util.Scanner;

import constantes.Rutas;
import clases.ClaveTuberia;
import clases.Tuberia;
import estructuras.grafos.Grafo;
import funciones.GestorOperaciones;
import validaciones.Validar;
import funciones.CargaEstructuras;
import mensajesPorConsola.Imprimir;
import estructuras.conjuntistas.ArbolAVL;
import java.util.HashMap;

public class TransporteAgua {

    // CONSTANTES Y VARIABLES GLOBALES

    // RUTAS DE REGISTROS DINÁMICOS
    public static final String RUTA_REGISTRO_CIUDAD = Rutas.RUTA_REGISTRO_CIUDAD;
    public static final String RUTA_REGISTRO_CIUDAD_HABITANTES = Rutas.RUTA_REGISTRO_CIUDAD_HABITANTES;
    public static final String RUTA_REGISTRO_CONTADOR_CIUDAD = Rutas.RUTA_REGISTRO_CONTADOR_CIUDAD;
    public static final String RUTA_REGISTRO_TUBERIA = Rutas.RUTA_REGISTRO_TUBERIA;
    // RUTAS DE REGISTROS PARA CONTAR CON UNA CARGA INICIAL
    public static final String RUTA_SEED_CIUDAD = Rutas.RUTA_SEED_CIUDAD;
    public static final String RUTA_SEED_CIUDAD_HABITANTES = Rutas.RUTA_SEED_CIUDAD_HABITANTES;
    public static final String RUTA_SEED_CONTADOR_CIUDAD = Rutas.RUTA_SEED_CONTADOR_CIUDAD;
    public static final String RUTA_SEED_TUBERIA = Rutas.RUTA_SEED_TUBERIA;

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
        Grafo grafoCiudades = new Grafo();

        Imprimir.portada();

        // Controlar carga inicial de datos
        opcionElegida = mostrarMenuDeOpciones("cargaDeDatosInicial", sc);
        controlCargaDeDatosInicial(opcionElegida, arbolCiudades, hashTuberias, grafoCiudades);

        // Mostrar menú principal
        while (opcionElegida != 0) {
            opcionElegida = mostrarMenuDeOpciones("menuPrincipal", sc);
            controlOperacionesMenuPrincipal(opcionElegida, arbolCiudades, hashTuberias, grafoCiudades, sc);
        }

        System.out.println();
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

    public static void detenerEjecucion(Scanner sc){
        String presionarEnter;

        System.out.println();

        sc.nextLine();
        do {
            // Mensaje de parada para leer los resultados
            Imprimir.continuarEjecucion();
            // Obligación de ingresar un valor para continuar la ejecución del código
            presionarEnter = sc.nextLine();

        }while (!presionarEnter.equals(""));

        System.out.println();
    }

    /**
     * Controla las operaciones de la carga de datos inicial
     * @param opcion
     */
    public static void  controlCargaDeDatosInicial(
            int opcion,
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ) {

        switch(opcion) {
            case 0:// 0. Salir.
                break;
            case 1:// 1. Utilizar los últimos registros de datos
                CargaEstructuras.cargarEstructurasCompleto(
                        arbolCiudades,
                        RUTA_REGISTRO_CIUDAD,
                        RUTA_REGISTRO_CIUDAD_HABITANTES,
                        hashTuberias,
                        RUTA_REGISTRO_TUBERIA,
                        grafoCiudades
                );
                break;
            case 2:// 2. Utilizar la carga inicial de datos
                CargaEstructuras.cargarEstructurasCompleto(
                        arbolCiudades,
                        RUTA_SEED_CIUDAD,
                        RUTA_SEED_CIUDAD_HABITANTES,
                        hashTuberias,
                        RUTA_SEED_TUBERIA,
                        grafoCiudades
                        );
                GestorOperaciones.reiniciarRegistros(arbolCiudades, hashTuberias,  grafoCiudades);
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
    public static void  controlOperacionesMenuPrincipal(
            int opcion,
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades,
            Scanner sc
    ) {

        switch(opcion) {
            case 0:// 0. Salir.
                break;
            case 1:// 1. Agregar una ciudad.
                GestorOperaciones.insertarUnaNuevaCiudad(arbolCiudades, grafoCiudades, RUTA_REGISTRO_CONTADOR_CIUDAD, sc);
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
                System.out.println(arbolCiudades.toString());
                break;
            case 9:// 9. Ver el Grafo Etiquetado.
                System.out.println(grafoCiudades.toString());
                break;
            case 10:// 10. Ver el HashMap.
                Imprimir.hashMapTuberias(hashTuberias);
                break;
            default:
                break;
        }

        if (opcion != 0) {
            detenerEjecucion(sc);
        }
    }
}