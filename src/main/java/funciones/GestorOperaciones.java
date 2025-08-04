package funciones;

import clases.ClaveTuberia;
import clases.Tuberia;
import constantes.Rutas;
import controladores.*;
import clases.Ciudad;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import estructuras.lineales.dinamicas.Lista;
import formularios.Formulario;
import manipuladorDeRegistros.ManipuladorDeRegistros;
import mensajesPorConsola.Imprimir;

import java.util.HashMap;
import java.util.Scanner;

public class GestorOperaciones {

    // VARIABLES GLOBALES
    public static final String RUTA_SEED_CONTADOR_CIUDAD = Rutas.RUTA_SEED_CONTADOR_CIUDAD;
    public static final String RUTA_REGISTRO_CONTADOR_CIUDAD = Rutas.RUTA_REGISTRO_CONTADOR_CIUDAD;
    public static final String RUTA_LOG = Rutas.RUTA_LOG;

    /**
     * Hace una carga completa de todas las estructuras con las que trabaja el
     * sistema<br>
     * 1 - Recibe un ArbolAVL y 3 rutas para cargar todos los datos de ciudades<br>
     * 2 - Recibe un HashMap y 1 ruta para cargar todos los datos de las
     * tuberias<br>
     *
     * @param arbolCiudades  ArbolAVL de tipo Ciudad
     * @param rutaCiudades   String de la ruta con los datos de las ciudades
     * @param rutaHabitantes String de la ruta con los datos de los habitantes de
     *                       cada ciudad
     * @param hashTuberias   HashMap de tipo Tuberia
     * @param rutaTuberias   String de la ruta con los datos de las tuberias
     * @param grafoCiudades   Grafo que conecta Ciudades con Tuberias
     */
    public static void cargarEstructurasCompleto(
            ArbolAVL arbolCiudades,
            String rutaCiudades,
            String rutaHabitantes,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            String rutaTuberias,
            Grafo grafoCiudades
    ) {
        CargaEstructuras.cargarArbolAVLCiudades(arbolCiudades, rutaCiudades, rutaHabitantes);
        CargaEstructuras.cargarHashTuberias(hashTuberias, rutaTuberias);
        CargaEstructuras.cargarGrafoCiudades(arbolCiudades, hashTuberias, grafoCiudades);
    }

    /**
     * Copia todos los datos que se encuentran en los SEED hacia los registros dinámicos
     *
     * @param arbolCiudades ArbolAVL
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     * @param grafoCiudades Grafo
     */
    public static void reiniciarRegistrosConSeed(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ){
        int contadorCiudad = CargaEstructuras.recuperarContadorCiudad(RUTA_SEED_CONTADOR_CIUDAD);
        RegistraEstructuras.registrarContadorCiudad(contadorCiudad);
        RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
        RegistraEstructuras.registrarHashMapTuberias(hashTuberias);
    }

    /**
     * Deja todos los registros vacios o con los valores base necesarios
     * para trabajar con las estructuras desde cero.
     */
    public static void vaciarRegistros(){
        ArbolAVL arbolCiudadesVacio = new ArbolAVL();
        HashMap<ClaveTuberia, Tuberia> hashTuberiasVacio = new HashMap<>();
        RegistraEstructuras.registrarContadorCiudad(3000);
        RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudadesVacio);
        RegistraEstructuras.registrarHashMapTuberias(hashTuberiasVacio);
    }

    /**
     * Inserta una nueva ciudad en el ArbolAVL, si la operación es exitosa lo registra y la agrega al grafo
     *
     * @param arbolCiudades ArbolAVL
     * @param grafoCiudades Grafo
     * @param rutaContadorCiudad String
     * @param sc Scanner
     */
    public static void altaCiudad(
            ArbolAVL arbolCiudades,
            Grafo grafoCiudades,
            String rutaContadorCiudad,
            Scanner sc
    ){

        int numCiudad = CargaEstructuras.recuperarContadorCiudad(rutaContadorCiudad);
        Ciudad nuevaCiudad = Formulario.crearNuevaCiudad(numCiudad, sc);

        boolean exito = ControladorCiudad.alta(arbolCiudades, grafoCiudades, nuevaCiudad);

        if (exito) {
            RegistraEstructuras.registrarContadorCiudad((numCiudad + 1));
            RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
            String titulo = "ÉXITO al cargar una nueva ciudad en el sistema";
            String contenido = nuevaCiudad.toString();
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.exitoAltaCiudad();
        } else {
            String titulo = "ERROR al intentar cargar una nueva ciudad en el sistema";
            String contenido = nuevaCiudad.toString();
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.errorAltaCiudad();
        }

    }

    public static void bajaCiudad(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades,
            Scanner sc){

        String nombreCiudad = Formulario.nombreStringValido(sc);

        boolean exito = ControladorCiudad.baja(
                arbolCiudades,
                grafoCiudades,
                hashTuberias,
                nombreCiudad);

        if (exito) {
            RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
            RegistraEstructuras.registrarHashMapTuberias(hashTuberias);
            String titulo = "ÉXITO al eliminar una ciudad del sistema";
            String contenido = "Se elimina la ciudad " + nombreCiudad + " del sistema," +
                    " junto con todas las tuberías que la conectaban";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.exitoBajaCiudad();
        } else {
            String titulo = "ERROR al intentar eliminar una ciudad del sistema";
            String contenido = "La ciudad " + nombreCiudad + " no existe dentro el sistema,";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.errorBajaCiudad();
        }
    }

    public static void modificarCiudad(
            ArbolAVL arbolCiudades,
            Scanner sc){

    }


}
