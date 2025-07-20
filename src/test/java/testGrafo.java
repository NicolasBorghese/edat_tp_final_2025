import estructuras.grafos.Grafo;

public class testGrafo {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        cargar(grafo);
        System.out.println(grafo.listarEnProfundidad().toString());
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

}
