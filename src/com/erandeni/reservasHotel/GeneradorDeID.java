package com.erandeni.reservasHotel;

public class GeneradorDeID {
    static int contadorReservas = 1;
    static int contadorCliente = 1;
    static int contadorHabitaciones = 1;

    public static String generarCodigoReserva() {
       return "R" + String.format("%05d", contadorReservas++);
    }

    public static String generarCodigoCliente() {
        return "C" + String.format("%05d", contadorCliente++);
    }

    public static int generarNumeroHabitacion() {
        return contadorHabitaciones++;
    }


}
