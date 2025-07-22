package estructuras.grafos;
// NodoAdy son los arcos.
public class NodoAdy{
    private NodoVert destino;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert vertice, NodoAdy sigAdy, Object etiqueta) {
        this.destino = vertice;
        this.sigAdyacente = sigAdy;
        this.etiqueta = etiqueta;
    }

    public NodoVert getVertice() {
        return this.destino;
    }

    public void setVertice(NodoVert nuevoVertice) {
        this.destino = nuevoVertice;
    }

    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy nuevoSigAdy){
        this.sigAdyacente = nuevoSigAdy;
    }

    public Object getEtiqueta(){
        return this.etiqueta;
    }

    public void setEtiqueta(Object nuevaEtiqueta){
        this.etiqueta = nuevaEtiqueta;
    }
}
