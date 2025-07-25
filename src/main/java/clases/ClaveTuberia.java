package clases;

import java.util.Objects;

public class ClaveTuberia {
    private String nom1;
    private String nom2;

    public ClaveTuberia(Ciudad cOrigen, Ciudad cDestino) {
        this.nom1 = cOrigen.getNomenclatura();
        this.nom2 = cDestino.getNomenclatura();
    }

    @Override
    public boolean equals(Object o) {
        boolean iguales = true;
        if (this != o) {
            if (!(o instanceof ClaveTuberia)) {
                iguales = false;
            } else {
                ClaveTuberia otra = (ClaveTuberia) o;
                if (!this.nom1.equals(otra.nom1) || this.nom2.equals(otra.nom2)) {
                    iguales = false;
                }
            }
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom1, nom2);
    }

    @Override
    public String toString() {
        return this.nom1 + "-" + this.nom2;
    }

}
