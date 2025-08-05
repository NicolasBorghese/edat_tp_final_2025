import java.util.HashMap;

import clases.*;
import constantes.Rutas;
import consultas.*;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.grafos.Grafo;
import funciones.*;
import constantes.*;

public class TestConsultasTuberias {
        private static String RESET = Estilos.RESET;
        private static String BOLD = Estilos.BOLD;
        private static String YELLOW = Estilos.YELLOW;
        private static String GREEN = Estilos.GREEN;
        private static String BLUE = Estilos.BLUE;
        private static String RED = Estilos.RED;
        private static String CYAN = Estilos.CYAN;
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

                GestorOperaciones.cargarEstructurasCompleto(
                                arbolCiudades,
                                RUTA_REGISTRO_CIUDAD,
                                RUTA_REGISTRO_CIUDAD_HABITANTES,
                                hashTuberias,
                                rutaTuberias,
                                grafoCiudades);
                // VERIFICADOS.
                System.out.println(BOLD + GREEN + "Verificados: ");
                // ToString del grafo.
                System.out.println(BOLD + YELLOW + "ToString del grafo" + RESET);
                System.out.println(grafoCiudades.toString());
                // Test camino más corto de Caviahue a Las Grutas.
                System.out.println(BOLD + PURPLE + "Camino más corto de Caviahue a Las Grutas." + RESET);
                System.out.println(BOLD + "Predicción: \n" + "Camino:" + YELLOW
                                + "Caviahue->Neuquén->Chocón->Las Grutas \n"
                                + RESET + BOLD + "Estado: " + RESET + RED + "EN REPARACIÓN" + RESET);
                System.out.println(BOLD + PURPLE + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoMasCorto(arbolCiudades, grafoCiudades, hashTuberias,
                                                "Caviahue",
                                                "Las Grutas"));
                // Camino más corto de Ciudad Origen inexistente.
                System.out.println(BOLD + PURPLE + "Camino más corto de Bariloche a Sprigfield." + RESET);
                System.out.println(BOLD + "Predicción: \n" + RED + "No existen una o ambas Ciudades." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET + ConsultasTuberia.getCaminoMasCorto(arbolCiudades,
                                grafoCiudades, hashTuberias, "Bariloche", "Springfield"));
                // Camino más corto de Ciudad Destino inexistente.
                System.out.println(BOLD + PURPLE + "Camino más corto de Springfield a Bariloche." + RESET);
                System.out.println(BOLD + "Predicción: \n" + RED + "No existen una o ambas Ciudades." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET + ConsultasTuberia.getCaminoMasCorto(arbolCiudades,
                                grafoCiudades, hashTuberias, "Springfield", "Bariloche"));
                // Camino más corto de Ciudad Origen y Destino inexistentes.
                System.out.println(BOLD + PURPLE + "Camino más corto de Springfield a South Park." + RESET);
                System.out.println(BOLD + "Predicción: \n" + RED + "No existen una o ambas Ciudades." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET + ConsultasTuberia.getCaminoMasCorto(arbolCiudades,
                                grafoCiudades, hashTuberias, "Springfield", "South Park"));
                // Camino más corto de Ciudades nulas.
                System.out.println(BOLD + PURPLE + "Camino más corto de null a null." + RESET);
                System.out.println(BOLD + "Predicción: \n" + RED + "No existen una o ambas Ciudades." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET + ConsultasTuberia.getCaminoMasCorto(arbolCiudades,
                                grafoCiudades, hashTuberias, null, null));
                // Camino más corto de un camino que no existe.
                System.out.println(BOLD + PURPLE + "Camino más corto de Bariloche a Caviahue." + RESET);
                System.out.println(BOLD + "Predicción: \n" + RED + "No existe el camino." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET + ConsultasTuberia.getCaminoMasCorto(arbolCiudades,
                                grafoCiudades, hashTuberias, "Bariloche", "Caviahue"));
                // Camino con menor caudal pleno entre Caviahue y San Martín.
                System.out.println(
                                BOLD + PURPLE + "Camino con mayor caudal pleno entre Caviahue y San Martín." + RESET);
                System.out.println(
                                BOLD + "Predicción: \n" + "Camino:" + YELLOW
                                                + "Caviahue->Neuquén->Cipolletti->Allen->Chocón->Bariloche->San Martín. \n"
                                                + RESET + BOLD + "Estado: " + RESET + CYAN + "EN DISEÑO \n" + RESET
                                                + BOLD + "Caudal: "
                                                + RESET + BLUE + "123" + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoConMayorCaudalPleno(arbolCiudades,
                                                grafoCiudades, hashTuberias, "Caviahue", "San Martín de los andes"));
                // Camino con menor caudal pleno con Ciudad Origen inexistente.
                System.out.println(BOLD + PURPLE + "Camino con mayor caudal pleno entre Springfield y San Martín."
                                + RESET);
                System.out.println(
                                BOLD + "Predicción: \n" + RED + "Una o ambas ciudades inexistentes." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoConMayorCaudalPleno(arbolCiudades,
                                                grafoCiudades, hashTuberias, "Springfield", "San Martín de los andes"));
                // Camino con menor caudal pleno con Ciudad Destino inexistente.
                System.out.println(
                                BOLD + PURPLE + "Camino con mayor caudal pleno entre Caviahue y Springfield." + RESET);
                System.out.println(
                                BOLD + "Predicción: \n" + RED + "Una o ambas ciudades inexistentes." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoConMayorCaudalPleno(arbolCiudades,
                                                grafoCiudades, hashTuberias, "Caviahue", "Springfield"));
                // Camino con menor caudal pleno con ambas Ciudades inexistentes.
                System.out.println(BOLD + PURPLE + "Camino con mayor caudal pleno entre Springfield y South Park."
                                + RESET);
                System.out.println(
                                BOLD + "Predicción: \n" + RED + "Una o ambas ciudades inexistentes." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoConMayorCaudalPleno(arbolCiudades,
                                                grafoCiudades, hashTuberias, "Springfield", "South Park"));
                // Camino con menor caudal pleno entre ciudades no conectadas.
                System.out.println(BOLD + PURPLE + "Camino con mayor caudal pleno entre Bariloche y Caviahue." + RESET);
                System.out.println(
                                BOLD + "Predicción: \n" + RED + "Camino inexistente." + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoConMayorCaudalPleno(arbolCiudades,
                                                grafoCiudades, hashTuberias, "Bariloche", "Caviahue"));
                // TESTEANDO.
                System.out.println("");
                System.out.println(BOLD + PURPLE + "TESTING" + RESET);
                System.out.println(BOLD + GREEN
                                + "Todas las consultas que se hacen a las tuberías están testeadas y funcionan \n :)"
                                + RESET);
                // Camino con menor caudal pleno entre Caviahue y San Martín.
                System.out.println(
                                BOLD + PURPLE + "Camino con mayor caudal pleno entre Caviahue y San Martín." + RESET);
                System.out.println(
                                BOLD + "Predicción: \n" + "Camino:" + YELLOW
                                                + "Caviahue->Neuquén->Cipolletti->Allen->Chocón->Bariloche->San Martín. \n"
                                                + RESET + BOLD + "Estado: " + RESET + CYAN + "EN DISEÑO \n" + RESET
                                                + BOLD + "Caudal: "
                                                + RESET + BLUE + "123" + RESET);
                System.out.println(BOLD + "Resultado: \n" + RESET
                                + ConsultasTuberia.getCaminoConMayorCaudalPleno(arbolCiudades,
                                                grafoCiudades, hashTuberias, "Caviahue", "San Martín de los andes"));

        }

}
