/************* Autores ***********
Gonzalo Piacentini, Legajo FAI-2514
Nicolas Martin Borghese, Legajo FAI-997
Santiago Alzuguren, Legajo FAI-4691
Sol Juliana Grant Reyes, Legajo FAI-4808
 */

package clases.estructuras.lineales.dinamicas;
public class Nodo {
    private Object elem;
    private Nodo enlace;
    
    // Constructor.
    public Nodo (Object elem, Nodo enlace){
        this.elem = elem;
        this.enlace = enlace;
    }

    // Modificadores.
    public void setElem(Object unElem){
        this.elem = unElem;
    }

    public void setEnlace(Nodo unEnlace){
        this.enlace = unEnlace;
    }

    // Observadores.
    public Object getElem(){
        return elem;
    }

    public Nodo getEnlace(){
        return enlace;
    }
    
}
