package proyectoFinal;

public class Empleado {
    private String nombre;
    private String apellido;
    private String idEmpleado;

    // Constructor
    public Empleado(String nombre, String apellido, String idEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idEmpleado = idEmpleado;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (ID: " + idEmpleado + ")";
    }
}