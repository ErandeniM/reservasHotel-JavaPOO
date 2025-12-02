
🏨 SISTEMA DE RESERVAS DE HOTEL — JAVA (CONSOLA)

Este proyecto es una aplicación en Java ejecutada en consola que permite gestionar un sistema básico de reservas de hotel.

--------------------------------------------------------
📌 CARACTERISTICAS PRINCIPALES

✔ Registro de habitaciones (ID autogenerado)
✔ Registro de clientes
✔ Sistema de reservas con validaciones reales
✔ Control manual de disponibilidad de habitaciones
✔ Validaciones de fechas y datos obligatorios

--------------------------------------------------------
🧠 LOGICA DEL SISTEMA

HABITACIONES:
- Tipo, precio y número autogenerado.
- Estados: Disponible / Ocupada.
- No se muestran habitaciones ocupadas al solicitar reserva.

CLIENTES:
- Se registran con: nombre, apellido, teléfono y email.
- No permite datos vacíos ni emails duplicados.

RESERVAS:
- Validación de fechas formateadas correctamente.
- La fecha de salida debe ser posterior a la llegada.
- No se permite crear reservas sin habitación disponible.

--------------------------------------------------------
🧪 VALIDACIONES IMPORTANTES

❌ Campos vacíos no permitidos.
❌ Fechas incorrectas o mal formateadas.
❌ Reservas con noches igual a 0.
✔ Mensajes claros cuando no hay datos.

--------------------------------------------------------
📂 ESTRUCTURA DEL PROYECTO

src/
 └── com.erandeni.reservasHotel
        ├── Cliente.java
        ├── Habitacion.java
        ├── Reserva.java
        ├── Hotel.java
        └── Main.java

--------------------------------------------------------
▶ COMO EJECUTAR

1. Clonar o copiar el proyecto.
2. Compilar y ejecutar Main.java
3. Navegar por el menú:

1. Registrar habitación
2. Registrar cliente
3. Crear reserva
4. Consultar reservas
5. Modificar estado de habitación
6. Salir

--------------------------------------------------------
👨‍💻 AUTOR ERAN

Proyecto académico en Java orientado a objetos.
