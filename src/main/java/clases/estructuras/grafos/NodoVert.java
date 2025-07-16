package clases.estructuras.grafos;

public class NodoVert {
    private Object elemento;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object elem, NodoVert sigV) {
        this.elemento = elem;
        this.sigVertice = sigV;
        this.primerAdy = null;
    }

    public Object getElemento(){
        return this.elemento;
    }

    public void setElemento(Object elem){
        this.elemento = elem;
    }

    public NodoVert getSigVertice(){
        return this.sigVertice;
    }

    public void setSigVertice(NodoVert sigVert){
        this.sigVertice = sigVert;
    }

    public NodoAdy getPrimerAdy(){
        return this.primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy){
        this.primerAdy = primerAdy;
    }
}
