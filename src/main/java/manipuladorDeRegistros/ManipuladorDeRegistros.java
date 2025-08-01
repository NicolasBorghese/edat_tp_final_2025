package manipuladorDeRegistros;


import constantes.Rutas;
import estructuras.lineales.dinamicas.Lista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class ManipuladorDeRegistros {

    final static String RUTA_LOG = Rutas.RUTA_LOG;

    /**
     * Abre, lee y devuelve los contenidos de un archivo como String
     * @param ruta La ruta del archivo para abrir y leer
     * @return Una Lista donde cada elemento es un arreglo que contiene los campos de una tupla
     */
    public static Lista obtenerRegistros(String ruta) {

        Lista listaGeneral = new Lista();
        int posicion = 1;

        try {
            FileReader fileReader = new FileReader(ruta);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {// Leo hasta que me quede sin líneas
                String[] tupla =  linea.split(";");
                listaGeneral.insertar(tupla, posicion);
                posicion++;
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {    // Posible error en el constructor de FileReader
            System.err.println(ex.getMessage() + "no se encontró el archivo o no puede ser abierto para lectura");
        } catch (IOException ex) {              // Posible error en bufferedReader.readLine
            System.err.println(ex.getMessage() + "error de lectura");
        }

        return listaGeneral;
    }

    /**
     * Abre, lee y devuelve los contenidos de un archivo como String
     * @param ruta La ruta del archivo para abrir y leer
     * @return Un String con los contenidos del archivo. Si surge un error devuelve una cadena vacía.
     */
    public static String[] obtenerContenidosPorTupla(String ruta) {
        StringBuilder contenidos = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(ruta);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {// Leo hasta que me quede sin líneas
                contenidos.append(linea);
                contenidos.append("\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {    // Posible error en el constructor de FileReader
            System.err.println(ex.getMessage() + "no se encontró el archivo o no puede ser abierto para lectura");
        } catch (IOException ex) {              // Posible error en bufferedReader.readLine
            System.err.println(ex.getMessage() + "error de lectura");
        }

        return contenidos.toString().split("\n");
    }

    /**
     * Lee una colección de registros, una ruta de archivo y se encarga de sobreescribir ese archivo
     * con los nuevos registros generados
     * @param ruta - Dirección del archivo donde se deben escribir los nuevos registros
     * @param nuevosRegistros - Arreglo bidimensional donde cada columna es un campo y cada fila es una tupla
     * @param continuarRegistro - Si es true continúa registrando datos sin borrar los anteriores, si es false sobrescribe los anteriores
     */
    public static void escribirRegistros(String ruta, String[][] nuevosRegistros, boolean continuarRegistro) {
        try {
            FileWriter fr = new FileWriter(ruta, continuarRegistro);

            String data = "";
            for (int i = 0; i < nuevosRegistros.length; i++) {
                if (nuevosRegistros[i] != null) {
                    for (int j = 0; i < nuevosRegistros[i].length; j++) {
                        data += nuevosRegistros[i][j]+ ";";
                    }
                    data += ";\n";
                    fr.append(data);
                    data = "";
                }
            }
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lee un arreglo compuesto por un título y un mensaje, luego genera un registro con esos datos en un archivo
     * logs.txt
     *
     * @param nuevoRegistro String[]
     */
    public static void registrarEnLog(String[] nuevoRegistro) {
        try {


            //Se genera el timestamp con formato
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = ahora.format(formato);

            //Se genera el string que se va a anexar al archivos de logs
            FileWriter fr = new FileWriter(RUTA_LOG, true);
            String registroToString = "["+ timestamp +"][" + nuevoRegistro[0] + "]\n" + nuevoRegistro[1] + "\n\n" ;

            fr.append(registroToString);
            fr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
