package clases;

public class CiudadConsumo implements Comparable<CiudadConsumo> {
    private Ciudad ciudad;
    private double consumoAnual;

    public CiudadConsumo(Ciudad ciudad, double consumoAnual) {
        this.ciudad = ciudad;
        this.consumoAnual = consumoAnual;
    }

    public Ciudad getCiudad() {
        return this.ciudad;
    }

    public double getConsumoAnual() {
        return consumoAnual;
    }

    @Override 
    public String toString(){
        return ciudad.getNombre() + ", " + this.consumoAnual;
    }
    @Override
    public int compareTo(CiudadConsumo otro) {
        return Double.compare(this.consumoAnual, otro.consumoAnual);
    }
}
