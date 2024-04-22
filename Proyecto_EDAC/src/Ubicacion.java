import java.util.Objects;

public class Ubicacion {
    // Atributos
    private final String nombre; // Nombre de la ubicaci�n

    // Constructor
    public Ubicacion(String nombre) {
        this.nombre = nombre;
        // Mapa de tiempos hacia otras ubicaciones
        // Se inicializa el mapa de tiempos
    }

    // M�todo para obtener el nombre de la ubicaci�n
    public String getNombre() {
        return nombre;
    }

    // M�todo equals para comparar ubicaciones bas�ndose en el nombre
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) obj;
        return Objects.equals(nombre, ubicacion.nombre);
    }

    // M�todo hashCode para generar un c�digo hash basado en el nombre
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    // M�todo toString para obtener una representaci�n en cadena de la ubicaci�n
    @Override
    public String toString() {
        return nombre;
    }
}