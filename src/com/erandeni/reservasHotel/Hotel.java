package com.erandeni.reservasHotel;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Hotel {
    String nombre;
    List<Habitacion> habitaciones;
    List<Reserva> reservas;
    List<Cliente> clientes;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    public void agregarReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
}

    public boolean habitacionDisponible(Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion)) {
                if (!(fechaFin.isBefore(reserva.getFechaInicio()) || fechaInicio.isAfter(reserva.getFechaFin()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Habitacion> obtenerHabitacionesDisponibles(LocalDate inicio, LocalDate fin) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacionDisponible(habitacion, inicio, fin)) {
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }



    public String getNombreHotel() {
        return nombre;
    }

}
