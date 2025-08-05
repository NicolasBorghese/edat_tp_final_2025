package formularios;

import clases.Ciudad;
import clases.Tuberia;
import constantes.Estilos;
import estructuras.conjuntistas.ArbolAVL;
import validaciones.Validar;

import java.util.Scanner;

public class Formulario {

    // VARIABLES GLOBALES
    public static final String RESET = Estilos.RESET;
    public static final String BOLD = Estilos.BOLD;

    /**
     * Solicita los datos necesarios para crear una nueva ciudad, los controla y la retorna.
     *
     * @param numeroCiudad int
     * @param sc           Scanner
     * @return Ciudad
     */
    public static Ciudad altaCiudad(int numeroCiudad, Scanner sc) {
        sc.nextLine();

        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea ingresar: " + RESET);
        String nombre = Validar.textoNoVacio2Caracteres(sc);
        System.out.print(BOLD + "Ingrese la superficie en m2 de esa ciudad: " + RESET);
        double superficie = Validar.numeroReal(sc);
        System.out.print(BOLD + "Ingrese el promedio de consumo diario de agua por día por habitante en M3: " + RESET);
        double consumoPromM3 = Validar.numeroReal(sc);

        return new Ciudad(nombre, numeroCiudad, superficie, consumoPromM3);
    }

    /**
     * Valida si el valor ingresado es válido para un nombre de ciudad, mínimo debe tener 2 carácteres porque
     * sino rompe la convención para generar nomenclaturas de ciudades
     *
     * @param sc Scaner
     * @return String
     */
    public static String bajaCiudad(Scanner sc) {
        sc.nextLine();

        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea dar de baja: " + RESET);

        return Validar.textoNoVacio2Caracteres(sc);
    }

    /**
     * Valida si el valor ingresado es válido para un nombre de ciudad, mínimo debe tener 2 carácteres porque
     * sino rompe la convención para generar nomenclaturas de ciudades
     *
     * @param sc Scaner
     * @return String
     */
    public static String modificarCiudadNombreCiudad(Scanner sc) {
        sc.nextLine();

        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea modificar: " + RESET);

        return Validar.textoNoVacio2Caracteres(sc);
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @param sc Scaner
     * @return double[]
     */
    public static double[] modificarCiudadDatos(Scanner sc) {
        sc.nextLine();
        double[] datosCiudad = new double[2];

        System.out.print(BOLD + "Desea modificar la superficie? Ingrese 's' para SI, cualquier otra cosa para no: " + RESET);
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para la superficie en m2: " + RESET);
            datosCiudad[0] = Validar.numeroRealNoNegativo(sc);
        } else {
            datosCiudad[0] = -1;
        }
        sc.nextLine();
        System.out.print(BOLD + "Desea modificar la cantidad de consumo promedio por día? Ingrese 's' para SI, cualquier otra cosa para no: " + RESET);
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print(BOLD + "Ingrese la nueva cantidad de consumo promedio por día por persona en m3: " + RESET);
            datosCiudad[1] = Validar.numeroRealNoNegativo(sc);
        } else {
            datosCiudad[1] = -1;
        }

        return datosCiudad;
    }



    /**
     * Formulario a completar para dar de alta una tubería en el sistema
     *
     * @param ciudadOrigen Ciudad
     * @param ciudadDestino Ciudad
     * @param sc Scanner
     * @return Tuberia
     */
    public static Tuberia altaTuberia(Ciudad ciudadOrigen, Ciudad ciudadDestino, Scanner sc) {
        sc.nextLine();

        String nomenclatura = Tuberia.crearNomenclatura(ciudadOrigen, ciudadDestino);

        System.out.print(BOLD + "Ingrese el caudal mínimo de la tubería: " + RESET);
        double caudalMinimo = Validar.numeroRealNoNegativo(sc);
        System.out.print(BOLD + "Ingrese el caudal máximo de la tubería: " + RESET);
        double caudalMaximo = Validar.numeroRealNoNegativo(sc);
        System.out.print(BOLD + "Ingrese el diametro de la tubería: " + RESET);
        double diametro = Validar.numeroRealNoNegativo(sc);
        System.out.println(BOLD + "Indique el Estado de la tubería ingresando un valor según las siguientes opciones: " + RESET);
        System.out.println(BOLD + "[ [1] ACTIVO        ]" + RESET);
        System.out.println(BOLD + "[ [2] INACTIVO      ]" + RESET);
        System.out.println(BOLD + "[ [3] EN DISEÑO     ]" + RESET);
        System.out.println(BOLD + "[ [4] EN REPARACIÓN ]" + RESET);
        int opcionElegida = Validar.opcionEntreRango(1, 4, sc);

        String estado;

        switch (opcionElegida) {
            case 1:
                estado = "ACTIVO";
                break;
            case 2:
                estado = "INACTIVO";
                break;
            case 3:
                estado = "EN DISEÑO";
                break;
            case 4:
                estado = "EN REPARACIÓN";
                break;
            default:
                estado = "ACTIVO";
                break;
        }

        return new Tuberia(nomenclatura, caudalMinimo, caudalMaximo, diametro, estado);
    }

    /**
     * Formulario para validar que la ciudad de origen existe
     *
     * @param arbolCiudades ArbolAVL
     * @param sc Scanner
     * @return Ciudad
     */
    public static Ciudad existeCiudadOrigen(ArbolAVL arbolCiudades, Scanner sc){
        sc.nextLine();
        System.out.print(BOLD + "Ingrese el nombre de la ciudad de origen de la tubería: " + RESET);

        return Validar.existeCiudad(arbolCiudades, sc);
    }

    /**
     * Formulario para validar que la ciudad de destino existe
     * TODO FALTA IMPLEMENTAR QUE NO SE PUEDAN REPETIR CON LA DE ORIGEN
     *
     * @param arbolCiudades ArbolVL
     * @param ciudadOrigen Ciudad
     * @param sc Scanner
     * @return Ciudad
     */
    public static Ciudad existeCiudadDestino(ArbolAVL arbolCiudades, Ciudad ciudadOrigen, Scanner sc){
        sc.nextLine();

        System.out.print(BOLD + "Ingrese el nombre de la ciudad de destino de la tubería: " + RESET);

        return Validar.existeCiudad(arbolCiudades, sc);
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @param sc Scanner
     * @return String
     */
    public static String nombreCiudadValido(Scanner sc){

        System.out.print(BOLD + "Ingrese el nombre de la ciudad: " + RESET);

        return Validar.textoNoVacio2Caracteres(sc);
    }


    /**
     * Formulario para validar un valor de año
     *
     * @param sc Scanner
     * @return int
     */
    public static int generarAnioValido(Scanner sc){

        System.out.print(BOLD + "Ingrese el año con el formato AAAA numérico: " + RESET);

        return Validar.anio(sc);
    }

    /**
     * Formulario para validar un valor de año
     *
     * @param sc Scanner
     * @return int
     */
    public static int generarMesValido(Scanner sc){

        System.out.print(BOLD + "Ingrese el mes con formato MM: " + RESET);

        return Validar.opcionEntreRango(1, 12, sc);
    }

    /**
     * Formulario para validar la cantidad de población ingresada para una fecha
     *
     * @param sc Scanner
     * @return int
     */
    public static int cantidadPoblacionValido(Scanner sc){

        System.out.print(BOLD + "Ingrese la cantidad de población correspondiente a la fecha indicada: " + RESET);

        return Validar.numeroEnteroNoNegativo(sc);
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @param sc Scaner
     * @return double[]
     */
    public static double[] modificarTuberiaDatosDouble(Scanner sc) {
        sc.nextLine();
        double[] datosDouble = new double[3];

        System.out.print(BOLD + "Desea modificar el caudal mínimo? Ingrese 's' para SI, cualquier otra cosa para no: " + RESET);
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para el caudal mínimo: " + RESET);
            datosDouble[0] = Validar.numeroRealNoNegativo(sc);
        } else {
            datosDouble[0] = -1;
        }
        sc.nextLine();
        System.out.print(BOLD + "Desea modificar el caudal máximo? Ingrese 's' para SI, cualquier otra cosa para no: " + RESET);
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para el caudal máximo: " + RESET);
            datosDouble[1] = Validar.numeroRealNoNegativo(sc);
        } else {
            datosDouble[1] = -1;
        }
        sc.nextLine();
        System.out.print(BOLD + "Desea modificar el diametro? Ingrese 's' para SI, cualquier otra cosa para no: " + RESET);
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para el diametro: " + RESET);
            datosDouble[2] = Validar.numeroRealNoNegativo(sc);
        } else {
            datosDouble[2] = -1;
        }

        return datosDouble;
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @param sc Scaner
     * @return String[]
     */
    public static String[] modificarTuberiaDatosString(Scanner sc) {
        sc.nextLine();
        String[] datosString = new String[1];

        System.out.print(BOLD + "Desea modificar el estado? Ingrese 's' para SI, cualquier otra cosa para no: " + RESET);
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.println(BOLD + "Indique el Estado de la tubería ingresando un valor según las siguientes opciones: " + RESET);
            System.out.println(BOLD + "[ [1] ACTIVO        ]" + RESET);
            System.out.println(BOLD + "[ [2] INACTIVO      ]" + RESET);
            System.out.println(BOLD + "[ [3] EN DISEÑO     ]" + RESET);
            System.out.println(BOLD + "[ [4] EN REPARACIÓN ]" + RESET);
            int opcionElegida = Validar.opcionEntreRango(1, 4, sc);

            switch (opcionElegida) {
                case 1:
                    datosString[0] = "ACTIVO";
                    break;
                case 2:
                    datosString[0] = "INACTIVO";
                    break;
                case 3:
                    datosString[0] = "EN DISEÑO";
                    break;
                case 4:
                    datosString[0] = "EN REPARACIÓN";
                    break;
                default:
                    datosString[0] = "ACTIVO";
                    break;
            }
        } else {
            datosString[0] = null;
        }

        return datosString;
    }


}
