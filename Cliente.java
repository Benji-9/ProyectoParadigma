package proyectoFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private static int contadorClientes = 0; // Para generar un ID único
    private int idCliente;
    private String nombre;
    private String apellido;
    private Vehiculo vehiculoReservado;
    private String licenciaConducir;
    private List<Reserva> historialAlquiler; // Lista de reservas del cliente

    // Constructor
    public Cliente(String nombre, String apellido, String licenciaConducir) {
        this.idCliente = ++contadorClientes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.vehiculoReservado = null;
        this.licenciaConducir = licenciaConducir;
        this.historialAlquiler = new ArrayList<>();
    }

    // Método para realizar una reserva
    public boolean realizarReserva(Vehiculo vehiculo, Empleado empleado, LocalDate fechaInicio, LocalDate fechaFin) {
        Reserva nuevaReserva = new Reserva(vehiculo, this, empleado, fechaInicio, fechaFin);
        if (nuevaReserva.confirmarReserva()) {
            historialAlquiler.add(nuevaReserva); // Agregar la reserva al historial
            return true;
        } else {
            return false; // Si el vehículo no está disponible
        }
    }

    // Método para ver el historial de alquileres del cliente
    public void verHistorial() {
        if (historialAlquiler.isEmpty()) {
            System.out.println("No hay reservas en el historial.");
        } else {
            for (Reserva reserva : historialAlquiler) {
                System.out.println("Reserva ID: " + reserva.getIdReserva() +
                        ", Vehículo: " + reserva.getVehiculo().getModelo() +
                        ", Desde: " + reserva.getFechaInicio() +
                        ", Hasta: " + reserva.getFechaFin() +
                        ", Costo: " + reserva.getCostoTotal());
            }
        }
    }

    // Sobrescribir el método toString
    @Override
    public String toString() {
        return nombre + " " + apellido + " (ID: " + idCliente + ", Licencia: " + licenciaConducir + ")";
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public List<Reserva> getHistorialAlquiler() {
        return historialAlquiler;
    }

    public void setHistorialAlquiler(List<Reserva> historialAlquiler) {
        this.historialAlquiler = historialAlquiler;
    }

    public Vehiculo getVehiculoReservado() {
        return vehiculoReservado;
    }

    public void setVehiculoReservado(Vehiculo vehiculoReservado) {
        this.vehiculoReservado = vehiculoReservado;
    }
}