package formularios;

import clases.Ciudad;
import clases.Tuberia;
import constantes.Estilos;
import estructuras.conjuntistas.ArbolAVL;
import mensajesPorConsola.Imprimir;
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
     * @return Ciudad
     */
    public static Ciudad altaCiudad(int numeroCiudad) {

        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea ingresar: " + RESET);
        String nombre = utiles.TecladoIn.readLine();
        System.out.print(BOLD + "Ingrese la superficie en m2 de esa ciudad: " + RESET);
        double superficie = utiles.TecladoIn.readDouble();
        System.out.print(BOLD + "Ingrese el promedio de consumo diario de agua por día por habitante en M3: " + RESET);
        double consumoDiarioProm = utiles.TecladoIn.readDouble();

        return new Ciudad(nombre, numeroCiudad, superficie, consumoDiarioProm);
    }

    /**
     * Valida si el valor ingresado es válido para un nombre de ciudad, mínimo debe tener 2 carácteres porque
     * sino rompe la convención para generar nomenclaturas de ciudades
     *
     * @return String
     */
    public static String bajaCiudad() {

        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea dar de baja: " + RESET);

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Valida si el valor ingresado es válido para un nombre de ciudad, mínimo debe tener 2 carácteres porque
     * sino rompe la convención para generar nomenclaturas de ciudades
     *
     * @return String
     */
    public static String modificarCiudadNombreCiudad() {

        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea modificar: " + RESET);

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @return double[]
     */
    public static double[] modificarCiudadDatos() {

        double[] datosCiudad = new double[2];

        System.out.print(BOLD + "Desea modificar la superficie?\nIngrese 'S' para SI, cualquier otra cosa para NO: " + RESET);
        String modSuperficie = utiles.TecladoIn.readLine();
        if (modSuperficie.equalsIgnoreCase("S")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para la superficie en m2: " + RESET);
            datosCiudad[0] = Validar.numeroRealNoNegativo();
        } else {
            datosCiudad[0] = -1;
        }
        System.out.print(BOLD + "Desea modificar la cantidad de consumo promedio por día?\nIngrese 'S' para SI, cualquier otra cosa para NO: " + RESET);
        String consumoPromDia = utiles.TecladoIn.readLine();
        if (consumoPromDia.equalsIgnoreCase("S")) {
            System.out.print(BOLD + "Ingrese la nueva cantidad de consumo promedio por día por persona en m3: " + RESET);
            datosCiudad[1] = Validar.numeroRealNoNegativo();
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
     * @return Tuberia
     */
    public static Tuberia altaTuberia(Ciudad ciudadOrigen, Ciudad ciudadDestino) {

        String nomenclatura = Tuberia.crearNomenclatura(ciudadOrigen, ciudadDestino);

        System.out.print(BOLD + "Ingrese el caudal mínimo de la tubería: " + RESET);
        double caudalMinimo = Validar.numeroRealNoNegativo();
        System.out.print(BOLD + "Ingrese el caudal máximo de la tubería: " + RESET);
        double caudalMaximo = Validar.numeroRealNoNegativo();
        System.out.print(BOLD + "Ingrese el diámetro de la tubería: " + RESET);
        double diametro = Validar.numeroRealNoNegativo();
        System.out.println(BOLD + "Indique el Estado de la tubería ingresando un valor según las siguientes opciones: " + RESET);
        System.out.println(BOLD + "[ [1] ACTIVO        ]" + RESET);
        System.out.println(BOLD + "[ [2] INACTIVO      ]" + RESET);
        System.out.println(BOLD + "[ [3] EN DISEÑO     ]" + RESET);
        System.out.println(BOLD + "[ [4] EN REPARACIÓN ]" + RESET);
        int opcionElegida = Validar.opcionEntreRango(1, 4);

        String estado;

        switch (opcionElegida) {
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
     * @return Ciudad
     */
    public static Ciudad existeCiudadOrigen(ArbolAVL arbolCiudades){

        System.out.print(BOLD + "Ingrese el nombre de la ciudad de origen de la tubería: " + RESET);

        return Validar.existeCiudad(arbolCiudades);
    }

    /**
     * Formulario para validar que la ciudad de destino existe
     * TODO FALTA IMPLEMENTAR QUE NO SE PUEDAN REPETIR CON LA DE ORIGEN
     *
     * @param arbolCiudades ArbolVL
     * @param ciudadOrigen Ciudad
     * @return Ciudad
     */
    public static Ciudad existeCiudadDestino(ArbolAVL arbolCiudades, Ciudad ciudadOrigen){

        System.out.print(BOLD + "Ingrese el nombre de la ciudad de destino de la tubería: " + RESET);

        return Validar.existeCiudad(arbolCiudades);
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @return String
     */
    public static String nombreCiudadValido(){

        System.out.print(BOLD + "Ingrese el nombre de la ciudad: " + RESET);

        return Validar.textoNoVacio2Caracteres();
    }


    /**
     * Formulario para validar un valor de año
     *
     * @return int
     */
    public static int generarAnioValido(){

        System.out.print(BOLD + "Ingrese el año con el formato AAAA numérico: " + RESET);

        return Validar.anio();
    }

    /**
     * Formulario para validar un valor de año
     *
     * @return int
     */
    public static int generarMesValido(){

        System.out.print(BOLD + "Ingrese el mes con formato MM: " + RESET);

        return Validar.opcionEntreRango(1, 12);
    }

    /**
     * Formulario para validar la cantidad de población ingresada para una fecha
     *
     * @return int
     */
    public static int cantidadPoblacionValido(){

        System.out.print(BOLD + "Ingrese la cantidad de población correspondiente a la fecha indicada: " + RESET);

        return Validar.numeroEnteroNoNegativo();
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @return double[]
     */
    public static double[] modificarTuberiaDatosDouble() {

        double[] datosDouble = new double[3];

        System.out.print(BOLD + "Desea modificar el caudal mínimo?\nIngrese 'S' para SI, cualquier otra cosa para NO: " + RESET);
        String modCaudalMinimo = utiles.TecladoIn.readLine();
        if (modCaudalMinimo.equalsIgnoreCase("S")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para el caudal mínimo: " + RESET);
            datosDouble[0] = Validar.numeroRealNoNegativo();
        } else {
            datosDouble[0] = -1;
        }
        System.out.print(BOLD + "Desea modificar el caudal máximo?\nIngrese 'S' para SI, cualquier otra cosa para NO: " + RESET);
        String modCaudalMaximo = utiles.TecladoIn.readLine();
        if (modCaudalMaximo.equalsIgnoreCase("S")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para el caudal máximo: " + RESET);
            datosDouble[1] = Validar.numeroRealNoNegativo();
        } else {
            datosDouble[1] = -1;
        }
        System.out.print(BOLD + "Desea modificar el diámetro?\nIngrese 'S' para SI, cualquier otra cosa para NO: " + RESET);
        String modDiametro = utiles.TecladoIn.readLine();
        if (modDiametro.equalsIgnoreCase("S")) {
            System.out.print(BOLD + "Ingrese el nuevo valor para el diámetro: " + RESET);
            datosDouble[2] = Validar.numeroRealNoNegativo();
        } else {
            datosDouble[2] = -1;
        }

        return datosDouble;
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @return String[]
     */
    public static String[] modificarTuberiaDatosString() {

        String[] datosString = new String[1];

        System.out.print(BOLD + "Desea modificar el estado? Ingrese 'S' para SI, cualquier otra cosa para NO: " + RESET);
        String modEstado = utiles.TecladoIn.readLine();
        if (modEstado.equalsIgnoreCase("S")) {
            System.out.println(BOLD + "Indique el Estado de la tubería ingresando un valor según las siguientes opciones: " + RESET);
            System.out.println(BOLD + "[ [1] ACTIVO        ]" + RESET);
            System.out.println(BOLD + "[ [2] INACTIVO      ]" + RESET);
            System.out.println(BOLD + "[ [3] EN DISEÑO     ]" + RESET);
            System.out.println(BOLD + "[ [4] EN REPARACIÓN ]" + RESET);
            int opcionElegida = Validar.opcionEntreRango(1, 4);

            switch (opcionElegida) {
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

    /**
     * Formulario que devuelve un String que corresponde al nombre de una ciudad
     *
     * @return String
     */
    public static String nombreCiudadLimiteInferior(){

        Imprimir.mensajeFormularioSinSalto("Ingrese el nombre de la ciudad que representa el límite inferior: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario que devuelve un String que representa el nombre de una ciudad
     *
     * @return String
     */
    public static String nombreCiudadLimiteSuperior(){

        Imprimir.mensajeFormularioSinSalto("Ingrese el nombre de la ciudad que representa el límite superior: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario que devuelve un real positivo que representa un consumo mínimo de agua indicado
     *
     * @return double
     */
    public static Double consumoMinimo(){

        Imprimir.mensajeFormularioSinSalto("Ingrese la cantidad de consumo de agua que representa el límite inferior: ");

        return Validar.numeroRealNoNegativo();
    }

    /**
     * Formulario que devuelve un real positivo que representa un consumo máximo de agua indicado
     *
     * @return double
     */
    public static Double consumoMaximo(){

        Imprimir.mensajeFormularioSinSalto("Ingrese la cantidad de consumo de agua que representa el límite superior: ");

        return Validar.numeroRealNoNegativo();
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @return String
     */
    public static String nombreCiudadOrigen(){

        System.out.print(BOLD + "Ingrese el nombre de la ciudad de origen: " + RESET);

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @return String
     */
    public static String nombreCiudadDestino(){

        System.out.print(BOLD + "Ingrese el nombre de la ciudad de destino: " + RESET);

        return Validar.textoNoVacio2Caracteres();
    }

}
