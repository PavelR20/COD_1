import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ubicacion {
    // Atributos
    private final String nombre; // Nombre de la ubicación
    private final Map<Ubicacion, Integer> tiempos; // Mapa de tiempos hacia otras ubicaciones

    // Constructor
    public Ubicacion(String nombre) {
        this.nombre = nombre;
        this.tiempos = new HashMap<>(); // Se inicializa el mapa de tiempos
    }

    // Método para obtener el nombre de la ubicación
    public String getNombre() {
        return nombre;
    }

    // Método para agregar un tiempo asociado a una ubicación de destino
    public void agregarTiempo(Ubicacion destino, int tiempo) {
        tiempos.put(destino, tiempo);
    }

    // Método para obtener el tiempo asociado a una ubicación de destino
    public Integer getTiempo(Ubicacion destino) {
        return tiempos.get(destino);
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