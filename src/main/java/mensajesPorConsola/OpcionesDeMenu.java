package mensajesPorConsola;

public class OpcionesDeMenu {

    public static String[][] obtenerMenu(String nombreMenu){

        String[][] menu = new String[0][0];

        switch (nombreMenu){
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
        }
        return menu;
    }
}
