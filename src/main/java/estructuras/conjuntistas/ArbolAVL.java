package estructuras.conjuntistas;

import clases.Ciudad;
import estructuras.lineales.dinamicas.*;

public class ArbolAVL {
    private NodoAVLDicc raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable buscado) {
        boolean encontrado = false;

        if (!this.vacio()) {
            encontrado = perteneceAux(this.raiz, buscado);
        }

        return encontrado;
    }

    private boolean perteneceAux(NodoAVLDicc nodo, Comparable buscado) {
        boolean encontrado = false;

        if (nodo != null) {
            if (buscado.compareTo(nodo.getClave()) == 0) {
                encontrado = true;
            } else {
                if (buscado.compareTo(nodo.getClave()) < 0) {
                    encontrado = perteneceAux(nodo.getIzquierdo(), buscado);
                } else {
                    encontrado = perteneceAux(nodo.getDerecho(), buscado);
                }
            }
        }

        return encontrado;
    }

    public boolean insertar(Comparable nuevaClave, Object nuevoObjeto) {
        boolean exito;

        if (!this.vacio()) {
            exito = insertarAux(null, this.raiz, nuevaClave, nuevoObjeto);
        } else {
            this.raiz = new NodoAVLDicc(nuevaClave, nuevoObjeto);
            exito = true;
        }

        return exito;
    }

    private boolean insertarAux(NodoAVLDicc padre, NodoAVLDicc nodo, Comparable nuevaClave, Object nuevoObjeto) {
        boolean exito = true;

        if ((nuevaClave.compareTo(nodo.getClave()) == 0)) {
            // Si existe no lo insertamos
            exito = false;
        } else if (nuevaClave.compareTo(nodo.getClave()) < 0) {
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo, nodo.getIzquierdo(), nuevaClave, nuevoObjeto);
            } else {
                nodo.setIzquierdo(new NodoAVLDicc(nuevaClave, nuevoObjeto));
            }
        } else {
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo, nodo.getDerecho(), nuevaClave, nuevoObjeto);
            } else {
                nodo.setDerecho(new NodoAVLDicc(nuevaClave, nuevoObjeto));
            }
        }

        if (exito) {
            balancear(padre, nodo);
        }

        return exito;
    }

    public boolean eliminar(Comparable buscado) {
        return eliminarAux(null, this.raiz, buscado);
    }

    private boolean eliminarAux(NodoAVLDicc padre, NodoAVLDicc hijo, Comparable buscado) {
        boolean eliminado = false;

        if (hijo != null) {
            if (buscado.compareTo(hijo.getClave()) == 0) {
                if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                    eliminarHoja(padre, buscado);
                } else {
                    if (hijo.getIzquierdo() == null || hijo.getDerecho() == null) {
                        eliminarUnicoHijo(padre, hijo, buscado);
                    } else {
                        eliminarDosHijos(hijo);
                    }
                }
                eliminado = true;
            } else {
                if (buscado.compareTo(hijo.getClave()) < 0) {
                    eliminado = eliminarAux(hijo, hijo.getIzquierdo(), buscado);
                } else {
                    eliminado = eliminarAux(hijo, hijo.getDerecho(), buscado);
                }
            }

            if (eliminado) {
                balancear(padre, hijo);
            }
        }

        return eliminado;
    }

    private void eliminarHoja(NodoAVLDicc padre, Comparable buscado) {

        if (padre != null) {
            if (padre.getDerecho() != null && buscado.compareTo(padre.getDerecho().getClave()) == 0) {
                padre.setDerecho(null);
            } else {
                padre.setIzquierdo(null);
            }
        } else {
            this.raiz = null;
        }

    }

    private void eliminarUnicoHijo(NodoAVLDicc padre, NodoAVLDicc hijo, Comparable buscado) {

        NodoAVLDicc subArbol;
        if (hijo.getDerecho() != null) {
            subArbol = hijo.getDerecho();
        } else {
            subArbol = hijo.getIzquierdo();
        }

        if (padre != null) {
            if (padre.getDerecho() != null && buscado.compareTo(padre.getDerecho().getClave()) == 0) {
                padre.setDerecho(subArbol);
            } else {
                padre.setIzquierdo(subArbol);
            }
        } else {
            this.raiz = subArbol;
        }

    }

    private void eliminarDosHijos(NodoAVLDicc hijo) {
        NodoAVLDicc padreNuevoNodo = buscarPadreNodoMinimo(hijo.getDerecho());
        NodoAVLDicc nuevoNodo = padreNuevoNodo.getIzquierdo();

        // Candidato con el menor hijo del subarbol derecho
        // Ponemos el elemento de dicho candidato
        if (nuevoNodo != null) {
            hijo.setClave(nuevoNodo.getClave());
            hijo.setObjeto(nuevoNodo.getObjeto());
            // Eliminamos al candidato
            eliminarAux(hijo, hijo.getDerecho(), nuevoNodo.getClave());
        } else {
            hijo.setClave(padreNuevoNodo.getClave());
            hijo.setObjeto(padreNuevoNodo.getObjeto());
            // Eliminamos al candidato
            eliminarAux(hijo, padreNuevoNodo, padreNuevoNodo.getClave());
        }

    }

    private NodoAVLDicc buscarPadreNodoMinimo(NodoAVLDicc nodo) {
        NodoAVLDicc encontrado = nodo;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null && nodo.getIzquierdo().getIzquierdo() != null) {
                encontrado = buscarPadreNodoMinimo(nodo.getIzquierdo());
            }
        }

        return encontrado;
    }

    private void balancear(NodoAVLDicc padre, NodoAVLDicc nodo) {
        NodoAVLDicc retorno;

        nodo.recalcularAltura();
        if (this.balance(nodo) < -1) {
            // Está desbalanceado hacia la derecha
            if (this.balance(nodo.getDerecho()) < 0) {
                // Si ambos están desbalanceados a la derecha es rotación simple izquierda
                retorno = rotarIzquierda(nodo);
            } else {
                // Si no rota derecha-izquierda
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
                retorno = rotarIzquierda(nodo);
            }
            if (padre != null) {
                if (padre.getDerecho().equals(nodo)) {
                    // Si era el derecho asignamos ese
                    padre.setDerecho(retorno);
                } else {
                    // Sino el izquierdo
                    padre.setIzquierdo(retorno);
                }
            }
        } else if (this.balance(nodo) > 1) {
            // Está desbalanceado hacia la izquierda
            if (this.balance(nodo.getIzquierdo()) > 0) {
                // Si ambos están desbalanceados a la izquierda es rotación simple derecha
                retorno = rotarDerecha(nodo);
            } else {
                // Si no rota izquierda-derecha
                nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
                retorno = rotarDerecha(nodo);
            }
            if (padre != null) {
                if (padre.getDerecho().equals(nodo)) {
                    // Si era el derecho asignamos ese
                    padre.setDerecho(retorno);
                } else {
                    // Sino el izquierdo
                    padre.setIzquierdo(retorno);
                }
            }
        }
        nodo.recalcularAltura();
    }

    private int balance(NodoAVLDicc nodo) {
        int alturaDerecho = (nodo.getDerecho() == null) ? -1 : nodo.getDerecho().getAltura();
        int alturaIzquierdo = (nodo.getIzquierdo() == null) ? -1 : nodo.getIzquierdo().getAltura();
        return alturaIzquierdo - alturaDerecho;
    }

    private NodoAVLDicc rotarDerecha(NodoAVLDicc nodo) {
        NodoAVLDicc hijoIzq = nodo.getIzquierdo();
        NodoAVLDicc temp = hijoIzq.getDerecho();
        hijoIzq.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        // Recalculo sus alturas
        nodo.recalcularAltura();
        hijoIzq.recalcularAltura();

        if (nodo == this.raiz) {
            this.raiz = hijoIzq;
        }

        return hijoIzq;
    }

    private NodoAVLDicc rotarIzquierda(NodoAVLDicc nodo) {
        NodoAVLDicc hijoDer = nodo.getDerecho();
        NodoAVLDicc temp = hijoDer.getIzquierdo();
        hijoDer.setIzquierdo(nodo);
        nodo.setDerecho(temp);
        // Recalculo sus alturas
        nodo.recalcularAltura();
        hijoDer.recalcularAltura();

        if (nodo == this.raiz) {
            this.raiz = hijoDer;
        }

        return hijoDer;
    }

    public Lista listar() {
        Lista lista = new Lista();

        listarAux(this.raiz, lista);

        return lista;
    }

    private void listarAux(NodoAVLDicc nodo, Lista lista) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getObjeto(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista lista = new Lista();

        listarRangoAux(this.raiz, min, max, lista);

        return lista;
    }

    private void listarRangoAux(NodoAVLDicc nodo, Comparable min, Comparable max, Lista lista) {
        if (nodo != null) {
            if (nodo.getClave().compareTo(min) > 0) {
                listarRangoAux(nodo.getIzquierdo(), min, max, lista);
            }

            if (nodo.getClave().compareTo(min) >= 0 && nodo.getClave().compareTo(max) <= 0) {
                lista.insertar(nodo.getObjeto(), lista.longitud() + 1);
            }
            if (nodo.getClave().compareTo(max) < 0) {
                listarRangoAux(nodo.getDerecho(), min, max, lista);
            }
        }
    }

    public Comparable minimoElem() {
        return minimoElemAux(this.raiz);
    }

    private Comparable minimoElemAux(NodoAVLDicc nodo) {
        Comparable encontrado = null;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                encontrado = minimoElemAux(nodo.getIzquierdo());
            } else {
                encontrado = nodo.getClave();
            }
        }

        return encontrado;
    }

    public Comparable maximoElem() {
        return maximoAux(this.raiz);
    }

    private Comparable maximoAux(NodoAVLDicc nodo) {
        Comparable encontrado = null;

        if (nodo != null) {
            if (nodo.getDerecho() != null) {
                encontrado = maximoAux(nodo.getDerecho());
            } else {
                encontrado = nodo.getClave();
            }
        }

        return encontrado;
    }

    public int diferenciarCandidatos(Comparable buscado) {
        int resultado;
        // Buscamos el nodo
        NodoAVLDicc nodo = this.buscarNodo(this.raiz, buscado);

        // Si tiene ambos hijos podemos operar
        if (nodo.getClave().compareTo(buscado) == 0) {
            if (nodo.getDerecho() != null && nodo.getIzquierdo() != null) {
                resultado = (int) this.minimoElemAux(nodo.getDerecho()) - (int) this.maximoAux(nodo.getIzquierdo());
            } else {
                resultado = -2;
            }
        } else {
            resultado = -1;
        }

        return resultado;
    }

    private NodoAVLDicc buscarNodo(NodoAVLDicc nodo, Comparable buscado) {
        if (nodo != null) {
            if (nodo.getClave().compareTo(buscado) != 0) {
                if (nodo.getClave().compareTo(buscado) < 0) {
                    nodo = buscarNodo(nodo.getDerecho(), buscado);
                } else {
                    nodo = buscarNodo(nodo.getIzquierdo(), buscado);
                }
            }
        }

        return nodo;
    }

    public Object getObjeto(Comparable clave) {
        // Dada la clave retorna el objeto correspondiente del nodo
        NodoAVLDicc nodo = buscarNodo(this.raiz, clave);
        Object objeto = null;

        if (nodo != null) {
            objeto = nodo.getObjeto();
        }

        return objeto;
    }

    public Lista listarPreorden() {
        // Retorna una lista con los elementos del arbol en PREORDEN.
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoAVLDicc nodo, Lista lista) {
        // Metodo recursivo PRIVADO porque su paramentro es de tipo NodoArbol.
        if (nodo != null) {
            // Visita el elemento en el nodo.
            lista.insertar(nodo.getObjeto(), lista.longitud() + 1);
            // Recorre sus hijos en preorden.
            listarPreordenAux(nodo.getIzquierdo(), lista);
            listarPreordenAux(nodo.getDerecho(), lista);
        }
    }

     public Lista listarInorden() {
        // Retorna una lista con los elementos del arbol en INORDEN.
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoAVLDicc nodo, Lista lista) {
        // Metodo recursivo PRIVADO porque se parametro es tipo arbol.
        if (nodo != null) {
            // Visita el subarbol izquierdo en INORDEN.
            listarInordenAux(nodo.getIzquierdo(), lista);
            // Visita el elemento raiz.
            lista.insertar(nodo.getObjeto(), lista.longitud() + 1);
            // Visita el subarbol derecho en INORDEN.
            listarInordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPostorden() {
        // Retorna una lista con los elementos del arbol en POSTORDEN.
        Lista lista = new Lista();
        listarPostordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPostordenAux(NodoAVLDicc nodo, Lista lista) {
        // Metodo recursivo PRIVADO porque su parametro es tipo arbol.
        if (nodo != null) {
            // Visita el subarbol izquierdo en POSTORDEN.
            listarPostordenAux(nodo.getIzquierdo(), lista);
            // Visita el subarbol derecho en POSTORDEN.
            listarPostordenAux(nodo.getDerecho(), lista);
            // Visita el elemento raiz.
            lista.insertar(nodo.getObjeto(), lista.longitud() + 1);
        }
    }

    public boolean vacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        raiz = null;
    }

    @Override
    public String toString() {
        String cadena = "ArbolAVL vacío";

        if (this.raiz != null) {
            cadena = stringAux(this.raiz, "");
        }
        return cadena;
    }

    private String stringAux(NodoAVLDicc nodo, String cadena) {
        cadena += "Nodo: " + nodo.getObjeto().toString();

        if (nodo.getIzquierdo() != null) {
            cadena += " | HI: " + nodo.getIzquierdo().getObjeto().toString();
        } else {
            cadena += " | HI: -";
        }

        if (nodo.getDerecho() != null) {
            cadena += " | HD: " + nodo.getDerecho().getObjeto().toString() + "";
        } else {
            cadena += " | HD: -";
        }

        cadena += " | Altura : " + nodo.getAltura() + "\n";

        if (nodo.getIzquierdo() != null) {
            cadena = stringAux(nodo.getIzquierdo(), cadena);
        }
        if (nodo.getDerecho() != null) {
            cadena = stringAux(nodo.getDerecho(), cadena);
        }
        return cadena;
    }

    public String toStringTipoCiudad() {
        String cadena = "ArbolAVL vacío";

        if (this.raiz != null) {
            cadena = stringTipoCiudadAux(this.raiz, "");
        }
        return cadena;
    }

    private String stringTipoCiudadAux(NodoAVLDicc nodo, String cadena) {
        cadena += "Nodo: " + ((Ciudad) nodo.getObjeto()).getNombre();

        if (nodo.getIzquierdo() != null) {
            cadena += " | HI: " + ((Ciudad) nodo.getIzquierdo().getObjeto()).getNombre();
        } else {
            cadena += " | HI: -";
        }

        if (nodo.getDerecho() != null) {
            cadena += " | HD: " + ((Ciudad) nodo.getDerecho().getObjeto()).getNombre() + "";
        } else {
            cadena += " | HD: -";
        }

        cadena += " | Altura : " + nodo.getAltura() + "\n";

        if (nodo.getIzquierdo() != null) {
            cadena = stringTipoCiudadAux(nodo.getIzquierdo(), cadena);
        }
        if (nodo.getDerecho() != null) {
            cadena = stringTipoCiudadAux(nodo.getDerecho(), cadena);
        }
        return cadena;
    }

}
