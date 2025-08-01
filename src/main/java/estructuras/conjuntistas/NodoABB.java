package estructuras.conjuntistas;

public class NodoABB {
    private Comparable elemento;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Comparable elem, NodoABB izq, NodoABB der) {
        this.elemento = elem;
        this.izquierdo = izq;
        this.derecho = der;
    }

    public NodoABB(Comparable elem) {
        this.elemento = elem;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Comparable getElemento() {
        return this.elemento;
    }

    public void setElemento(Comparable elem) {
        this.elemento = elem;
    }

    public NodoABB getIzquierdo() {
        return this.izquierdo;
    }

    public void setIzquierdo(NodoABB izq) {
        this.izquierdo = izq;
    }

    public NodoABB getDerecho() {
        return this.derecho;
    }

    public void setDerecho(NodoABB der){
        this.derecho = der;
    }
}
