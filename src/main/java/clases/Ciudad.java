package clases;

import java.util.TreeMap;
import java.util.Map;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class Ciudad implements Comparable<Ciudad> {
    private String nombre;
    private String nomenclatura;
    private double superficie;
    private double consumoDiarioProm;
    private TreeMap<YearMonth, Integer> poblacionPorFecha;

    /**
     * Constructor que se utiliza al momento de cargar los datos de una ciudad existente
     *
     * @param nombre
     * @param nomenclatura
     * @param superficie
     * @param consumoDiarioProm
     */
    public Ciudad(String nombre, String nomenclatura, double superficie, double consumoDiarioProm) {
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.superficie = superficie;
        this.consumoDiarioProm = consumoDiarioProm;
        this.poblacionPorFecha = new TreeMap<>();
    }

    /**
     * Constructor que se utiliza al momento de crear una nueva ciudad
     *
     * @param nombre
     * @param numero
     * @param superficie
     * @param consumoDiarioProm
     */
    public Ciudad(String nombre, int numero,double superficie, double consumoDiarioProm) {
        this.nombre = nombre;
        this.nomenclatura = crearNomenclatura(numero);
        this.superficie = superficie;
        this.consumoDiarioProm = consumoDiarioProm;
        this.poblacionPorFecha = new TreeMap<>();
    }

    /**
     * Constructor que se utiliza solo para comparar su id (nombre)
     *
     * @param nombre
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.nomenclatura = "AA-0000";
        this.superficie = 0;
        this.consumoDiarioProm = 0;
        this.poblacionPorFecha = new TreeMap<>();
    }

    // OBSERVADORES
    public String getNombre() {
        return this.nombre;
    }

    public String getNomenclatura() {
        return this.nomenclatura;
    }

    public double getConsumoDiarioProm() {
        return this.consumoDiarioProm;
    }

    public double getSuperficie() {
        return this.superficie;
    }

    /**
     * Devuelve un TreeMap donde cada clave es una fecha y cada valor contenido
     * representa una cantidad de habitantes
     *
     * @return TreeMap
     */
    public TreeMap<YearMonth, Integer> getPoblacionPorFecha() {
        return poblacionPorFecha;
    }

    public Integer getPoblacionEnFecha(YearMonth fecha) {
        return poblacionPorFecha.get(fecha);
    }

    // MODIFICADORES
    public void setPoblacionPorFecha(TreeMap<YearMonth, Integer> poblacionPorFecha) {
        this.poblacionPorFecha = poblacionPorFecha;
    }

    public void setSuperficie(double superficie) { this.superficie = superficie; }

    public void setConsumoDiarioProm(double nuevoConsumo) {
        this.consumoDiarioProm = nuevoConsumo;
    }

    /**
     * Si no existe un registro para la población en esa fecha entonces lo crea y le asigna un valor.<br>
     * Si existe un registro entonces sobreescribe su valor con el dato recibido por parámetro.
     *
     * @param fecha YearMonth
     * @param cantPoblacion int
     * @return
     */
    public boolean modificarPoblacionPorFecha(YearMonth fecha, int cantPoblacion){

        if (!this.agregarPoblacion(fecha, cantPoblacion)) {
            this.modificarPoblacion(fecha, cantPoblacion);
        }

        return true;
    }

    /**
     * Si no hay un registro en la fecha indicada, entonces agrega un registro
     * con la cantidad de poblacion para esa fecha
     * 
     * @param fecha YearMoth
     * @param cantPoblacion int
     * @return boolean
     */
    public boolean agregarPoblacion(YearMonth fecha, int cantPoblacion) {
        System.out.println("La fecha es: "+fecha+" y la cantidad es: "+cantPoblacion);
        return poblacionPorFecha.putIfAbsent(fecha, cantPoblacion) == null;
    }

    /**
     * Si existe un registro para la fecha indicada entonces sobreescribe la
     * cantidad de poblacion para esa fecha
     * 
     * @param fecha
     * @param cantPoblacion
     * @return boolean
     */
    public boolean modificarPoblacion(YearMonth fecha, int cantPoblacion) {
        return poblacionPorFecha.replace(fecha, cantPoblacion) != null;
    }

    private String crearNomenclatura(int numUltimaCiudad) {
        String nom = "";
        String[] palabras = nombre.toUpperCase().split(" ");
        if (palabras.length == 1) {
            nom = palabras[0].substring(0, 2);
        } else {
            nom = palabras[0].substring(0, 1).toUpperCase() + palabras[1].substring(0, 1);
        }
        nom += numUltimaCiudad + 1;
        return nom;
    }

    public double getConsumoEnFecha(YearMonth fecha) {
        double consumo = this.poblacionPorFecha.get(fecha) * this.consumoDiarioProm;
        return Math.round(consumo * 100.0) / 100.0;
    }

    public double calcularAprovisionamiento(Month mes) {
        double cons = 0;
        int poblacionProm = calcularPobPromedio(mes);
        cons = poblacionProm * consumoDiarioProm;
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

    public double calcularConsumoAnual(Year year) {
        double total = 0;
        for (Map.Entry<YearMonth, Integer> entry : poblacionPorFecha.entrySet()) {
            if (Year.of(entry.getKey().getYear()).equals(year)) {
                total += (entry.getValue() * consumoDiarioProm);
            }
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public boolean equals(Ciudad c) {
        return this.nombre == c.nombre;
    }

    @Override
    public int compareTo(Ciudad c) {
        String n1 = (this.nombre.toUpperCase()).replaceAll("\\s+", "");
        String n2 = (c.nombre.toUpperCase()).replaceAll("\\s+", "");
        return n1.compareTo(n2);
    }

    @Override
    public String toString() {
        String respuesta = "";
        respuesta += this.nombre;

        return respuesta;
    }

    public String toStringCompleto() {
        return "Ciudad: " + this.nombre + "\n" + "Nomenclatura: " + this.nomenclatura + "\n" + "Superficie: "
                + this.superficie + "\n";
    }
}
