package com.erandeni.reservasHotel;

public class Habitacion {
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


    public String getTipo() {
        return tipo;
    }

    public int getNumero() {
        return this.numero;
    }

    public String toString() {
        return "Habitacion " + tipo + ". Precio: " + precio;
    }


}
