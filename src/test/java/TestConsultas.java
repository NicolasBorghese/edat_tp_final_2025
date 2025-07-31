import java.util.HashMap;
import java.time.YearMonth;

import consultas.*;
import estructuras.*;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.lineales.dinamicas.Lista;
import funciones.*;

public class TestConsultas {
    public static void main(String[] args) {
        ArbolAVL arbolCiudades = new ArbolAVL();
        HashMap hashTuberias = new HashMap<>();
        String rutaCiudades = "";
        String rutaCiudadHabitantes = "";
        String rutaTuberias = "";
        rutaCiudades = "src/main/java/datos/registro_ciudad.txt";
        rutaCiudadHabitantes = "src/main/java/datos/registro_ciudad_habitantes.txt";
        rutaTuberias = "src/main/java/datos/registro_tuberia.txt";
        CargaEstructuras.cargarEstructurasCompleto(arbolCiudades, rutaCiudades, rutaCiudadHabitantes, hashTuberias,
                rutaTuberias);
        Lista lista = ConsultasCiudad.getCiudadesEnRango(arbolCiudades, "A", "Z", 0, 1000000, YearMonth.of(2024, 6));
        System.out.println(lista.toStringVertical());
        System.out.println(ConsultasCiudad.getPobYConsEnFecha(arbolCiudades, "Allen", YearMonth.of(2024, 6)));
    }

}
