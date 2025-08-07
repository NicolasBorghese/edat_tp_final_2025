package funciones;

import clases.ClaveTuberia;
import clases.Tuberia;
import constantes.Rutas;
import consultas.ConsultasCiudad;
import consultas.ConsultasTuberia;
import controladores.*;
import clases.Ciudad;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import estructuras.lineales.dinamicas.Lista;
import formularios.Formulario;
import mensajesPorConsola.Imprimir;
import validaciones.Validar;

import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Scanner;

public class GestorOperaciones {

    // VARIABLES GLOBALES
    public static final String RUTA_SEED_CONTADOR_CIUDAD = Rutas.RUTA_SEED_CONTADOR_CIUDAD;

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
     * @param grafoCiudades  Grafo que conecta Ciudades con Tuberias
     */
    public static void cargarEstructurasCompleto(
            ArbolAVL arbolCiudades,
            String rutaCiudades,
            String rutaHabitantes,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            String rutaTuberias,
            Grafo grafoCiudades) {
        CargaEstructuras.cargarArbolAVLCiudades(arbolCiudades, rutaCiudades, rutaHabitantes);
        CargaEstructuras.cargarHashTuberias(hashTuberias, rutaTuberias);
        CargaEstructuras.cargarGrafoCiudades(arbolCiudades, hashTuberias, grafoCiudades);
    }

    /**
     * Copia todos los datos que se encuentran en los SEED hacia los registros
     * dinámicos
     *
     * @param arbolCiudades ArbolAVL
     * @param hashTuberias  HashMap<ClaveTuberia, Tuberia>
     */
    public static void reiniciarRegistrosConSeed(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias) {
        int contadorCiudad = CargaEstructuras.recuperarContadorCiudad(RUTA_SEED_CONTADOR_CIUDAD);
        RegistraEstructuras.registrarContadorCiudad(contadorCiudad);
        RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
        RegistraEstructuras.registrarHashMapTuberias(hashTuberias);
    }

    /**
     * Deja todos los registros vacios o con los valores base necesarios
     * para trabajar con las estructuras desde cero.
     */
    public static void vaciarRegistros() {
        ArbolAVL arbolCiudadesVacio = new ArbolAVL();
        HashMap<ClaveTuberia, Tuberia> hashTuberiasVacio = new HashMap<>();
        RegistraEstructuras.registrarContadorCiudad(3000);
        RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudadesVacio);
        RegistraEstructuras.registrarHashMapTuberias(hashTuberiasVacio);
    }

    /**
     * Inserta una nueva ciudad en el ArbolAVL, si la operación es exitosa lo
     * registra y la agrega al grafo
     *
     * @param arbolCiudades      ArbolAVL
     * @param grafoCiudades      Grafo
     * @param rutaContadorCiudad String
     */
    public static void altaCiudad(
            ArbolAVL arbolCiudades,
            Grafo grafoCiudades,
            String rutaContadorCiudad
    ) {
        int numCiudad = CargaEstructuras.recuperarContadorCiudad(rutaContadorCiudad);
        Ciudad nuevaCiudad = Formulario.altaCiudad(numCiudad);

        boolean exito = ControladorCiudad.alta(arbolCiudades, grafoCiudades, nuevaCiudad);

        if (exito) {
            RegistraEstructuras.registrarContadorCiudad((numCiudad + 1));
            RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
            String titulo = "ÉXITO al cargar una nueva ciudad en el sistema";
            String contenido = nuevaCiudad.toString();
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeExito("La ciudad se cargo con éxito en el sistema.\n");
        } else {
            String titulo = "ERROR al intentar cargar una nueva ciudad en el sistema";
            String contenido = nuevaCiudad.toString();
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeError("No fue posible cargar la ciudad, nombre duplicado.\n");
        }

    }

    /**
     * Función que da de baja una ciudad del sistema
     *
     * @param arbolCiudades
     * @param hashTuberias
     * @param grafoCiudades
     */
    public static void bajaCiudad(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ) {
        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        String nombreCiudad = Formulario.bajaCiudad();

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
            Imprimir.mensajeExito("La ciudad se dio de baja con éxito del sistema.\n");
        } else {
            String titulo = "ERROR al intentar eliminar una ciudad del sistema";
            String contenido = "La ciudad " + nombreCiudad + " no existe dentro el sistema.";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeError("No fue posible dar de baja la ciudad, nombre inexistente en el sistema.\n");
        }
    }

    /**
     * Función para modificar los datos de una ciudad
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void modificarCiudad(
            ArbolAVL arbolCiudades) {
        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        String nombreCiudad = Formulario.modificarCiudadNombreCiudad();
        double[] datosCiudad = Formulario.modificarCiudadDatos();

        boolean intentaModificar = false;
        boolean exito = false;

        if (datosCiudad[0] != -1) {
            intentaModificar = true;
            exito = ControladorCiudad.modificarSuperficie(arbolCiudades, nombreCiudad, datosCiudad[0]);
        }
        if (datosCiudad[1] != -1) {
            intentaModificar = true;
            exito = ControladorCiudad.modificarConsumoDiarioProm(arbolCiudades, nombreCiudad, datosCiudad[1]);
        }

        if (intentaModificar) {

            if (exito) {
                RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
                String titulo = "ÉXITO al modificar la ciudad: " + nombreCiudad;
                String contenido = "Nuevo Estado de la ciudad:\n";
                if (datosCiudad[0] != -1) {
                    contenido += "Nuevo valor para superficie: " + datosCiudad[0];
                }
                if (datosCiudad[0] != -1 && datosCiudad[1] != -1) {
                    contenido += "\n";
                }
                if (datosCiudad[0] != -1) {
                    contenido += "Nuevo valor para consumoDiarioProm: " + datosCiudad[0];
                }
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeExito("La ciudad fue modificada con éxito.\n");
            } else {
                String titulo = "ERROR al intentar modificar la ciudad: " + nombreCiudad;
                String contenido = "La ciudad " + nombreCiudad + " no existe dentro el sistema";
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeError("No fue posible modificar la ciudad, nombre inexistente en el sistema.\n");
            }

        }
    }

    /**
     * Función para modificar la población de una ciudad en una fecha determinada
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void modificarPoblacionPorFechaCiudad(
            ArbolAVL arbolCiudades
    ) {
        int anio = Formulario.generarAnioValido();
        int mes = Formulario.generarMesValido();
        String nombreCiudad = Formulario.nombreCiudadValido();
        YearMonth fecha = YearMonth.of(anio, mes);
        int cantPoblacion = Formulario.cantidadPoblacionValido();

        boolean exito = ControladorCiudad.modificarPoblacionPorFecha(arbolCiudades, nombreCiudad, fecha, cantPoblacion);

        if (exito) {
            RegistraEstructuras.registrarArbolAVLCiudades(arbolCiudades);
            String titulo = "ÉXITO al modificar la cantidad de habitantes por fecha de la ciudad: " + nombreCiudad;
            String contenido = "La nueva cantidad de habitantes para la fecha " + fecha + " es: " + cantPoblacion;
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeExito("Población en fecha indicada actualizada con éxito.\n");
        } else {
            String titulo = "ERROR al intentar la cantidad de habitantes por fecha de la ciudad: " + nombreCiudad;
            String contenido = "La ciudad " + nombreCiudad + " no existe dentro el sistema";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeError("La población no pudo ser actualizada en la fecha indicada, no existe la ciudad.\n");
        }

    }

    /**
     * Función para dar de alta una tubería
     *
     * @param arbolCiudades ArbolAVL
     * @param hashTuberias  HashMap<ClaveTuberia, Tuberia>
     * @param grafoCiudades Grafo
     */
    public static void altaTuberia(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ) {
        int cantidadCiudades = (arbolCiudades.listar()).longitud();

        if (cantidadCiudades >= 2) {

            Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

            Ciudad ciudadOrigen = Formulario.existeCiudadOrigen(arbolCiudades);
            Ciudad ciudadDestino = Formulario.existeCiudadDestino(arbolCiudades, ciudadOrigen);
            ClaveTuberia claveTub = new ClaveTuberia(ciudadOrigen.getNomenclatura(), ciudadDestino.getNomenclatura());

            if (!hashTuberias.containsKey(claveTub)) {

                Tuberia nuevaTuberia = Formulario.altaTuberia(ciudadOrigen, ciudadDestino);

                boolean exito = ControladorTuberia.alta(hashTuberias, grafoCiudades, nuevaTuberia);

                if (exito) {
                    RegistraEstructuras.registrarHashMapTuberias(hashTuberias);
                    String titulo = "ÉXITO al cargar una nueva tubería en el sistema";
                    String contenido = nuevaTuberia.toString();
                    RegistraEstructuras.registrarComoLog(titulo, contenido);
                    Imprimir.mensajeExito("La tubería se cargo con éxito en el sistema.\n");
                } else {
                    String titulo = "ERROR al intentar cargar una nueva tubería en el sistema";
                    String contenido = nuevaTuberia.toString();
                    RegistraEstructuras.registrarComoLog(titulo, contenido);
                    Imprimir.mensajeError("No fue posible cargar la tubería en el sistema.\n");
                }
            } else {
                String titulo = "ERROR al intentar cargar una nueva tubería en el sistema";
                String contenido = "Ya existe una tubería en el sistema que conecta las mismas ciudades en la misma dirección";
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeError("No fue posible cargar la tubería en el sistema.\n");
                Imprimir.mensajeError("Ya existe una tubería que conecta las mismas ciudades en la misma dirección.\n");
            }
        } else {
            String titulo = "ERROR al intentar cargar una nueva tubería en el sistema";
            String contenido = "El sistema no cuenta con ciudades para conectar la tubería";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeError("El sistema actualmente no cuenta con ciudades.\n");
            Imprimir.mensajeError("Ingrese al menos dos ciudades al sistema para conectarlas con una nueva tubería.\n");
        }
    }

    /**
     * Función para dar de baja una tubería
     *
     * @param arbolCiudades ArbolAVL
     * @param hashTuberias  HashMap<ClaveTuberia, Tuberia>
     * @param grafoCiudades Grafo
     */
    public static void bajaTuberia(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ) {
        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        Ciudad ciudadOrigen = Formulario.existeCiudadOrigen(arbolCiudades);
        Ciudad ciudadDestino = Formulario.existeCiudadDestino(arbolCiudades, ciudadOrigen);

        ClaveTuberia claveTub = new ClaveTuberia(ciudadOrigen.getNomenclatura(), ciudadDestino.getNomenclatura());

        if (!hashTuberias.containsKey(claveTub)) {
            Tuberia tuberiaAEliminar = hashTuberias.get(claveTub);
            boolean exito = ControladorTuberia.baja(hashTuberias, grafoCiudades, tuberiaAEliminar);

            if (exito) {
                RegistraEstructuras.registrarHashMapTuberias(hashTuberias);
                String titulo = "ÉXITO al eliminar una tubería en el sistema";
                String contenido = tuberiaAEliminar.toString();
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeExito("La tubería se eliminó con éxito del sistema.\n");
            } else {
                String titulo = "ERROR al intentar eliminar una tubería en el sistema";
                String contenido = tuberiaAEliminar.toString();
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeError("No pudo eliminarse la tubería del sistema, la misma no fue encontrada.\n");
            }
        } else {
            String titulo = "ERROR al intentar eliminar una tubería en el sistema";
            String contenido = "La tubería no existe en el sistema";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeError("No pudo eliminarse la tubería del sistema, la misma no fue encontrada.\n");
        }

    }

    /**
     * Función para modificar los datos de una tubería
     *
     * @param arbolCiudades
     * @param hashTuberias
     */
    public static void modificarTuberia(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias
    ) {
        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        Ciudad ciudadOrigen = Formulario.existeCiudadOrigen(arbolCiudades);
        Ciudad ciudadDestino = Formulario.existeCiudadDestino(arbolCiudades, ciudadOrigen);

        ClaveTuberia claveTub = new ClaveTuberia(ciudadOrigen.getNomenclatura(), ciudadDestino.getNomenclatura());

        if (hashTuberias.containsKey(claveTub)) {

            Tuberia tuberiaAModificar = hashTuberias.get(claveTub);

            double[] datosDouble = Formulario.modificarTuberiaDatosDouble();
            String[] datosString = Formulario.modificarTuberiaDatosString();

            boolean exito = ControladorTuberia.modificar(
                    hashTuberias,
                    ciudadOrigen.getNomenclatura(),
                    ciudadDestino.getNomenclatura(),
                    datosDouble,
                    datosString);

            if (exito) {
                RegistraEstructuras.registrarHashMapTuberias(hashTuberias);
                String titulo = "ÉXITO al modificar una tubería en el sistema";
                String contenido = tuberiaAModificar.toString();
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeExito("La tubería se modificó con éxito en el sistema.\n");
            } else {
                String titulo = "ERROR al intentar modificar una tubería del sistema";
                String contenido = tuberiaAModificar.toString();
                RegistraEstructuras.registrarComoLog(titulo, contenido);
                Imprimir.mensajeError("No pudo modificarse la tubería del sistema, la misma no fue encontrada.\n");
            }
        } else {
            String titulo = "ERROR al intentar modificar una tubería del sistema";
            String contenido = "La tubería no existe en el sistema";
            RegistraEstructuras.registrarComoLog(titulo, contenido);
            Imprimir.mensajeError("No pudo modificarse la tubería del sistema, la misma no fue encontrada.\n");
        }
    }

    /**
     * Muestra la cantidad de habitantes y agua consumida en una fecha
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void cantidadHabitantesYAguaConsumidaPorFecha(ArbolAVL arbolCiudades) {

        int anio = Formulario.generarAnioValido();
        int mes = Formulario.generarMesValido();
        String nombreCiudad = Formulario.nombreCiudadValido();
        YearMonth fecha = YearMonth.of(anio, mes);

        String respuesta = ConsultasCiudad.getPobYConsEnFecha(arbolCiudades, nombreCiudad, fecha);
        System.out.println(respuesta);
    }

    /**
     * Dadas dos subcadenas minNomb y maxNomb (nombres de ciudades), y dos volúmenes de agua (minVol y maxVol)
     * devolver todas las ciudades cuyo nombre esté en el rango [minNomb, maxNomb] que en un mes y año determinado hayan
     * consumido un volumen de agua en el rango [minVol, maxVol].
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void consumoDeAguaEntreRangoDeNombres(ArbolAVL arbolCiudades){

        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        String nombreMin = Formulario.nombreCiudadLimiteInferior();
        String nombreMax = Formulario.nombreCiudadLimiteSuperior();
        Double consumoMin = Formulario.consumoMinimo();
        Double consumoMax = Formulario.consumoMaximo();
        int anio = Formulario.generarAnioValido();
        int mes = Formulario.generarMesValido();
        YearMonth fecha = YearMonth.of(anio, mes);

        Lista listaCiudades = ConsultasCiudad.getCiudadesEnRango(arbolCiudades, nombreMin, nombreMax, consumoMin, consumoMax, fecha);
        String titulo = "Lista de ciudades entre " + nombreMin + " y " + nombreMax + ", con consumo entre: (" + consumoMin + " - " + consumoMax + ") en " + fecha + "\n";
        Imprimir.mensajeTitulo(titulo);
        System.out.print(listaCiudades.toStringVertical());
    }

    /**
     * Obtener el camino que llegue de A a B tal que el caudal pleno del camino
     * completo sea el mínimo entre los
     * caminos posibles. El caudal pleno es el caudal definido por la tubería más
     * pequeña del camino.
     * Decir en qué estado se encuentra el camino.
     *
     * @param arbolCiudades ArbolAVL
     * @param grafoCiudades Grafo
     * @param hashTuberias  HashMap<ClaveTuberia, Tuberia>
     */
    public static void caminoDeAaBconCaudalPlenoMinimo(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ) {

        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        String ciudadOrigen = Formulario.nombreCiudadOrigen();
        String ciudadDestino = Formulario.nombreCiudadDestino();

        String resultado = ConsultasTuberia.getCaminoConMenorCaudalPleno(arbolCiudades, grafoCiudades, hashTuberias,
                ciudadOrigen, ciudadDestino);
        System.out.println("Resultado: " + resultado);
    }

    /**
     * Obtener el camino que llegue de A a B pasando por la mínima cantidad de
     * ciudades.
     * Una vez obtenido el camino decir cuál es el estado.
     *
     * @param arbolCiudades ArbolAVL
     * @param grafoCiudades Grafo
     * @param hashTuberias  HashMap<ClaveTuberia, Tuberia>
     */
    public static void caminoDeAaBconMenorRecorrido(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades
    ) {

        Imprimir.arbolAVLCiudadesAlfabetico(arbolCiudades);

        String ciudadOrigen = Formulario.nombreCiudadOrigen();
        String ciudadDestino = Formulario.nombreCiudadDestino();

        String resultadoCamino = ConsultasTuberia.getCaminoMasCorto(arbolCiudades, grafoCiudades, hashTuberias,
                ciudadOrigen, ciudadDestino);
        System.out.println("Resultado: " + resultadoCamino);
    }

    /**
     * Imprime las ciudades ordenadas por consumo según un año indicado
     *
     * @param arbolCiudades
     */
    public static void ciudadesOrdenadasPorConsumoDeAgua(ArbolAVL arbolCiudades) {

        int anio = Formulario.generarAnioValido();

        String ciudades = ConsultasCiudad.generarListaConsumoAnual(arbolCiudades, Year.of(anio));
        Imprimir.mensajeTitulo("Listado de Ciudades ordenadas por consumo anual en " + anio + ": \n");
        System.out.println(ciudades);

    }

    /**
     * Imprime la estructura del ArbolAVL de ciudades y la registra en LOGS
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void visualizarEstructuraArbolDeCiudades(ArbolAVL arbolCiudades) {
        System.out.println(arbolCiudades.toStringTipoCiudad());
        String titulo = "Estado de estructura del ArbolAVL de Ciudades";
        String contenido = arbolCiudades.toStringTipoCiudad();
        RegistraEstructuras.registrarComoLog(titulo, contenido);
    }

    /**
     * Imprime la estructura del HashMap de Tuberías y la registra en LOGS
     *
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     */
    public static void visualizarEstructuraHashMapTuberias(HashMap<ClaveTuberia, Tuberia> hashTuberias) {
        String estructuraHash = Imprimir.hashMapTuberias(hashTuberias);
        System.out.println();
        String titulo = "Estado de estructura del HashMap de Tuberías";
        RegistraEstructuras.registrarComoLog(titulo, estructuraHash);
    }

    /**
     * Imprime la estructura del HashMap de Tuberías y la registra en LOGS
     *
     * @param grafoCiudades Grafo
     */
    public static void visualizarEstructuraGrafo(Grafo grafoCiudades) {
        System.out.println(grafoCiudades.toString());
        String titulo = "Estado de estructura del Grafo de Ciudades con tuberías";
        String contenido = grafoCiudades.toString();
        RegistraEstructuras.registrarComoLog(titulo, contenido);
    }

}
