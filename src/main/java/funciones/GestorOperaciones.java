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
     * Copia todos los datos que se encuentran en los SEED hacia los registros dinámicos
     *
     * @param arbolCiudades ArbolAVL
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     * @param grafoCiudades Grafo
     */
    public static void reiniciarRegistros(
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
     * Inserta una nueva ciudad en el ArbolAVL, si la operación es exitosa lo registra y la agrega al grafo
     *
     * @param arbolCiudades ArbolAVL
     * @param grafoCiudades Grafo
     * @param rutaContadorCiudad String
     * @param sc Scanner
     */
    public static void insertarUnaNuevaCiudad(
            ArbolAVL arbolCiudades,
            Grafo grafoCiudades,
            String rutaContadorCiudad,
            Scanner sc
    ){

        int numCiudad = CargaEstructuras.recuperarContadorCiudad(rutaContadorCiudad);
        Ciudad nuevaCiudad = Formulario.crearNuevaCiudad(sc, numCiudad);

        boolean exito = ControladorCiudad.alta(arbolCiudades, grafoCiudades, nuevaCiudad);

        if (exito) {
            RegistraEstructuras.registrarContadorCiudad((numCiudad + 1));
            RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
            String titulo = "ÉXITO al cargar una nueva ciudad en el sistema";
            String contenido = nuevaCiudad.toString();
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.exitoIngresarCiudad();
        } else {
            String titulo = "ERROR al intentar cargar una nueva ciudad en el sistema";
            String contenido = nuevaCiudad.toString();
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.errorIngresarCiudad();
        }

    }


}
