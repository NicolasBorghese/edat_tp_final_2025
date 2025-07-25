package clases;

public class Tuberia {
    private String nomenclatura;
    private double caudalMinimo;
    private double caudalMaximo;
    private double diametro;
    private String estado;

    public Tuberia(String nomenclatura, double caudalMinimo, double caudalMaximo,
            double diametro) {
        nomenclatura = nomenclatura;
        this.caudalMinimo = caudalMinimo;
        this.caudalMaximo = caudalMaximo;
        this.diametro = diametro;
        this.estado = "ACTIVO";
    }

    public String crearNomenclatura(Ciudad cOri, Ciudad cDest) {
        String nom = "";
        nom = cOri.getNomenclatura();
        nom += "-" + cDest.getNomenclatura();
        return nom;
    }

    public String getNomenclatura() {
        return this.nomenclatura;
    }

    public double getCaudalMinimo() {
        return this.caudalMinimo;
    }

    public double getCaudalMaximo() {
        return this.caudalMaximo;
    }

    public double getDiametro() {
        return this.diametro;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setCaudalMinimo(double nuevoCMinimo) {
        this.caudalMinimo = nuevoCMinimo;
    }

    public void setCaudalMaximo(double nuevoCMaximo) {
        this.caudalMaximo = nuevoCMaximo;
    }

    public void setDiametro(double nuevoDiametro) {
        this.diametro = nuevoDiametro;
    }

    public void setActivo() {
        this.estado = "ACTIVO";
    }

    public void setInactivo() {
        this.estado = "INACTIVO";
    }

    public void setEnDis() {
        this.estado = "EN DISEÑO";
    }

    public void setEnRep() {
        this.estado = "EN REPARACION";
    }
}
