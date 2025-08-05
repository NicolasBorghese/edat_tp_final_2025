package controladores;

import java.util.HashMap;

import clases.*;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;

public class ControladorTuberia {

    /**
     * Función para dar de alta una tubería
     *
     * @param hash HashMap<ClaveTuberia, Tuberia>
     * @param grafo Grafo
     * @param tuberia Tuberia
     * @return boolean
     */
    public static boolean alta(HashMap<ClaveTuberia, Tuberia> hash, Grafo grafo, Tuberia tuberia) {
        boolean alta = false;
        ClaveTuberia clave = new ClaveTuberia(tuberia.getCiudadOrigen(), tuberia.getCiudadDestino());
        if (grafo.existeVertice(tuberia.getCiudadOrigen()) && grafo.existeVertice(tuberia.getCiudadDestino())) {
            // Verifico que existan las ciudades.
            if (!grafo.existeArco(tuberia.getCiudadOrigen(), tuberia.getCiudadDestino(), tuberia.getCaudalMaximo())
                    && !hash.containsKey(clave)) {
                grafo.insertarArco(tuberia.getCiudadOrigen(), tuberia.getCiudadDestino(), tuberia.getCaudalMaximo());
                hash.put(clave, tuberia);
                alta = true;
            }
        }
        return alta;
    }

    /**
     * Función para dar de baja una tubería
     *
     * @param hash HashMap<ClaveTuberia, Tuberia>
     * @param grafo Grafo
     * @param tuberia Tuberia
     * @return boolean
     */
    public static boolean baja(HashMap<ClaveTuberia, Tuberia> hash, Grafo grafo, Tuberia tuberia) {
        boolean baja = false;
        ClaveTuberia clave = new ClaveTuberia(tuberia.getCiudadOrigen(), tuberia.getCiudadDestino());
        if (grafo.eliminarVertice(tuberia.getCiudadOrigen()) && grafo.eliminarVertice(tuberia.getCiudadDestino())) {
            if (grafo.existeArco(tuberia.getCiudadOrigen(), tuberia.getCiudadDestino(), tuberia.getCaudalMaximo())
                    && hash.containsKey(clave)) {
                grafo.eliminarArco(tuberia.getCiudadOrigen(), tuberia.getCiudadDestino());
                hash.remove(clave);
                baja = true;
            }
        }
        return baja;
    }

    /**
     * Función para modificar los datos de una tubería
     *
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     * @param ciudadOrigen String
     * @param ciudadDestino String
     * @param datosDouble double[]
     * @param datosString String[]
     * @return boolean
     */
    public static boolean modificar(HashMap<ClaveTuberia, Tuberia> hashTuberias, String ciudadOrigen, String ciudadDestino, double[] datosDouble, String[] datosString) {
        boolean modificado = false;

        ClaveTuberia clave = new ClaveTuberia(ciudadOrigen, ciudadDestino);

        if (hashTuberias.containsKey(clave)) {
            Tuberia tuberia = hashTuberias.get(clave);

            if(datosDouble[0] != -1){
                tuberia.setCaudalMinimo(datosDouble[0]);
            }
            if(datosDouble[1] != -1){
                tuberia.setCaudalMaximo(datosDouble[1]);
            }
            if(datosDouble[2] != -1){
                tuberia.setDiametro(datosDouble[2]);
            }
            if(datosString[0] != null){
                tuberia.setEstado(datosString[0]);
            }
            modificado = true;
        }
        return modificado;
    }
}
