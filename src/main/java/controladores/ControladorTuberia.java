package controladores;

import java.util.HashMap;

import clases.*;
import estructuras.grafos.Grafo;

public class ControladorTuberia {
    public static boolean alta(Grafo grafo, HashMap<ClaveTuberia, Tuberia> hash, Tuberia tuberia) {
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

    public static boolean baja(Grafo grafo, Tuberia tuberia) {
        boolean baja = false;
        return baja;
    }
}
