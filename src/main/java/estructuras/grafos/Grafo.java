package estructuras.grafos;

import estructuras.lineales.dinamicas.Cola;
import estructuras.lineales.dinamicas.Lista;

// Grafo Dirigido, Etiquetado, NO permite ciclos, con listas de Adyacencia.
public class Grafo {
    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        // Inserta un vertice NUEVO en el grafo.
        boolean insertado = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            insertado = true;
        }
        return insertado;
    }

    private NodoVert ubicarVertice(Object buscado) {
        // Retorna el vertice que contiene al Objeto 'buscado'.
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElemento().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object vertAEliminar) {
        // Para eliminar el vertice voy a tener que eliminar todos los arcos que apunte
        // a él.
        boolean eliminado = false;
        NodoVert actual = this.inicio;
        NodoVert anterior = null;
        // Acá busco el Vertice.
        while (actual != null && !actual.getElemento().equals(vertAEliminar)) {
            anterior = actual;
            actual = actual.getSigVertice();
        }
        if (actual != null) {
            // Si lo encontré, elimino todos los arcos que apunten al vértice.
            NodoVert aux = this.inicio;
            while (aux != null) {
                eliminarVerticeAux(aux, vertAEliminar);
                aux = aux.getSigVertice();
            }
            // Elimino el vertice
            if (anterior == null) {
                // En caso de que sea el primer vertice.
                inicio = actual.getSigVertice();
            } else {
                anterior.setSigVertice(actual.getSigVertice());
            }
            eliminado = true;
        }
        return eliminado;
    }

    private void eliminarVerticeAux(NodoVert origen, Object vertAEliminar) {
        NodoAdy actual = origen.getPrimerAdy();
        NodoAdy anterior = null;

        while (actual != null) {
            if (actual.getVertice().getElemento().equals(vertAEliminar)) {
                // Es el que quiero eliminar.
                if (anterior == null) {
                    // Es el primer ady.
                    origen.setPrimerAdy(actual.getSigAdyacente());
                    actual = origen.getPrimerAdy();
                } else {
                    // No es el primer ady.
                    anterior.setSigAdyacente(actual.getSigAdyacente());
                    actual = anterior.getSigAdyacente();
                }
            } else {
                // No es el que hay que eliminar.
                anterior = actual;
                actual = actual.getSigAdyacente();
            }
        }
    }

    public boolean existeVertice(Object elemento) {
        return (ubicarVertice(elemento) != null);
    }

    public boolean insertarArco(Object ori, Object dest, Object etiqueta) {
        boolean insertado = false;
        NodoVert origen = ubicarVertice(ori);
        NodoVert destino = ubicarVertice(dest);
        if (origen != null && destino != null) {
            NodoAdy nuevo = new NodoAdy(destino, origen.getPrimerAdy(), etiqueta);
            origen.setPrimerAdy(nuevo);
            insertado = true;
        }
        return insertado;
    }

    public boolean eliminarArco(Object ori, Object dest) {
        /*
         * Como suponemos, por ahora, que solo hay un arco entre 2 vertices no importa
         * la etiqueta.
         */
        boolean eliminado = false;
        NodoVert origen = ubicarVertice(ori);
        NodoVert destino = ubicarVertice(dest);
        if (origen != null && destino != null) {
            eliminarArcoAux(origen, destino);
            eliminado = true;
        }
        return eliminado;
    }

    private void eliminarArcoAux(NodoVert origen, NodoVert destino) {
        NodoAdy actual = origen.getPrimerAdy();
        NodoAdy anterior = null;
        while (actual != null && actual.getVertice().getElemento().equals(destino.getElemento())) {
            anterior = actual;
            actual = actual.getSigAdyacente();
        }
        if (actual != null) {
            if (anterior == null) {
                origen.setPrimerAdy(actual.getSigAdyacente());
            } else {
                anterior.setSigAdyacente(actual.getSigAdyacente());
            }
        }
    }

    public boolean existeArco(Object origen, Object destino, Object etiqueta) {
        // Para que exista el arco tiene que:
        // Existir el vertice de Origen y Destino.
        // Existir una relación entre los 2.
        // Que esa relación tenga la misma etiqueta.
        boolean existe = false;
        NodoVert nOrigen = ubicarVertice(origen);
        NodoVert nDestino = ubicarVertice(destino);
        if (nOrigen != null && nDestino != null) {
            NodoAdy aux = nOrigen.getPrimerAdy();
            while (aux != null && !existe) {
                if (aux.getVertice().getElemento().equals(destino) && aux.getEtiqueta().equals(etiqueta)) {
                    existe = true;
                }
                aux = aux.getSigAdyacente();
            }
        }
        return existe;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        // Define un vértice para empezar a recorrer.
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElemento()) < 0) {
                // Si el vértice no fue visitado aún, avanza en profundidad.
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista visitados) {
        if (n != null) {
            // Marca al vertice n como visitado.
            visitados.insertar(n.getElemento(), visitados.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                // Visita en profundidad los adyacentes de n aún no visitados.
                if (visitados.localizar(ady.getVertice().getElemento()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElemento()) < 0) {
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert n, Lista visitados) {
        Cola col = new Cola();
        visitados.insertar(n.getElemento(), visitados.longitud() + 1);
        col.poner(n);
        while (!col.esVacia()) {
            NodoVert aux = (NodoVert) col.obtenerFrente();
            col.sacar();
            NodoAdy ady = aux.getPrimerAdy();
            while (ady != null) {
                NodoVert vert = ady.getVertice();
                if (visitados.localizar(vert.getElemento()) < 0) {
                    visitados.insertar(vert, visitados.longitud() + 1);
                    col.poner(vert);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        // Verifica si ambos vertices existen.
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElemento().equals(origen))
                auxO = aux;
            if (aux.getElemento().equals(destino))
                auxD = aux;
            aux = aux.getSigVertice();
        }

        if (auxO != null && auxD != null) {
            // Si ambos vertices existen busca si existe camino entre ambos.
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            // Si vertice n es el destino, HAY CAMINO!
            if (n.getElemento().equals(dest)) {
                exito = true;
            } else {
                // Si no es el destino verifica si hay camino entre n y destino,
                vis.insertar(n.getElemento(), vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElemento()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public boolean vacio() {
        return this.inicio == null;
    }

}
