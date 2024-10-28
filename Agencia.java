package proyectoFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agencia {
    private String nombre;
    private List<Vehiculo> vehiculos;
    private List<Reserva> reservas;
    private List<Cliente> clientes;

    public Agencia(String nombre) {
        this.nombre = nombre;
        this.vehiculos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    // Agregar vehículo
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    // Agregar cliente
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Ver vehículos disponibles
    public void verVehiculosDisponibles() {
        System.out.println("Vehículos disponibles:");
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.isDisponible()) {
                System.out.println(vehiculo);
            }
        }
    }

    // Ver autos contratados
    public void verAutosContratados() {
        System.out.println("Autos contratados:");
        for (Reserva reserva : reservas) {
            System.out.printf("Vehículo: %s - Patente: %s - Cliente: %s - Días restantes: %d\n",
                    reserva.getVehiculo().getModelo(),
                    reserva.getVehiculo().getPatente(),
                    reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido(),
                    reserva.calcularTiempoRestante());
        }
    }

    // Realizar reserva
    public boolean realizarReserva(Cliente cliente, Empleado empleado, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        if (vehiculo.isDisponible()) {
            Reserva nuevaReserva = new Reserva(vehiculo, cliente, empleado, fechaInicio, fechaFin);
            reservas.add(nuevaReserva);
            cliente.realizarReserva(vehiculo, empleado,fechaInicio, fechaFin);
            System.out.println("Reserva realizada: Vehículo " + vehiculo.getModelo() + " para " + cliente.getNombre());
            return true;
        } else {
            System.out.println("El vehículo no está disponible.");
            return false;
        }
    }

    public void cancelarReserva(String criterio, boolean porCliente) {
        if (porCliente) {
            Cliente cliente = buscarClientePorNombre(criterio);
            if (cliente != null && cliente.getVehiculoReservado() != null) {
                Vehiculo vehiculo = cliente.getVehiculoReservado();
                vehiculo.setDisponible(true);
                cliente.setVehiculoReservado(null);
                System.out.println("Reserva cancelada para el cliente: " + cliente.getNombre());
            } else {
                System.out.println("Cliente no tiene ninguna reserva o no existe.");
            }
        } else {
            Vehiculo vehiculo = buscarVehiculoPorPatente(criterio);
            if (vehiculo != null && !vehiculo.isDisponible()) {
                Cliente cliente = buscarClientePorVehiculo(vehiculo);
                if (cliente != null) {
                    vehiculo.setDisponible(true);
                    cliente.setVehiculoReservado(null);
                    System.out.println("Reserva cancelada para el vehículo con patente: " + vehiculo.getPatente());
                } else {
                    System.out.println("No se encontró el cliente que contrató este vehículo.");
                }
            } else {
                System.out.println("El vehículo ya está disponible o no existe.");
            }
        }
    }

    private Cliente buscarClientePorNombre(String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    private Vehiculo buscarVehiculoPorPatente(String patente) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                return vehiculo;
            }
        }
        return null;
    }

    private Cliente buscarClientePorVehiculo(Vehiculo vehiculo) {
        for (Cliente cliente : clientes) {
            if (vehiculo.equals(cliente.getVehiculoReservado())) {
                return cliente;
            }
        }
        return null;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}