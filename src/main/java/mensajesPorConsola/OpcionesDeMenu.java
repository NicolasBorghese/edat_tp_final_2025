package mensajesPorConsola;

public class OpcionesDeMenu {

    public static String[][] obtenerMenu(String nombreMenu) {

        String[][] menu = new String[0][0];

        switch (nombreMenu) {
            case "cargaDeDatosInicial":
                menu = new String[][]{
                        {
                                "CARGA INICIAL DE DATOS"
                        },
                        {
                                "0. Salir.",
                                "1. Utilizar los últimos registros de datos",
                                "2. Utilizar la carga inicial de datos",
                                "3. Iniciar programa sin cargar datos"
                        }
                };
                break;
            case "menuPrincipal":
                menu = new String[][]{
                        {
                                "MENÚ PRINCIPAL"
                        },
                        {
                                "0. Salir.",
                                "1. Agregar una ciudad.",
                                "2. Dar de baja una ciudad.",
                                "3. Modificar una ciudad.",
                                "4. Agregar una tubería.",
                                "5. Dar de baja una tubería.",
                                "6. Modificar una tubería.",
                                "7. Ver el Ranking de ciudades.",
                                "8. Ver el árbol AVL.",
                                "9. Ver el Grafo Etiquetado.",
                                "10. Ver el HashMap."
                        }
                };
                break;
            default:
                /*menu = new String[][]{{},{}};*/
                break;
        }
        return menu;
    }
}
