package funciones;

import clases.Ciudad;
import clases.ClaveTuberia;
import clases.Tuberia;
import constantes.Rutas;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.lineales.dinamicas.Lista;
import manipuladorDeRegistros.ManipuladorDeRegistros;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RegistraEstructuras {

    // VARIABLES GLOBALES

    // RUTAS DE REGISTROS DINÁMICOS
    public static final String RUTA_REGISTRO_CIUDAD = Rutas.RUTA_REGISTRO_CIUDAD;
    public static final String RUTA_REGISTRO_CIUDAD_HABITANTES = Rutas.RUTA_REGISTRO_CIUDAD_HABITANTES;
    public static final String RUTA_REGISTRO_CONTADOR_CIUDAD = Rutas.RUTA_REGISTRO_CONTADOR_CIUDAD;
    public static final String RUTA_REGISTRO_TUBERIA = Rutas.RUTA_REGISTRO_TUBERIA;
    public static final String RUTA_LOG = Rutas.RUTA_LOG;

    /**
     * Registra el contenido de un ArbolAVL de Ciudades en su correspondiente archivo de datos
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void registrarArbolAVLCiudades(ArbolAVL arbolCiudades){

        Lista listaCiudades = arbolCiudades.listar();
        int cantCiudades = listaCiudades.longitud();
        Lista listaCiudadesString = new Lista();
        Lista listaCiudadHabitantesString = new Lista();
        int cantRegistrosHabitantes = 0;

        for (int i = 1; i <= cantCiudades; i++) {
            String[] tuplaCiudad = new String[5];

            Ciudad ciudad = (Ciudad) listaCiudades.recuperar(i);

            tuplaCiudad[0] = "C";
            tuplaCiudad[1] = ciudad.getNombre();
            tuplaCiudad[2] = ciudad.getNomenclatura();
            tuplaCiudad[3] = String.valueOf(ciudad.getSuperficie());
            tuplaCiudad[4] = String.valueOf(ciudad.getConsumoDiarioProm());

            listaCiudadesString.insertar(tuplaCiudad, i);

            TreeMap<YearMonth, Integer> poblacionPorFecha = ciudad.getPoblacionPorFecha();

            for (Map.Entry<YearMonth, Integer> entry : poblacionPorFecha.entrySet()) {

                cantRegistrosHabitantes++;

                String[] tuplaFechaPoblacion = new String[3];

                String fecha = entry.getKey().toString();
                String cantPoblacion = Integer.toString(entry.getValue());

                tuplaFechaPoblacion[0] = ciudad.getNombre();
                tuplaFechaPoblacion[1] = fecha;
                tuplaFechaPoblacion[2] = cantPoblacion;

                listaCiudadHabitantesString.insertar(tuplaFechaPoblacion, cantRegistrosHabitantes);
            }
        }

        ManipuladorDeRegistros.escribirRegistros(RUTA_REGISTRO_CIUDAD, listaCiudadesString, false);
        ManipuladorDeRegistros.escribirRegistros(
                RUTA_REGISTRO_CIUDAD_HABITANTES,
                listaCiudadHabitantesString,
                false);
    }

    /**
     * Registra el contenido de un hash de tuberias en su correspondiente archivo de datos
     *
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     */
    public static void registrarHashMapTuberias(HashMap<ClaveTuberia, Tuberia> hashTuberias){

        int cantRegistrosTuberias = 0;
        Lista listaTuberiasString = new Lista();

        for (Map.Entry<ClaveTuberia, Tuberia> entry : hashTuberias.entrySet()) {

            cantRegistrosTuberias++;

            String[] tuplaTuberia = new String[6];
            Tuberia tuberia = entry.getValue();

            tuplaTuberia[0] = "T";
            tuplaTuberia[1] = tuberia.getNomenclatura();
            tuplaTuberia[2] = String.valueOf(tuberia.getCaudalMinimo());
            tuplaTuberia[3] = String.valueOf(tuberia.getCaudalMaximo());
            tuplaTuberia[4] = String.valueOf(tuberia.getDiametro());
            tuplaTuberia[5] = tuberia.getEstado();

            listaTuberiasString.insertar(tuplaTuberia, cantRegistrosTuberias);
        }

        ManipuladorDeRegistros.escribirRegistros(RUTA_REGISTRO_TUBERIA, listaTuberiasString, false);
    }

    /**
     * Esta función recibe 2 string (un título y un contenido), para generar un nuevo registro en el archivo de logs
     *
     * @param titulo String
     * @param contenido String
     */
    public static void registrarComoLog(String titulo, String contenido){

        String[] registroComoLog = new String[2];
        registroComoLog[0] = titulo;
        registroComoLog[1] = contenido;

        ManipuladorDeRegistros.escribirComoLog(RUTA_LOG, registroComoLog, true);
    }

    /**
     * Reemplaza el último valor almacenado del contador de ciudades por uno nuevo
     *
     * @param nuevoValor int
     */
    public static void registrarContadorCiudad(int nuevoValor){

        Lista listaNumCiudad = new Lista();
        String[] tupla = new String[1];
        tupla[0] = String.valueOf(nuevoValor);
        listaNumCiudad.insertar(tupla, 1);
        ManipuladorDeRegistros.escribirRegistros(RUTA_REGISTRO_CONTADOR_CIUDAD, listaNumCiudad, false);

    }

}
