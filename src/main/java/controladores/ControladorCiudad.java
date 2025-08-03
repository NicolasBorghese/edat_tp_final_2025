package controladores;

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
     * @param arbol ArbolAvL
     * @param grafo Grafo
     * @param ciudad Ciudad
     * @return
     */
    public static boolean alta(ArbolAVL arbol, Grafo grafo, Ciudad ciudad) {
        boolean alta = arbol.insertar(ciudad.getNombre(), ciudad);
        if (alta == true) {
            alta = grafo.insertarVertice(ciudad.getNomenclatura());
        }
        return alta;
    }


    public static boolean baja(ArbolAVL arbol, Grafo grafo, HashMap<ClaveTuberia, Tuberia> hash, String nombreCiudad) {
        Ciudad c = (Ciudad) arbol.getObjeto(nombreCiudad);
        boolean baja = false;
        if (c != null) {
            grafo.eliminarVertice(c.getNomenclatura());
            /*
             * Este método existe para eliminar las tuberías correspondientes a los arcos
             * del vértice de la ciudad que se eliminó.
             */
            eliminarTuberias(hash, nombreCiudad);
        }
        return baja;
    }

    private static void eliminarTuberias(HashMap<ClaveTuberia, Tuberia> hash, String nomenclaturaCiudad){
        Iterator<ClaveTuberia> i = hash.keySet().iterator();
        while (i.hasNext()) {
            ClaveTuberia clave = i.next();
            if(clave.getNomCOrigen().equals(nomenclaturaCiudad) || clave.getNomCDestino().equals(nomenclaturaCiudad)){
                i.remove();
            }
        }
    }

    public static boolean modificarConsumo(ArbolAVL arbol, String nombreCiudad, double consumo) {
        boolean modificado = false;
        Ciudad c = (Ciudad) arbol.getObjeto(nombreCiudad);
        if (c != null) {
            c.setConsumoDiarioProm(consumo);
            modificado = true;
        }
        return modificado;
    }
}
