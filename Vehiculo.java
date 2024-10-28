package proyectoFinal;

public class Vehiculo {

    // Enumerado para los tipos de auto
    public enum TipoAuto {
        SEDAN, SUV, CAMIONETA, MINIVAN, COUPE, DEPORTIVO, HATCHBACK
    }

    // Enumerado para los colores
    public enum Color {
        NEGRO, BLANCO, ROJO, AZUL, GRIS, AMARILLO
    }

    private String patente;
    private String modelo;
    private TipoAuto tipoAuto;  // Usamos el enum TipoAuto
    private Color color;        // Usamos el enum Color
    private double precioPorDia;
    private boolean disponible;

    // Constructor
    public Vehiculo(String patente, String modelo, TipoAuto tipoAuto, Color color, double precioPorDia) {
        this.patente = patente;
        this.modelo = modelo;
        this.tipoAuto = tipoAuto;
        this.color = color;
        this.precioPorDia = precioPorDia;
        this.disponible = true; // Por defecto disponible al crear
    }

    // Métodos
    public boolean alquilar() {
        if (disponible) {
            disponible = false;
            return true; // Alquiler exitoso
        } else {
            return false; // El vehículo ya está alquilado
        }
    }

    public void devolver() {
        disponible = true; // El vehículo vuelve a estar disponible
    }

    // Getters y Setters
    public String getPatente() {
        return patente;
    }

    public String getModelo() {
        return modelo;
    }

    public TipoAuto getTipoAuto() {
        return tipoAuto;
    }

    public Color getColor() {
        return color;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método toString para mostrar información del vehículo
    @Override
    public String toString() {
        return String.format("Modelo: %s - Patente: %s - Tipo: %s - Color: %s - Precio por día: $%.2f",
                modelo, patente, tipoAuto.name(), color.name(), precioPorDia);
    }
}