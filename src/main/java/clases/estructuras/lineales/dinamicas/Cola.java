/************* Autores ***********
Gonzalo Piacentini, Legajo FAI-2514
Nicolas Martin Borghese, Legajo FAI-997
Santiago Alzuguren, Legajo FAI-4691
Sol Juliana Grant Reyes, Legajo FAI-4808
 */

package clases.estructuras.lineales.dinamicas;

public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elemento) {
        // Pone el elemento al final de la cola.
        Nodo nuevoNodo = new Nodo(elemento, null);
        if (this.frente == null) {
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
        } else {
            fin.setEnlace(nuevoNodo);
            this.fin = nuevoNodo;
        }

        return true;
        // Por ser dinamica.
        // Se pone porque tiene que ser compatible con la clase estatica.
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            // La cola está vacía, reporta error.
            exito = false;
        } else {
            // Al menos hay un elemento.
            // Quita el primer elemento y actualiza frente (y fin queda vacía).
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = this.fin.getEnlace();
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        // Retorna el objeto del frente de la cola, en caso de no estar vacia.
        Object objetoFrente = null;
        if (this.frente != null) {
            objetoFrente = this.frente.getElem();
        }
        return objetoFrente;
    }

    public boolean esVacia() {
        // Devuelve un booleano: verdadero si la cola es vacia falso caso contrario.
        return this.frente == null;
    }

    public void vaciar() {
        // Vacia la pila.
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        // Realiza una copia exacta de los elementos de la cola.
        Cola colaClon = new Cola();
        if (this.frente != null) {
            Nodo aux1 = this.frente; // Con este recorro la Cola Original.
            colaClon.frente = new Nodo(aux1.getElem(), null);
            aux1 = aux1.getEnlace();// Avanzo
            Nodo aux2 = colaClon.frente; // Con este recorro la Cola clon.
            while (aux1 != null) {

                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();

            }
            colaClon.fin = aux2; // Pongo el fin del clon.
        }
        return colaClon;
    }

    public String toString() {
        /*
         * Devuelve una cadena de caracteres formada por todos los elementos de la pila
         * para poder mostrarla por pantalla.
         */
        String cad = "";
        if (this.frente == null) {
            cad = "[]";
        } else {
            // Se ubica a recorrer la pila.
            Nodo aux = this.frente;
            cad = "[";
            while (aux != null) {
                // Agrega el texto del elem y avanza.
                cad += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    cad += ",";
                }
            }
            cad += "]";
        }
        return cad;
    }
}
