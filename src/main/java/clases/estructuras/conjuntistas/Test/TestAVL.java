package clases.estructuras.conjuntistas.Test;

import clases.estructuras.conjuntistas.ArbolAVL;

public class TestAVL {
    public static void main(String[] args) {
        /*
         * testInsertarDesbalanceDerecho();
         * testInsertarDesbalanceIzquierdo();
         * testInsertarDesbalanceDerechoIzquierdo();
         * testInsertarDesbalanceIzquierdoDerecho();
         */
        //testInsertarGeneral();
        testEliminarElemento();
    }

    public static void testInsertarDesbalanceDerecho() {
        ArbolAVL a = new ArbolAVL();
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Neuquen", "Neuquen");
        a.insertar("Roca", "Roca");
        System.out.println(a.toString());
    }

    public static void testInsertarDesbalanceIzquierdo() {
        ArbolAVL a = new ArbolAVL();
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Bariloche", "Bariloche");
        a.insertar("Allen", "Allen");
        System.out.println(a.toString());
    }

    public static void testInsertarDesbalanceDerechoIzquierdo() {
        ArbolAVL a = new ArbolAVL();
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Roca", "Roca");
        a.insertar("Neuquen", "Neuquen");
        System.out.println(a.toString());
    }

    public static void testInsertarDesbalanceIzquierdoDerecho() {
        ArbolAVL a = new ArbolAVL();
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Allen", "Allen");
        a.insertar("Bariloche", "Bariloche");
        System.out.println(a.toString());
    }

    public static void testInsertarGeneral() {
        ArbolAVL a = new ArbolAVL();
        a.insertar("Allen", "Allen");
        a.insertar("Bariloche", "Bariloche");
        a.insertar("Centenario", "Centenario");
        a.insertar("Cinco Saltos", "Cinco Saltos");
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Cutral Co", "Cutral Co");
        a.insertar("Ferri", "Ferri");
        a.insertar("General Fernandez Oro", "General Fernandez Oro");
        a.insertar("General Roca", "General Roca");
        a.insertar("Mari Menuco", "Mari Menuco");
        a.insertar("Neuquen", "Neuquen");
        a.insertar("Plaza Huincul", "Plaza Huincul");
        a.insertar("Plottier", "Plottier");
        a.insertar("Senillosa", "Senillosa");
        a.insertar("Zapala", "Zapala");
        System.out.println(a.toString());
        a.vaciar();
        System.out.println();
        a.insertar("Zapala", "Zapala");
        a.insertar("Senillosa", "Senillosa");
        a.insertar("Plottier", "Plottier");
        a.insertar("Plaza Huincul", "Plaza Huincul");
        a.insertar("Neuquen", "Neuquen");
        a.insertar("Mari Menuco", "Mari Menuco");
        a.insertar("General Roca", "General Roca");
        a.insertar("General Fernandez Oro", "General Fernandez Oro");
        a.insertar("Ferri", "Ferri");
        a.insertar("Cutral Co", "Cutral Co");
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Cinco Saltos", "Cinco Saltos");
        a.insertar("Centenario", "Centenario");
        a.insertar("Bariloche", "Bariloche");
        a.insertar("Allen", "Allen");
        System.out.println(a.toString());
    }

    public static void testEliminarElemento(){
        ArbolAVL a = new ArbolAVL();
        /* a.insertar("Allen", "Allen");
        a.insertar("Bariloche", "Bariloche");
        a.insertar("Centenario", "Centenario");
        a.insertar("Cinco Saltos", "Cinco Saltos");
        a.insertar("Cipolletti", "Cipolletti");
        a.insertar("Cutral Co", "Cutral Co");
        a.insertar("Ferri", "Ferri");
        a.insertar("General Fernandez Oro", "General Fernandez Oro");
        a.insertar("General Roca", "General Roca");
        a.insertar("Mari Menuco", "Mari Menuco");
        a.insertar("Neuquen", "Neuquen");
        a.insertar("Plaza Huincul", "Plaza Huincul");
        a.insertar("Plottier", "Plottier");
        a.insertar("Senillosa", "Senillosa");
        a.insertar("Zapala", "Zapala");
        System.out.println(a.toString());
        a.eliminar("General Fernandez Oro");
        System.out.println(a.toString());
        a.eliminar("General Roca");
        System.out.println(a.toString());
        a.eliminar("Mari Menuco");
        System.out.println(a.toString());*/

        ArbolAVL b = new ArbolAVL();
        b.insertar(1, 1);
        b.insertar(2, 2);
        b.insertar(3, 3);
        b.insertar(4, 4);
        b.insertar(5, 5);
        b.insertar(6, 6);
        b.insertar(7, 7);
        b.insertar(8, 8);
        b.insertar(9, 9);
        b.insertar(10, 10);
        b.insertar(11, 11);
        b.insertar(12, 12);
        b.insertar(13, 13);
        b.insertar(14, 14);
        b.insertar(15, 15);
        

        System.out.println(b.toString());
        b.eliminar(8);
        System.out.println(b.toString());
        b.eliminar(9);
        System.out.println(b.toString());
        b.eliminar(10);
        System.out.println(b.toString());
    }
}
