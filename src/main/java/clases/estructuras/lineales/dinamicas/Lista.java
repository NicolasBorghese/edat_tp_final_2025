/************* Autores ***********
Gonzalo Piacentini, Legajo FAI-2514
Nicolas Martin Borghese, Legajo FAI-997
Santiago Alzuguren, Legajo FAI-4691
Sol Juliana Grant Reyes, Legajo FAI-4808

 */

package clases.estructuras.lineales.dinamicas;

public class Lista {
    private Nodo cabecera;
    private int longitud = 0;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object nuevoElem, int pos) {
        /*
         * Inserta el elemento nuevo en la posicion pos, detecta y reporta error
         * posicion invalida.
         */
        boolean exito = true;
        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else {
            if (pos == 1) { // Crea un nuevoNodo y se enlaza en la cabecera.
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
                this.longitud++;
            } else { // Avanza hasta el elemento en la pos-1.
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // Crea el nodo y lo enlaza.
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
                this.longitud++;
            }
        }
        // Nunca hay error de lista llena, entonces devuelve true.
        return exito;
    }

    public boolean eliminar(int pos) {
        /*
         * Elimina el elemento en la posicion pos, detecta y reporta error posicion
         * invalida.
         */
        boolean exito = true;
        if (pos < 1 || pos > this.longitud) {
            exito = false;
        } else {
            if (pos == 1) {
                /* En caso de querer eliminar el primer elemento, actualizo la cabecera. */
                this.cabecera = this.cabecera.getEnlace();
                longitud--;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace()); // Salteo el nodo que quiero eliminar.
                longitud--;
            }

        }
        return exito;
    }

    public Object recuperar(int pos) {
        // Devuelve el elemento de la posicion pos. Precondicion: pos valida.
        Object elemento;
        if (pos < 1 || pos > this.longitud) {
            elemento = null;
        } else {
            if (pos == 1) {
                elemento = this.cabecera.getElem();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                /*
                 * Como llego hasta una pos antes de la pos que quiero pido el enlace y recien
                 * ahí el elemento.
                 */
                elemento = aux.getElem();
            }

        }
        return elemento;
    }

    public int localizar(Object objetoBuscado) {
        int pos = -1;
        Nodo aux = this.cabecera;
        if (this.cabecera != null && objetoBuscado != null) {
            if (objetoBuscado.equals(this.cabecera.getElem())) {
                pos = 1;
            } else {
                int i = 2;
                aux = aux.getEnlace();
                boolean encontrado = false;
                while (i <= longitud && !encontrado) {
                    if (objetoBuscado.equals(aux.getElem())) {
                        pos = i;
                        encontrado = true;
                    } else {
                        aux = aux.getEnlace();
                        i++;
                    }
                }
            }
        }

        return pos;
    }

    public int longitud() {
        // Retorna la cantidad de elementos de la lista.
        return this.longitud;
    }

    public boolean esVacia() {
        // Devuelve verdadero si la lista no tiene elementos falso caso contrario.
        return this.cabecera == null;
    }

    public void vaciar() {
        // Vacio la lista, misma logica que cola y pila dinamica.
        this.cabecera = null;
        this.longitud = 0;
    }

    public Lista clone() {
        /*
         * Devuelve una copia exacta de los datos de la estructura original, respetando
         * el orden de los mismos, en otra estructura del mismo tipo
         */
        Lista clon = new Lista();
        if (this.cabecera != null) {
            Nodo nodo = this.cabecera;
            clon.cabecera = new Nodo(nodo.getElem(), null);
            clon.longitud = 1;
            Nodo aux2 = clon.cabecera;
            nodo = nodo.getEnlace();
            while (nodo != null) {
                aux2.setEnlace(new Nodo(nodo.getElem(), null));
                clon.longitud++;
                aux2 = aux2.getEnlace(); // Avanzar en el clon.
                nodo = nodo.getEnlace(); // Avanzar en la original.
            }

        }
        return clon;
    }

    public String toString() {
        /*
         * Devuelve una cadena de caracteres formada por todos los elementos de la pila
         * para poder mostrarla por pantalla.
         */
        String cad = "";
        if (this.cabecera == null) {
            cad = "[]";
        } else {
            // Se ubica a recorrer la pila.
            Nodo aux = this.cabecera;
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

    public boolean moverAAnteultimaPosicion(int pos) {
        boolean exito = false;

        if (pos >= 1 && pos < this.longitud - 1) { // Validamos que se pueda mover a anteúltima.
            Nodo nodo = this.cabecera;
            Object elemento = null;
            int i = 1;

            // Auxiliares.
            Nodo anteriorAEliminar = null;
            Nodo anteUltimo = null;

            while (nodo != null && nodo.getEnlace() != null) {
                // Guardo nodo anterior al que vamos a eliminar.
                if (i == pos - 1) {
                    anteriorAEliminar = nodo;
                }

                // Guardo el nodo anterior al último.
                if (i == this.longitud - 1) {
                    anteUltimo = nodo;
                }

                nodo = nodo.getEnlace();
                i++;
            }

            if (pos == 1) {
                // Caso especial: mover cabecera.
                elemento = this.cabecera.getElem();
                this.cabecera = this.cabecera.getEnlace();
            } else if (anteriorAEliminar != null && anteriorAEliminar.getEnlace() != null) {
                elemento = anteriorAEliminar.getEnlace().getElem();
                anteriorAEliminar.setEnlace(anteriorAEliminar.getEnlace().getEnlace());
            }

            if (elemento != null && anteUltimo != null) {
                Nodo nuevo = new Nodo(elemento, anteUltimo.getEnlace());
                anteUltimo.setEnlace(nuevo);
                exito = true;
            }
        }

        return exito;
    }

    public Lista obtenerMultiplos(int num) {
        Lista nueva = new Lista();
        if (num > 0 && this.cabecera != null) {
            Object elemento = null;
            Nodo nodo = this.cabecera;
            Nodo nodoLNueva = null;
            int i = 1;
            while (i <= this.longitud) {
                if (i % num == 0) {
                    elemento = nodo.getElem();
                }
                if (nueva.cabecera == null && elemento != null) {
                    nueva.cabecera = new Nodo(elemento, null);
                    nodoLNueva = nueva.cabecera;
                    elemento = null;
                } else if (elemento != null) {
                    Nodo aux = new Nodo(elemento, null);
                    nodoLNueva.setEnlace(aux);
                    nodoLNueva = nodoLNueva.getEnlace();
                    elemento = null;
                }
                i++;
                nodo = nodo.getEnlace();
            }
        }
        return nueva;
    }

    public void eliminarApariciones(Object elemento) {
        if (this.cabecera != null) {
            Nodo actual = this.cabecera;
            Nodo anterior = null;
            while (actual != null) {
                if (actual.getElem().equals(elemento)) {
                    if (anterior == null) {
                        // Elimina el primer nodo.
                        this.cabecera = actual.getEnlace();
                    } else {
                        // Saltea actual.
                        anterior.setEnlace(actual.getEnlace());
                    }
                } else {
                    // Solo avanzamos anterior si no se eliminó el nodo actual.
                    anterior = actual;
                }
                actual = actual.getEnlace();
            }
        }
    }
}
