package com.erandeni.reservasHotel;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Instrucciones
 * Diseñar un sistema orientado a objetos que gestione las reservas de un hotel.
 * El programa debe permitir registrar clientes, habitaciones y reservas, realizando las validaciones necesarias para evitar inconsistencias (como fechas incorrectas, habitaciones ocupadas o datos incompletos).
 * Los recepcionistas del hotel podrán:
 * -Registrar habitaciones con su información (número, tipo, precio, disponibilidad).
 * -Registrar clientes.
 * -Crear reservas, verificando disponibilidad y fechas válidas.
 * -Consultar reservas realizadas.
 * -Marcar habitaciones como ocupadas o disponibles según las reservas activas.
 * • Se sugiere la clase Habitación, con los siguientes atributos: número, tipo, precio, disponibilidad.
 * • La clase cliente, con los siguientes atributos: cliente id, nombre, teléfono, email.
 * • La clase Reserva: código de reserva, cliente, habitación, fecha de inicio, fecha fin, total.
 * • La clase Hotel: nombre, lista habitaciones, lista clientes, lista reservas.
 * • Y la clase que contiene el método principal.
 * • Las validaciones:
 * Para fechas:
 * No se aceptan fechas vacías o con formato incorrecto.
 * La fecha de inicio no puede ser posterior a la de fin.
 * • Para la disponibilidad de habitación:
 * Antes de crear una reserva, verificar que la habitación esté libre.
 * Al cancelar una reserva, la habitación vuelve a estar disponible.
 * • Datos del cliente:
 * Todos los campos deben ser válidos y completos.
 * Evitar duplicidad de clientes (mismo ID o correo).
 * • Reservas:
 * No debe crearse una reserva sin cliente ni habitación asociados.
 * No se permiten reservas con duración de 0 noches.
 * Listados:
 * Si no hay habitaciones, clientes o reservas registradas, mostrar mensajes como: "No hay habitaciones registradas.", "No existen reservas en el sistema."
 */

public class Main {
    static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcion;

        System.out.println("BIENVENIDO AL PROGRAMA ADMIN HOTEL");
        //esta seccion eventualmente poodria funcionar como un login pero por ahora solo usamos un nombre ingresado
        System.out.println("Introduce el nombre del hotel: ");
        String nombreHotel = input.nextLine();
        Hotel hotel = new Hotel(nombreHotel);

        System.out.println("Bienvenido a " + hotel.getNombreHotel());
        do {
            System.out.println("SELECCIONE UNA OPCION DEL MENU");
            System.out.println("1. Registrar habitación.");
            System.out.println("2. Registrar cliente.");
            System.out.println("3. Crear reserva.");
            System.out.println("4. Consultar reservas.");
            System.out.println("5. Modificar estado de habitacion.");
            System.out.println("6. Salir del sistema.");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("AGREGANDO HABITACIONES");
                    System.out.println("Ingrese el tipo de habitacion (REGULAR, DOBLE, SUITE, PRESIDENCIAL)");
                    String tipoHabitacion = input.nextLine();
                    System.out.println("Ingrese el precio de la habitacion: ");
                    double precioHabitacion = input.nextDouble();
                    input.nextLine();
                    int numeroHabitacion = GeneradorDeID.generarNumeroHabitacion();
                    Habitacion nuevaHabitacion = new Habitacion(tipoHabitacion, numeroHabitacion, precioHabitacion);
                    hotel.agregarHabitacion(nuevaHabitacion);
                    System.out.println("Habitacion registrada con número: " + numeroHabitacion);
                    break;
                case 2:
                    System.out.println("CLIENTES");
                    hotel.mostrarClientes();
                    System.out.println("AGREGANDO DATOS DEL CLIENTE");
                    System.out.println("Nombre: ");
                    String nombreCliente = input.nextLine();
                    System.out.println("Apellido: ");
                    String apellidoCliente = input.nextLine();
                    System.out.println("Telefono de contacto:");
                    String telefonoCliente = input.nextLine();
                    System.out.println("Email: ");
                    String emailCliente = input.nextLine();

                    Cliente cliente = new Cliente(nombreCliente, apellidoCliente, telefonoCliente, emailCliente);

                    if (hotel.agregarCliente(cliente)) {
                        System.out.println("✔ Cliente agregado con éxito.");
                    } else {
                        System.out.println("❌ No se pudo registrar. Intente nuevamente.");
                    }
                    break;
                case 3:
                    while (true) {

                        System.out.println("CONSULTANDO FECHAS DISPONIBLES PARA RESERVAR");
                        //solicitando fechas de llegada...
                        System.out.println("Fecha de llegada: DD/MM/YYYY\nIndica el dia: ");
                        String diaLlegada = input.nextLine();
                        System.out.println("Mes en formato 1-12");
                        String mesLlegada = input.nextLine();
                        System.out.println("Año en formato 4 dígitos");
                        String anioLlegada = input.nextLine();

//validaciones llegada
                        if (diaLlegada.isEmpty() || mesLlegada.isEmpty() || anioLlegada.isEmpty()) {
                            System.out.println("❌ Error: Ningún campo de la fecha de llegada puede estar vacío.");
                            continue;
                        }
                        if (diaLlegada.length() == 1) diaLlegada = "0" + diaLlegada;
                        if (mesLlegada.length() == 1) mesLlegada = "0" + mesLlegada;
                        String fechaLlegadaCompleta = diaLlegada + "/" + mesLlegada + "/" + anioLlegada;

                        LocalDate fechaLlegadaParsed;
                        try {
                            fechaLlegadaParsed = LocalDate.parse(fechaLlegadaCompleta, formatoFechas);
                        } catch (Exception e) {
                            System.out.println("❌ Formato de fecha inválido. Usa DD/MM/YYYY.");
                            continue;
                        }


                        //solicitando fechas de salida...
                        System.out.println("Fecha de salida: DD/MM/YYYY\nIndica el dia de salida: ");
                        String diaSalida = input.nextLine();
                        System.out.println("Mes en formato 1-12");
                        String mesSalida = input.nextLine();
                        System.out.println("Año en formato 4 dígitos");
                        String anioSalida = input.nextLine();
//validaciones salida
                        if (diaSalida.isEmpty() || mesSalida.isEmpty() || anioSalida.isEmpty()) {
                            System.out.println("Error: Ningún campo de la fecha de salida puede estar vacío.");
                            continue;
                        }

                        if (diaSalida.length() == 1) diaSalida = "0" + diaSalida;
                        if (mesSalida.length() == 1) mesSalida = "0" + mesSalida;
                        String fechaSalidaCompleta = diaSalida + "/" + mesSalida + "/" + anioSalida;

                        LocalDate fechaSalidaParsed;
                        try {
                            fechaSalidaParsed = LocalDate.parse(fechaSalidaCompleta, formatoFechas);
                        } catch (Exception e) {
                            System.out.println("Formato inválido para la fecha de salida.");
                            continue;
                        }
// llegada < salida
                        if (!fechaSalidaParsed.isAfter(fechaLlegadaParsed)) {
                            System.out.println("La fecha de salida debe ser posterior a la fecha de llegada.");
                            continue;
                        }
                        //corroborando si hay habitaciones disponibles...
                        List<Habitacion> opcionesDisponibles = hotel.obtenerHabitacionesDisponibles(fechaLlegadaParsed, fechaSalidaParsed);

                        if (opcionesDisponibles.isEmpty()) {
                            System.out.println("No hay habitaciones disponibles.");
                            System.out.println("¿Desea volver a consultar?");
                            System.out.println("1. Sí, volver a intentar.");
                            System.out.println("2. No, volver al menú principal.");
                            int op = input.nextInt();
                            input.nextLine();

                            if (op == 1) {
                                continue;
                            } else {
                                break;
                            }

                        }

                        //Habitaciones disponibles si es q hay
                        System.out.println("Habitaciones disponibles en esas fechas :");
                        for (Habitacion h : opcionesDisponibles) {
                            System.out.println(h.toString());
                        }
                        //menu 2...
                        System.out.println("\n¿Qué desea hacer");
                        System.out.println("1. Proceder con la reserva.");
                        System.out.println("2. Volver al menú principal.");

                        int opcionInnerReservas = input.nextInt();
                        input.nextLine();

                        if (opcionInnerReservas == 2) {
                            break;
                        }
                        if (opcionInnerReservas != 1) {
                            System.out.println("Opcion invalida, seleccione 1 o 2.");
                            continue;
                        }

                        //solo controlamos q ponga 1 o 2
                        //llamar al metodo de reserva
                        //tomar los datos del cliente para reservar
                        System.out.println(":::::::::Datos del cliente::::::::");
                        System.out.println("Nombre:");
                        String nombre = input.nextLine();
                        System.out.println("Apellido:");
                        String apellido = input.nextLine();
                        System.out.println("Telefono:");
                        String telefono = input.nextLine();
                        System.out.println("Email:");
                        String email = input.nextLine();
                        //validaciones del cliente de nuevo
                        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                            System.out.println("❌ Error: Todos los campos del cliente son obligatorios.");
                            continue; // vuelve a pedir los datos
                        }
                        if (!email.contains("@") || !email.contains(".")) {
                            System.out.println("❌ Email inválido.");
                            continue;
                        }


                        System.out.println(":::::::::::::Habitacion:::::::: ");
                        System.out.println("Disponibles en esas fechas:");
                        for (int i = 0; i < opcionesDisponibles.size(); i++) {
                            System.out.println((i + 1) + ". " + opcionesDisponibles.get(i).toString());
                        }
                        System.out.println("Seleccione una habitación (1 - " + opcionesDisponibles.size() + "):");
                        int seleccion = input.nextInt();
                        input.nextLine();

                        if (seleccion < 1 || seleccion > opcionesDisponibles.size()) {
                            System.out.println("Opción inválida. Volviendo al menú principal...");
                            break;
                        }
                        Habitacion habitacionElegida = opcionesDisponibles.get(seleccion - 1);
//validando si la habitacion esta disponible...
                        if (!hotel.habitacionDisponible(habitacionElegida, fechaLlegadaParsed, fechaSalidaParsed)) {
                            System.out.println("❌ La habitación ya fue reservada mientras estabas ingresando datos. Intenta de nuevo.");
                            continue;
                        }

                        Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email);
                        Reserva nuevaReserva = new Reserva(nuevoCliente, habitacionElegida, fechaLlegadaParsed, fechaSalidaParsed);
                        hotel.agregarReserva(nuevaReserva);
                        habitacionElegida.setDisponible(false);
                        System.out.println("Se ha creado la reserva... ");
                        System.out.printf(nuevaReserva.toString());
                        System.out.println("");
                        break;
                    }
                    break;
                case 4:
                    System.out.println("CONSULTANDO RESERVAS");
                    hotel.mostrarReservas();
                    break;
                case 5:
                    System.out.println(":::::MODIFICAR ESTADO DE LA HABITACION::::");

                    if (!hotel.mostrarHabitaciones()){
                        break;
                    }

                    System.out.println("\nIngrese número de la habitación:");
                    int numHab = input.nextInt();
                    input.nextLine();

                    Habitacion habitacionModificar = hotel.buscarHabitacionPorNumero(numHab);

                    if (habitacionModificar == null) {
                        System.out.println("Habitación no encontrada.");
                        break;
                    }
                    System.out.println("\nEstado actual: " + (habitacionModificar.isDisponible() ? "Disponible" : "Ocupada"));
                    System.out.println("¿Qué desea hacer?");
                    System.out.println("1. Marcar como OCUPADA");
                    System.out.println("2. Marcar como DISPONIBLE");
                    int opEstado = input.nextInt();
                    input.nextLine();

                    if (opEstado == 1) {
                        habitacionModificar.setDisponible(false);
                        System.out.println("Habitación marcada como **OCUPADA**.");
                    } else if (opEstado == 2) {
                        habitacionModificar.setDisponible(true);
                        System.out.println("Habitación marcada como **DISPONIBLE**.");
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del sistema ADMIN HOTEL");
                default:
                    System.out.println("Opcion no disponible.");


            }
        } while (opcion != 6);


/**no hacer reserva hasta corroborar que esten disponibles las habitaciones en esos dias
 si hay disponibilidad esos dias continuar con la recaudacion de datos para proceder con la reserva
 */


        //portal de reservaciones...


        //creando cliente, reserva, añadiendo a mis reservas y mis clientes...


    }


}



        /*creando habitaciones...
        Habitacion habitacion1 = new Habitacion("regular", 1, 599.9);
        Habitacion habitacion2 = new Habitacion("doble", 2, 899.9);
        Habitacion habitacion3 = new Habitacion("Suite", 3, 1399.9);
        Habitacion habitacion4 = new Habitacion("Presidencial", 4, 2099.9);
        //asignando habitaciones al hotel...
        elHotelitoBonito.agregarHabitacion(habitacion1);
        elHotelitoBonito.agregarHabitacion(habitacion2);
        elHotelitoBonito.agregarHabitacion(habitacion3);
        elHotelitoBonito.agregarHabitacion(habitacion4);
*/
