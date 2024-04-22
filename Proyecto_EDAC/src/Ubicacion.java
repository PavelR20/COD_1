import java.util.Objects;

public class Ubicacion {
    // Atributos
    private final String nombre; // Nombre de la ubicación

    // Constructor
    public Ubicacion(String nombre) {
        this.nombre = nombre;
        // Mapa de tiempos hacia otras ubicaciones
        // Se inicializa el mapa de tiempos
    }

    // Método para obtener el nombre de la ubicación
    public String getNombre() {
        return nombre;
    }

    // Método equals para comparar ubicaciones basándose en el nombre
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) obj;
        return Objects.equals(nombre, ubicacion.nombre);
    }

    // Método hashCode para generar un código hash basado en el nombre
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    // Método toString para obtener una representación en cadena de la ubicación
    @Override
    public String toString() {
        return nombre;
    }
}