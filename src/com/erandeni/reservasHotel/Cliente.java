package com.erandeni.reservasHotel;
import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    String clienteID;
    String nombre;
    String apellido;
    String telefono;
    String email;


    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.clienteID = GeneradorDeID.generarCodigoCliente();
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }


    //obtener informacion del cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "ID='" + clienteID + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getClienteID() {
        return clienteID;
    }

    public String getNombre() {return nombre;}

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }


}
