package main;
import java.util.Scanner;
public class TransporteAgua {
    // Colores ANSI
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        do {
            mostrarMenu();
            System.out.print(BOLD + "Seleccione una opción: " + RESET);

            // Validación para que sea un int.
            while (!sc.hasNextInt()) {
                System.out.println(RED + "Por favor, ingrese un número válido." + RESET);
                System.out.print(BOLD + "Seleccione una opción: " + RESET);
                sc.next();
            }

            opcion = sc.nextInt();

            // Validación para que sea número válido.
            while (opcion < 0 || opcion > 10) {
                System.out.println(RED + "La opción debe estar entre 0 y 10." + RESET);
                System.out.print(BOLD + "Seleccione una opción: " + RESET);
                while (!sc.hasNextInt()) {
                    System.out.println(RED + "Por favor, ingrese un número válido." + RESET);
                    System.out.print(BOLD + "Seleccione una opción: " + RESET);
                    sc.next();
                }
                opcion = sc.nextInt();
            }
        } while (opcion != 0);
        sc.close();
    }

    public static void mostrarMenu() {
        String[] opciones = {
                "0. Salir.",
                "1.  Agregar una ciudad. ",
                "2.  Dar de baja una ciudad. ",
                "3.  Modificar una ciudad. ",
                "4.  Agregar una tubería. ",
                "5.  Dar de baja una tubería. ",
                "6.  Modificar una tubería. ",
                "7.  Ver el Ranking de ciudades según el consumo. ",
                "8.  Ver el árbol AVL de ciudades. ",
                "9.  Ver el Grafo Etiquetado. ",
                "10. Ver el HashMap. "
        };

        String lineaSuperior = "╔══════════════════════════════════════════════════════════╗";
        String lineaMedia = "╠══════════════════════════════════════════════════════════╣";
        String lineaInferior = "╚══════════════════════════════════════════════════════════╝";

        System.out.println(BLUE + lineaSuperior);
        System.out.println(BOLD + "║                    BIENVENIDO AL MENÚ                    ║");
        System.out.println("║               RED DE DISTRIBUCIÓN DE AGUA                ║" + RESET);
        System.out.println(BLUE + lineaMedia + RESET);
        System.out.println(BOLD + BLUE + "║ Ingrese:                                                 ║" + RESET);

        for (int i = 0; i < opciones.length; i++) {
            String color = (i % 2 == 0) ? CYAN : BLUE;
            System.out.printf(color + "║ %-56s ║%n" + RESET, opciones[i]);
        }

        System.out.println(BLUE + lineaInferior + RESET);
    }
}

