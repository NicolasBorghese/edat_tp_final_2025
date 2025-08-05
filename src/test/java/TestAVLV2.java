import estructuras.conjuntistas.ArbolAVL;

public class TestAVLV2 {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        cargarArbol(arbol);
        System.out.println(arbol.toString());
        System.out.println("");
        arbol.eliminar(30);
        System.out.println("30 eliminado:");
        System.out.println(arbol.toString());
        arbol.eliminar(33);
        System.out.println("33 eliminado:");
        System.out.println(arbol.toString());
        arbol.eliminar(35);
        System.out.println("35 eliminado:");
        System.out.println(arbol.toString());
    }

    public static void cargarArbol(ArbolAVL arbol) {
        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(50, 50);
        arbol.insertar(15, 15);
        arbol.insertar(24, 24);
        arbol.insertar(35, 35);
        arbol.insertar(100, 100);
        arbol.insertar(12, 12);
        arbol.insertar(18, 18);
        arbol.insertar(22, 22);
        arbol.insertar(27, 27);
        arbol.insertar(33, 33);
        arbol.insertar(40, 40);
        arbol.insertar(90, 90);
        arbol.insertar(105, 105);
        arbol.insertar(19, 19);
        arbol.insertar(23, 23);
        arbol.insertar(37, 37);
        arbol.insertar(75, 75);
        arbol.insertar(95, 95);
        arbol.insertar(190, 190);
        arbol.insertar(93, 93);

    }
}
