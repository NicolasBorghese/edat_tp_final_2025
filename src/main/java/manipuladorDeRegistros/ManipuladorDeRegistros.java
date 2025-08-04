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
     * Abre, lee y devuelve los contenidos de un archivo en una Lista de Arreglos, donde cada arreglo
     * en sus distintas posiciones contiene un String que representa el valor de cada campo de una tupla
     *
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
            while ((linea = bufferedReader.readLine()) != null) {// Lee hasta que se encuentra una línea vacía
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
     * Lee una lista de arreglos donde cada arreglo representa una tupla y su contenido el valor de los distintos
     * campos de la misma, procesa esa información y la escribre en la ruta indicada, además recibe un booleano
     * que indica si se debe continuar escribiendo en ese archivo a continuación de lo anterior (true) o no (false).
     *
     * @param ruta Dirección del archivo donde se deben escribir los nuevos registros
     * @param listaRegistros Lista de arreglos donde el valor de cada celda corresponde a un campo de una tupla
     * @param continuarRegistro Si es true continúa registrando datos sin borrar los anteriores,
     *                          si es false sobrescribe los anteriores
     */
    public static void escribirRegistros(String ruta, Lista listaRegistros, boolean continuarRegistro) {
        try {
            FileWriter fr = new FileWriter(ruta, continuarRegistro);

            int cantRegistros = listaRegistros.longitud();

            String data = "";
            for (int i = 1; i <= cantRegistros; i++) {
                if (listaRegistros.recuperar(i) != null) {

                    String[] tupla = (String[]) listaRegistros.recuperar(i);

                    for (int j = 0; j < tupla.length; j++) {

                        if (j != tupla.length - 1) {
                            data += tupla[j]+ ";";
                        } else {
                            data += tupla[j];
                        }

                    }

                    data += "\n";
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
     * Lee un arreglo compuesto por un título y un mensaje, luego genera un registro que anexa la fecha
     * en que fue realizado y se escribe en la ruta indicada
     *
     * @param ruta Dirección del archivo donde se deben escribir los nuevos registros
     * @param nuevoRegistro String[]
     */
    public static void escribirComoLog(String ruta, String[] nuevoRegistro, boolean continuarRegistro) {
        try {

            //Se genera el timestamp con formato
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = ahora.format(formato);

            //Se genera el string que se va a registrar dentro del archivo indicado en la ruta
            FileWriter fr = new FileWriter(ruta, true);
            String registroToString = "["+ timestamp +"][" + nuevoRegistro[0] + "]\n" + nuevoRegistro[1] + "\n\n" ;

            fr.append(registroToString);
            fr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
