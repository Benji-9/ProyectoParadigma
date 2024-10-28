package proyectoFinal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private static int contadorReservas = 0; // Para generar un ID único
    private int idReserva;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private Empleado empleado; // Referencia al empleado que hizo la reserva
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor
    public Reserva(Vehiculo vehiculo, Cliente cliente, Empleado empleado, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idReserva = ++contadorReservas;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Método para confirmar la reserva
    public boolean confirmarReserva() {
        if (vehiculo.isDisponible()) {
            vehiculo.setDisponible(false); // Marcar el vehículo como no disponible
            return true;
        }
        return false; // No se puede confirmar si el vehículo no está disponible
    }

    // Método para calcular el tiempo restante del contrato
    public long calcularTiempoRestante() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    // Getters
    public int getIdReserva() {
        return idReserva;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getCostoTotal() {
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return dias * vehiculo.getPrecioPorDia(); // Costo total basado en la duración de la reserva
    }
}