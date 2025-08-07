package formularios;

import clases.Ciudad;
import clases.Tuberia;
import estructuras.conjuntistas.ArbolAVL;
import mensajesPorConsola.Imprimir;
import validaciones.Validar;

public class Formulario {

    /**
     * Solicita los datos necesarios para crear una nueva ciudad, los controla y la retorna.
     *
     * @param numeroCiudad int
     * @return Ciudad
     */
    public static Ciudad altaCiudad(int numeroCiudad) {
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad que desea ingresar: ");
        String nombre = utiles.TecladoIn.readLine();
        Imprimir.mensajeDestacado("Ingrese la superficie en m2 de esa ciudad: ");
        double superficie = utiles.TecladoIn.readDouble();
        Imprimir.mensajeDestacado("Ingrese el promedio de consumo diario de agua por día por habitante en M3: ");
        double consumoDiarioProm = utiles.TecladoIn.readDouble();

        return new Ciudad(nombre, numeroCiudad, superficie, consumoDiarioProm);
    }

    /**
     * Valída si el valor ingresado es válido para un nombre de ciudad, mínimo debe tener 2 carácteres porque
     * si no rompe la convención para generar nomenclaturas de ciudades
     *
     * @return String
     */
    public static String bajaCiudad() {
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad que desea dar de baja: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Valída si el valor ingresado es válido para un nombre de ciudad, mínimo debe tener 2 carácteres porque
     * si no rompe la convención para generar nomenclaturas de ciudades
     *
     * @return String
     */
    public static String modificarCiudadNombreCiudad() {
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad que desea modificar: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @return double[]
     */
    public static double[] modificarCiudadDatos() {
        double[] datosCiudad = new double[2];

        Imprimir.mensajeDestacado("Desea modificar la superficie?\nIngrese 'S' para SI, cualquier otra cosa para NO: ");
        String modSuperficie = utiles.TecladoIn.readLine();
        if (modSuperficie.equalsIgnoreCase("S")) {
            Imprimir.mensajeDestacado("Ingrese el nuevo valor para la superficie en m2: ");
            datosCiudad[0] = Validar.numeroRealNoNegativo();
        } else {
            datosCiudad[0] = -1;
        }
        Imprimir.mensajeDestacado("Desea modificar la cantidad de consumo promedio por día?\nIngrese 'S' para SI, cualquier otra cosa para NO: ");
        String consumoPromDia = utiles.TecladoIn.readLine();
        if (consumoPromDia.equalsIgnoreCase("S")) {
            Imprimir.mensajeDestacado("Ingrese la nueva cantidad de consumo promedio por día por persona en m3: ");
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

        Imprimir.mensajeDestacado("Ingrese el caudal mínimo de la tubería: ");
        double caudalMinimo = Validar.numeroRealNoNegativo();
        Imprimir.mensajeDestacado("Ingrese el caudal máximo de la tubería: ");
        double caudalMaximo = Validar.numeroRealNoNegativo();
        Imprimir.mensajeDestacado("Ingrese el diámetro de la tubería: ");
        double diametro = Validar.numeroRealNoNegativo();

        Imprimir.mensajeDestacado("Indique el Estado de la tubería ingresando un valor según las siguientes opciones: \n");
        Imprimir.mensajeDestacado("[ [1] ACTIVO        ]\n");
        Imprimir.mensajeDestacado("[ [2] INACTIVO      ]\n");
        Imprimir.mensajeDestacado("[ [3] EN DISEÑO     ]\n");
        Imprimir.mensajeDestacado("[ [4] EN REPARACIÓN ]\n");
        Imprimir.mensajeDestacado("Elección: ");
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
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad de origen de la tubería: ");

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
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad de destino de la tubería: ");

        return Validar.existeCiudad(arbolCiudades);
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @return String
     */
    public static String nombreCiudadValido(){
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario para validar un valor de año
     *
     * @return int
     */
    public static int generarAnioValido(){
        Imprimir.mensajeDestacado("Ingrese el año con el formato AAAA numérico: ");

        return Validar.anio();
    }

    /**
     * Formulario para validar un valor de año
     *
     * @return int
     */
    public static int generarMesValido(){
        Imprimir.mensajeDestacado("Ingrese el mes con formato MM: ");

        return Validar.opcionEntreRango(1, 12);
    }

    /**
     * Formulario para validar la cantidad de población ingresada para una fecha
     *
     * @return int
     */
    public static int cantidadPoblacionValido(){
        Imprimir.mensajeDestacado("Ingrese la cantidad de población correspondiente a la fecha indicada: ");

        return Validar.numeroEnteroNoNegativo();
    }

    /**
     * Controla los datos que desea modificar el usuario
     *
     * @return double[]
     */
    public static double[] modificarTuberiaDatosDouble() {
        double[] datosDouble = new double[3];

        Imprimir.mensajeDestacado("Desea modificar el caudal mínimo?\nIngrese 'S' para SI, cualquier otra cosa para NO: ");
        String modCaudalMinimo = utiles.TecladoIn.readLine();
        if (modCaudalMinimo.equalsIgnoreCase("S")) {
            Imprimir.mensajeDestacado("Ingrese el nuevo valor para el caudal mínimo: ");
            datosDouble[0] = Validar.numeroRealNoNegativo();
        } else {
            datosDouble[0] = -1;
        }
        Imprimir.mensajeDestacado("Desea modificar el caudal máximo?\nIngrese 'S' para SI, cualquier otra cosa para NO: ");
        String modCaudalMaximo = utiles.TecladoIn.readLine();
        if (modCaudalMaximo.equalsIgnoreCase("S")) {
            Imprimir.mensajeDestacado("Ingrese el nuevo valor para el caudal máximo: ");
            datosDouble[1] = Validar.numeroRealNoNegativo();
        } else {
            datosDouble[1] = -1;
        }
        Imprimir.mensajeDestacado("Desea modificar el diámetro?\nIngrese 'S' para SI, cualquier otra cosa para NO: ");
        String modDiametro = utiles.TecladoIn.readLine();
        if (modDiametro.equalsIgnoreCase("S")) {
            Imprimir.mensajeDestacado("Ingrese el nuevo valor para el diámetro: ");
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

        Imprimir.mensajeDestacado("Desea modificar el estado?\nIngrese 'S' para SI, cualquier otra cosa para NO: ");
        String modEstado = utiles.TecladoIn.readLine();

        if (modEstado.equalsIgnoreCase("S")) {
            Imprimir.mensajeDestacado("Indique el nuevo estado de la tubería ingresando un valor según las siguientes opciones: \n");
            Imprimir.mensajeDestacado("[ [1] ACTIVO        ]\n");
            Imprimir.mensajeDestacado("[ [2] INACTIVO      ]\n");
            Imprimir.mensajeDestacado("[ [3] EN DISEÑO     ]\n");
            Imprimir.mensajeDestacado("[ [4] EN REPARACIÓN ]\n");
            Imprimir.mensajeDestacado("Elección: ");
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
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad que representa el límite inferior: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario que devuelve un String que representa el nombre de una ciudad
     *
     * @return String
     */
    public static String nombreCiudadLimiteSuperior(){
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad que representa el límite superior: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario que devuelve un real positivo que representa un consumo mínimo de agua indicado
     *
     * @return double
     */
    public static Double consumoMinimo(){
        Imprimir.mensajeDestacado("Ingrese la cantidad de consumo de agua que representa el límite inferior: ");

        return Validar.numeroRealNoNegativo();
    }

    /**
     * Formulario que devuelve un real positivo que representa un consumo máximo de agua indicado
     *
     * @return double
     */
    public static Double consumoMaximo(){
        Imprimir.mensajeDestacado("Ingrese la cantidad de consumo de agua que representa el límite superior: ");

        return Validar.numeroRealNoNegativo();
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @return String
     */
    public static String nombreCiudadOrigen(){
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad de origen: ");

        return Validar.textoNoVacio2Caracteres();
    }

    /**
     * Formulario para validar que el nombre ingresado es válido para una ciudad
     *
     * @return String
     */
    public static String nombreCiudadDestino(){
        Imprimir.mensajeDestacado("Ingrese el nombre de la ciudad de destino: ");

        return Validar.textoNoVacio2Caracteres();
    }

}
