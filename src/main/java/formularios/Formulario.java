package formularios;

import clases.Ciudad;
import constantes.Estilos;
import validaciones.Validar;

import java.util.Scanner;

public class Formulario {

    // VARIABLES GLOBALES
    public static final String RESET = Estilos.RESET;
    public static final String BOLD = Estilos.BOLD;
    public static final String ERROR = Estilos.RED;
    public static final String PRINCIPAL_1 = Estilos.BLUE;
    public static final String PRINCIPAL_2 = Estilos.CYAN;
    public static final String PORTADA = Estilos.PURPLE;
    public static final String DESTACADO = Estilos.YELLOW;

    /**
     * Solicita los datos necesarios para crear una nueva ciudad, los controla y la retorna.
     *
     * @param sc Scanner
     * @param numeroCiudad int
     * @return Ciudad
     */
    public static Ciudad crearNuevaCiudad( int numeroCiudad, Scanner sc){

        sc.nextLine();
        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea ingresar: " + RESET);
        String nombre = Validar.textoNoVacio2Caracteres(sc);
        System.out.print(BOLD + "Ingrese la superficie en m2 de esa ciudad: " + RESET);
        double superficie = Validar.numeroReal(sc);
        System.out.print(BOLD + "Ingrese el promedio de consumo diario de agua por día por habitante en M3: " + RESET);
        double consumoPromM3 = Validar.numeroReal(sc);

        return new Ciudad(nombre, numeroCiudad, superficie, consumoPromM3);
    }

    public static String nombreStringValido(Scanner sc){
        System.out.print(BOLD + "Ingrese el nombre de la ciudad que desea dar de baja: " + RESET);
        return Validar.textoNoVacio2Caracteres(sc);
    }

}
