import java.util.HashMap;
import java.time.Year;
import java.time.YearMonth;

import clases.*;
import consultas.*;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import funciones.*;
import constantes.*;

public class TestConsultasCiudad {
    private static String RESET = Estilos.RESET;
    private static String BOLD = Estilos.BOLD;
    private static String YELLOW = Estilos.YELLOW;
    private static String GREEN = Estilos.GREEN;
    private static String RED = Estilos.RED;
    private static String PURPLE = Estilos.PURPLE;

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
        String rutaTuberias = "src/main/java/datos/registro_tuberia2.txt";
        CargaEstructuras.cargarEstructurasCompleto(
                arbolCiudades,
                RUTA_REGISTRO_CIUDAD,
                RUTA_REGISTRO_CIUDAD_HABITANTES,
                hashTuberias,
                rutaTuberias,
                grafoCiudades);

        // VERIFICADOS.
        System.out.println(BOLD + GREEN + "Verificados: ");
        // toString() AVL Ciudades.
        System.out.println(BOLD + YELLOW + "AVL Ciudades: \n" + RESET);
        System.out.println(arbolCiudades.toString());
        // Consulta Poblacion y Consumo para Bariloche en abril del 2020.
        System.out.println(
                BOLD + PURPLE + "Consulta Población y consumo en Bariloche (abril 2020): \n" + RESET + "Población: "
                        + GREEN +
                        "147844" + RESET + "\n" + "Consumo: " + GREEN + "59137.6" + RESET);
        System.out.println(BOLD + PURPLE + "Resultado:" + RESET);
        System.out.println(ConsultasCiudad.getPobYConsEnFecha(arbolCiudades, "Bariloche", YearMonth.of(2020, 04)));
        // Consulta Poblacion y Consumo para Bariloche en abril del 1920.
        System.out.println(
                BOLD + PURPLE + "Consulta Población y consumo en Bariloche (abril 1920): \n" + RESET + RED
                        + "No existe la Fecha." + RESET);
        System.out.println(BOLD + PURPLE + "Resultado:" + RESET);
        System.out.println(ConsultasCiudad.getPobYConsEnFecha(arbolCiudades, "Bariloche", YearMonth.of(1920, 04)));
        // Consulta Poblacion y Consumo para Springfield en abril del 2120.
        System.out.println(
                BOLD + PURPLE + "Consulta Población y consumo en Bariloche (04 del 2020): \n" + RESET + RED
                        + "No existe la Ciudad." + RESET);
        System.out.println(BOLD + PURPLE + "Resultado:" + RESET);
        System.out.println(ConsultasCiudad.getPobYConsEnFecha(arbolCiudades, "Springfield", YearMonth.of(2020, 04)));
        // Listar Ciudades de la A a Z con clq consumo y fecha 10-2020.
        System.out.println(BOLD + PURPLE + "Listar Ciudades de 'A' a 'Z'; Cualquier Consumo; Fecha 10-2020" + RESET);
        System.out.println(PURPLE + "Predicción: \n" + RESET + " Me lista las 15 ciudades.");
        System.out.println(PURPLE + "Resultado: \n" + RESET + ConsultasCiudad
                .getCiudadesEnRango(arbolCiudades, "A", "Z", 0, 1000000, YearMonth.of(2020, 10)).toStringVertical());
        // Listar Ciudades de Cipo a Primeros Pinos con clq consumo y fecha 10-2020.
        System.out.println(BOLD + PURPLE
                + "Listar Ciudades de 'Cipo' a 'Primeros Pinos'; Cualquier Consumo; Fecha 10-2020" + RESET);
        System.out.println(PURPLE + "Predicción: \n" + RESET + "Me lista 7 Ciudades (De Cipo a Primeros Pinos)");
        System.out.println(PURPLE + "Resultado: \n" + RESET + ConsultasCiudad
                .getCiudadesEnRango(arbolCiudades, "Cipolletti", "Primeros Pinos", 0, 1000000, YearMonth.of(2020, 10))
                .toStringVertical());
        // Listar Ciudades de 'A' a 'Z' con consumo de 50k a 170k y fecha 10-2020.
        System.out.println(
                BOLD + PURPLE + "Listar Ciudades de 'A' a 'Z'; Con Consumo de 50mil a 170mil; Fecha 10-2020" + RESET);
        System.out.println(PURPLE + "Predicción: \n" + RESET + "Me lista 2 Ciudades (Bariloche, Neuquén)");
        System.out.println(PURPLE + "Resultado: \n" + RESET + ConsultasCiudad
                .getCiudadesEnRango(arbolCiudades, "A", "Z", 50000, 170000, YearMonth.of(2020, 10)).toStringVertical());
        // Listar Ciudades de 'Bariloche' a 'Las Grutas' con consumo de 500 a 15k y
        // fecha 10-2020.
        System.out.println(
                BOLD + PURPLE
                        + "Listar Ciudades de 'Bariloche' a 'Las Grutas'; Con Consumo de 500 a 15mil; Fecha 10-2020"
                        + RESET);
        System.out.println(PURPLE + "Predicción: \n" + RESET + "Me lista 3 Ciudades (El Bolsón, Junín, Las Grutas)");
        System.out.println(PURPLE + "Resultado: \n" + RESET + ConsultasCiudad
                .getCiudadesEnRango(arbolCiudades, "Bariloche", "Las Grutas", 500, 15000, YearMonth.of(2020, 10))
                .toStringVertical());
        // Listar Ciudades de 'Z' a 'A' con clq consumo y fecha 10-2020.
        System.out.println(
                BOLD + PURPLE + "Listar Ciudades de 'Z' a 'A'; Con cualquier consumo; Fecha 10-2020" + RESET);
        System.out.println(PURPLE + "Predicción: \n" + RESET + "NADA");
        System.out.println(PURPLE + "Resultado: \n" + RESET + ConsultasCiudad
                .getCiudadesEnRango(arbolCiudades, "Z", "A", 0, 1000000, YearMonth.of(2020, 10)).toStringVertical());
        // Listar Ciudades de 'A' a 'Z' con consumo invertido y fecha 10-2020.
        System.out.println(
                BOLD + PURPLE + "Listar Ciudades de 'A' a 'Z'; con consumo invertido; Fecha 10-2020" + RESET);
        System.out.println(PURPLE + "Predicción: \n" + RESET + "NADA");
        System.out.println(PURPLE + "Resultado: \n" + RESET + ConsultasCiudad
                .getCiudadesEnRango(arbolCiudades, "Z", "A", 10000, 100, YearMonth.of(2020, 10)).toStringVertical());
        // Listado Ciudades por Consumo Anual 2020.
        System.out.println(BOLD + PURPLE + "Listado Ciudades Consumo Anual 2020: " + RESET);
        System.out.println(ConsultasCiudad.generarListaConsumoAnual(arbolCiudades, Year.of(2020)));
        // Listado Ciudades por Consumo Anual 1920.
        System.out.println(BOLD + PURPLE + "Listado Ciudades Consumo Anual 1920: " + RESET);
        System.out.println(ConsultasCiudad.generarListaConsumoAnual(arbolCiudades, Year.of(1920)));
        // TESTEANDO.
        System.out.println(BOLD + PURPLE + "TESTING" + RESET);
        System.out.println(GREEN + "No queda nada más para Testear \n :))" + RESET);

    }
}
