package funciones;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.time.YearMonth;
import java.util.Comparator;

import clases.Ciudad;
import clases.Tuberia;
import clases.ClaveTuberia;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import estructuras.lineales.dinamicas.Lista;
import manipuladorDeRegistros.ManipuladorDeRegistros;

public class CargaEstructuras {

    /**
     * Recibe un ArbolAVL y 3 rutas para cargar todos los datos de ciudades
     *
     * @param arbolCiudades  ArbolAVL de tipo Ciudad
     * @param rutaCiudades   String de la ruta con los datos de las ciudades
     * @param rutaHabitantes String de la ruta con los datos de los habitantes de
     *                       cada ciudad
     */
    public static void cargarArbolAVLCiudades(ArbolAVL arbolCiudades, String rutaCiudades, String rutaHabitantes) {

        Lista listaCiudades = ManipuladorDeRegistros.obtenerRegistros(rutaCiudades);
        Lista listaHabitantes = ManipuladorDeRegistros.obtenerRegistros(rutaHabitantes);

        int cantRegistrosCiudades = listaCiudades.longitud();
        int cantRegistrosHabitantes = listaHabitantes.longitud();
        int iterRegistrosHabitantes = 1;

        HashMap<String, TreeMap<YearMonth, Integer>> habitantesPorCiudad = new HashMap<>();

        while (iterRegistrosHabitantes <= cantRegistrosHabitantes) {
            String nombreCiudad = ((String[]) listaHabitantes.recuperar(iterRegistrosHabitantes))[0];
            TreeMap<YearMonth, Integer> habitantesPorFecha = new TreeMap<>();

            while ((iterRegistrosHabitantes <= cantRegistrosHabitantes) &&
                    (nombreCiudad.equals(((String[]) listaHabitantes.recuperar(iterRegistrosHabitantes))[0]))) {

                String fechaStr = ((String[]) listaHabitantes.recuperar(iterRegistrosHabitantes))[1];
                YearMonth fecha = YearMonth.parse(fechaStr); 
                int cantHabitantes = Integer
                        .parseInt(((String[]) listaHabitantes.recuperar(iterRegistrosHabitantes))[2]);

                habitantesPorFecha.put(fecha, cantHabitantes);
                iterRegistrosHabitantes++;
            }

            habitantesPorCiudad.put(nombreCiudad, habitantesPorFecha);
        }

        for (int i = 1; i <= cantRegistrosCiudades; i++) {
            String[] arreglo = (String[]) listaCiudades.recuperar(i);
            Ciudad ciudad = new Ciudad(
                    arreglo[1], arreglo[2], Double.parseDouble(arreglo[3]), Double.parseDouble(arreglo[4]));
            if (habitantesPorCiudad.containsKey(ciudad.getNombre())) {
                ciudad.setPoblacionPorFecha(habitantesPorCiudad.get(ciudad.getNombre()));
            }

            arbolCiudades.insertar(arreglo[1], ciudad);
        }
    }

    /**
     * Recibe un HashMap y 1 ruta para cargar todos los datos de las tuberias
     *
     * @param hashTuberias HashMap de tipo Tuberia
     * @param rutaTuberias String de la ruta con los datos de las tuberias
     */
    public static void cargarHashTuberias(HashMap<ClaveTuberia, Tuberia> hashTuberias, String rutaTuberias) {

        Lista listaTuberias = ManipuladorDeRegistros.obtenerRegistros(rutaTuberias);

        int cantRegistrosTuberias = listaTuberias.longitud();

        for (int i = 1; i <= cantRegistrosTuberias; i++) {
            String[] arreglo = (String[]) listaTuberias.recuperar(i);
            Tuberia nuevaTuberia = new Tuberia(
                    arreglo[1], // nomenclatura
                    Double.parseDouble(arreglo[2]), // caudalMínimo
                    Double.parseDouble(arreglo[3]), // caudalMáximo
                    Double.parseDouble(arreglo[4]), // diametro
                    arreglo[5]// estado
            );

            String[] nomenclaturas = arreglo[1].split("-");

            ClaveTuberia nuevaClaveTuberia = new ClaveTuberia(nomenclaturas[0], nomenclaturas[1]);
            hashTuberias.put(nuevaClaveTuberia, nuevaTuberia);
        }

    }

    /**
     * Carga un grafo a partir de un ArbolAVL de tipo Ciudad y una HashMap de tipo Tuberia
     *
     * @param arbolCiudades
     * @param hashTuberias
     * @param grafoCiudades
     */
    public static void cargarGrafoCiudades(
            ArbolAVL arbolCiudades,
            HashMap<ClaveTuberia, Tuberia> hashTuberias,
            Grafo grafoCiudades) {

        Lista listaCiudades =  arbolCiudades.listar();
        int posUltimaCiudad = listaCiudades.longitud();

        for(int posActual = 1 ; posActual <= posUltimaCiudad;  posActual++) {
            String nomenclaturaCiudad = ((Ciudad) listaCiudades.recuperar(posActual)).getNomenclatura();
            grafoCiudades.insertarVertice(nomenclaturaCiudad);
        }

        for (Tuberia tuberia : hashTuberias.values()) {
            double caudalMaximo = tuberia.getCaudalMaximo();
            String[] nomenclaturas = tuberia.getNomenclatura().split("-");
            grafoCiudades.insertarArco(nomenclaturas[0], nomenclaturas[1], caudalMaximo);
        }

    }

    /**
     * Recupera el último valor almacenado del contador de ciudades que se encuentre en la ruta indicada
     *
     * @param rutaContadorCiudad int
     * @return int
     */
    public static int recuperarContadorCiudad(String rutaContadorCiudad){
        Lista listaNumCiudad = ManipuladorDeRegistros.obtenerRegistros(rutaContadorCiudad);
        String[] tupla = (String[]) listaNumCiudad.recuperar(1);

        return Integer.parseInt(tupla[0]);
    }


}