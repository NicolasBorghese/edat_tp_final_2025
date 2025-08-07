package main;

import clases.ClaveTuberia;
import clases.Tuberia;
import constantes.Rutas;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import funciones.GestorOperaciones;
import mensajesPorConsola.Imprimir;
import validaciones.Validar;

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

        int opcionElegida;
        ArbolAVL arbolCiudades = new ArbolAVL();
        HashMap<ClaveTuberia, Tuberia> hashTuberias = new HashMap<>();
        Grafo grafoCiudades = new Grafo();

        Imprimir.portada();

        // Controlar carga inicial de datos
        opcionElegida = mostrarMenuDeOpciones("cargaDeDatosInicial");
        controlCargaDeDatosInicial(opcionElegida, arbolCiudades, hashTuberias, grafoCiudades);

        // Mostrar menú principal
        while (opcionElegida != 0) {
            opcionElegida = mostrarMenuDeOpciones("menuPrincipal");
            controlOperacionesMenuPrincipal(opcionElegida, arbolCiudades, hashTuberias, grafoCiudades);
        }

        System.out.println();
        Imprimir.finDeEjecucion();
    }

    /**
     * Recibe un nombre de menú, carga sus opciones y devuelve la opción elegida si es válida
     * @param nombreMenu - nombre del menú que hay que cargar
     * @return int
     */
    public static int mostrarMenuDeOpciones(String nombreMenu) {

        int opcionElegida;
        int cantOpciones;

        cantOpciones = Imprimir.menuDeOpciones(nombreMenu);
        opcionElegida = Validar.opcionDeMenu(cantOpciones);

        return opcionElegida;
    };

    public static void detenerEjecucion(){
        String presionarEnter;

        do {
            // Mensaje de parada para leer los resultados
            Imprimir.continuarEjecucion();
            // Obligación de ingresar un valor para continuar la ejecución del código
            presionarEnter = utiles.TecladoIn.readLine();

        }while (!presionarEnter.equals(""));

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
                GestorOperaciones.cargarEstructurasCompleto(
                        arbolCiudades,
                        RUTA_REGISTRO_CIUDAD,
                        RUTA_REGISTRO_CIUDAD_HABITANTES,
                        hashTuberias,
                        RUTA_REGISTRO_TUBERIA,
                        grafoCiudades
                );
                Imprimir.mensajeExito("Éxito al cargar las estructuras con los últimos registros.\n");
                break;
            case 2:// 2. Utilizar la carga inicial de datos
                GestorOperaciones.cargarEstructurasCompleto(
                        arbolCiudades,
                        RUTA_SEED_CIUDAD,
                        RUTA_SEED_CIUDAD_HABITANTES,
                        hashTuberias,
                        RUTA_SEED_TUBERIA,
                        grafoCiudades
                        );
                GestorOperaciones.reiniciarRegistrosConSeed(arbolCiudades, hashTuberias);
                Imprimir.mensajeExito("Éxito al cargar las estructuras con los los registros de base.\n");
                break;
            case 3:// 3. Iniciar programa con registros vacios
                GestorOperaciones.vaciarRegistros();
                Imprimir.mensajeExito("Éxito al vaciar los registros.\n");
                break;
            default:
                break;
        }

        if (opcion != 0) {
            detenerEjecucion();
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
            Grafo grafoCiudades
    ) {

        switch(opcion) {
            case 0:// 0. Salir.
                break;
            case 1:// 1. Ciudad - Alta
                GestorOperaciones.altaCiudad(arbolCiudades, grafoCiudades, RUTA_REGISTRO_CONTADOR_CIUDAD);
                break;
            case 2:// 2. Ciudad - Baja
                GestorOperaciones.bajaCiudad(arbolCiudades, hashTuberias, grafoCiudades);
                break;
            case 3:// 3. Ciudad - Modificar
                GestorOperaciones.modificarCiudad(arbolCiudades);
                break;
            case 4:// 4. Ciudad - Actualizar población por fecha.
                GestorOperaciones.modificarPoblacionPorFechaCiudad(arbolCiudades);
                break;
            case 5:// 5. Tubería - Alta
                GestorOperaciones.altaTuberia(arbolCiudades, hashTuberias, grafoCiudades);
                break;
            case 6:// 6. Tubería - Baja
                GestorOperaciones.bajaTuberia(arbolCiudades, hashTuberias, grafoCiudades);
                break;
            case 7:// 7. Tubería - Modificar
                GestorOperaciones.modificarTuberia(arbolCiudades, hashTuberias);
                break;
            case 8:// 8. Cantidad habitantes y agua consumida por fecha
                GestorOperaciones.cantidadHabitantesYAguaConsumidaPorFecha(arbolCiudades);
                break;
            case 9:// 9. Consumo de agua entre rango de nombres
                GestorOperaciones.consumoDeAguaEntreRangoDeNombres(arbolCiudades);
                break;
            case 10:// 10. Camino de A a B con caudal pleno mínimo
                GestorOperaciones.caminoDeAaBconCaudalPlenoMinimo(arbolCiudades, hashTuberias, grafoCiudades);
                break;
            case 11:// 11. Camino de A a B con menor recorrido
                GestorOperaciones.caminoDeAaBconMenorRecorrido(arbolCiudades, hashTuberias, grafoCiudades);
                break;
            case 12:// 12. Ciudades ordenadas por consumo de agua
                GestorOperaciones.ciudadesOrdenadasPorConsumoDeAgua(arbolCiudades);
                break;
            case 13:// 13. Visualizar arbolAVL de ciudades
                GestorOperaciones.visualizarEstructuraArbolDeCiudades(arbolCiudades);
                break;
            case 14:// 14. Visualizar HashMap de tuberías
                GestorOperaciones.visualizarEstructuraHashMapTuberias(hashTuberias);
                break;
            case 15:// 15. Visualizar Grafo (Ciudad-Tubería)
                GestorOperaciones.visualizarEstructuraGrafo(grafoCiudades);
                break;
            default:
                break;
        }

        if (opcion != 0) {
            detenerEjecucion();
        }
    }
}