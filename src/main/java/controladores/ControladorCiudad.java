package controladores;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Iterator;

import clases.*;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;

public class ControladorCiudad {


    /**
     * Recibe una instancia de Ciudad, un Grafo y un ArbolAVL, si es posible insertar la ciudad en el Arbol
     * entonces también la agregará como vertice dentro del grafo. <br>
     * Devuelve true si la operación es exitosa, false en caso contrario
     *
     * @param arbol ArbolAVL
     * @param grafo Grafo
     * @param ciudad Ciudad
     * @return boolean
     */
    public static boolean alta(ArbolAVL arbol, Grafo grafo, Ciudad ciudad) {
        boolean alta = arbol.insertar(ciudad.getNombre(), ciudad);
        if (alta == true) {
            alta = grafo.insertarVertice(ciudad.getNomenclatura());
        }
        return alta;
    }

    /**
     * Da de baja una ciudad en el sistema
     *
     * @param arbolCiudades ArbolAVL
     * @param grafoCiudades Grafo
     * @param hash HashMap<ClaveTuberia, Tuberia>
     * @param nombreCiudad String
     * @return boolean
     */
    public static boolean baja(ArbolAVL arbolCiudades, Grafo grafoCiudades, HashMap<ClaveTuberia, Tuberia> hash, String nombreCiudad) {
        Ciudad c = (Ciudad) arbolCiudades.getObjeto(nombreCiudad);
        boolean baja = false;
        if (c != null) {
            arbolCiudades.eliminar(c.getNombre());
            grafoCiudades.eliminarVertice(c.getNomenclatura());
            eliminarTuberias(hash, c.getNomenclatura());
            baja = true;
        }
        return baja;
    }

    /**
     * Elimina tuberias conectadas a una ciudad que fue dada de baja
     *
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     * @param nomenclaturaCiudad String
     */
    private static void eliminarTuberias(HashMap<ClaveTuberia, Tuberia> hashTuberias, String nomenclaturaCiudad){
        Iterator<ClaveTuberia> i = hashTuberias.keySet().iterator();
        while (i.hasNext()) {
            ClaveTuberia clave = i.next();
            if(clave.getNomCOrigen().equals(nomenclaturaCiudad) || clave.getNomCDestino().equals(nomenclaturaCiudad)){
                i.remove();
            }
        }
    }

    /**
     * Modifica el dato de consumo diario promedio por M3 por persona de una Ciudad
     *
     * @param arbol ArbolAVL
     * @param nombreCiudad String
     * @param superficie double
     * @return boolean
     */
    public static boolean modificarSuperficie(ArbolAVL arbol, String nombreCiudad, double superficie) {
        boolean modificado = false;
        Ciudad c = (Ciudad) arbol.getObjeto(nombreCiudad);
        if (c != null) {
            c.setSuperficie(superficie);
            modificado = true;
        }
        return modificado;
    }

    /**
     * Modifica el dato de consumo diario promedio por M3 por persona de una Ciudad
     *
     * @param arbol ArbolAVL
     * @param nombreCiudad String
     * @param consumo double
     * @return boolean
     */
    public static boolean modificarConsumoDiarioProm(ArbolAVL arbol, String nombreCiudad, double consumo) {
        boolean modificado = false;
        Ciudad c = (Ciudad) arbol.getObjeto(nombreCiudad);
        if (c != null) {
            c.setConsumoDiarioProm(consumo);
            modificado = true;
        }
        return modificado;
    }

    /**
     * Modifica la población de la ciudad para una fecha dada
     *
     * @param arbol ArbolAVL
     * @param nombreCiudad String
     * @param fecha YearMoth
     * @param poblacion int
     * @return boolean
     */
    public static boolean modificarPoblacionPorFecha(ArbolAVL arbol, String nombreCiudad, YearMonth fecha, int poblacion) {
        boolean modificado = false;
        Ciudad c = (Ciudad) arbol.getObjeto(nombreCiudad);
        if (c != null) {
            c.modificarPoblacionPorFecha(fecha, poblacion);
            modificado = true;
        }
        return modificado;
    }






}
