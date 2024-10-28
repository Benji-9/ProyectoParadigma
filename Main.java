package proyectoFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear la agencia de alquiler de vehículos
        Agencia agencia = new Agencia("Agencia de Alquiler de Autos");

        // Crear y agregar vehículos a la agencia
        agencia.agregarVehiculo(new Vehiculo("AAA123", "Toyota Corolla", Vehiculo.TipoAuto.SEDAN, Vehiculo.Color.BLANCO, 50));
        agencia.agregarVehiculo(new Vehiculo("BBB456", "Honda CR-V", Vehiculo.TipoAuto.SUV, Vehiculo.Color.GRIS, 70));
        agencia.agregarVehiculo(new Vehiculo("CCC789", "Ford Ranger", Vehiculo.TipoAuto.CAMIONETA, Vehiculo.Color.NEGRO, 90));
        agencia.agregarVehiculo(new Vehiculo("DDD321", "Chevrolet Camaro", Vehiculo.TipoAuto.DEPORTIVO, Vehiculo.Color.AMARILLO, 120));
        agencia.agregarVehiculo(new Vehiculo("EEE654", "Hyundai Tucson", Vehiculo.TipoAuto.SUV, Vehiculo.Color.AZUL, 80));
        agencia.agregarVehiculo(new Vehiculo("EEE101", "Toyota Etios", Vehiculo.TipoAuto.HATCHBACK, Vehiculo.Color.BLANCO, 25));

        // Crear empleados
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Benjamin", "Martinez", "E001"));
        empleados.add(new Empleado("Juan", "Fernández", "E002"));

        // Crear un escáner para recibir la interacción del usuario
        Scanner scanner = new Scanner(System.in);

        // Menú de selección de empleado
        System.out.println("Seleccione el empleado que usará el sistema:");
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println((i + 1) + ". " + empleados.get(i));
        }
        System.out.print("Ingrese el número del empleado: ");
        int opcionEmpleado = scanner.nextInt();
        Empleado empleadoSeleccionado = empleados.get(opcionEmpleado - 1);
        System.out.println("\nEmpleado seleccionado: " + empleadoSeleccionado);

        // Lista de clientes
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Martin", "Garcia", "B2"));

        // Ejemplo de reserva previa
        Cliente clientePrevia = obtenerClientePrevia(clientes); // Método para obtener el cliente
        Empleado empleadoPrevia = obtenerEmpleadoPrevia(empleados); // Método para obtener el empleado
        Vehiculo vehiculoPrevia = obtenerVehiculoPrevia(agencia, "DDD321"); // Método para obtener el vehículo por patente

        // Fechas de la reserva
        LocalDate fechaInicioPrevio = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinPrevio = LocalDate.of(2024, 3, 1);

        // Realizar la reserva
        agencia.realizarReserva(clientePrevia, empleadoPrevia, vehiculoPrevia, fechaInicioPrevio, fechaFinPrevio);

        // Mostrar el menú principal
        mostrarMenu(scanner, agencia, clientes, empleadoSeleccionado);
    }

    // Método para mostrar el menú de opciones
    public static void mostrarMenu(Scanner scanner, Agencia agencia, List<Cliente> clientes, Empleado empleadoSeleccionado) {
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\nMenú de la Agencia de Alquiler");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("2. Ver autos contratados");
            System.out.println("3. Contratar auto");
            System.out.println("4. Agregar cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Ver vehículos disponibles
                    agencia.verVehiculosDisponibles();
                    break;

                case 2:
                    // Ver autos contratados
                    agencia.verAutosContratados();
                    break;

                case 3:
                    // Contratar un auto
                    contratarAuto(scanner, agencia, clientes, empleadoSeleccionado);
                    break;

                case 4:
                    // Agregar un nuevo cliente
                    agregarCliente(scanner, clientes, agencia);
                    break;

                case 5:
                    // Salir
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    // Método para contratar un auto
    public static void contratarAuto(Scanner scanner, Agencia agencia, List<Cliente> clientes, Empleado empleadoSeleccionado) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados. Primero registre un cliente.");
        } else {
            System.out.println("\nSeleccione un cliente para la reserva:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + ". " + clientes.get(i));
            }
            System.out.print("Ingrese el número del cliente: ");
            int opcionCliente = scanner.nextInt();
            Cliente clienteSeleccionado = clientes.get(opcionCliente - 1);

            System.out.println("\nSeleccione un vehículo para reservar (por número de patente): ");
            agencia.verVehiculosDisponibles();
            System.out.print("Ingrese la patente del vehículo que desea contratar: ");
            String patente = scanner.next();
            Vehiculo vehiculoSeleccionado = buscarVehiculoPorPatente(agencia, patente);

            if (vehiculoSeleccionado != null && vehiculoSeleccionado.isDisponible()) {
                try {
                    System.out.println("Ingrese la fecha de inicio de la reserva (AAAA-MM-DD): ");
                    String fechaInicioStr = scanner.next();
                    LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);

                    System.out.println("Ingrese la fecha de fin de la reserva (AAAA-MM-DD): ");
                    String fechaFinStr = scanner.next();
                    LocalDate fechaFin = LocalDate.parse(fechaFinStr);

                    if (fechaInicio.isAfter(fechaFin)) {
                        System.out.println("La fecha de inicio debe ser anterior a la fecha de fin.");
                    } else {
                        if (agencia.realizarReserva(clienteSeleccionado, empleadoSeleccionado, vehiculoSeleccionado, fechaInicio, fechaFin)) {
                            System.out.println("Reserva realizada con éxito.");
                        } else {
                            System.out.println("No se pudo realizar la reserva. El vehículo no está disponible.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error al ingresar la fecha. Asegúrese de seguir el formato correcto (AAAA-MM-DD).");
                }
            } else {
                System.out.println("El vehículo seleccionado no está disponible o no existe.");
            }
        }
    }

    // Método para agregar un nuevo cliente
    public static void agregarCliente(Scanner scanner, List<Cliente> clientes, Agencia agencia) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.next();
        System.out.print("Ingrese la licencia de conducir del cliente: ");
        String licenciaConducir = scanner.next();

        Cliente nuevoCliente = new Cliente(nombre, apellido, licenciaConducir);
        clientes.add(nuevoCliente);
        agencia.agregarCliente(nuevoCliente);
        System.out.println("Cliente agregado: " + nuevoCliente);
    }

    // Métodos auxiliares para obtener cliente, empleado y vehículo previamente
    public static Cliente obtenerClientePrevia(List<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            return clientes.getFirst();
        } else {
            System.out.println("No hay clientes disponibles.");
            return null;
        }
    }

    public static Empleado obtenerEmpleadoPrevia(List<Empleado> empleados) {
        if (!empleados.isEmpty()) {
            return empleados.getFirst();
        } else {
            System.out.println("No hay empleados disponibles.");
            return null;
        }
    }

    public static Vehiculo obtenerVehiculoPrevia(Agencia agencia, String patente) {
        Vehiculo vehiculo = buscarVehiculoPorPatente(agencia, patente);
        if (vehiculo != null) {
            return vehiculo;
        } else {
            System.out.println("Vehículo no encontrado o no disponible.");
            return null;
        }
    }

    public static Vehiculo buscarVehiculoPorPatente(Agencia agencia, String patente) {
        for (Vehiculo vehiculo : agencia.getVehiculos()) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                return vehiculo;
            }
        }
        return null;
    }
}