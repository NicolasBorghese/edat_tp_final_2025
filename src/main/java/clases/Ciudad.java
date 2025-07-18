package clases;

import java.util.HashMap;
import java.util.Map;
import java.time.Month;
import java.time.YearMonth;

public class Ciudad implements Comparable<Ciudad> {
    private String nombre;
    private String nomenclatura;
    private double superficie;
    private double cantM3ConsXDia;
    private static int contadorNomenclatura = 3000;

    private Map<YearMonth, Integer> poblacionPorFecha;

    public Ciudad(String nombre, double superficie, double consumoDiario) {
        this.nombre = nombre;
        this.superficie = superficie;
        this.nomenclatura = crearNomenclatura();
        this.cantM3ConsXDia = consumoDiario;

        /*
         * Podría implementarse un TreeMap, porque serviria más adelante para hacer
         * graficos/tablas porque ordena cronologicamente.
         */
        poblacionPorFecha = new HashMap<>();
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

    /*
     * Hay 2 métodos para cargar datos. Uno que no puede sobreescribir (solo carga).
     * y otro que si sobreescribe (para modificar).
     */
    public boolean agregarPoblacion(YearMonth fecha, int cantPoblacion) {
        boolean agregado = false;
        if (poblacionPorFecha.get(fecha) == null) {
            poblacionPorFecha.put(fecha, cantPoblacion);
            agregado = true;
        }
        return agregado;
    }

    public boolean modificarPoblacion(YearMonth fecha, int cantPoblacion) {
        boolean modificado = false;
        if (poblacionPorFecha.get(fecha) != null) {
            poblacionPorFecha.put(fecha, cantPoblacion);
            modificado = true;
        }
        return modificado;
    }

    private String crearNomenclatura() {
        String nom = "";
        String[] palabras = nombre.toUpperCase().split(" ");
        if (palabras.length == 1) {
            nom = palabras[0].substring(0, 2);
        } else {
            nom = palabras[0].substring(0, 1).toUpperCase() + palabras[1].substring(0, 1);
        }
        nom += contadorNomenclatura;
        contadorNomenclatura += 1;
        return nom;
    }

    public double calcularAprovisionamiento(Month mes) {
        /*
         * ANOTACION: Faltaría calcular por lo de las tuberías (todavía no está hecho).
         */
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
}
