import constantes.Estilos;
import estructuras.grafos.Grafo;

public class TestGrafo {
    public static final String RESET = Estilos.RESET;
    public static final String BOLD = Estilos.BOLD;
    public static final String RED = Estilos.RED;
    public static final String BLUE = Estilos.BLUE;
    public static final String CYAN = Estilos.CYAN;
    public static final String GREEN = Estilos.GREEN;

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        Grafo grafo2 = new Grafo();
        Grafo aux = new Grafo();
        cargar(grafo);
        cargarNuevo(grafo2);
        System.out.println(BOLD + GREEN + "Verificados: " + RESET);
        // Recorridos.
        System.out.println(CYAN + "Recorridos: " + RESET);
        System.out.println("Lista en profundidad: " + BOLD + CYAN + grafo.listarEnProfundidad('C') + RESET);
        System.out.println("Lista en anchura: " + BOLD + CYAN + grafo.listarEnAnchura('B') + RESET);
        System.out.println(
                "Camino más corto del grafo2 de A a E: " + BOLD + CYAN + grafo2.caminoMasCorto('A', 'E') + RESET);
        System.out.println(
                "Camino más liviano del grafo2 de a a E: " + BOLD + CYAN + grafo2.caminoMasLiviano('A', 'E') + RESET);
        // Insercion.
        System.out.println(CYAN + "Inserción: " + RESET);
        System.out.println("Insertar un elemento: " + GREEN + aux.insertarVertice(1) + RESET);
        System.out.println("Insertar un elemento existente: " + GREEN + aux.insertarVertice(1) + RESET);
        System.out.println("Insertar un nulo: " + GREEN + aux.insertarVertice(null) + RESET);
        // Existencia.
        System.out.println(CYAN + "Existencia: " + RESET);
        System.out.println("Existe Vertice Existente? " + GREEN + aux.existeVertice(1) + RESET);
        System.out.println("Existe Vertice no Existente? " + GREEN + aux.existeVertice(99) + RESET);
        System.out.println("Existe Vertice Nulo? " + GREEN + aux.existeVertice(null) + RESET);
        System.out.println("Existe arco existente? " + GREEN + grafo.existeArco('A', 'B', 1) + RESET);
        System.out.println("Existe arco inexistente? " + GREEN + grafo.existeArco('A', 'E', 24) + RESET);
        System.out.println("Existe arco con etiqueta mal? " + GREEN + grafo.existeArco('A', 'B', 99) + RESET);
        System.out.println("Existe arco con nulos? " + GREEN + grafo.existeArco(null, null, null) + RESET);
        System.out.println("Existe camino Existente? " + GREEN + grafo.existeCamino('A', 'E') + RESET);
        System.out.println("Existe camino Inexistente? " + GREEN + grafo.existeCamino('E', 'A') + RESET);
        System.out.println("Existe camino de nulo a nulo? " + GREEN + grafo.existeCamino(null, null) + RESET);
        // Eliminacion.
        System.out.println(CYAN + "Eliminación: " + RESET);
        System.out.println("Eliminar un Vertice existente: " + GREEN + aux.eliminarVertice(1) + RESET);
        System.out.println("Eliminar un Vertice no existente: " + GREEN + aux.eliminarVertice(99) + RESET);
        System.out.println("Eliminar un nulo: " + GREEN + aux.eliminarVertice(null) + RESET);
        aux.insertarVertice(1);
        aux.insertarVertice(2);
        aux.insertarArco(1, 2, "Arco");
        System.out.println("Eliminar un arco existente: " + GREEN + aux.eliminarArco(1, 2) + RESET);
        System.out.println("Eliminar un arco nulo: " + GREEN + aux.eliminarArco(null, null) + RESET);
        // Consultas.
        System.out.println(CYAN + "Consultas: " + RESET);
        System.out.println("Grafo Vacio es Vacio? " + GREEN + aux.vacio() + RESET);
        System.out.println("Grafo no Vacio es Vacio? " + GREEN + grafo.vacio() + RESET);

        System.out.println(BOLD + BLUE + "Testeando: " + RESET);
    }

    public static void cargar(Grafo grafo) {
        // Vertices.
        grafo.insertarVertice('H');
        grafo.insertarVertice('G');
        grafo.insertarVertice('E');
        grafo.insertarVertice('F');
        grafo.insertarVertice('D');
        grafo.insertarVertice('C');
        grafo.insertarVertice('B');
        grafo.insertarVertice('A');

        // Arcos.
        grafo.insertarArco('A', 'B', 1);
        grafo.insertarArco('B', 'F', 6);
        grafo.insertarArco('B', 'D', 2);
        grafo.insertarArco('D', 'G', 3);
        grafo.insertarArco('D', 'E', 4);
        grafo.insertarArco('G', 'E', 5);
        grafo.insertarArco('C', 'A', 7);
        grafo.insertarArco('C', 'F', 8);
        grafo.insertarArco('C', 'H', 9);

    }

    public static void cargarNuevo(Grafo grafo) {
        grafo.insertarVertice('E');
        grafo.insertarVertice('D');
        grafo.insertarVertice('C');
        grafo.insertarVertice('B');
        grafo.insertarVertice('I');
        grafo.insertarVertice('H');
        grafo.insertarVertice('G');
        grafo.insertarVertice('F');
        grafo.insertarVertice('A');

        grafo.insertarArco('A', 'B', 1000);
        grafo.insertarArco('B', 'C', 100);
        grafo.insertarArco('C', 'D', 200);
        grafo.insertarArco('D', 'E', 400);
        grafo.insertarArco('B', 'F', 200);
        grafo.insertarArco('F', 'G', 200);
        grafo.insertarArco('G', 'H', 600);
        grafo.insertarArco('H', 'I', 500);
        grafo.insertarArco('I', 'E', 1);
    }

}
