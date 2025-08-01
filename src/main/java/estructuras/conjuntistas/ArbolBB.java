package estructuras.conjuntistas;

import estructuras.lineales.dinamicas.*;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elemento);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elemento) {
        // Precondición: nodo no es nulo.
        boolean exito = true;
        if ((elemento.compareTo(nodo.getElemento()) == 0)) {
            // Reportar error: Elemento repetido.
            exito = false;
        } else if ((elemento.compareTo(nodo.getElemento())) < 0) {
            // Elemento es menor que n.getElem().
            // Si tiene HI baja a la izquierda, sino agrega el elemento.
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elemento);
            } else {
                nodo.setIzquierdo(new NodoABB(elemento));
            }
        } else { // Elemento es mayor que n.getElem()
            // Si tiene HD baja a la derecha, sino agrega el elemento.
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), elemento);
            } else {
                nodo.setDerecho(new NodoABB(elemento));
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        boolean[] eliminado = { false };
        if (this.raiz != null) {
            eliminarAux(this.raiz, elemento, eliminado);
        }
        return eliminado[0];
    }

    private NodoABB eliminarAux(NodoABB nodo, Comparable elemento, boolean[] eliminado) {
        NodoABB aux = nodo; // aux empieza apuntando al nodo actual

        if (nodo != null) {
            int comparador = elemento.compareTo(nodo.getElemento());

            if (comparador < 0) {
                nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elemento, eliminado));
            } else if (comparador > 0) {
                nodo.setDerecho(eliminarAux(nodo.getDerecho(), elemento, eliminado));
            } else {
                // Nodo encontrado
                eliminado[0] = true;

                if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                    // Caso 3: tiene dos hijos
                    Comparable min = minimoElemAux(nodo.getDerecho());
                    nodo.setElemento(min);
                    nodo.setDerecho(eliminarAux(nodo.getDerecho(), min, eliminado));
                } else {
                    // Caso 1 y 2: cero o un hijo
                    if (nodo.getIzquierdo() != null) {
                        // CASO 2: Solo hijo izquierdo
                        aux = nodo.getIzquierdo();
                    } else {
                        // CASO 1 y 2: Solo hijo derecho o sin hijos
                        aux = nodo.getDerecho();// Puede ser null (caso sin hijos)
                    }
                }
            }
        }

        return aux;
    }

    public boolean pertenece(Comparable elemento) {
        /*
         * Devulve verdadero si el elemento recibido por paramentro está en el árbol y
         * falso caso contrario
         */
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = perteneceAux(this.raiz, elemento);
        }
        return pertenece;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable elemento) {
        boolean pertenece = false;
        if (elemento.equals(nodo.getElemento())) {
            pertenece = true;
        } else {
            if (elemento.compareTo(nodo.getElemento()) < 0 && nodo.getIzquierdo() != null) {
                pertenece = perteneceAux(nodo.getIzquierdo(), elemento);
            } else if (elemento.compareTo(nodo.getElemento()) > 0 && nodo.getDerecho() != null) {
                pertenece = perteneceAux(nodo.getDerecho(), elemento);
            }

        }
        return pertenece;
    }

    public Lista listar() {
        /*
         * Recorre el árbol completo y devuelve una lista ordenada con los elementos que
         * se encuentran almacenados dentro de él
         */
        Lista lista = new Lista();
        listarAux(this.raiz, lista);
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        Lista lista = new Lista();
        if (this.raiz != null && perteneceAux(this.raiz, elemMin) && perteneceAux(this.raiz, elemMax)) {
            listarRangoAux(this.raiz, elemMin, elemMax, lista);
        }

        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Comparable elemMin, Comparable elemMax, Lista lista) {
        if (nodo != null) {
        }
    }

    public Object minimoElem() {
        /*
         * Recorre la rama correspondiente y devuelve el elemento más chico dentro del
         * arbol
         */
        return minimoElemAux(this.raiz);
    }

    private Comparable minimoElemAux(NodoABB nodo) {
        Comparable minimo = null;
        if (nodo != null) {
            minimo = minimoElemAux(nodo.getIzquierdo());
            if (minimo == null) {
                minimo = nodo.getElemento();
            }
        }
        return minimo;
    }

    public Comparable maximoElem() {
        /*
         * Recorre la rama correspondiente y devuelve el elemento más grande dentro del
         * arbol
         */
        return maximoElemAux(this.raiz);
    }

    private Comparable maximoElemAux(NodoABB nodo) {
        Comparable maximo = null;
        if (nodo != null) {
            maximo = maximoElemAux(nodo.getDerecho());
            if (maximo == null) {
                maximo = nodo.getElemento();
            }

        }
        return maximo;
    }

    public boolean eliminarMinimo() {
        boolean eliminado = false;
        if (this.raiz != null && this.raiz.getIzquierdo() == null) {
            this.raiz = this.raiz.getDerecho();
            eliminado = true;
        } else {
            eliminarMinimoAux(this.raiz);
            eliminado = true;
        }
        return eliminado;
    }

    private NodoABB eliminarMinimoAux(NodoABB nodo) {
        NodoABB retorno = nodo;
        if (nodo != null) {
            // Casos:
            if (nodo.getIzquierdo() == null) {
                // Es el minimo. Si es el minimo retorno al HD del nodo.
                retorno = nodo.getDerecho();
            }
            nodo.setIzquierdo(eliminarMinimoAux(nodo.getIzquierdo()));

        }
        return retorno;
    }

    public String toString() {
        String s = "[";
        s = toString(raiz, s);
        if (s.length() > 1)
            s = s.substring(0, s.length() - 1);
        return s + ']';
    }

    private String toString(NodoABB nodo, String s) {
        if (nodo != null) {
            s += '[' + nodo.getElemento().toString() + ',';
            s += nodo.getIzquierdo() != null ? nodo.getIzquierdo().getElemento().toString() + ',' : ',';
            s += nodo.getDerecho() != null ? nodo.getDerecho().getElemento().toString() + "]," : "],";
            s = toString(nodo.getIzquierdo(), s);
            s = toString(nodo.getDerecho(), s);
        }
        return s;
    }

    public String toStringMayorAMenor() {
        // Lista verticalmente de mayor a menor.
        String s = "";
        s = toStringVerticalMayorAMenor(raiz, s);
        if (s.length() > 1)
            s = s.substring(0, s.length() - 1);
        return s;
    }

    private String toStringVerticalMayorAMenor(NodoABB nodo, String s) {
        if (nodo != null) {
            s = toStringVerticalMayorAMenor(nodo.getDerecho(), s);
            s += nodo.getElemento().toString() + "\n";
            s = toStringVerticalMayorAMenor(nodo.getIzquierdo(), s);
        }
        return s;
    }

    public String toStringMenorAMayor() {
        // Lista verticalmente de menor a mayor.
        String s = "";
        s = toStringInvertidoMenorAMayor(raiz, s);
        if (s.length() > 1)
            s = s.substring(0, s.length() - 1);
        return s;
    }

    private String toStringInvertidoMenorAMayor(NodoABB nodo, String s) {
        if (nodo != null) {
            s = toStringInvertidoMenorAMayor(nodo.getIzquierdo(), s);
            s += nodo.getElemento().toString() + "\n";
            s = toStringInvertidoMenorAMayor(nodo.getDerecho(), s);
        }
        return s;
    }

    public boolean eliminarMaximo() {
        boolean eliminado = false;
        if (this.raiz != null && this.raiz.getDerecho() == null) {
            // La raiz es el maximo.
            this.raiz = this.raiz.getIzquierdo();
            eliminado = true;
        } else {
            eliminarMaximoAux(this.raiz);
            eliminado = true;
        }

        return eliminado;
    }

    private NodoABB eliminarMaximoAux(NodoABB nodo) {
        NodoABB retorno = nodo;
        if (nodo != null) {
            if (nodo.getDerecho() == null) {
                // Es el maximo. Retorno su HI.
                retorno = nodo.getIzquierdo();
            }
            nodo.setDerecho(eliminarMaximoAux(nodo.getDerecho()));
        }
        return retorno;
    }

    public ArbolBB clonarParteInvertida(Comparable elemento) {
        ArbolBB nuevoArbol = new ArbolBB();
        NodoABB buscado = buscarNodo(this.raiz, elemento);
        if (buscado != null) {
            nuevoArbol.raiz = clonarParteInvertidaAux(buscado);
        }
        return nuevoArbol;
    }

    private NodoABB buscarNodo(NodoABB nodo, Comparable elemento) {
        NodoABB buscado = null;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) < 0) {
                /*
                 * El elemento es mas chico que el valor del nodo.(Me tengo que meter en la
                 * parte izquierda).
                 */
                buscado = buscarNodo(nodo.getIzquierdo(), elemento);
            } else if (elemento.compareTo(nodo.getElemento()) > 0) {
                /*
                 * El elemento es mas grande que el valor del nodo.(Me tengo que meter en la
                 * parte derecha).
                 */
                buscado = buscarNodo(nodo.getDerecho(), elemento);
            } else {
                // Encontre el nodo.
                buscado = nodo;
            }
        }
        return buscado;
    }

    private NodoABB clonarParteInvertidaAux(NodoABB nodo) {
        NodoABB retorno = null;
        if (nodo != null) {
            retorno = new NodoABB(nodo.getElemento());
            retorno.setIzquierdo(clonarParteInvertidaAux(nodo.getDerecho()));
            retorno.setDerecho(clonarParteInvertidaAux(nodo.getIzquierdo()));
        }
        return retorno;
    }

    public Comparable mejorCandidato(Comparable elemento) {
        // Retorna el mejor candidato para reemplazar a elem, si tiene un hijo retorna
        // ese hijo, si tiene 2 retorna al aritmeticamente más cercano.
        Comparable valor = -1;
        if (this.raiz != null) {
            NodoABB buscado = buscarNodo(this.raiz, elemento);
            if (buscado != null) {
                valor = mejorCandidatoAux(buscado, elemento);
            }
        }
        return valor;
    }

    private Comparable mejorCandidatoAux(NodoABB nodo, Comparable elemento) {
        Comparable valor;
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            // Caso sin hijos.
            valor = -1;
        } else if (nodo.getIzquierdo() != null && nodo.getDerecho() == null
                || nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
            // Caso un hijo.
            if (nodo.getIzquierdo() != null) {
                valor = nodo.getIzquierdo().getElemento();
            } else {
                valor = nodo.getDerecho().getElemento();
            }
        } else {
            // Caso dos hijos.
            Comparable auxIzq = buscarMaxElemAux(nodo.getIzquierdo());
            Comparable auxDer = buscarMinElemAux(nodo.getDerecho());
            double difIzq = Math.abs(((Number) nodo.getElemento()).doubleValue() - ((Number) auxIzq).doubleValue());
            double difDer = Math.abs(((Number) nodo.getElemento()).doubleValue() - ((Number) auxDer).doubleValue());
            if (difIzq <= difDer) {
                // El auxIzq esta mas cercano porque tiene un valor menor al otro.
                valor = auxIzq;
            } else {
                valor = auxDer;
            }
        }
        return valor;
    }

    private Comparable buscarMinElemAux(NodoABB nodo) {
        Comparable minimo = null;
        if (nodo.getIzquierdo() == null) {
            minimo = nodo.getElemento();
        } else {
            minimo = buscarMinElemAux(nodo.getIzquierdo());
        }
        return minimo;
    }

    private Comparable buscarMaxElemAux(NodoABB nodo) {
        Comparable maximo = null;
        if (nodo.getDerecho() == null) {
            maximo = nodo.getElemento();
        } else {
            maximo = buscarMaxElemAux(nodo.getDerecho());
        }
        return maximo;
    }
}
