package funciones;

import clases.ClaveTuberia;
import clases.Tuberia;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.lineales.dinamicas.Lista;

import java.util.HashMap;
import java.util.Map;

public class VisualizaEstructuras {

    /**
     * Imprime en pantalla el contenido de la estructura del HashMap de tuberías
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     */
    public static void visualizarHashMapTuberias(HashMap<ClaveTuberia, Tuberia> hashTuberias){

        if (hashTuberias.isEmpty()) {
            System.out.println("La estructura HashMap de tuberías se encuentra vacía.");
        } else {
            System.out.println("Contenido del HashMap de tuberías: ");
            for (Map.Entry<ClaveTuberia, Tuberia> entry : hashTuberias.entrySet()) {
                System.out.println("- Clave: " + entry.getKey() + " | Valor: " + entry.getValue().toString());
            }
        }

    }

    /**
     * Imprime en pantalla el contenido de la estructura del ArbolAVL de Ciudades
     * @param arbolCiudades ArbolAVL
     */
    public static void visualizarArbolAVLCiudades(ArbolAVL arbolCiudades){

        if (arbolCiudades.vacio()) {
            System.out.println("La estructura ArbolAVL de ciudades se encuentra vacía.");
        } else {
            System.out.println("Contenido del ArbolAVL de ciudades: ");
            Lista listaCiudades = arbolCiudades.listar();
            int cantElem = listaCiudades.longitud();
            for(int i = 1; i <= cantElem; i++){
                System.out.println(listaCiudades.recuperar(i).toString());
            }
        }

    }
}
