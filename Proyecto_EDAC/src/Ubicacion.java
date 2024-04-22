import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ubicacion {
    // Atributos
    private final String nombre; // Nombre de la ubicaci�n
    private final Map<Ubicacion, Integer> tiempos; // Mapa de tiempos hacia otras ubicaciones

    // Constructor
    public Ubicacion(String nombre) {
        this.nombre = nombre;
        this.tiempos = new HashMap<>(); // Se inicializa el mapa de tiempos
    }

    // M�todo para obtener el nombre de la ubicaci�n
    public String getNombre() {
        return nombre;
    }

    // M�todo para agregar un tiempo asociado a una ubicaci�n de destino
    public void agregarTiempo(Ubicacion destino, int tiempo) {
        tiempos.put(destino, tiempo);
    }

    // M�todo para obtener el tiempo asociado a una ubicaci�n de destino
    public Integer getTiempo(Ubicacion destino) {
        return tiempos.get(destino);
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