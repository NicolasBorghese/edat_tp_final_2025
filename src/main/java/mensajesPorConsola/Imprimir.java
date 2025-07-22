package mensajesPorConsola;

import constantes.Estilos;


public class Imprimir {

    // VARIABLES GLOBALES
    public static final String RESET = Estilos.RESET;
    public static final String BOLD = Estilos.BOLD;
    public static final String ERROR = Estilos.RED;
    public static final String PRINCIPAL_1 = Estilos.BLUE;
    public static final String PRINCIPAL_2 = Estilos.CYAN;
    public static final String YELLOW = Estilos.YELLOW;

    private static final int ANCHO_MENU = 60;

    public static int menuPrincipal(String nombreMenu) {

        String titulo = OpcionesDeMenu.obtenerMenu(nombreMenu)[0][0];
        String[] opciones = OpcionesDeMenu.obtenerMenu(nombreMenu)[1];

        String lineaSuperior = "╔" + "═".repeat(ANCHO_MENU) + "╗";
        String lineaMedia = "╠" + "═".repeat(ANCHO_MENU) + "╣";
        String lineaInferior = "╚" + "═".repeat(ANCHO_MENU) + "╝";

        System.out.println(PRINCIPAL_1 + lineaSuperior);
        System.out.println(BOLD + centrar("RED DE DISTRIBUCIÓN DE AGUA"));
        System.out.println(centrar(titulo) + RESET);
        System.out.println(PRINCIPAL_1 + lineaMedia + RESET);
        System.out.println(BOLD + PRINCIPAL_1 + alinear("Ingrese:") + RESET);

        for (int i = 0; i < opciones.length; i++) {
            String color = (i % 2 == 0) ? PRINCIPAL_2 : PRINCIPAL_1;
            System.out.printf(color + "║ %-" + (ANCHO_MENU - 2) + "s ║%n" + RESET, opciones[i]);
        }

        System.out.println(PRINCIPAL_1 + lineaInferior + RESET);

        System.out.print(BOLD + "Seleccione una opción: " + RESET);

        return opciones.length;
    }

    private static String centrar(String texto) {
        int espacios = (ANCHO_MENU - texto.length()) / 2;
        return "║" + " ".repeat(Math.max(0, espacios)) + texto + " ".repeat(Math.max(0, ANCHO_MENU - texto.length() - espacios)) + "║";
    }

    private static String alinear(String texto) {
        return "║ " + texto + " ".repeat(ANCHO_MENU - texto.length() - 1) + "║";
    }

    public static void errorOpcionNoEsEntero(){
        System.out.println(ERROR + "Por favor, ingrese un número válido." + RESET);
        System.out.print(BOLD + "Seleccione una opción: " + RESET);
    }

    public static void errorOpcionExcedeRango(int cantOpciones){
        System.out.println(ERROR + "La opción debe estar entre 0 y " + cantOpciones + "." + RESET);
        System.out.print(BOLD + "Seleccione una opción: " + RESET);
    }

    public static void finDeEjecucion(){
        System.out.println(BOLD + YELLOW + "El programa ha finalizado" + RESET);
    }
}
