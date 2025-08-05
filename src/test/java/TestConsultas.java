import java.util.HashMap;
import java.time.Year;
import java.time.YearMonth;

import clases.*;
import constantes.Rutas;
import consultas.*;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import estructuras.lineales.dinamicas.Lista;
import funciones.*;

public class TestConsultas {
    // RUTAS DE REGISTROS DINÁMICOS
    public static final String RUTA_REGISTRO_CIUDAD = Rutas.RUTA_REGISTRO_CIUDAD;
    public static final String RUTA_REGISTRO_CIUDAD_HABITANTES = Rutas.RUTA_REGISTRO_CIUDAD_HABITANTES;
    public static final String RUTA_REGISTRO_CONTADOR_CIUDAD = Rutas.RUTA_REGISTRO_CONTADOR_CIUDAD;
    public static final String RUTA_REGISTRO_TUBERIA = Rutas.RUTA_REGISTRO_TUBERIA;
    // RUTAS DE REGISTROS PARA CONTAR CON UNA CARGA INICIAL
    public static final String RUTA_SEED_CIUDAD = Rutas.RUTA_SEED_CIUDAD;
    public static final String RUTA_SEED_CIUDAD_HABITANTES = Rutas.RUTA_SEED_CIUDAD_HABITANTES;
    public static final String RUTA_SEED_CONTADOR_CIUDAD = Rutas.RUTA_SEED_CONTADOR_CIUDAD;
    public static final String RUTA_SEED_TUBERIA = Rutas.RUTA_SEED_TUBERIA;

    public static void main(String[] args) {
        ArbolAVL arbolCiudades = new ArbolAVL();
        HashMap<ClaveTuberia, Tuberia> hashTuberias = new HashMap<>();
        Grafo grafoCiudades = new Grafo();
        String rutaCiudades = "";
        String rutaCiudadHabitantes = "";
        String rutaTuberias = "";

        GestorOperaciones.cargarEstructurasCompleto(
                arbolCiudades,
                RUTA_REGISTRO_CIUDAD,
                RUTA_REGISTRO_CIUDAD_HABITANTES,
                hashTuberias,
                RUTA_REGISTRO_TUBERIA,
                grafoCiudades);
        Lista lista = ConsultasCiudad.getCiudadesEnRango(arbolCiudades, "A", "Z", 0, 1000000, YearMonth.of(2024, 6));
        System.out.println(lista.toStringVertical());
        System.out.println(ConsultasCiudad.getPobYConsEnFecha(arbolCiudades, "Villa el Chocón", YearMonth.of(2024, 6)));
        System.out.println("");
        System.out.println("Ciudades ordenadas por consumo anual");
        System.out.println(ConsultasCiudad.generarListaConsumoAnual(arbolCiudades, Year.of(2024)));
        System.out.println("");
        System.out.println(ConsultasTuberia.getCaminoConMenorCaudalPleno(arbolCiudades, grafoCiudades, hashTuberias,
                "El Bolsón", "Neuquén"));
        System.out.println("");
        System.out.println("Camino con menor cantidad de ciudades: ");
        System.out.println(ConsultasTuberia.getCaminoMasCorto(arbolCiudades, grafoCiudades, hashTuberias, "Bariloche", "Las Grutas"));
    }

}
