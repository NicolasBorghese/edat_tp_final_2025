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
                                "3. Iniciar programa con registros vacios"
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
                                "1. Ciudad - Alta",
                                "2. Ciudad - Baja",
                                "3. Ciudad - Modificar",
                                "4. Ciudad - Actualizar población por fecha",
                                "5. Tubería - Alta",
                                "6. Tubería - Baja",
                                "7. Tubería - Modificar",
                                "8. Cantidad habitantes y agua consumida por fecha",
                                "9. Consumo de agua entre rango de nombres",
                                "10. Camino de A a B con caudal pleno mínimo",
                                "11. Camino de A a B con menor recorrido",
                                "12. Ciudades ordenadas por consumo de agua",
                                "13. Visualizar arbolAVL de ciudades",
                                "14. Visualizar HashMap de tuberias",
                                "15. Visualizar Grafo (Ciudad-Tubería)"
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
