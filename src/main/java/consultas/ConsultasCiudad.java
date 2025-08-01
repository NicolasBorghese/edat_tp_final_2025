package consultas;

import java.time.YearMonth;
import java.time.Year;

import constantes.Estilos;
import clases.*;
//import estructuras.*;
import estructuras.lineales.dinamicas.Lista;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.conjuntistas.ArbolBB;

public class ConsultasCiudad {
    private static String RESET = Estilos.RESET;
    private static String BOLD = Estilos.BOLD;
    private static String YELLOW = Estilos.YELLOW;
    private static String ERROR = Estilos.RED;

    public static String getPobYConsEnFecha(ArbolAVL arbol, String nombreCiudad, YearMonth fecha) {
        String res = ERROR + "ERROR: Nombre de ciudad erróneo." + RESET;
        Ciudad ciudad = (Ciudad) arbol.getObjeto(nombreCiudad);
        if (ciudad != null) {
            res = ERROR + "ERROR: Fecha inexistente." + RESET;
            int poblacion = getPoblacion(arbol, ciudad, fecha);
            double consumo = getConsumoPromedioFecha(arbol, ciudad, fecha);
            if (poblacion != -1 && consumo != -1) {
                res = BOLD + "Ciudad: " + YELLOW + nombreCiudad + RESET + ".\n" + BOLD + "Fecha: " + RESET + fecha
                        + ".\n"
                        + BOLD + "Poblacion: " + RESET + poblacion + ".\n"
                        + BOLD + "Consumo Promedio: " + RESET + consumo + ".";
            }
        }
        return res;
    }

    private static int getPoblacion(ArbolAVL arbol, Ciudad ciudad, YearMonth fecha) {
        int poblacion = -1;
        if (ciudad.getPoblacionPorFecha().containsKey(fecha)) {
            poblacion = ciudad.getPoblacionEnFecha(fecha);
        }
        return poblacion;
    }

    private static double getConsumoPromedioFecha(ArbolAVL arbol, Ciudad ciudad, YearMonth fecha) {
        double consumo = -1;
        if (ciudad.getPoblacionPorFecha().containsKey(fecha)) {
            consumo = ciudad.getConsumoEnFecha(fecha);
        }
        return consumo;
    }

    public static Lista getCiudadesEnRango(ArbolAVL arbol, String minNom, String maxNom, double minCons, double maxCons,
            YearMonth fecha) {
        // Revisar este método porque recorro el Arbol, armo la lista y dsp recorro la
        // lista para filtrar (eficiencia).
        Lista lista = new Lista();
        if (!arbol.vacio() && minNom.compareTo(maxNom) <= 0 && minCons < maxCons) {
            Lista ciudades = arbol.listarRango(minNom, maxNom);
            for (int i = 1; i <= ciudades.longitud(); i++) {
                Ciudad c = (Ciudad) ciudades.recuperar(i);
                double consumo = c.getConsumoEnFecha(fecha);
                if (consumo >= minCons && consumo <= maxCons) {
                    lista.insertar(c, lista.longitud() + 1);
                }
            }
        }
        return lista;
    }

    public static String generarListaConsumoAnual(ArbolAVL arbol, Year year) {
        String listado = "";
        if (!arbol.vacio()) {
            Lista listaCiudades = arbol.listarPreorden();
            ArbolBB ciudadesOrdenadas = new ArbolBB();
            for (int i = 1; i <= listaCiudades.longitud(); i++) {
                Ciudad c = (Ciudad) listaCiudades.recuperar(i);
                CiudadConsumo aux = new CiudadConsumo(c, c.calcularConsumoAnual(year));
                ciudadesOrdenadas.insertar(aux);
            }
            listado = ciudadesOrdenadas.toStringMayorAMenor();
            arbol.vaciar();
        }
        return listado;
    }
}
