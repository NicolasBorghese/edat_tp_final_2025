package clases;

import java.util.TreeMap;
import java.util.Map;
import java.time.Month;
import java.time.YearMonth;

public class Ciudad implements Comparable<Ciudad> {
    private String nombre;
    private String nomenclatura;
    private double superficie;
    private double cantM3ConsXDia;
    private Map<YearMonth, Integer> poblacionPorFecha;

    public Ciudad(String nombre, String nomenclatura, double superficie, double consumoDiario) {
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.superficie = superficie;
        this.cantM3ConsXDia = consumoDiario;
        this.poblacionPorFecha = new TreeMap<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNomenclatura() {
        return this.nomenclatura;
    }

    public double getConsumoDiarioProm() {
        return this.cantM3ConsXDia;
    }

    public double getSuperficie() {
        return this.superficie;
    }

    public Integer getPoblacion(YearMonth fecha) {
        return poblacionPorFecha.get(fecha);
    }

    public void setConsumoDiarioProm(double nuevoConsumo) {
        this.cantM3ConsXDia = nuevoConsumo;
    }

    /**
     * Si no hay un registro en la fecha indicada, entonces agrega un registro
     * con la cantidad de poblacion para esa fecha
     * 
     * @param fecha         YearMoth
     * @param cantPoblacion int
     * @return boolean
     */
    public boolean agregarPoblacion(YearMonth fecha, int cantPoblacion) {
        boolean agregado = false;
        if (poblacionPorFecha.get(fecha) == null) {
            poblacionPorFecha.put(fecha, cantPoblacion);
            agregado = true;
        }
        return agregado;
    }

    /**
     * Si existe un registro para la fecha indicada entonces sobreescribe la
     * cantidad
     * de poblacion para esa fecha
     * 
     * @param fecha
     * @param cantPoblacion
     * @return boolean
     */
    public boolean modificarPoblacion(YearMonth fecha, int cantPoblacion) {
        boolean modificado = false;
        if (poblacionPorFecha.get(fecha) != null) {
            poblacionPorFecha.put(fecha, cantPoblacion);
            modificado = true;
        }
        return modificado;
    }

    private String crearNomenclatura(int numCiudad) {
        String nom = "";
        String[] palabras = nombre.toUpperCase().split(" ");
        if (palabras.length == 1) {
            nom = palabras[0].substring(0, 2);
        } else {
            nom = palabras[0].substring(0, 1).toUpperCase() + palabras[1].substring(0, 1);
        }
        nom += numCiudad;
        return nom;
    }

    public double calcularAprovisionamiento(Month mes) {
        double cons = 0;
        int poblacionProm = calcularPobPromedio(mes);
        cons = poblacionProm * cantM3ConsXDia;
        return cons;
    }

    private int calcularPobPromedio(Month mes) {
        int suma = 0;
        int cantMeses = 0;
        for (Map.Entry<YearMonth, Integer> entry : poblacionPorFecha.entrySet()) {
            if (entry.getKey().getMonth() == mes) {
                suma += entry.getValue();
                cantMeses++;
            }
        }

        return suma / cantMeses;
    }

    public boolean equals(Ciudad c) {
        return this.nombre == c.nombre;
    }

    @Override
    public int compareTo(Ciudad c) {
        return this.nombre.compareTo(c.nombre);
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public String datos() {
        return "Ciudad: " + this.nombre + "\n" + "Nomenclatura: " + this.nomenclatura + "\n" + "Superficie: "
                + this.superficie + "\n";
    }
}
