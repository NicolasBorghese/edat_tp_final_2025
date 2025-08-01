package consultas;

import java.util.HashMap;

import constantes.Estilos;

import clases.Ciudad;
import clases.ClaveTuberia;
import clases.Tuberia;
import constantes.Estilos;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import estructuras.lineales.dinamicas.Lista;

public class ConsultasTuberia {
    private static String RESET = Estilos.RESET;
    private static String BOLD = Estilos.BOLD;
    private static String YELLOW = Estilos.YELLOW;
    private static String ERROR = Estilos.RED;
    private static String ACTIVO = Estilos.GREEN;
    private static String DISEÑO = Estilos.CYAN;
    private static String REPARACIÓN = Estilos.RED;
    private static String BLUE = Estilos.BLUE;

    public String getCaminoConMayorCaudalPleno(ArbolAVL arbol, Grafo grafo, HashMap<ClaveTuberia, Tuberia> hash,
            String cOrigen,
            String cDestino) {
        String retorno = BOLD + ERROR + "Una o ambas ciudades no existen" + RESET;
        double caudalPleno = Double.MAX_VALUE;
        String estado = ACTIVO + "ACTIVO" + RESET;
        // Primero verifico si existen las ciudades y si existe un camino entre las
        // ciudades.
        Ciudad ciudadOrigen = (Ciudad) arbol.getObjeto(cOrigen);
        Ciudad ciudadDestino = (Ciudad) arbol.getObjeto(cDestino);
        if (ciudadOrigen != null && ciudadDestino != null) {
            retorno = BOLD + ERROR + "No existe camino entre las ciudades" + RESET;
            if (grafo.existeCamino(ciudadOrigen.getNomenclatura(), ciudadDestino.getNomenclatura())) {
                // Si se cumplen las condiciones, tengo que listar el camino más liviano del
                // grafo e ir buscando las tuberías en el hash ¡Cómo hago esto al mismo tiempo'
                Lista lCamino = grafo.caminoMasLiviano(ciudadOrigen.getNomenclatura(), ciudadDestino.getNomenclatura());
                for (int i = 1; i < lCamino.longitud(); i++) {
                    String nom1 = (String) lCamino.recuperar(i);
                    String nom2 = (String) lCamino.recuperar(i++);

                    ClaveTuberia clave = new ClaveTuberia(nom1, nom2);
                    Tuberia tuberia = (Tuberia) hash.get(clave);
                    caudalPleno = (tuberia.getCaudalMaximo() < caudalPleno) ? tuberia.getCaudalMaximo() : caudalPleno;
                    String estadoTuberia = tuberia.getEstado();
                    if (estadoTuberia.equals("EN DISEÑO")) {
                        estado = DISEÑO + "EN DISEÑO" + RESET;
                    } else if (estadoTuberia.equals("INACTIVO") && !estado.equals("EN DISEÑO")) {
                        estado = RESET + "INACTIVO";
                    } else if (estadoTuberia.equals("EN REPARACIÓN") && !estado.equals("INACTIVO")) {
                        estado = REPARACIÓN + "EN REPARACIÓN" + RESET;
                    }
                }
                retorno = BOLD + "Camino: " + RESET + "\n" + YELLOW + lCamino.toString() + RESET + "\n" + BOLD
                        + "ESTADO: " + RESET + estado + "\n" + BOLD + "Caudal Pleno: " + BLUE + caudalPleno + RESET;
            }
        }
        return retorno;
    }

    public String getCaminoMasCorto(ArbolAVL arbol, Grafo grafo, HashMap<ClaveTuberia, Tuberia> hash, String cOrigen,
            String cDestino) {
        String retorno = BOLD + ERROR + "Una o ambas ciudades no existen" + RESET;
        String estado = ACTIVO + "ACTIVO" + RESET;
        Ciudad ciudadOrigen = (Ciudad) arbol.getObjeto(cOrigen);
        Ciudad ciudadDestino = (Ciudad) arbol.getObjeto(cDestino);
        if (ciudadOrigen != null && ciudadDestino != null) {
            retorno = BOLD + ERROR + "No existe camino entre las ciudades" + RESET;
            if (grafo.existeCamino(ciudadOrigen.getNomenclatura(), ciudadDestino.getNomenclatura())) {
                Lista lCamino = grafo.caminoMasCorto(ciudadOrigen, ciudadDestino);
                for (int i = 1; i < lCamino.longitud(); i++) {
                    String nom1 = (String) lCamino.recuperar(i);
                    String nom2 = (String) lCamino.recuperar(i++);

                    ClaveTuberia clave = new ClaveTuberia(nom1, nom2);
                    Tuberia tuberia = (Tuberia) hash.get(clave);
                    String estadoTuberia = tuberia.getEstado();
                    if (estadoTuberia.equals("EN DISEÑO")) {
                        estado = DISEÑO + "EN DISEÑO" + RESET;
                    } else if (estadoTuberia.equals("INACTIVO") && !estado.equals("EN DISEÑO")) {
                        estado = RESET + "INACTIVO";
                    } else if (estadoTuberia.equals("EN REPARACIÓN") && !estado.equals("INACTIVO")) {
                        estado = REPARACIÓN + "EN REPARACIÓN" + RESET;
                    }
                }
                retorno = BOLD + "Camino: " + RESET + "\n" + YELLOW + lCamino.toString() + RESET + "\n" + BOLD
                        + "ESTADO: " + RESET + estado;
            }
        }
        return retorno;
    }
}
