package mensajesPorConsola;

import clases.ClaveTuberia;
import clases.Tuberia;
import constantes.Estilos;
import estructuras.conjuntistas.ArbolAVL;
import estructuras.lineales.dinamicas.Lista;

import java.util.HashMap;
import java.util.Map;

public class Imprimir {

    // VARIABLES GLOBALES
    public static final String RESET = Estilos.RESET;
    public static final String BOLD = Estilos.BOLD;
    public static final String ERROR = Estilos.RED;
    public static final String EXITO = Estilos.GREEN;
    public static final String PRINCIPAL_1 = Estilos.BLUE;
    public static final String PRINCIPAL_2 = Estilos.CYAN;
    public static final String PORTADA = Estilos.PURPLE;
    public static final String RESALTADO = Estilos.YELLOW;

    private static final int ANCHO_MENU = 60;

    // ███╗   ███╗███████╗███╗   ██╗██╗   ██╗
    // ████╗ ████║██╔════╝████╗  ██║██║   ██║
    // ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║
    // ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║
    // ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝
    // ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝
    //
    // ██████╗ ██████╗ ██╗███╗   ██╗ ██████╗██╗██████╗  █████╗ ██╗
    // ██╔══██╗██╔══██╗██║████╗  ██║██╔════╝██║██╔══██╗██╔══██╗██║
    // ██████╔╝██████╔╝██║██╔██╗ ██║██║     ██║██████╔╝███████║██║
    // ██╔═══╝ ██╔══██╗██║██║╚██╗██║██║     ██║██╔═══╝ ██╔══██║██║
    // ██║     ██║  ██║██║██║ ╚████║╚██████╗██║██║     ██║  ██║███████╗
    // ╚═╝     ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝╚═╝╚═╝     ╚═╝  ╚═╝╚══════╝

    public static int menuDeOpciones(String nombreMenu) {

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
        return "║" + " ".repeat(Math.max(0, espacios)) + texto
                + " ".repeat(Math.max(0, ANCHO_MENU - texto.length() - espacios)) + "║";
    }

    private static String alinear(String texto) {
        return "║ " + texto + " ".repeat(ANCHO_MENU - texto.length() - 1) + "║";
    }

    public static void continuarEjecucion() {
        System.out.print(BOLD + RESALTADO + "Presione [ENTER] para continuar. " + RESET);
    }

    public static void finDeEjecucion() {
        System.out.println(BOLD + RESALTADO + "EL PROGRAMA HA FINALIZADO" + RESET);
    }

    // ███╗   ███╗███████╗███╗   ██╗███████╗ █████╗      ██╗███████╗███████╗
    // ████╗ ████║██╔════╝████╗  ██║██╔════╝██╔══██╗     ██║██╔════╝██╔════╝
    // ██╔████╔██║█████╗  ██╔██╗ ██║███████╗███████║     ██║█████╗  ███████╗
    // ██║╚██╔╝██║██╔══╝  ██║╚██╗██║╚════██║██╔══██║██   ██║██╔══╝  ╚════██║
    // ██║ ╚═╝ ██║███████╗██║ ╚████║███████║██║  ██║╚█████╔╝███████╗███████║
    // ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚════╝ ╚══════╝╚══════╝
    //
    // ███████╗███████╗████████╗██╗██╗      █████╗ ██████╗  ██████╗ ███████╗
    // ██╔════╝██╔════╝╚══██╔══╝██║██║     ██╔══██╗██╔══██╗██╔═══██╗██╔════╝
    // █████╗  ███████╗   ██║   ██║██║     ███████║██║  ██║██║   ██║███████╗
    // ██╔══╝  ╚════██║   ██║   ██║██║     ██╔══██║██║  ██║██║   ██║╚════██║
    // ███████╗███████║   ██║   ██║███████╗██║  ██║██████╔╝╚██████╔╝███████║
    // ╚══════╝╚══════╝   ╚═╝   ╚═╝╚══════╝╚═╝  ╚═╝╚═════╝  ╚═════╝ ╚══════╝

    /**
     * - Imprime en pantalla un mensaje estilado como error sin salto incorporado. <br>
     * - Utilice "\n" si desea incorporar saltos de línea en su mensaje
     *
     * @param mensaje String: Es el contenido del mensaje
     */
    public static void mensajeError(String mensaje) {
        System.out.print(BOLD + ERROR + mensaje + RESET);
    }

    /**
     * - Imprime en pantalla un mensaje estilado como éxito sin salto incorporado. <br>
     * - Utilice "\n" si desea incorporar saltos de línea en su mensaje
     *
     * @param mensaje String: Es el contenido del mensaje
     */
    public static void mensajeExito(String mensaje) {
        System.out.print(BOLD + EXITO + mensaje + RESET);
    }

    /**
     * - Imprime en pantalla un mensaje estilado como título sin salto incorporado. <br>
     * - Utilice "\n" si desea incorporar saltos de línea en su mensaje
     *
     * @param mensaje String
     */
    public static void mensajeTitulo(String mensaje) {
        System.out.print(BOLD + RESALTADO + mensaje + RESET);
    }

    /**
     * - Imprime en pantalla un mensaje estilado con bold sin salto incorporado. <br>
     * - Utilice "\n" si desea incorporar saltos de línea en su mensaje
     *
     * @param mensaje String
     */
    public static void mensajeDestacado(String mensaje) {
        System.out.print(BOLD + mensaje + RESET);
    }

    // ██╗   ██╗ █████╗ ██████╗ ██╗ ██████╗ ███████╗
    // ██║   ██║██╔══██╗██╔══██╗██║██╔═══██╗██╔════╝
    // ██║   ██║███████║██████╔╝██║██║   ██║███████╗
    // ╚██╗ ██╔╝██╔══██║██╔══██╗██║██║   ██║╚════██║
    //  ╚████╔╝ ██║  ██║██║  ██║██║╚██████╔╝███████║
    //   ╚═══╝  ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝ ╚══════╝

    /**
     * Imprime en pantalla el contenido de la estructura del HashMap de tuberías
     * y a su vez la retorna
     *
     * @param hashTuberias HashMap<ClaveTuberia, Tuberia>
     * @return String
     */
    public static String hashMapTuberias(HashMap<ClaveTuberia, Tuberia> hashTuberias) {

        String estructura;

        if (hashTuberias.isEmpty()) {
            estructura = "La estructura HashMap de tuberías se encuentra vacía.";
            System.out.print(estructura);
        } else {
            estructura = "Contenido del HashMap de tuberías: ";
            System.out.println(estructura);
            for (Map.Entry<ClaveTuberia, Tuberia> entry : hashTuberias.entrySet()) {
                String claveValor = "- Clave: " + entry.getKey() + " | Valor: " + entry.getValue().toString() + "\n";
                System.out.print(claveValor);
                estructura += claveValor;
            }
        }

        return estructura;
    }

    /**
     * Imprime en pantalla el contenido de la estructura del ArbolAVL de Ciudades
     *
     * @param arbolCiudades ArbolAVL
     */
    public static void arbolAVLCiudadesAlfabetico(ArbolAVL arbolCiudades) {

        if (arbolCiudades.vacio()) {
            System.out.println("La estructura ArbolAVL de ciudades se encuentra vacía.");
        } else {
            System.out.println("Contenido del ArbolAVL de ciudades: ");
            Lista listaCiudades = arbolCiudades.listar();
            int cantElem = listaCiudades.longitud();
            for (int i = 1; i <= cantElem; i++) {
                System.out.println(listaCiudades.recuperar(i).toString());
            }
        }
    }

    /**
     * Font: ANSI -> ANSI Shadow
     */
    public static void portada() {
        String[] art = {
                BOLD + PORTADA +
                "███████╗██████╗  █████╗ ████████╗                          ",
                "██╔════╝██╔══██╗██╔══██╗╚══██╔══╝                          ",
                "█████╗  ██║  ██║███████║   ██║                             ",
                "██╔══╝  ██║  ██║██╔══██║   ██║                             ",
                "███████╗██████╔╝██║  ██║   ██║                             ",
                "╚══════╝╚═════╝ ╚═╝  ╚═╝   ╚═╝                             ",
                "                                                           ",
                "████████╗██████╗  █████╗ ██████╗  █████╗      ██╗ ██████╗ ",
                "╚══██╔══╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗     ██║██╔═══██╗",
                "   ██║   ██████╔╝███████║██████╔╝███████║     ██║██║   ██║",
                "   ██║   ██╔══██╗██╔══██║██╔══██╗██╔══██║██   ██║██║   ██║",
                "   ██║   ██║  ██║██║  ██║██████╔╝██║  ██║╚█████╔╝╚██████╔╝",
                "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝ ╚════╝  ╚═════╝ ",
                "                                                           ",
                "███████╗██╗███╗   ██╗ █████╗ ██╗                           ",
                "██╔════╝██║████╗  ██║██╔══██╗██║                           ",
                "█████╗  ██║██╔██╗ ██║███████║██║                           ",
                "██╔══╝  ██║██║╚██╗██║██╔══██║██║                           ",
                "██║     ██║██║ ╚████║██║  ██║███████╗                      ",
                "╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝                      " + RESET
        };

        for (String line : art) {
            System.out.println(line);
        }
        integrantes();
    }

    /**
     * Font: Outline -> big
     */
    public static void portada1() {
        System.out.println(
                " ______ _____       _______                          \n" +
                        "|  ____|  __ \\   /\\|__   __|                         \n" +
                        "| |__  | |  | | /  \\  | |                            \n" +
                        "|  __| | |  | |/ /\\ \\ | |                            \n" +
                        "| |____| |__| / ____ \\| |                            \n" +
                        "|______|_____/_/    \\_\\_| ____              _  ____  \n" +
                        "|__   __|  __ \\     /\\   |  _ \\   /\\       | |/ __ \\ \n" +
                        "   | |  | |__) |   /  \\  | |_) | /  \\      | | |  | |\n" +
                        "   | |  |  _  /   / /\\ \\ |  _ < / /\\ \\ _   | | |  | |\n" +
                        "   | |  | | \\ \\  / ____ \\| |_) / ____ \\ |__| | |__| |\n" +
                        " __|_|_ |_|__\\_\\/_/    \\_\\____/_/    \\_\\____/ \\____/ \n" +
                        "|  ____|_   _| \\ | |   /\\   | |                      \n" +
                        "| |__    | | |  \\| |  /  \\  | |                      \n" +
                        "|  __|   | | | . ` | / /\\ \\ | |                      \n" +
                        "| |     _| |_| |\\  |/ ____ \\| |____                  \n" +
                        "|_|    |_____|_| \\_/_/    \\_\\______|                 ");
        integrantes();
    }

    /**
     * Font: Outline -> Doom
     */
    public static void portada2() {
        String[] art = {
                BOLD + PORTADA + "                     ___________  ___ _____",
                "                    |  ___|  _  \\/ _ \\_   _|",
                "                    | |__ | | | / /_\\ \\| |",
                "                    |  __|| | | |  _  || |",
                "                    | |___| |/ /| | | || |",
                "                    \\____/|___/ \\_| |_/\\_/",
                "         ___________  ___  ______  ___     ___  _____ ",
                "        |_   _| ___ \\/ _ \\ | ___ \\/ _ \\   |_  ||  _  |",
                "          | | | |_/ / /_\\ \\| |_/ / /_\\ \\    | || | | |",
                "          | | |    /|  _  || ___ \\  _  |    | || | | |",
                "          | | | |\\ \\| | | || |_/ / | | |/\\__/ /\\ \\_/ /",
                "          \\_/ \\_| \\_\\_| |_/\\____/\\_| |_/\\____/  \\___/ ",
                "                ______ _____ _   _   ___   _",
                "                |  ___|_   _| \\ | | / _ \\ | |",
                "                | |_    | | |  \\| |/ /_\\ \\| |",
                "                |  _|   | | | . ` ||  _  || |",
                "                | |    _| |_| |\\  || | | || |____ ",
                "                \\_|    \\___/\\_| \\_/\\_| |_/\\_____/" + RESET
        };

        for (String line : art) {
            System.out.println(line);
        }
        integrantes();
    }

    public static void integrantes() {
        System.out.println();
        System.out.println(BOLD + RESALTADO + "Alzuguren Santiago FAI-4691" + RESET);
        System.out.println(BOLD + RESALTADO + "Borghese Nicolas FAI-997" + RESET);
        System.out.println(BOLD + RESALTADO + "Piacentini Gonzalo FAI-2514" + RESET);
        System.out.println();
    }
}
