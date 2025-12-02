package com.erandeni.reservasHotel;
import java.io.Serializable;

public class Habitacion implements Serializable {
    private static final long serialVersionUID = 1L;
    String tipo;
    int numero;
    double precio;
    boolean disponible;

    public Habitacion(String tipo, int numero, double precio) {
        this.tipo = tipo;
        this.numero = numero;
        this.precio = precio;
        disponible = true;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipo() {
        return tipo;
    }
    public int getNumero() {
        return this.numero;
    }
    public boolean isDisponible() {return disponible;}
    public String toString() {
        return "Habitación " + tipo + ". Precio: " + precio;
    }


}
