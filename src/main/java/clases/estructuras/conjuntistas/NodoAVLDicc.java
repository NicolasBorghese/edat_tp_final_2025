package clases.estructuras.conjuntistas;

public class NodoAVLDicc {
    // La clave se utiliza para buscar
    private Comparable clave;
    // El objeto tiene el resto de los atributos
    private Object objeto;
    private int altura;
    private NodoAVLDicc izquierdo;
    private NodoAVLDicc derecho;

    // Constructores
    public NodoAVLDicc(Comparable clave, Object objeto) {
        this.clave = clave;
        this.objeto = objeto;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0;
    }

    public NodoAVLDicc(Comparable clave, Object objeto, NodoAVLDicc izq, NodoAVLDicc der) {
        this.clave = clave;
        this.objeto = objeto;
        this.izquierdo = izq;
        this.derecho = der;
        this.altura = 0;
    }

    // Observadores
    public Comparable getClave() {
        return this.clave;
    }

    public Object getObjeto() {
        return this.objeto;
    }

    public int getAltura() {
        return this.altura;
    }

    public NodoAVLDicc getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVLDicc getDerecho() {
        return this.derecho;
    }

    // Modificadores
    public void setClave(Comparable claveNueva) {
        this.clave = claveNueva;
    }

    public void setObjeto(Object objetoNuevo) {
        this.objeto = objetoNuevo;
    }

    public void setIzquierdo(NodoAVLDicc izq) {
        this.izquierdo = izq;
    }

    public void setDerecho(NodoAVLDicc der) {
        this.derecho = der;
    }

    public void recalcularAltura() {
        int alturaDerecho = (this.derecho == null) ? -1 : this.derecho.getAltura();
        int alturaIzquierdo = (this.izquierdo == null) ? -1 : this.izquierdo.getAltura();
        this.altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

}
