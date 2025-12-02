package com.erandeni.reservasHotel;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.io.*;

public class Hotel {
    String nombre;
    List<Habitacion> habitaciones;
    List<Reserva> reservas;
    List<Cliente> clientes;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.clientes = new ArrayList<>();
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

    public boolean agregarCliente(Cliente cliente) {

        // Validación: nombre requerido
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            System.out.println("Error: El nombre es obligatorio.");
            return false;
        }

        // Validación: email válido
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            System.out.println("Error: Email inválido.");
            return false;
        }

        // Validación: cliente duplicado por email
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                System.out.println("Error: Este cliente ya está registrado.");
                return false;
            }
        }

        // Si pasa todas las validaciones, agregar
        clientes.add(cliente);
      //  System.out.println("✔ Cliente agregado correctamente.");
        return true;
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
            if (!habitacion.isDisponible()) {
                continue;
            }
            if (habitacionDisponible(habitacion, inicio, fin)) {
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }

    public boolean mostrarHabitaciones() {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
            return false;
        }

        System.out.println("\nLista de habitaciones:");
        for (Habitacion h : habitaciones) {
            System.out.println("N° " + h.getNumero() + " - " + h.getTipo() + " → " +
                    (h.isDisponible() ? "Disponible" : "Ocupada"));
        }
        return true;
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("⚠ No hay clientes registrados.");
            return;
        }
        clientes.forEach(System.out::println);
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("⚠ No existen reservas en el sistema.");
            return;
        }
        reservas.forEach(System.out::println);
    }

    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public void cargarReservas() {
        File archivo = new File("reservas.dat");
        if (!archivo.exists()) {
            System.out.println("No hay archivo previo de reservas. Se iniciará uno nuevo.");
            return;
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            reservas = (List<Reserva>) entrada.readObject();
            System.out.println("Reservas cargadas correctamente.");
        } catch (Exception e) {
            System.out.println("⚠ Error al cargar reservas: " + e.getMessage());
        }
    }


    public void guardarReservas() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("reservas.dat"))) {
            salida.writeObject(reservas);
            System.out.println("Reservas guardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas: " + e.getMessage());
        }
    }

    public String getNombreHotel() {
        return nombre;
    }

}
