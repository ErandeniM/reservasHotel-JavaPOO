package com.erandeni.reservasHotel;

import java.time.LocalDate;

public class Reserva {
    Cliente cliente;
    Habitacion habitacion;
    String codigoReserva;
    LocalDate fechaInicio;
    LocalDate fechaFin;



    public Reserva(Cliente cliente,Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
       this.codigoReserva = GeneradorDeID.generarCodigoReserva();
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    @Override
    public String toString() {
        return "Reserva {" +
                "\n  Código: " + codigoReserva +
                "\n  Cliente: " + cliente.getNombreCompleto() +
                "\n  Habitación: " +
                (habitacion != null
                        ? habitacion.getNumero() + " (" + habitacion.getTipo() + ")"
                        : "No asignada") +
                "\n  Fecha de inicio: " + fechaInicio +
                "\n  Fecha de fin: " + fechaFin +
                "\n}";
    }

public Habitacion getHabitacion() {
    return this.habitacion;
}
    public String getCodigoReserva() {
        return codigoReserva;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
    public LocalDate getFechaInicio() {
        return  fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }




}
