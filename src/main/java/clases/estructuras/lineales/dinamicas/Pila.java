/************* Autores ***********
Gonzalo Piacentini, Legajo FAI-2514
Nicolas Martin Borghese, Legajo FAI-997
Santiago Alzuguren, Legajo FAI-4691
Sol Juliana Grant Reyes, Legajo FAI-4808
 */

package clases.estructuras.lineales.dinamicas;
public class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        // Crea un nuevo nodo delante de la antigua cabecera.
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        // Actualiza tope para que apunte al nodo nuevo.
        this.tope = nuevo;
        // Nunca hay error de pila nueva, entonces devuelve True.
        return true;
    }

    public boolean desapilar() {
        /*
         * Saca el elemento del tope de la pila. Devuelve true si la pila no estaba
         * vacía en el momento de desapilar
         */
        boolean exito = false;
        if (tope != null) {
            this.tope = tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        // Retorna el elemento del tope de la pila. Si la pila no esta vacía.
        Object elemTope = null;
        if (tope != null) {
            elemTope = tope.getElem();
        }
        return elemTope;
    }

    public boolean esVacia() {
        // Retorna verdadero si la pila esta vacía, falso caso contrario.
        return (tope == null);
    }

    public void vaciar() {
        // Vacía la pila.
        this.tope = null;
    }

    public Pila clone() {
        // Clona los elementos de la pila original en una nueva.
        Pila pilaClon = new Pila();

        if (this.tope != null) {
            Nodo aux1 = this.tope; // Nodo para recorrer la pila original
            pilaClon.tope = new Nodo(aux1.getElem(), null); // Copiar el primer nodo
            Nodo aux2 = pilaClon.tope; // Nodo para recorrer la pila clonada

            aux1 = aux1.getEnlace(); // Avanzar en la original

            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElem(), null)); // Crear y enlazar el nuevo nodo
                aux2 = aux2.getEnlace(); // Avanzar en la clonada
                aux1 = aux1.getEnlace(); // Avanzar en la original
            }
        }

        return pilaClon;
    }

    /*
     * Retorna un string que muestra los elementos contenido en la Pila
     * 
     * @return String
     */
    public String toString() {
        String texto = "";
        if (this.tope == null) {
            texto = "[]";
        } else {
            texto = nodosToString();
        }
        return texto;
    }

    /*
     * cadena de Nodos a String estilizado
     * 
     * @return String
     */
    public String nodosToString() {
        String texto = "";
        Nodo nodoEnPila;
        nodoEnPila = this.tope;
        texto = "[";
        while (nodoEnPila != null) {
            texto += nodoEnPila.getElem();
            nodoEnPila = nodoEnPila.getEnlace();
            if (nodoEnPila != null) {
                texto += ",";
            }
        }
        texto += "]";
        return texto;
    }
}
